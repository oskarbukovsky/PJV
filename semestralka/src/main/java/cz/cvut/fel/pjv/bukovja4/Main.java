package cz.cvut.fel.pjv.bukovja4;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

import cz.cvut.fel.pjv.bukovja4.utils.Utils;
import cz.cvut.fel.pjv.bukovja4.engine.scenes.*;
import cz.cvut.fel.pjv.bukovja4.utils.Exceptions.*;
import cz.cvut.fel.pjv.bukovja4.utils.config.*;

/**
 * Main entry point for the game application.
 * Handles initialization, configuration loading, and game loop startup.
 */
public final class Main {
    /**
     * Application entry point that initializes and starts the game.
     * Sets up the main thread, loads configuration, starts game loop,
     * and ensures proper cleanup on exit.
     * 
     * @param args Command line arguments
     */
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
            game.setInitScene(SceneFactory.create(SceneTypes.MENU, "main/main_menu.yml"));
            game.start();

        } catch (ConfigException e) {
            LOG.error(e, true);
        } catch (SceneException e) {
            LOG.error(e, true);
        } catch (GameException e) {
            LOG.error(e, true);
        } catch (Throwable e) {
            LOG.error("Unexpected error", e, true);
        } finally {
            Utils.CloseGame();
            LOG.info("Game exited");
        }
    }
}
