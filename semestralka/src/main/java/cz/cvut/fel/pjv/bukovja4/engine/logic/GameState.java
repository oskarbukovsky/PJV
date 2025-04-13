package cz.cvut.fel.pjv.bukovja4.engine.logic;

import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.Controls;
import cz.cvut.fel.pjv.bukovja4.engine.scenes.BaseScene;

/**
 * Manages the current state of the game.
 * Tracks input states, window handle, and the currently loaded scene.
 */
public final class GameState {
    /** Flag indicating if mouse button is currently pressed */
    public boolean isMousePressed = false;
    /** Current mouse X coordinate */
    public int mouseX = 0;
    /** Current mouse Y coordinate */
    public int mouseY = 0;
    /** Change in mouse X position since last update */
    public int mouseXDelta = 0;
    /** Change in mouse Y position since last update */
    public int mouseYDelta = 0;

    /** Handle to the window this game state is associated with */
    public static long windowHandle;
    /** Input controls manager */
    public static Controls controls;

    /** Flag indicating if any keyboard key is currently pressed */
    public boolean isKeyPressed = false;
    /** Keycode of the currently pressed key */
    public int keyPressed = 0;

    /** Currently loaded and active game scene */
    private BaseScene loadedScene = null;

    /**
     * Sets the current active scene.
     * Unloads the previous scene if one exists.
     * 
     * @param scene The new scene to activate
     */
    public void setScene(BaseScene scene) {
        if (loadedScene != null) {
            this.loadedScene.Unload();
        }
        this.loadedScene = scene;
    }

    /**
     * Gets the currently active scene
     * 
     * @return The currently loaded scene, or null if no scene is loaded
     */
    public BaseScene getScene() {
        return this.loadedScene;
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
