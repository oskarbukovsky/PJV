package cz.cvut.fel.pjv.bukovja4.engine.logic;


import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.Controls;
import cz.cvut.fel.pjv.bukovja4.engine.scenes.BaseScene;

public final class GameState {
    public static boolean isMousePressed = false;
    public static int mouseX = 0;
    public static int mouseY = 0;
    public static int mouseXDelta = 0;
    public static int mouseYDelta = 0;

    public static long windowHandle;
    private static Controls controls;

    public static Controls getControls() {
        return GameState.controls;
    }

    public static boolean isKeyPressed = false;
    public static int keyPressed = 0;

    private static BaseScene loadedScene = null;


    public void setScene(BaseScene scene) {
        if (loadedScene != null) {
            GameState.loadedScene.Unload();
        }
        GameState.loadedScene = scene;
    }

    public BaseScene getScene() {
        return GameState.loadedScene;
    }

    public GameState(long windowHandle) {
        GameState.windowHandle = windowHandle;
        GameState.controls = new Controls(windowHandle);
    }
}
