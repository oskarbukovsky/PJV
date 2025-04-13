package cz.cvut.fel.pjv.bukovja4.engine.logic.controls;

import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.events.*;

/**
 * Enumeration of available input control types.
 * Each type is associated with a specific event handler class.
 */
public enum ControlTypes {
    /** Keyboard key press event */
    KEY_PRESS(KeyPress.class),
    /** Mouse click event */
    CLICK(Click.class),
    /** Mouse movement event */
    MOUSE_MOVE(MouseMove.class),
    /** Mouse scroll wheel event */
    SCROLL(Scroll.class);

    /** The event handler class associated with this control type */
    private final Class<? extends BaseEvent> eventClass;

    /**
     * Creates a control type linked to a specific event handler class
     * 
     * @param eventClass The class implementing this event type
     * @param <T>        The specific BaseEvent subclass type
     */
    <T extends BaseEvent> ControlTypes(Class<T> eventClass) {
        this.eventClass = eventClass;
    }

    /**
     * Gets the event handler class associated with this control type
     * 
     * @return The event's implementing class
     */
    public Class<? extends BaseEvent> getEventClass() {
        return eventClass;
    }
}
