package cz.cvut.fel.pjv.bukovja4.engine.logic.controls.events;

import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;

import org.lwjgl.glfw.GLFWMouseButtonCallbackI;

import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.BaseEvent;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

public class Click extends BaseEvent implements GLFWMouseButtonCallbackI {
    public Click(long windowHandle) {
        this.windowHandle = windowHandle;
    }

    @Override
    public void register() {
        LOG.warn("Registering event: " + this.getClass().getSimpleName());
        glfwSetMouseButtonCallback(windowHandle, this);
    }

    @Override
    public void unRegister() {
        glfwSetMouseButtonCallback(windowHandle, null);
    }

    @Override
    public void invoke(long window, int button, int action, int mods){
        LOG.info("Mouse button: " + button + ", Action: " + action + ", Modifiers: " + mods);
        if (this.callback != null) {
            try {
                this.callback.apply(new Object[] { button, action, mods });
            } catch (Throwable e) {
                try {
                    LOG.error("Error in click callback", e);
                } catch (Throwable ignore) {
                }
            }
        }
    }
}
