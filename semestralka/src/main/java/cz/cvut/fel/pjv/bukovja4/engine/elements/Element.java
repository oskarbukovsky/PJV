package cz.cvut.fel.pjv.bukovja4.engine.elements;

import cz.cvut.fel.pjv.bukovja4.engine.elements.specific.*;

/**
 * Enum reprezentující typy elementů s mapováním na odpovídající třídy.
 */
public enum Element {
    BUTTON(Button.class),
    LABEL(Label.class);

    <T extends BaseElement<?>> Element(Class<T> elementClass) {
    }

}
