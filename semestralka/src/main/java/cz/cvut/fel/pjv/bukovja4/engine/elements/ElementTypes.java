package cz.cvut.fel.pjv.bukovja4.engine.elements;

import cz.cvut.fel.pjv.bukovja4.engine.elements.specific.*;

public enum ElementTypes {
    BUTTON(Button.class),
    LABEL(Label.class);

    <T extends BaseElement<?>> ElementTypes(Class<T> elementClass) {
    }
}
