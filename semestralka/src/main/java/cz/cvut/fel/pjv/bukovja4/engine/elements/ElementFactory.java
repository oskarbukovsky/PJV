package cz.cvut.fel.pjv.bukovja4.engine.elements;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
import cz.cvut.fel.pjv.bukovja4.engine.elements.specific.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.*;

public class ElementFactory<D extends Dim> {
    public static final ElementFactory<Dim2d> Factory2d = new ElementFactory<>();
    public static final ElementFactory<Dim3d> Factory3d = new ElementFactory<>();

    public ElementFactory() {
    }

    @SuppressWarnings("unchecked")
    public <E extends BaseElement<D>> E create(Class<E> elementClass, Pos<D>... positions) throws IllegalArgumentException {
        if (elementClass == Button.class) {
            return (E) new Button<D>(positions[0], positions[1]);
        } else if (elementClass == Label.class) {
            return (E) new Label<D>(positions[0], positions[1]);
        } else {
            LOG.error("Unknown element type", new IllegalArgumentException(elementClass.getName()));
            System.out.println("HEHE!!!");
            return null; // Should not execute, but ... compiler bug i guess ¯\_(ツ)_/¯
        }
    }
}
