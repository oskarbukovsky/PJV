package cz.cvut.fel.pjv.bukovja4.engine.logic;

import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.Controls;
import cz.cvut.fel.pjv.bukovja4.engine.scenes.BaseScene;

public final class GameState {
    public boolean isMousePressed = false;
    public int mouseX = 0;
    public int mouseY = 0;
    public int mouseXDelta = 0;
    public int mouseYDelta = 0;

    public static long windowHandle;
    public static Controls controls;

    public boolean isKeyPressed = false;
    public int keyPressed = 0;

    private BaseScene loadedScene = null;

    public void setScene(BaseScene scene) {
        if (loadedScene != null) {
            this.loadedScene.Unload();
        }
        this.loadedScene = scene;
    }

    public BaseScene getScene() {
        return this.loadedScene;
    }

    public GameState(long windowHandle) {
        GameState.windowHandle = windowHandle;
        GameState.controls = new Controls(windowHandle);
    }
}
