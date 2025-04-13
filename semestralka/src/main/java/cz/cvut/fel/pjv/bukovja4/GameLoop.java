package cz.cvut.fel.pjv.bukovja4;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
import cz.cvut.fel.pjv.bukovja4.client.Window;
import cz.cvut.fel.pjv.bukovja4.engine.logic.GameState;
import cz.cvut.fel.pjv.bukovja4.utils.clocks.Clock;
import cz.cvut.fel.pjv.bukovja4.utils.config.Config;

import cz.cvut.fel.pjv.bukovja4.engine.scenes.*;

import static cz.cvut.fel.pjv.bukovja4.engine.logic.controls.ControlTypes.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.*;

public final class GameLoop extends Thread {

    Config config;
    Clock clock;

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
            window.init();
        } catch (Throwable e) {
            return;
        }

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glfwSetWindowSizeCallback(window.getHandle(), (windowHandle, newWidth, newHeight) -> {
            LOG.debug("Window resized to: " + newWidth + "x" + newHeight);

            this.config.getConfig().window.width = newWidth;
            this.config.getConfig().window.height = newHeight;

            Window.setWidth(newWidth);
            Window.setHeight(newHeight);

            try {
                this.config.Update(this.config.getConfig());
            } catch (Throwable e) {

            }
            // Update OpenGL viewport
            glViewport(0, 0, newWidth, newHeight);
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(0, newWidth, newHeight, 0, 1, -1);
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();
        });

        // Create and configure the Controls instance with proper callback management
        // Controls controls = new Controls(window.getHandle());
        // try {
        // controls.register(SCROLL, (event) -> {
        // LOG.warn("Scroll fired");
        // return null;
        // });

        // controls.register(CLICK, (event) -> {
        // LOG.warn("Click fired");
        // return null;
        // });

        // controls.register(KEY_PRESS, (event) -> {
        // LOG.warn("KeyPress fired");
        // return null;
        // });

        // controls.register(MOUSE_MOVE, (event) -> {
        // LOG.warn("MouseMove fired");
        // return null;
        // });

        // controls.unRegister(MOUSE_MOVE);
        // // controls.unRegisterAll();

        // } catch (Throwable e) {
        // LOG.error("Error while registering handlers", (RuntimeException) e);
        // }

        GameState gameState = null;
        try {
            gameState = new GameState(window.getHandle());
            GameState.controls.register(SCROLL, (event) -> {
                LOG.warn("Scroll fired");
                return null;
            });
            gameState.setScene(SceneFactory.create(SceneTypes.MENU, "main/main_menu.yml"));
        } catch (Throwable e) {
            LOG.error("Error while creating game state", (RuntimeException) e);
        }

        long counter = 0;
        while (!glfwWindowShouldClose(window.getHandle())) {
            clock.awaitTick();
            LOG.debug("Tick: " + counter++);

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            gameState.getScene().Tick();
            window.Render();

            glfwSwapBuffers(window.getHandle());
            glfwPollEvents();
        }

        gameState.getScene().Unload();
        glfwDestroyWindow(window.getHandle());
        glfwTerminate();
        LOG.info("GameLoop finished");
        this.clock.interrupt();
        Thread.currentThread().interrupt();
    }
}