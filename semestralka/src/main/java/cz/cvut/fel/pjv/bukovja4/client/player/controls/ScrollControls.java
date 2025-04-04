package cz.cvut.fel.pjv.bukovja4.client.player.controls;

import org.lwjgl.glfw.GLFWScrollCallbackI;

import cz.cvut.fel.pjv.bukovja4.client.player.Camera;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

public class ScrollControls implements GLFWScrollCallbackI {
    @Override
    public void invoke(long window, double dx, double dy) {
        LOG.info("Scroll: " + dx + ", " + dy);
        Camera.setZoom((float) (Camera.getZoom() + dy * 0.1f));
    }
}
