package cz.cvut.fel.pjv.bukovja4.utils.audio;

import javax.sound.sampled.*;

import java.io.IOException;

import cz.cvut.fel.pjv.bukovja4.Main;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

public final class Audio {
    public static Playback play(String fileName) {
        Playback playback = new Playback(new Thread() {
            public void run() {
                LOG.debug("Playing audio file: " + fileName);
                AudioListener listener = new AudioListener();
                try {
                    try (AudioInputStream audioInputStream = AudioSystem
                            .getAudioInputStream(Main.class.getResourceAsStream("/audio/" + fileName))) {
                        try (Clip clip = AudioSystem.getClip()) {
                            clip.addLineListener(listener);
                            clip.open(audioInputStream);
                            clip.start();
                            listener.waitUntilDone();
                        }
                    } catch (UnsupportedAudioFileException e) {
                        LOG.error("Unsupported audio file: " + fileName, e);
                    } catch (LineUnavailableException e) {
                        LOG.error("Line unavailable for audio file: " + fileName, e);
                    } catch (IOException e) {
                        LOG.error("I/O error while playing audio file: " + fileName, e);
                    } catch (final InterruptedException ignored) {
                    } catch (Throwable e) {
                        LOG.error("Error while playing audio file: " + fileName, e);
                    }
                } catch (final Throwable ignored) {
                }
            }
        });
        playback.playback.setName("AudioPlaybackThread");
        playback.playback.start();
        return playback;
    }
}
