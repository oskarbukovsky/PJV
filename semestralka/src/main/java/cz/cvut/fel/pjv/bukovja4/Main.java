package cz.cvut.fel.pjv.bukovja4;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

import cz.cvut.fel.pjv.bukovja4.utils.Utils;
// import cz.cvut.fel.pjv.bukovja4.utils.clocks.Clock;
import cz.cvut.fel.pjv.bukovja4.utils.config.*;

public final class Main {
    public static void main(String[] args) {
        try {
            Thread.currentThread().setName("MainThread");

            LOG.info("Starting game...");
            LOG.debug("Game DIR: " + System.getProperty("user.dir"));

            Config config = new Config();
            config.Load();

            // Clock clock = new Clock(Const.DESIRED_TPS);
            // clock.start();

            GameLoop game = new GameLoop(config);
            game.start();
            try {
                game.join();
            } catch (InterruptedException e) {
                LOG.error("Game interrupted", e);
            }
        } catch (Throwable e) {
        } finally {
            Utils.CloseGame();
        }
    }
}