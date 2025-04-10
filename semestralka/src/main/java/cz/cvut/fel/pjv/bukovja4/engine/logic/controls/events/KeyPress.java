package cz.cvut.fel.pjv.bukovja4.engine.logic.controls.events;

import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;

import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.BaseEvent;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

import static cz.cvut.fel.pjv.bukovja4.utils.Utils.getCharacter;

public class KeyPress extends BaseEvent implements GLFWKeyCallbackI {
    public KeyPress(long windowHandle) {
        this.windowHandle = windowHandle;
    }

    @Override
    public void register() {
        LOG.warn("Registering event: " + this.getClass().getSimpleName());
        glfwSetKeyCallback(windowHandle, this);
    }

    @Override
    public void unRegister() {
        glfwSetScrollCallback(windowHandle, null);
    }

    @Override
    public void invoke(long window, int key, int scanCode, int action, int modifiers) {
        LOG.info("Key: " + key + ", Action: " + action);

        if (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT) {
            Character character = getCharacter(key, modifiers);

            if (character != null) {
                System.out.println("Pressed key: " + character);
            } else {
                System.out.println("Pressed non-printable key: " + key);
            }
        }

        if (this.callback != null) {
            try {
                this.callback.apply(new Object[]{key, scanCode, action, modifiers});
            } catch (Throwable e) {
                try {
                    LOG.error("Error in scroll callback", e);
                } catch (Throwable ignore) {
                }
            }
        }
    }
}