package cz.cvut.fel.pjv.bukovja4.client;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import cz.cvut.fel.pjv.bukovja4.utils.config.AppConfig;
import cz.cvut.fel.pjv.bukovja4.utils.config.Config;
import cz.cvut.fel.pjv.bukovja4.utils.constants.Const;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

import org.lwjgl.opengl.GL;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.stb.STBImage;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
    private final int width;
    private final int height;
    private final long handle;
    private AppConfig config;

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public long getHandle() {
        return handle;
    }

    public Window(Config config) throws Throwable {
        this.config = config.getConfig();

        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            LOG.error("Could not initialize GLFW", new RuntimeException());
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        this.width = this.config.window.width;
        this.height = this.config.window.height;

        handle = glfwCreateWindow(width, height, Const.APP_TITLE, NULL, NULL);
        if (handle == NULL) {
            LOG.error("GLFW window could not be created", new RuntimeException());
        }

        setIcon();

        glfwMakeContextCurrent(handle);
        glfwShowWindow(handle);
        GL.createCapabilities();

        glOrtho(0, this.config.window.width, this.config.window.height, 0, 1, -1);
        glfwSwapInterval(1);
    }

    private void setIcon() throws RuntimeException, IOException {
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);

        // Load the image
        ByteBuffer image = null;
        try {
            // First try to load from classpath resources
            InputStream imageStream = getClass().getResourceAsStream("/favicon.png");
            if (imageStream != null) {
                // Convert to bytes
                byte[] imageBytes = imageStream.readAllBytes();
                ByteBuffer imageBuf = BufferUtils.createByteBuffer(imageBytes.length);
                imageBuf.put(imageBytes);
                imageBuf.flip();

                // Load from memory
                STBImage.stbi_set_flip_vertically_on_load(false);
                image = STBImage.stbi_load_from_memory(imageBuf, width, height, channels, 4);
            }

            if (image == null) {
                LOG.warn("Failed to load window icon favicon.png");
                return;
            }

            // Create a GLFWImage
            GLFWImage.Buffer icons = GLFWImage.malloc(1);
            icons.position(0);
            icons.width(width.get(0));
            icons.height(height.get(0));
            icons.pixels(image);

            // Set the window icon
            glfwSetWindowIcon(handle, icons);

            // Free the image memory
            STBImage.stbi_image_free(image);
            icons.free();

            LOG.info("Window icon set successfully");
        } catch (IOException e) {
            LOG.error("Failed to set window icon", e);
        }
    }
}
