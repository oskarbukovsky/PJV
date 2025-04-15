package cz.cvut.fel.pjv.bukovja4.engine.logic.controls.events;

import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;

// import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.glfw.GLFWScrollCallbackI;

import cz.cvut.fel.pjv.bukovja4.client.player.Camera;
import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.BaseEvent;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

/**
 * Handles mouse scroll wheel events.
 * Extends {@link BaseEvent} to provide scroll-specific functionality.
 */
public class Scroll extends BaseEvent implements GLFWScrollCallbackI {
    /**
     * Creates a scroll event handler for the specified window
     * 
     * @param windowHandle The GLFW window handle to capture scroll events from
     */
    public Scroll(long windowHandle) {
        this.windowHandle = windowHandle;
    }

    /**
     * Registers this scroll handler with the window
     * {@inheritDoc}
     */
    @Override
    public void register() {
        glfwSetScrollCallback(windowHandle, this);
    }

    /**
     * Unregisters this scroll handler from the window
     * {@inheritDoc}
     */
    @Override
    public void unRegister() {
        glfwSetScrollCallback(windowHandle, null);
    }

    /**
     * Handles scroll wheel events and adjusts camera zoom
     * 
     * @param window Window that received the event
     * @param dx     Horizontal scroll amount
     * @param dy     Vertical scroll amount
     */
    @Override
    public void invoke(long window, double dx, double dy) {
        LOG.info("Scroll: " + dx + ", " + dy);
        Camera.setZoom((float) (Camera.getZoom() + dy * 0.1f));
        if (this.callback != null) {
            try {
                this.callback.apply(new Object[] { dx, dy });
            } catch (Throwable e) {
                LOG.error("Error in scroll callback", e, true);
            }
        }
    }
}
