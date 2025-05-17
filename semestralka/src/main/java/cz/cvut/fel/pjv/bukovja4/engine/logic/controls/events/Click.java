package cz.cvut.fel.pjv.bukovja4.engine.logic.controls.events;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;

import org.lwjgl.glfw.GLFWMouseButtonCallbackI;

import cz.cvut.fel.pjv.bukovja4.engine.logic.GameState;
import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.BaseEvent;
import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.ControlTypes;
import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.Selector;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

/**
 * Handles mouse button click events.
 * Extends {@link BaseEvent} to provide mouse click-specific functionality.
 */
public class Click extends BaseEvent implements GLFWMouseButtonCallbackI {
    /**
     * Creates a mouse click event handler for the specified window
     * 
     * @param windowHandle The GLFW window handle to capture mouse events from
     */
    public Click(long windowHandle) {
        this.windowHandle = windowHandle;
    }

    /**
     * Registers this mouse click handler with the window
     * {@inheritDoc}
     */
    @Override
    public void register() {
        glfwSetMouseButtonCallback(windowHandle, this);
    }

    /**
     * Unregisters this mouse click handler from the window
     * {@inheritDoc}
     */
    @Override
    public void unRegister() {
        glfwSetMouseButtonCallback(windowHandle, null);
    }

    /**
     * Handles mouse button events
     * 
     * @param window    Window that received the event
     * @param button    Mouse button that was pressed
     * @param action    Action performed (press/release)
     * @param modifiers Bit field describing modifier keys held
     */
    @Override
    synchronized public void invoke(long window, int button, int action, int modifiers) {

        if (action == GLFW_PRESS) { // Only call on press
            try {
                for (Selector selector : BaseEvent.events.keySet()) {
                    if (selector.eventType == ControlTypes.CLICK) {
                        if (selector.element.bounds.x1 <= GameState.mouseX
                                && selector.element.bounds.x2 >= GameState.mouseX
                                && selector.element.bounds.y1 <= GameState.mouseY
                                && selector.element.bounds.y2 >= GameState.mouseY) {
                            LOG.info("Click: Button=" + button + ", Action=" + action + ", Modifiers=" + modifiers);
                            // Call the callback function with the event data on right element
                            BaseEvent.events.get(selector).apply(new Object[] { button, action, modifiers });
                            break;
                        }
                    }
                }
            } catch (Throwable e) {
                LOG.error("Error in click callback", e, true);
            }
        }
    }
}
