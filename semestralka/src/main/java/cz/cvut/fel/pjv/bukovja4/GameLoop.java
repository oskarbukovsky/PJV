package cz.cvut.fel.pjv.bukovja4;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

import org.lwjgl.glfw.GLFW;

import cz.cvut.fel.pjv.bukovja4.client.Window;
import cz.cvut.fel.pjv.bukovja4.utils.clocks.Clock;
import cz.cvut.fel.pjv.bukovja4.utils.config.Config;

public final class GameLoop extends Thread {

    Config config;
    Clock clock;
    private final Object windowLock = new Object();
    private Object gameLock;

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
            window = new Window(this.config, windowLock);
            window.start();
        } catch (Throwable e) {
            return;
        }
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

            // GLFW.glfwSwapBuffers(window.getHandle());
            GLFW.glfwPollEvents();
        }
        GLFW.glfwDestroyWindow(window.getHandle());
        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
        LOG.info("GameLoop finished");
        this.clock.interrupt();
        Thread.currentThread().interrupt();
    }
}
