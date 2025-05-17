package cz.cvut.fel.pjv.bukovja4.engine.actions;

import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

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
        GameState.getScene().Unload();
        GameState.setScene(SceneFactory.create(SceneTypes.MENU, "pause/pause_menu.yml"));
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
    public static void open_settings() {
        LOG.warn("open_settings() is not implemented yet");
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
        LOG.warn("resume_game() is not implemented yet");
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
}
