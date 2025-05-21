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
        if (args.length > 0) {
            switch (args[0]) {
                case "-h":
                case "--help":
                case "help":
                    System.out.println("Usage: java -jar game.jar [debug|info|warn|error]");
                    System.out.println("  debug(--debug): Set log level to DEBUG(default)");
                    System.out.println("  info(--info): Set log level to INFO");
                    System.out.println("  warn(--warn): Set log level to WARN");
                    System.out.println("  error(--error): Set log level to ERROR");
                    return;
                case "--debug":
                case "debug":
                    LOG.setLevel(LOG.LogLevel.DEBUG);
                    break;
                case "--info":
                case "info":
                    LOG.setLevel(LOG.LogLevel.INFO);
                    break;
                case "--warn":
                case "warn":
                    LOG.setLevel(LOG.LogLevel.WARN);
                    break;
                case "--error":
                case "error":
                    LOG.setLevel(LOG.LogLevel.ERROR);
                    break;
                default:
                    LOG.setLevel(LOG.LogLevel.DEBUG);
            }
        }

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
            game.setInitScene(SceneTypes.MENU, "main/main_menu.yml");
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
