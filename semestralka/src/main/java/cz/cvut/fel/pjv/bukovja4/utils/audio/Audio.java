package cz.cvut.fel.pjv.bukovja4.utils.audio;

import javax.sound.sampled.*;

import java.io.IOException;

import cz.cvut.fel.pjv.bukovja4.Main;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

/**
 * Utility class for playing audio files.
 * Audio files are stored in the "/audio/" resource folder.
 * Supported formats include WAV and other formats supported by the Java Sound
 * API.
 */
public final class Audio {
    /**
     * Plays an audio file once.
     * 
     * @param fileName Name of the audio file in the "/audio/" resource folder
     * @return A Playback object to control the audio playback
     * @see #play(String, boolean)
     */
    public static Playback play(String fileName) {
        return play(fileName, false);
    }

    /**
     * Plays an audio file, with option to loop continuously.
     * 
     * @param fileName Name of the audio file in the "/audio/" resource folder
     * @param loop     If true, the audio will loop continuously until stopped
     * @return A Playback object to control the audio playback
     */
    public static Playback play(String fileName, boolean loop) {
        Playback playback = new Playback(new Thread() {
            public void run() {
                LOG.debug("Playing audio file: " + fileName);
                AudioListener listener = new AudioListener();
                try (AudioInputStream audioInputStream = AudioSystem
                        .getAudioInputStream(Main.class.getResourceAsStream("/audio/" + fileName))) {
                    try (Clip clip = AudioSystem.getClip()) {
                        clip.addLineListener(listener);
                        clip.open(audioInputStream);
                        if (loop) {
                            clip.loop(Clip.LOOP_CONTINUOUSLY);
                        } else {
                            clip.loop(0);
                        }
                        clip.start();
                        try {
                            listener.waitUntilDone();
                        } catch (InterruptedException e) {
                            // TODO: check if this is the right way to handle this
                            // LOG.error("Audio playback interrupted: " + fileName, e, true);
                        }
                    }
                } catch (UnsupportedAudioFileException e) {
                    LOG.error("Unsupported audio file: " + fileName, e, true);
                } catch (LineUnavailableException e) {
                    LOG.error("Line unavailable for audio file: " + fileName, e, true);
                } catch (IOException e) {
                    LOG.error("I/O error while playing audio file: " + fileName, e, true);
                } catch (Throwable e) {
                    LOG.error("Error while playing audio file: " + fileName, e, true);
                }
            }
        });
        playback.playback.setName("AudioThread");
        playback.playback.start();
        return playback;
    }
}
