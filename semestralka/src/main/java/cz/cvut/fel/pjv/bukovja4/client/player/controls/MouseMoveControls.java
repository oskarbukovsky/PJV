package cz.cvut.fel.pjv.bukovja4.client.player.controls;

import org.lwjgl.glfw.GLFWCursorPosCallbackI;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

public class MouseMoveControls implements GLFWCursorPosCallbackI{
    @Override
    public void invoke(long window, double posX, double posY) {
        LOG.info("Mouse move: " + posX + ", " + posY);
    }
}
