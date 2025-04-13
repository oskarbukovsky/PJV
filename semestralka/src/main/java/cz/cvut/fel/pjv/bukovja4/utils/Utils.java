package cz.cvut.fel.pjv.bukovja4.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.lwjgl.glfw.GLFW;

import cz.cvut.fel.pjv.bukovja4.Main;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

/**
 * Utility class providing common functionality used throughout the application.
 * Contains methods for application shutdown, resource management, and input handling.
 */
public final class Utils {
    /**
     * Gracefully closes the game application.
     * Interrupts all non-main threads and logs the shutdown process.
     * 
     */
    public static void CloseGame() {
        LOG.debug("Game is closing...");
        Thread[] threads = new Thread[Thread.activeCount()];
        Thread.enumerate(threads);
        for (Thread thread : threads) {
            if (thread != null && thread.isAlive() && !thread.getName().equals("MainThread")) {
                thread.interrupt();
                LOG.debug("Interrupted thread: " + thread.getName());
            }
        }
    }

    /**
     * Copies a resource file from the JAR to the filesystem.
     * Used for extracting configuration files and other resources.
     * 
     * @param targetPathString Destination path on the filesystem
     * @param sourcePathString Source path within the resources folder
     * @throws Throwable if resource cannot be found or copied
     * @see Files#copy(InputStream, Path, java.nio.file.CopyOption...)
     * @see Main#class For the ClassLoader used to locate resources
     */
    public static void copyFromResources(String targetPathString, String sourcePathString) throws Throwable {
        Path targetPath = Paths.get(targetPathString);

        InputStream sourceStream = Main.class.getResourceAsStream("/" + sourcePathString);
        if (sourceStream != null) {
            // System.out.println(sourceStream.toString());
            Files.copy(sourceStream, targetPath);
            LOG.info("Config file was successfully copied to " + targetPath);
        } else {
            LOG.error("Resource not found", new IOException(sourcePathString));
        }
    }

    /**
     * Converts GLFW key codes to their character representation.
     * Handles modifier keys like Shift to return appropriate characters.
     * 
     * @param key The GLFW key code
     * @param mods Bit field describing modifier keys (Shift, Alt, etc.)
     * @return The character corresponding to the key press, or null if not a printable character
     * @see org.lwjgl.glfw.GLFW For key code definitions
     */
    public static Character getCharacter(int key, int mods) {
        boolean shift = (mods & GLFW.GLFW_MOD_SHIFT) != 0;

        if (key >= GLFW.GLFW_KEY_A && key <= GLFW.GLFW_KEY_Z) {
            char c = (char) ('A' + (key - GLFW.GLFW_KEY_A));
            return shift ? c : Character.toLowerCase(c);
        }

        if (key >= GLFW.GLFW_KEY_0 && key <= GLFW.GLFW_KEY_9) {
            if (shift) {
                char[] shiftNumbers = { ')', '!', '@', '#', '$', '%', '^', '&', '*', '(' };
                return shiftNumbers[key - GLFW.GLFW_KEY_0];
            } else {
                return (char) ('0' + (key - GLFW.GLFW_KEY_0));
            }
        }

        if (key >= GLFW.GLFW_KEY_KP_0 && key <= GLFW.GLFW_KEY_KP_9) {
            return (char) ('0' + (key - GLFW.GLFW_KEY_KP_0));
        }

        switch (key) {
            case GLFW.GLFW_KEY_SPACE:
                return ' ';
            case GLFW.GLFW_KEY_MINUS:
                return shift ? '_' : '-';
            case GLFW.GLFW_KEY_EQUAL:
                return shift ? '+' : '=';
            case GLFW.GLFW_KEY_LEFT_BRACKET:
                return shift ? '{' : '[';
            case GLFW.GLFW_KEY_RIGHT_BRACKET:
                return shift ? '}' : ']';
            case GLFW.GLFW_KEY_BACKSLASH:
                return shift ? '|' : '\\';
            case GLFW.GLFW_KEY_SEMICOLON:
                return shift ? ':' : ';';
            case GLFW.GLFW_KEY_APOSTROPHE:
                return shift ? '"' : '\'';
            case GLFW.GLFW_KEY_GRAVE_ACCENT:
                return shift ? '~' : '`';
            case GLFW.GLFW_KEY_COMMA:
                return shift ? '<' : ',';
            case GLFW.GLFW_KEY_PERIOD:
                return shift ? '>' : '.';
            case GLFW.GLFW_KEY_SLASH:
                return shift ? '?' : '/';
            case GLFW.GLFW_KEY_KP_DIVIDE:
                return '/';
            case GLFW.GLFW_KEY_KP_MULTIPLY:
                return '*';
            case GLFW.GLFW_KEY_KP_SUBTRACT:
                return '-';
            case GLFW.GLFW_KEY_KP_ADD:
                return '+';
            case GLFW.GLFW_KEY_KP_DECIMAL:
                return '.';
            case GLFW.GLFW_KEY_KP_EQUAL:
                return '=';
            default:
                return null;
        }
    }
}
