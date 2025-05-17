package cz.cvut.fel.pjv.bukovja4.utils.audio;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

/**
 * Controls the playback of an audio file.
 * Used in conjunction with {@link Audio} to manage playing sound files.
 */
public class Playback {
    /** The thread handling the playback */
    Thread playback;

    /**
     * Creates a new playback controller for the given playback thread.
     * 
     * @param playback The thread that handles audio playback
     */
    public Playback(Thread playback) {
        this.playback = playback;
    }

    /**
     * Stops the audio playback by interrupting the playback thread.
     */
    public void stop() {
        if (this.playback != null) {
            this.playback.interrupt();
        } else {
            LOG.warn("Playback is null");
        }
    }
}