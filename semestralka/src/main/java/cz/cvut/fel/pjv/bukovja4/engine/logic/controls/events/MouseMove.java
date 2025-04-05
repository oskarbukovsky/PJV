package cz.cvut.fel.pjv.bukovja4.engine.logic.controls.events;

import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;

import org.lwjgl.glfw.GLFWCursorPosCallbackI;

import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.BaseEvent;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

public class MouseMove extends BaseEvent implements GLFWCursorPosCallbackI {
    public MouseMove(long windowHandle) {
        this.windowHandle = windowHandle;
    }

    @Override
    public void register() {
        LOG.warn("Registering event: " + this.getClass().getSimpleName());
        glfwSetCursorPosCallback(windowHandle, this);
    }

    @Override
    public void unRegister() {
        glfwSetCursorPosCallback(windowHandle, null);
    }

    @Override
    public void invoke(long window, double posX, double posY) {

        LOG.info("Mouse move: " + posX + ", " + posY);

        if (this.callback != null) {
            try {
                this.callback.apply(new Object[] { posX, posY });
            } catch (Throwable e) {
                try {
                    LOG.error("Error in scroll callback", e);
                } catch (Throwable ignore) {
                }
            }
        }
    }
}
