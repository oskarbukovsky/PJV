package cz.cvut.fel.pjv.bukovja4;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
import cz.cvut.fel.pjv.bukovja4.utils.Utils;
import cz.cvut.fel.pjv.bukovja4.utils.config.*;

public final class Main {
    public static void main(String[] args) {
        try {
            Thread.currentThread().setName("MainThread");
            // LOG.setLevel(LOG.LogLevel.ERROR);

            LOG.info("Starting game...");
            LOG.debug("Game DIR: " + System.getProperty("user.dir"));

            // TestingThings.TestElements();
            // TestingThings.TestAudio();
            // TestingThings.Test();

            Config config = new Config();
            config.Load();

            GameLoop game = new GameLoop(config);
            game.start();

            try {
                game.join();
            } catch (InterruptedException e) {
                LOG.error("Game interrupted", e);
            }
        } catch (Throwable e) {
            // e.printStackTrace();
        } finally {
            Utils.CloseGame();
            LOG.info("Game exited");
        }
    }
}