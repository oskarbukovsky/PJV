package cz.cvut.fel.pjv.bukovja4.engine.elements;

import cz.cvut.fel.pjv.bukovja4.engine.elements.specific.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.Dim;

public enum ElementTypes {
    BUTTON(Button.class),
    LABEL(Label.class);

    private final Class<? extends BaseElement<?>> elementClass;

    /**
     * Constructor for the enum
     * 
     * @param elementClass The class associated with this element type
     */
    <T extends BaseElement<?>> ElementTypes(Class<T> elementClass) {
        this.elementClass = elementClass;
    }

    /**
     * Gets the class associated with this element type
     * 
     * @return The element class
     */
    @SuppressWarnings("unchecked")
    public <D extends Dim> Class<? extends BaseElement<D>> getElementClass() {
        return (Class<? extends BaseElement<D>>) elementClass;
    }
}
