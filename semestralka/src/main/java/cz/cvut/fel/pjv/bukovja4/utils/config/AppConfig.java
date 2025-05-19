package cz.cvut.fel.pjv.bukovja4.utils.config;

/**
 * Application configuration class that's loaded from and saved to YAML files.
 * Extends {@link YAMLPrint} to provide string representation of configuration.
 */
public class AppConfig extends YAMLPrint {
    /**
     * Window configuration settings.
     * Contains display properties like resolution and FOV.
     * Extends {@link YAMLPrint} to provide string representation of configuration.
     */
    public static class Window extends YAMLPrint {
        /** Field of view angle in degrees */
        public float fov;

        /** Flag indicating if the window should be fullscreen */
        public boolean fullscreen;

        /** Maximum frames per second (0 for unlimited) */
        public int fpsLock;

        /** Window width in pixels */
        public int width;

        /** Window height in pixels */
        public int height;

        /**
         * Creates a new window configuration with default values
         */
        public Window() {
        }

    }

    public static class GameState extends YAMLPrint {
        /** Player current level */
        public String level;

        /**
         * Creates a new game state configuration with default values
         */
        public GameState() {
        }
    }

    /** Window configuration settings */
    public Window window;

    /**
     * Game state save
     */
    public GameState gameState;

    /**
     * Creates a new application configuration with default values
     */
    public AppConfig() {
    }
}