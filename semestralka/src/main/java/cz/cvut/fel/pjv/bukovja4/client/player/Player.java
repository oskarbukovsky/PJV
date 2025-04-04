package cz.cvut.fel.pjv.bukovja4.client.player;

import cz.cvut.fel.pjv.bukovja4.client.player.controls.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.Dim;

import org.lwjgl.glfw.*;

import static org.lwjgl.glfw.GLFW.*;

public class Player<D extends Dim> {
    public Pos<D> position;
    public Vector<D> viewAngle;
    public float fov;

    private GLFWKeyCallbackI keyControls;
    private GLFWMouseButtonCallbackI mouseControls;
    private GLFWScrollCallbackI scrollControls;
    private GLFWCursorPosCallbackI cursorControls;

    public Player(Pos<D> position, Vector<D> viewAngle, float fov) {
        this.position = position;
        this.viewAngle = viewAngle;
        this.fov = fov;
    }

    public GLFWKeyCallbackI registerKeyControls(long handle) {
        this.keyControls = glfwSetKeyCallback(handle, new KeyControls());
        return this.keyControls;
    }

    public GLFWMouseButtonCallbackI registerMouseControls(long handle) {
        this.mouseControls = glfwSetMouseButtonCallback(handle, new MouseControls());
        return this.mouseControls;
    }

    public GLFWScrollCallbackI registerScrollControls(long handle) {
        this.scrollControls = glfwSetScrollCallback(handle, new ScrollControls());
        return this.scrollControls;
    }

    public GLFWCursorPosCallbackI registerMouseMoveControls(long handle) {
        this.cursorControls = glfwSetCursorPosCallback(handle, new MouseMoveControls());
        return this.cursorControls;
    }

    public void unregisterMouseControls() {
        
    }

    public void freeCallbacks(long windowHandle) {
        glfwSetKeyCallback(windowHandle, null);
        glfwSetMouseButtonCallback(windowHandle, null);
        glfwSetScrollCallback(windowHandle, null);
        glfwSetCursorPosCallback(windowHandle, null);
        
        keyControls = null;
        mouseControls = null;
        scrollControls = null;
        cursorControls = null;
    }
}
