package cz.cvut.fel.pjv.bukovja4.utils.clocks;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

public class Clock extends Thread {
    private final double TICK_TIME;
    private long last;
    private double deltaTick;

    private final Object tickLock = new Object();
    private volatile boolean tickOccurred = false;

    public Clock(int tps) {
        TICK_TIME = 1_000_000_000.0d / tps;
        this.last = System.nanoTime();
    }

    @Override
    public void run() {
        Thread.currentThread().setName("ClockThread");
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
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void signalTick() {
        synchronized (tickLock) {
            tickOccurred = true;
            tickLock.notifyAll();
        }
    }

    long lastAwait = System.nanoTime();

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
