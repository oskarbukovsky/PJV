package cz.cvut.fel.pjv.bukovja4.engine.logic.controls;

import cz.cvut.fel.pjv.bukovja4.engine.elements.BaseElement;


/**
 * Represents a selector for UI elements and their associated event types.
 * This class is used to manage the association between UI elements and the events
 * they respond to.
 */
@SuppressWarnings("rawtypes")
public class Selector {
    final public BaseElement element;
    final public ControlTypes eventType;

    /**
     * Constructs a new Selector instance with the specified element and event type.
     *
     * @param element   The UI element associated with this selector.
     * @param eventType The type of event this selector is associated with.
     */
    public Selector(BaseElement element, ControlTypes eventType) {
        this.element = element;
        this.eventType = eventType;
    }
}
