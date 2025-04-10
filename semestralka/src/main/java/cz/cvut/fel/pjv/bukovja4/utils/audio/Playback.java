package cz.cvut.fel.pjv.bukovja4.utils.audio;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

public class Playback {
    Thread playback;

    Playback(Thread playback) {
        this.playback = playback;
    }

    public void stop() {
        if (this.playback != null) {
            this.playback.interrupt();
        } else {
            LOG.warn("Playback is null");
        }
    }
}