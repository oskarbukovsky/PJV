package cz.cvut.fel.pjv.bukovja4.engine.logic;

import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.Controls;
import cz.cvut.fel.pjv.bukovja4.engine.scenes.BaseScene;
import cz.cvut.fel.pjv.bukovja4.utils.maze.CellTypes;

/**
 * Manages the current state of the game.
 * Tracks input states, window handle, and the currently loaded scene.
 */
public final class GameState {
    /** Flag indicating if mouse button is currently pressed */
    public boolean isMousePressed = false;
    /** Current mouse X coordinate */
    public static int mouseX = 0;
    /** Current mouse Y coordinate */
    public static int mouseY = 0;
    /** Change in mouse X position since last update */
    public static int mouseXDelta = 0;
    /** Change in mouse Y position since last update */
    public static int mouseYDelta = 0;

    /** Handle to the window this game state is associated with */
    public static long windowHandle;
    /** Input controls manager */
    public static Controls controls;

    /** Flag indicating if any keyboard key is currently pressed */
    public static boolean isKeyPressed = false;
    /** Keycode of the currently pressed key */
    public static int keyPressed = 0;

    /** Currently loaded and active game scene */
    private static BaseScene loadedScene = null;

    public static CellTypes[][] maze = null;

    /**
     * Sets the current active scene.
     * Unloads the previous scene if one exists.
     * 
     * @param scene The new scene to activate
     */
    public static void setScene(BaseScene scene) {
        loadedScene = scene;
    }

    /**
     * Gets the currently active scene
     * 
     * @return The currently loaded scene, or null if no scene is loaded
     */
    public static BaseScene getScene() {
        return loadedScene;
    }

    /**
     * Creates a new game state for the specified window
     * 
     * @param windowHandle The GLFW window handle this state is associated with
     */
    public GameState(long windowHandle) {
        GameState.windowHandle = windowHandle;
        GameState.controls = new Controls(windowHandle);
    }
}
