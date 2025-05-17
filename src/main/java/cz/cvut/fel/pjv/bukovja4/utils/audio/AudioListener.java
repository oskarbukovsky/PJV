package cz.cvut.fel.pjv.bukovja4.utils.audio;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;

/**
 * Monitors audio line events to detect when playback has completed.
 * Used internally by {@link Audio} to manage audio playback lifecycle.
 */
public class AudioListener implements LineListener {
    /** Flag indicating if playback has completed */
    private boolean done = false;

    /**
     * Handles audio line events such as OPEN, CLOSE, START, and STOP.
     * Sets the done flag to true when playback has completed (STOP event).
     * 
     * @param event The line event that occurred
     */
    @Override
    public synchronized void update(LineEvent event) {
        Type eventType = event.getType();
        if (eventType == Type.STOP || eventType == Type.CLOSE) {
            done = true;
            notifyAll();
        }
    }

    /**
     * Waits until the audio playback is complete.
     * This method blocks until the STOP event is received.
     * 
     * @throws InterruptedException if the waiting thread is interrupted
     */
    public synchronized void waitUntilDone() throws InterruptedException {
        while (!done) {
            wait();
        }
    }
}