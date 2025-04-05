package cz.cvut.fel.pjv.bukovja4.engine.logic.controls;

import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.events.*;

public enum ControlTypes {
    KEY_PRESS(KeyPress.class),
    CLICK(Click.class),
    MOUSE_MOVE(MouseMove.class),
    SCROLL(Scroll.class);

    private final Class<? extends BaseEvent> eventClass;

    <T extends BaseEvent> ControlTypes(Class<T> eventClass) {
        this.eventClass = eventClass;
    }

    public Class<? extends BaseEvent> getEventClass() {
        return eventClass;
    }
}
