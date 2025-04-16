package cz.cvut.fel.pjv.bukovja4.engine.logic.controls;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

/**
 * Manages input controls and event handlers.
 * Provides functionality to register and unregister event callbacks.
 */
public class Controls {
    /** Handle to the window associated with these controls */
    private static long windowHandle;

    /** Map of registered event handlers by control type */
    private static List<BaseEvent> events = new ArrayList<>();

    /**
     * Creates a new controls manager for the specified window
     * 
     * @param windowHandle The GLFW window handle to capture events from
     */
    public Controls(long windowHandle) {
        Controls.windowHandle = windowHandle;
    }

    /**
     * Registers a new event handler for the specified control type
     * 
     * @param <E>         The type of event to register
     * @param controlType The control type to register
     * @param callback    The function to call when the event occurs
     * @return The created event handler instance
     * @throws Throwable if event registration fails
     * @see BaseEvent
     */
    @SuppressWarnings("unchecked")
    public <E extends BaseEvent> E register(ControlTypes controlType, @SuppressWarnings("rawtypes") Function callback)
            throws Throwable {
        try {
            E event = (E) controlType.getEventClass().getDeclaredConstructor(long.class)
                    .newInstance(Controls.windowHandle);
            event.init(callback);
            event.register();
            events.add(event);
            return event;
        } catch (Exception e) {
            LOG.error("Error creating control " + controlType.getEventClass().getSimpleName(), e);
        }
        return null;
    }

    /**
     * Unregisters a specific control type event handler
     * 
     * @param controlType The control type to unregister
     */
    public void unRegister(ControlTypes controlType) {
        LOG.info("Unregistering control: " + controlType);
        // TODO: add type in constructor and add getControlType to base
        // for (BaseEvent event : events) {
        //     if (event.getControlType() == controlType) {
        //         event.clearCallback();
        //         events.remove(event);
        //         return;
        //     }
        // }
        // BaseEvent event = events.get(controlType);
        // if (event != null) {
        //     event.clearCallback();
        //     events.remove(controlType);
        // }
    }

    /**
     * Unregisters all registered event handlers
     */
    public void unRegisterAll() {
        LOG.info("Unregistering all controls");
        for (BaseEvent event : events) {
            event.clearCallback();
        }
        events.clear();
    }
}