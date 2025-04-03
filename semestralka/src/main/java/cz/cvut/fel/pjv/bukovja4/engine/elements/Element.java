package cz.cvut.fel.pjv.bukovja4.engine.elements;

import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.Dim;

public abstract class Element<D extends Dim> {
    protected D dimension;

    public Element(D dimension) {
        this.dimension = dimension;
    }

    public D getDimension() {
        return dimension;
    }

    // Common element methods
    public abstract void render();
}