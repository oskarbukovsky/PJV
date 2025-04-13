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

/**
 * Core game engine class that implements the main game loop.
 * Uses fixed-timestep pattern: wait for clock tick, clear screen, update state,
 * render elements, swap buffers, poll events. A dedicated Clock maintains 
 * consistent update rate defined in the configuration.
 * 
 * @see Clock For the timing mechanism
 * @see GameState For game state management
 * @see Window For rendering
 */
public final class GameLoop extends Thread {

    /** Application configuration with window settings, FPS limits, etc. */
    Config config;
    
    /** Clock for fixed-timestep update cycle */
    Clock clock;

    /**
     * Creates a game loop with the specified configuration.
     * Starts the game clock at the configured FPS limit.
     * 
     * @param config Application configuration
     */
    public GameLoop(Config config) {
        this.config = config;
        this.clock = new Clock(config.getConfig().window.fpsLock, "ClockThread");
        this.clock.start();
    }

    /**
     * Main game loop that runs in its own thread.
     * Sets up window, OpenGL context, input controls, and game state.
     * Runs fixed-timestep loop until window closes, then cleans up resources.
     */
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

        // Setup OpenGL blending for transparency
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // Configure window resize handler to update viewport and configuration
        glfwSetWindowSizeCallback(window.getHandle(), (windowHandle, newWidth, newHeight) -> {
            LOG.debug("Window resized to: " + newWidth + "x" + newHeight);

            this.config.getConfig().window.width = newWidth;
            this.config.getConfig().window.height = newHeight;

            Window.setWidth(newWidth);
            Window.setHeight(newHeight);

            try {
                this.config.Update(this.config.getConfig());
            } catch (Throwable e) {
                // Silent failure for config update errors
            }
            
            // Update OpenGL viewport and projection matrix for the new dimensions
            glViewport(0, 0, newWidth, newHeight);
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(0, newWidth, newHeight, 0, 1, -1);
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();
        });

        // Code for manual control registration - kept for reference
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
            
            // Load the initial scene (main menu)
            gameState.setScene(SceneFactory.create(SceneTypes.MENU, "main/main_menu.yml"));
        } catch (Throwable e) {
            LOG.error("Error while creating game state", (RuntimeException) e);
        }

        /**
         * Main game loop counter for debug purposes.
         * Tracks how many ticks have occurred since the game started.
         */
        long counter = 0;
        
        /**
         * Core game loop that runs until the window is closed.
         * Each iteration represents a single frame of the game.
         * The loop is synchronized to the clock's tick rate.
         * 
         * @see Clock#awaitTick() For the timing mechanism
         * @see GameState#getScene() For accessing the active scene
         * @see Window#Render() For rendering the frame
         */
        while (!glfwWindowShouldClose(window.getHandle())) {
            // Wait for the next tick to maintain consistent update rate
            clock.awaitTick();
            LOG.debug("Tick: " + counter++);

            // Clear the screen for the next frame
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Update game state and render the frame
            gameState.getScene().Tick();
            window.Render();

            // Display the rendered frame and check for window events
            glfwSwapBuffers(window.getHandle());
            glfwPollEvents();
        }

        // Cleanup resources when the game loop exits
        gameState.getScene().Unload();
        glfwDestroyWindow(window.getHandle());
        glfwTerminate();
        LOG.info("GameLoop finished");
        
        // Stop the clock thread and the game thread
        this.clock.interrupt();
        Thread.currentThread().interrupt();
    }
}