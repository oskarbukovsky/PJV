package cz.cvut.fel.pjv.bukovja4.engine.logic.controls;

/**
 * Functional interfaces for different control callbacks
 */
public class ControlCallbacks {
    
    /**
     * Base interface for all callback handlers with a common return value
     */
    public interface ControlHandler {
        /**
         * @return true to continue event propagation, false to stop
         */
        boolean handle();
    }
    
    /**
     * Keyboard key callback handler
     */
    @FunctionalInterface
    public interface KeyHandler extends ControlHandler {
        /**
         * Handle key events
         * 
         * @param window The window that received the event
         * @param key The keyboard key
         * @param scancode The system-specific scancode
         * @param action The key action (GLFW_PRESS, GLFW_RELEASE, GLFW_REPEAT)
         * @param mods Bit field describing which modifier keys were held down
         * @return true to continue event propagation, false to stop
         */
        boolean handle(long window, int key, int scancode, int action, int mods);
        
        @Override
        default boolean handle() {
            return true;
        }
    }
    
    /**
     * Mouse button callback handler
     */
    @FunctionalInterface
    public interface MouseButtonHandler extends ControlHandler {
        /**
         * Handle mouse button events
         * 
         * @param window The window that received the event
         * @param button The mouse button that was pressed or released
         * @param action The button action (GLFW_PRESS or GLFW_RELEASE)
         * @param mods Bit field describing which modifier keys were held down
         * @return true to continue event propagation, false to stop
         */
        boolean handle(long window, int button, int action, int mods);
        
        @Override
        default boolean handle() {
            return true;
        }
    }
    
    /**
     * Mouse movement callback handler
     */
    @FunctionalInterface
    public interface MouseMoveHandler extends ControlHandler {
        /**
         * Handle mouse cursor movement
         * 
         * @param window The window that received the event
         * @param xpos The new cursor x-coordinate, relative to the left edge of the content area
         * @param ypos The new cursor y-coordinate, relative to the top edge of the content area
         * @return true to continue event propagation, false to stop
         */
        boolean handle(long window, double xpos, double ypos);
        
        @Override
        default boolean handle() {
            return true;
        }
    }
    
    /**
     * Scroll callback handler
     */
    @FunctionalInterface
    public interface ScrollHandler extends ControlHandler {
        /**
         * Handle scrolling events
         * 
         * @param window The window that received the event
         * @param xoffset The scroll offset along the x-axis
         * @param yoffset The scroll offset along the y-axis
         * @return true to continue event propagation, false to stop
         */
        boolean handle(long window, double xoffset, double yoffset);
        
        @Override
        default boolean handle() {
            return true;
        }
    }
}