package cz.cvut.fel.pjv.bukovja4.engine.logic.controls;

import java.util.function.Function;

import org.lwjgl.system.Callback;
// import org.lwjgl.system.CallbackI;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

/**
 * Abstract base class for all input event handlers.
 * Provides common functionality for registering and handling input events.
 */
public abstract class BaseEvent {
    /** Event callback function */
    public Function<Object[], ?> callback;
    /** Window handle to register events with */
    public long windowHandle;

    /**
     * Registers this event handler with the window.
     * Must be implemented by subclasses to set up specific event types.
     */
    public abstract void register();

    /**
     * Unregisters this event handler from the window.
     * Must be implemented by subclasses to clean up specific event types.
     */
    public abstract void unRegister();

    /** LWJGL callback handler */
    public Callback handler;

    /**
     * Initializes the event handler with a callback function
     * 
     * @param callback Function to call when the event occurs
     */
    public void init(Function<Object[], ?> callback) {
        this.callback = callback;
        LOG.debug("Registering event: " + this.getClass().getSimpleName());
    }

    /**
     * Clears the current callback function and frees associated resources
     */
    public void clearCallback() {
        unRegister();

        if (this.handler != null) {
            this.handler.free();
            this.handler = null;
        }
        this.callback = null;
        LOG.debug("Unregistering event: " + this.getClass().getSimpleName());
    }
}
