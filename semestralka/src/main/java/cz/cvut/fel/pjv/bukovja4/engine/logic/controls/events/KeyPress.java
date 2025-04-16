package cz.cvut.fel.pjv.bukovja4.engine.logic.controls.events;

import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;

import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.BaseEvent;
import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.ControlTypes;
import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.Selector;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

import static cz.cvut.fel.pjv.bukovja4.utils.Utils.getCharacter;

/**
 * Handles keyboard key press events.
 * Extends {@link BaseEvent} to provide keyboard-specific functionality.
 */
public class KeyPress extends BaseEvent implements GLFWKeyCallbackI {
    /**
     * Creates a keyboard event handler for the specified window
     * 
     * @param windowHandle The GLFW window handle to capture keyboard events from
     */
    public KeyPress(long windowHandle) {
        this.windowHandle = windowHandle;
    }

    /**
     * Registers this keyboard handler with the window
     * {@inheritDoc}
     */
    @Override
    public void register() {
        glfwSetKeyCallback(windowHandle, this);
    }

    /**
     * Unregisters this keyboard handler from the window
     * {@inheritDoc}
     */
    @Override
    public void unRegister() {
        glfwSetScrollCallback(windowHandle, null);
    }

    /**
     * Handles keyboard events and translates key codes to characters
     * 
     * @param window    Window that received the event
     * @param key       GLFW key code
     * @param scanCode  Platform-specific scan code
     * @param action    Key action (press, release, repeat)
     * @param modifiers Bit field describing modifier keys held
     */
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

        try {
            for (Selector selector : BaseEvent.events.keySet()) {
                if (selector.eventType == ControlTypes.KEY_PRESS) {
                    BaseEvent.events.get(selector).apply(new Object[] { key, scanCode, action, modifiers });
                }
            }
        } catch (Throwable e) {
            LOG.error("Error in scroll callback", e, true);
        }
    }
}