package cz.cvut.fel.pjv.bukovja4.client.player.controls;

import org.lwjgl.glfw.GLFWMouseButtonCallbackI;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

public class MouseControls implements GLFWMouseButtonCallbackI {
    @Override
    public void invoke(long window, int button, int action, int mods){
        LOG.info("Mouse button: " + button + ", Action: " + action + ", Modifiers: " + mods);
    }
}
