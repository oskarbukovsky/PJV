package cz.cvut.fel.pjv.bukovja4.engine.logic.controls.events;

import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;

import org.lwjgl.glfw.GLFWCursorPosCallbackI;

import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.BaseEvent;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

/**
 * Handles mouse movement events.
 * Extends {@link BaseEvent} to provide mouse movement-specific functionality.
 */
public class MouseMove extends BaseEvent implements GLFWCursorPosCallbackI {
    /**
     * Creates a mouse movement event handler for the specified window
     * 
     * @param windowHandle The GLFW window handle to capture mouse events from
     */
    public MouseMove(long windowHandle) {
        this.windowHandle = windowHandle;
    }

    /**
     * Registers this mouse movement handler with the window
     * {@inheritDoc}
     */
    @Override
    public void register() {
        glfwSetCursorPosCallback(windowHandle, this);
    }

    /**
     * Unregisters this mouse movement handler from the window
     * {@inheritDoc}
     */
    @Override
    public void unRegister() {
        glfwSetCursorPosCallback(windowHandle, null);
    }

    /**
     * Handles mouse movement events
     * 
     * @param window Window that received the event
     * @param posX   New X position of the mouse cursor
     * @param posY   New Y position of the mouse cursor
     */
    @Override
    public void invoke(long window, double posX, double posY) {

        LOG.info("Mouse move: " + posX + ", " + posY);

        if (this.callback != null) {
            try {
                this.callback.apply(new Object[] { posX, posY });
            } catch (Throwable e) {
                LOG.error("Error in scroll callback", e, true);
            }
        }
    }
}
