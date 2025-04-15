package cz.cvut.fel.pjv.bukovja4.utils;

import java.io.IOException;

/**
 * Custom exception classes for the game application.
 * These exceptions are used to handle specific error scenarios
 * related to scenes, configuration, and general game operations.
 */
public class Exceptions {
    /**
     * Exception thrown when there is an error related to scenes.
     */
    public static class SceneException extends RuntimeException  {
        public SceneException(String message) {
            super(message);
        }
        public SceneException(String message, Throwable cause) {
            super(message, cause);
        }
        public SceneException(Throwable cause) {
            super(cause);
        }
    }
    /**
     * Exception thrown when there is an error related to configuration.
     */
    public static class ConfigException extends IOException  {
        public ConfigException(String message) {
            super(message);
        }
        public ConfigException(String message, Throwable cause) {
            super(message, cause);
        }
        public ConfigException(Throwable cause) {
            super(cause);
        }
    }
    /**
     * Exception thrown when there is a general game-loop-related error.
     */
    public static class GameException extends RuntimeException  {
        public GameException(String message) {
            super(message);
        }
        public GameException(String message, Throwable cause) {
            super(message, cause);
        }
        public GameException(Throwable cause) {
            super(cause);
        }
    }
}
