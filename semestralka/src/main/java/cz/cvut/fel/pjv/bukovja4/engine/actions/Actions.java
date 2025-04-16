package cz.cvut.fel.pjv.bukovja4.engine.actions;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

/**
 * Provides action methods that can be called from YAML configuration files.
 * This class contains static methods that represent various game actions and
 * operations.
 */
public class Actions {
    /**
     * Starts a new game session.
     * Called when the player initiates a new game.
     */
    public static void start_game() {
        LOG.warn("start_game() is not implemented yet");
    }

    /**
     * Exits the game application.
     * Called when the player chooses to quit the game.
     */
    public static void exit_game() {
        LOG.warn("exit_game() is not implemented yet");
    }

    /**
     * Opens the game settings menu.
     * Called when the player wants to view or modify game settings.
     */
    public static void open_settings() {
        LOG.warn("open_settings() is not implemented yet");
    }
}
