package cz.cvut.fel.pjv.bukovja4.engine.actions;

import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

import cz.cvut.fel.pjv.bukovja4.GameLoop;
import cz.cvut.fel.pjv.bukovja4.engine.logic.GameState;
import cz.cvut.fel.pjv.bukovja4.engine.scenes.SceneFactory;
import cz.cvut.fel.pjv.bukovja4.engine.scenes.SceneTypes;
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
        LOG.warn("start_game()");
        // GameState.getScene().Unload();
        // GameState.setScene(SceneFactory.create(SceneTypes.MENU, "pause/pause_menu.yml"));
        resume_game();
    }

    /**
     * Exits the game application.
     * Called when the player chooses to quit the game.
     */
    public static void exit_game() {
        LOG.warn("exit_game()");
        glfwSetWindowShouldClose(GameState.windowHandle, true);
    }

    /**
     * Opens the game settings menu.
     * Called when the player wants to view or modify game settings.
     */
    public static void open_help() {
        LOG.warn("open_help()");
        GameState.getScene().Unload();
        GameState.setScene(SceneFactory.create(SceneTypes.MENU, "help/help_menu.yml"));
    }

    /**
     * Pauses the game.
     * Called when the player wants to pause the game.
     */
    public static void pause_game() {
        LOG.warn("pause_game()");
        GameState.getScene().Unload();
        GameState.setScene(SceneFactory.create(SceneTypes.MENU, "pause/pause_menu.yml"));
    }

    /**
     * Resumes the game from a paused state.
     * Called when the player wants to continue playing after pausing.
     */
    public static void resume_game() {
        LOG.warn("resume_game()");
        GameState.getScene().Unload();

        if (GameState.next_level.contains("levels/") || GameLoop.config.getConfig().gameState.level.contains("1/")) {
            // GameState.setScene(SceneFactory.create(SceneTypes.GAME2D,
            // GameState.next_level.replace("levels/", "")));
            GameState.setScene(SceneFactory.create(SceneTypes.GAME2D,
                    GameLoop.config.getConfig().gameState.level + ".yml"));
        } else {
            // GameState.setScene(SceneFactory.create(SceneTypes.MENU, GameState.next_level));
            GameState.setScene(SceneFactory.create(SceneTypes.MENU,
                    GameLoop.config.getConfig().gameState.level + ".yml"));
        }
    }

    /**
     * Returns to the main menu of the game.
     * Called when the player wants to go back to the main menu.
     */
    public static void to_main_menu() {
        LOG.warn("to_main_menu()");
        GameState.getScene().Unload();
        GameState.setScene(SceneFactory.create(SceneTypes.MENU, "main/main_menu.yml"));
    }

    /**
     * Proceeds to the next level in the game.
     * Called when the player completes the current level.
     *
     * @param scene The name of the next level scene file
     */
    public static void next_level() {
        LOG.warn("next_level()");
        try {
            GameLoop.config.getConfig().gameState.level = GameState.next_level.replace("levels/", "").replace(".yml",
                    "");
            GameLoop.config.Update(GameLoop.getConfig());
        } catch (Throwable e) {
            LOG.error("Failed to save game progress", e, true);
        }
        GameState.getScene().Unload();
        if (GameState.next_level.contains("levels/")) {
            GameState.setScene(SceneFactory.create(SceneTypes.GAME2D, GameState.next_level.replace("levels/", "")));
        } else {
            GameState.setScene(SceneFactory.create(SceneTypes.MENU, GameState.next_level));
        }
    }

    /**
     * Restarts the current game session.
     * Called when the player wants to start the game over.
     */
    public static void restart_game() {
        LOG.warn("restart_game()");
        try {
            GameLoop.config.getConfig().gameState.level = "1/1_0";
            GameLoop.config.Update(GameLoop.getConfig());
        } catch (Throwable e) {
            LOG.error("Failed to restart game", e, true);
        }
        GameState.getScene().Unload();
        GameState.setScene(SceneFactory.create(SceneTypes.MENU, "main/main_menu.yml"));
    }
}
