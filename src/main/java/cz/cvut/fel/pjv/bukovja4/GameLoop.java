package cz.cvut.fel.pjv.bukovja4;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
import cz.cvut.fel.pjv.bukovja4.client.Window;
import cz.cvut.fel.pjv.bukovja4.engine.logic.GameState;
import cz.cvut.fel.pjv.bukovja4.utils.Exceptions.GameException;
import cz.cvut.fel.pjv.bukovja4.utils.Exceptions.SceneException;
import cz.cvut.fel.pjv.bukovja4.utils.clocks.Clock;
import cz.cvut.fel.pjv.bukovja4.utils.config.AppConfig;
import cz.cvut.fel.pjv.bukovja4.utils.config.Config;
import cz.cvut.fel.pjv.bukovja4.utils.constants.Const;
import cz.cvut.fel.pjv.bukovja4.utils.engine.SpriteManager;
import cz.cvut.fel.pjv.bukovja4.engine.scenes.*;

import static org.lwjgl.opengl.GL11.*;

import java.net.URISyntaxException;

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
    public static Config config;

    /**
     * Gets the current application configuration.
     * 
     * @return The current AppConfig instance
     */
    public static AppConfig getConfig() {
        return config.getConfig();
    }

    /** Clock for fixed-timestep update cycle */
    private Clock clock;

    /** Initial scene to be loaded when the game starts */
    private SceneTypes initSceneType;

    /** Name of the initial scene file */
    private String initSceneName;

    /**
     * Creates a game loop with the specified configuration.
     * Starts the game clock at the configured FPS limit.
     * 
     * @param config Application configuration
     */
    public GameLoop(Config config) {
        GameLoop.config = config;
        this.clock = new Clock(config.getConfig().window.fpsLock, "ClockThread");
        this.clock.start();
    }

    /**
     * Sets the initial scene for the game loop.
     * This scene will be loaded and displayed when the game starts.
     * 
     * @param sceneType The type of scene to create
     * @param name      The name of the scene file
     */
    public void setInitScene(SceneTypes sceneType, String name) {
        this.initSceneType = sceneType;
        this.initSceneName = name;

    }

    /**
     * Starts the game loop thread.
     * Calls the run method to initiate the main game loop.
     * 
     * @throws GameException if the initial scene is not set
     * @see Thread#start() For thread management
     */
    @Override
    public void start() throws GameException {
        if (this.initSceneName == null) {
            throw new GameException("Initial scene not set");
        }
        super.start();
        try {
            this.join();
        } catch (InterruptedException e) {
            LOG.error("Game interrupted", e, true);
        }
    }

    /**
     * Main game loop that runs in its own thread.
     * Sets up window, OpenGL context, input controls, and game state.
     * Runs fixed-timestep loop until window closes, then cleans up resources.
     */
    @Override
    public void run() throws SceneException {
        Thread.currentThread().setName("Game");
        LOG.info("GameLoop started");

        Window window;
        try {
            window = new Window(GameLoop.config);
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

            GameLoop.config.getConfig().window.width = newWidth;
            GameLoop.config.getConfig().window.height = newHeight;

            Window.setWidth(newWidth);
            Window.setHeight(newHeight);

            try {
                GameLoop.config.Update(GameLoop.config.getConfig());
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
            
            // Load the initial scene
            GameState.setScene(SceneFactory.create(this.initSceneType, this.initSceneName));
        } catch (Throwable e) {
            LOG.error("Error while creating game state", (RuntimeException) e);
        }
        window.setGameState(gameState);
        
        try {
            SpriteManager.loadSprite(Const.DEFAULT_FONT, true);
            LOG.debug("Default font loaded");
        } catch (URISyntaxException e) {
            LOG.error("Failed to load default font", e, true);
        }
        
        /**
         * Main game loop counter for debug purposes.
         * Tracks how many ticks have occurred since the game started.
         */
        long counter = 0;

        try{
            while (!glfwWindowShouldClose(window.getHandle())) {
                // Wait for the next tick to maintain consistent update rate
                clock.awaitTick();
                LOG.debug("Tick: " + counter++);
                
                // Clear the screen for the next frame
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                
                // Update game state and render the frame
                GameState.getScene().tick();
                window.render();
                
                // Display the rendered frame and check for window events
                glfwSwapBuffers(window.getHandle());
                glfwPollEvents();
            }
        } catch (final Throwable ignored) {
        }

        // Cleanup resources when the game loop exits
        GameState.getScene().Unload();
        glfwDestroyWindow(window.getHandle());
        glfwTerminate();
        LOG.info("GameLoop finished");

        // Stop the clock thread and the game thread
        this.clock.interrupt();
        Thread.currentThread().interrupt();
    }
}