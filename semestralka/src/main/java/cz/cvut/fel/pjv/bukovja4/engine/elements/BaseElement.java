package cz.cvut.fel.pjv.bukovja4.engine.elements;

import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.*;

public abstract class BaseElement<D extends Dim> {
    public Box<D> bounds;

    public BaseElement(Box<D> bounds) {
        this.bounds = bounds;
    }

    public BaseElement(Pos<D> corner1, Pos<D> corner2) {
        this.bounds = new Box<D>(corner1, corner2);
    }

    public BaseElement(float x1, float x2, float y1, float y2) {
        this.bounds = new Box<D>(x1, x1, y1, y2);
    }

    public BaseElement(float x1, float x2, float y1, float y2, float z1, float z2) {
        this.bounds = new Box<D>(x1, x2, y1, y2, z1, z2);
    }

    public BaseElement(float x1, float x2, float y1, float y2, float z1, float z2, float special) {
        this.bounds = new Box<D>(x1, x2, y1, y2, z1, z2, special);
    }

    public abstract void render();
}