package cz.cvut.fel.pjv.bukovja4.client;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import cz.cvut.fel.pjv.bukovja4.utils.config.AppConfig;
import cz.cvut.fel.pjv.bukovja4.utils.config.Config;
import cz.cvut.fel.pjv.bukovja4.utils.constants.Const;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

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

    public Window(Config config) throws Throwable{
        this.config = config.getConfig();

        GLFWErrorCallback.createPrint(System.err).set();

        if (!GLFW.glfwInit()) {
            LOG.error("Could not initialize GLFW", new RuntimeException());
        }

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);

        this.width = this.config.window.width;
        this.height = this.config.window.height;

        handle = GLFW.glfwCreateWindow(width, height, Const.APP_TITLE, NULL, NULL);
        if (handle == NULL) {
            LOG.error("GLFW window could not be created", new RuntimeException());
        }

        GLFW.glfwMakeContextCurrent(handle);
        GLFW.glfwShowWindow(handle);
        GL.createCapabilities();

        GL11.glOrtho(0, this.config.window.width, this.config.window.height, 0, 1, -1);
        GLFW.glfwSwapInterval(1);
    }
}
