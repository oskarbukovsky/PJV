package cz.cvut.fel.pjv.bukovja4.client.player.controls;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;

// import cz.cvut.fel.pjv.bukovja4.client.player.Camera;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
import static cz.cvut.fel.pjv.bukovja4.utils.Utils.getCharacter;

public class KeyControls implements GLFWKeyCallbackI {
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
        
        // if (key == GLFW.GLFW_KEY_UP && action == GLFW.GLFW_PRESS) {
        //     Camera.getPlayer().position.y -= 16;
        // }
        
        // if (key == GLFW.GLFW_KEY_DOWN && action == GLFW.GLFW_PRESS) {
        //     Camera.getPlayer().position.y += 16;
        // }
        
        // if (key == GLFW.GLFW_KEY_LEFT && action == GLFW.GLFW_PRESS) {
        //     Camera.getPlayer().position.x -= 16;
        // }
        
        // if (key == GLFW.GLFW_KEY_RIGHT && action == GLFW.GLFW_PRESS) {
        //     Camera.getPlayer().position.x += 16;
        // }

        // LOG.info("Player position: " + Camera.getPlayer().position.x + ", " + Camera.getPlayer().position.y);
    }
}