package cz.cvut.fel.pjv.bukovja4.engine.elements;

import cz.cvut.fel.pjv.bukovja4.engine.elements.specific.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.Dim;

public enum ElementTypes {
    BUTTON(Button.class),
    IMAGE(Image.class),
    LABEL(Label.class);

    private final Class<? extends BaseElement<?>> elementClass;

    <T extends BaseElement<?>> ElementTypes(Class<T> elementClass) {
        this.elementClass = elementClass;
    }

    @SuppressWarnings("unchecked")
    public <D extends Dim> Class<? extends BaseElement<D>> getElementClass() {
        return (Class<? extends BaseElement<D>>) elementClass;
    }
}
