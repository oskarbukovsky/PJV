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
    public <E extends BaseElement<D>> E create(ElementTypes elementClass, Box<D> box)
            throws IllegalArgumentException {
        return switch (elementClass) {
            case BUTTON -> (E) new Button<D>(box);
            case LABEL -> (E) new Label<D>(box);
            default -> {
                LOG.error("Unknown element type", new IllegalArgumentException(elementClass.name()));
                yield null;
            }
        };
    }

    public <E extends BaseElement<D>> E create(ElementTypes elementClass, Pos<D> position1, Pos<D> position2) {
        return this.create(elementClass, new Box<D>(position1, position2));
    }

    public <E extends BaseElement<D>> E create(ElementTypes elementClass, float x1, float x2, float y1, float y2) {
        return this.create(elementClass, new Box<D>(new Pos<D>(x1, y1), new Pos<D>(x2, y2)));
    }

    public <E extends BaseElement<D>> E create(ElementTypes elementClass, float x1, float x2, float y1, float y2, float z1,
            float z2) {
        return this.create(elementClass, new Box<D>(new Pos<D>(x1, y1, z2), new Pos<D>(x2, y2, z2)));
    }

    public <E extends BaseElement<D>> E create(ElementTypes elementClass, float x1, float x2, float y1, float y2, float z1,
            float z2, float special) {
        return this.create(elementClass, new Box<D>(new Pos<D>(x1, y1, z2, special), new Pos<D>(x2, y2, z2, special)));
    }
}
