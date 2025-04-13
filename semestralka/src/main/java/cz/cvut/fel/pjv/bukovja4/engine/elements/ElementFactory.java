package cz.cvut.fel.pjv.bukovja4.engine.elements;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.*;

public class ElementFactory<D extends Dim> {
    public static final ElementFactory<Dim2d> Factory2d = new ElementFactory<>();
    public static final ElementFactory<Dim3d> Factory3d = new ElementFactory<>();

    public ElementFactory() {
    }

    @SuppressWarnings("rawtypes")
    public static ElementFactory getFactory(int dim) throws IllegalArgumentException{
        return switch(dim) {
            case 1 -> new ElementFactory<Dim1d>();
            case 2 -> new ElementFactory<Dim2d>();
            case 3 -> new ElementFactory<Dim3d>();
            case 4 -> new ElementFactory<Dim4d>();
            default -> throw new IllegalArgumentException("Invalid dimension: " + dim);
        };
    }

    @SuppressWarnings("unchecked")
    public <E extends BaseElement<D>> E create(ElementTypes elementType, Box<D> box)
            throws Throwable {
        try {
            return (E) elementType.getElementClass().getConstructor(Box.class).newInstance(box);
        } catch (Exception ex) {
            LOG.error("Error creating element " + elementType.name(), ex);
        }
        return null;
    }

    public <E extends BaseElement<D>> E create(ElementTypes elementType, Pos<D> position1, Pos<D> position2)
            throws Throwable {
        return this.create(elementType, new Box<D>(position1, position2));
    }

    public <E extends BaseElement<D>> E create(ElementTypes elementType, float x1, float x2, float y1, float y2)
            throws Throwable {
        return this.create(elementType, new Box<D>(new Pos<D>(x1, y1), new Pos<D>(x2, y2)));
    }

    public <E extends BaseElement<D>> E create(ElementTypes elementType, float x1, float x2, float y1, float y2,
            float z1,
            float z2) throws Throwable {
        return this.create(elementType, new Box<D>(new Pos<D>(x1, y1, z1), new Pos<D>(x2, y2, z2)));
    }

    public <E extends BaseElement<D>> E create(ElementTypes elementType, float x1, float x2, float y1, float y2,
            float z1,
            float z2, float special) throws Throwable {
        return this.create(elementType, new Box<D>(new Pos<D>(x1, y1, z1, special), new Pos<D>(x2, y2, z2, special)));
    }
}
