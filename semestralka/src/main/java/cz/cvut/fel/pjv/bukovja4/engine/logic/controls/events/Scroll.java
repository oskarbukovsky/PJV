package cz.cvut.fel.pjv.bukovja4.engine.logic.controls.events;

import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;

// import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.glfw.GLFWScrollCallbackI;

import cz.cvut.fel.pjv.bukovja4.client.player.Camera;
import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.BaseEvent;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

public class Scroll extends BaseEvent implements GLFWScrollCallbackI {
    public Scroll(long windowHandle) {
        this.windowHandle = windowHandle;
    }

    @Override
    public void register() {
        LOG.warn("Registering event: " + this.getClass().getSimpleName());
        glfwSetScrollCallback(windowHandle, this);
    }
    
    @Override
    public void unRegister() {
        glfwSetScrollCallback(windowHandle, null);
    }

    @Override
    public void invoke(long window, double dx, double dy) {
        LOG.info("Scroll: " + dx + ", " + dy);
        Camera.setZoom((float) (Camera.getZoom() + dy * 0.1f));
        if (this.callback != null) {
            try {
                this.callback.apply(new Object[] { dx, dy });
            } catch (Throwable e) {
                try {
                    LOG.error("Error in scroll callback", e);
                } catch (Throwable ignore) {
                }
            }
        }
    }
}
