package cz.cvut.fel.pjv.bukovja4.engine.elements.elements.generic;

import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.Dim;

public abstract class BaseElement<T extends Dim> {
    protected T bounds;

    public BaseElement(T bounds) {
        this.bounds = bounds;
    }

    public abstract void render();
}