package cz.cvut.fel.pjv.bukovja4;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
import cz.cvut.fel.pjv.bukovja4.client.RenderWindow;
import cz.cvut.fel.pjv.bukovja4.client.Window;
import cz.cvut.fel.pjv.bukovja4.utils.clocks.Clock;
import cz.cvut.fel.pjv.bukovja4.utils.config.Config;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.opengl.GL11.*;

public final class GameLoop extends Thread {

    Config config;
    Clock clock;
    private final Object windowLock = new Object();

    public GameLoop(Config config) {
        this.config = config;
        this.clock = new Clock(config.getConfig().window.fpsLock, "ClockThread");
        this.clock.start();
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Game");
        LOG.info("GameLoop started");

        Window window;
        try {
            window = new Window(this.config);
            window.start();
        } catch (Throwable e) {
            return;
        }

        // glEnable(GL_BLEND);
        // glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // Add window resize callback
        // GLFW.glfwSetWindowSizeCallback(window.getHandle(), (windowHandle, newWidth,
        // newHeight) -> {
        // LOG.debug("Window resized to: " + newWidth + "x" + newHeight);

        // // Update the config with new dimensions
        // this.config.getConfig().window.width = newWidth;
        // this.config.getConfig().window.height = newHeight;

        // // Save the updated config
        // try {
        // this.config.Update(this.config.getConfig());
        // } catch (Throwable e) {

        // }
        // // Update OpenGL viewport
        // glViewport(0, 0, newWidth, newHeight);
        // glMatrixMode(GL_PROJECTION);
        // glLoadIdentity();
        // glOrtho(0, newWidth, newHeight, 0, 1, -1);
        // glMatrixMode(GL_MODELVIEW);
        // glLoadIdentity();

        // // RenderWindow.Render();
        // });

        long counter = 0;

        synchronized (windowLock) {
            while (!window.loadingFinished()) {
                try {
                    windowLock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        while (!GLFW.glfwWindowShouldClose(window.getHandle())) {
            clock.awaitTick();
            LOG.debug("Tick: " + counter++);

            synchronized (window.getGameLock()) {
                window.getGameLock().notifyAll();
            }

            // GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            RenderWindow.Render();

            GLFW.glfwSwapBuffers(window.getHandle());
            GLFW.glfwPollEvents();
        }

        // Clean up callbacks to avoid memory leaks
        GLFW.glfwSetWindowSizeCallback(window.getHandle(), null).free();

        GLFW.glfwDestroyWindow(window.getHandle());
        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
        LOG.info("GameLoop finished");
        this.clock.interrupt();
        Thread.currentThread().interrupt();
    }
}
