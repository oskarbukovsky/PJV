package cz.cvut.fel.pjv.bukovja4.client;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import cz.cvut.fel.pjv.bukovja4.utils.config.AppConfig;
import cz.cvut.fel.pjv.bukovja4.utils.config.Config;
import cz.cvut.fel.pjv.bukovja4.utils.constants.Const;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

import static org.lwjgl.system.MemoryUtil.*;

public class Window extends Thread {
    private final int width;
    private final int height;
    private long handle;
    private AppConfig config;
    private Object windowLock;
    private final Object gameLock = new Object();

    public final Object getGameLock() {
        return this.gameLock;
    }

    private boolean loadingFinished = false;

    public boolean loadingFinished() {
        return this.loadingFinished;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public long getHandle() {
        return handle;
    }

    public Window(Config config, Object windowLock) throws Throwable {
        this.config = config.getConfig();
        this.width = this.config.window.width;
        this.height = this.config.window.height;
        this.windowLock = windowLock;
    }

    @Override
    public void run() throws RuntimeException {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!GLFW.glfwInit()) {
            LOG.error("Could not initialize GLFW", new RuntimeException());
        }

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);

        handle = GLFW.glfwCreateWindow(width, height, Const.APP_TITLE, NULL, NULL);
        if (handle == NULL) {
            LOG.error("GLFW window could not be created", new RuntimeException());
        }

        GLFW.glfwMakeContextCurrent(handle);
        GLFW.glfwShowWindow(handle);
        GL.createCapabilities();

        GL11.glOrtho(0, this.config.window.width, this.config.window.height, 0, 1, -1);
        GLFW.glfwSwapInterval(1);

        this.loadingFinished = true;
        synchronized (windowLock) {
            windowLock.notifyAll();
        }
        while (true) {
            synchronized (gameLock) {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        gameLock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }
    }
}
