package cz.cvut.fel.pjv.bukovja4.client;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import cz.cvut.fel.pjv.bukovja4.Main;
import cz.cvut.fel.pjv.bukovja4.utils.config.AppConfig;
import cz.cvut.fel.pjv.bukovja4.utils.config.Config;
import cz.cvut.fel.pjv.bukovja4.utils.constants.Const;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.stb.STBImage;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.opengl.GL.*;

/**
 * Manages the application window using GLFW.
 * Handles window creation, icon setting, and rendering setup.
 */
public class Window {
    /** Current window width */
    private static int width;
    /** Current window height */
    private static int height;
    /** GLFW window handle */
    private long handle;
    /** Application configuration */
    private final AppConfig config;

    /**
     * Gets current window width
     * 
     * @return Window width in pixels
     */
    public static int getWidth() {
        return Window.width;
    }

    /**
     * Gets current window height
     * 
     * @return Window height in pixels
     */
    public static int getHeight() {
        return Window.height;
    }

    /**
     * Sets window width
     * 
     * @param width New width in pixels
     */
    public static void setWidth(int width) {
        Window.width = width;
    }

    /**
     * Sets window height
     * 
     * @param height New height in pixels
     */
    public static void setHeight(int height) {
        Window.height = height;
    }

    /**
     * Gets GLFW window handle
     * 
     * @return GLFW window handle
     */
    public long getHandle() {
        return handle;
    }

    /**
     * Creates a new window with configuration
     * 
     * @param config Application configuration
     */
    public Window(Config config) {
        this.config = config.getConfig();
        Window.width = this.config.window.width;
        Window.height = this.config.window.height;
    }

    /**
     * Renders window content
     */
    public void render() {
        RenderWindow.Render();
    }

    /**
     * Initializes the GLFW window
     * 
     * @throws RuntimeException if window initialization fails
     */
    public void init() throws RuntimeException {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            LOG.error("Could not initialize GLFW", new RuntimeException());
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        Window.width = this.config.window.width;
        Window.height = this.config.window.height;

        handle = glfwCreateWindow(width, height, Const.APP_TITLE, NULL, NULL);
        if (handle == NULL) {
            LOG.error("GLFW window could not be created", new RuntimeException());
        }

        try {
            setIcon();
        } catch (IOException e) {
        }

        glfwMakeContextCurrent(handle);
        glfwShowWindow(handle);
        createCapabilities();

        glOrtho(0, this.config.window.width, this.config.window.height, 0, 1, -1);
        glfwSwapInterval(1);
    }

    /**
     * Sets window icon from favicon.png resource
     * 
     * @throws RuntimeException if icon loading fails
     * @throws IOException      if icon file cannot be read
     */
    private void setIcon() throws RuntimeException, IOException {
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);

        ByteBuffer image = null;
        try (InputStream imageStream = Main.class.getResourceAsStream("/favicon.png");) {
            if (imageStream != null) {
                byte[] imageBytes = imageStream.readAllBytes();
                ByteBuffer imageBuf = BufferUtils.createByteBuffer(imageBytes.length);
                imageBuf.put(imageBytes);
                imageBuf.flip();

                STBImage.stbi_set_flip_vertically_on_load(false);
                image = STBImage.stbi_load_from_memory(imageBuf, width, height, channels, 4);
            }

            if (image == null) {
                LOG.warn("Failed to load window icon favicon.png");
                return;
            }

            GLFWImage.Buffer icons = GLFWImage.malloc(1);
            icons.position(0);
            icons.width(width.get(0));
            icons.height(height.get(0));
            icons.pixels(image);

            glfwSetWindowIcon(handle, icons);

            STBImage.stbi_image_free(image);
            icons.free();

            LOG.debug("Window icon set successfully");
        } catch (IOException e) {
            LOG.error("Failed to set window icon", e);
        }
    }
}