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

    private GLFWMouseButtonCallbackI mouseControls;
    private GLFWScrollCallbackI scrollControls;
    private GLFWKeyCallbackI keyControls;

    public Player(Pos<D> position, Vector<D> viewAngle, float fov) {
        this.position = position;
        this.viewAngle = viewAngle;
        this.fov = fov;
    }

    public GLFWMouseButtonCallbackI registerMouseControls(long handle) {
        this.mouseControls = glfwSetMouseButtonCallback(handle, new MouseControls());
        return this.mouseControls;
    }

    public GLFWScrollCallbackI registerScrollControls(long handle) {
        this.scrollControls = glfwSetScrollCallback(handle, new ScrollControls());
        return this.scrollControls;
    }

    public GLFWKeyCallbackI registerKeyControls(long handle) {
        this.keyControls = glfwSetKeyCallback(handle, new KeyControls());
        return this.keyControls;
    }
}
