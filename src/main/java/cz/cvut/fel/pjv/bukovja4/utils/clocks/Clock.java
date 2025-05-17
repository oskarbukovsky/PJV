package cz.cvut.fel.pjv.bukovja4.utils.clocks;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

/**
 * Game clock that ensures consistent timing for game updates.
 * Operates at a specified rate measured in ticks per second (TPS).
 * Provides thread-safe timing mechanisms for game loop synchronization.
 */
public final class Clock extends Thread {
    /** Time duration of a single tick in nanoseconds */
    private final double TICK_TIME;
    /** Timestamp of the last tick check in nanoseconds */
    private long last;
    /** Accumulator for partial ticks */
    private double deltaTick;

    /** Lock object for thread synchronization */
    private final Object tickLock = new Object();
    /** Flag indicating whether a tick has occurred */
    private volatile boolean tickOccurred = false;
    /** Name assigned to this clock's thread */
    private final String threadName;

    /**
     * Creates a new clock with the specified tick rate.
     * 
     * @param tps        Ticks per second (frequency of game updates)
     * @param threadName Name for the clock thread (for debugging)
     */
    public Clock(int tps, String threadName) {
        TICK_TIME = 1_000_000_000.0d / tps;
        this.last = System.nanoTime();
        this.threadName = threadName;
    }

    /**
     * Main clock loop that runs in its own thread.
     * Generates tick events at the specified frequency.
     * Continues until the thread is interrupted.
     */
    @Override
    public void run() {
        Thread.currentThread().setName(this.threadName);
        LOG.debug("Clock thread started");
        while (!Thread.currentThread().isInterrupted()) {
            long now = System.nanoTime();
            deltaTick += (now - last) / TICK_TIME;
            last = now;

            if (deltaTick >= 1) {
                deltaTick--;
                signalTick();
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * Signals that a tick has occurred.
     * Notifies all threads waiting for a tick.
     */
    private void signalTick() {
        synchronized (tickLock) {
            tickOccurred = true;
            tickLock.notifyAll();
        }
    }

    /** Timestamp of the last await call in nanoseconds */
    long lastAwait = System.nanoTime();

    /**
     * Blocks the calling thread until the next tick occurs.
     * Detects and logs information about skipped ticks if frames are dropping.
     */
    public void awaitTick() {
        long currentAwait = System.nanoTime();
        synchronized (tickLock) {
            while (!tickOccurred) {
                try {
                    tickLock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            tickOccurred = false;
        }
        int skippedTicks = (int) ((currentAwait - lastAwait - 1) / TICK_TIME);
        if (skippedTicks > 1) {
            if (skippedTicks <= 20) {
                LOG.debug("Skipped " + (skippedTicks - 1) + " ticks");
            } else if (skippedTicks <= 100) {
                LOG.info("Skipped " + (skippedTicks - 1) + " ticks");
            } else {
                LOG.warn("Skipped " + (skippedTicks - 1) + " ticks");
            }
        }
        lastAwait = currentAwait;
    }
}
