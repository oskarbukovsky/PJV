package cz.cvut.fel.pjv.bukovja4.engine.logic.controls;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import cz.cvut.fel.pjv.bukovja4.engine.elements.BaseElement;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

/**
 * Abstract base class for all input event handlers.
 * Provides common functionality for registering and handling input events.
 */
public abstract class BaseEvent {

    /** Window handle to register events with */
    public long windowHandle;

    /** Type for invoking in specific types */
    protected ControlTypes eventType;

    @SuppressWarnings("rawtypes")
    BaseElement element;

    public static Map<Selector, Function<Object[], ?>> events = new HashMap<>();

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

    public void init(Selector selector, Function<Object[], ?> callback) {
        BaseEvent.events.put(selector, callback);
        this.eventType = selector.eventType;
        this.element = selector.element;
        LOG.debug(
                "Registering event: " + selector.eventType + " on element: "
                        + (selector.element != null ? selector.element.getClass().getSimpleName() : "null"));
    }

    /**
     * Clears the current callback function and frees associated resources
     */
    public void clearCallback() {
        unRegister();
        BaseEvent.events.clear();
        LOG.debug("Unregistering event: " + this.getClass().getSimpleName());
    }
}
