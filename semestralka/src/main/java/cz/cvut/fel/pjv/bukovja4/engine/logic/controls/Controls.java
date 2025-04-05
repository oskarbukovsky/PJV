package cz.cvut.fel.pjv.bukovja4.engine.logic.controls;

import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

public class Controls {
    private static long windowHandle;

    public Controls(long windowHandle) {
        Controls.windowHandle = windowHandle;
    }

    private static Map<ControlTypes, BaseEvent> events = new HashMap<>();
    
    @SuppressWarnings("unchecked")
    public <E extends BaseEvent> E register(ControlTypes controlType, @SuppressWarnings("rawtypes") Function callback) throws Throwable {
        try {
            E event = (E) controlType.getEventClass().getDeclaredConstructor(long.class).newInstance(Controls.windowHandle);
            event.init(callback);
            event.register();
            events.put(controlType, event);
            return event;
        } catch (Exception e) {
            LOG.error("Error creating control " + controlType.getEventClass().getSimpleName(), e);
        }
        return null;
    }

    public void unRegister(ControlTypes controlType) {
        LOG.info("Unregistering control: " + controlType);
        BaseEvent event = events.get(controlType);
        if (event != null) {
            event.clearCallback();
            events.remove(controlType);
        }
    }

    public void unRegisterAll() {
        LOG.info("Unregistering all controls");
        for (BaseEvent event : events.values()) {
            event.clearCallback();
        }
        events.clear();
    }
}