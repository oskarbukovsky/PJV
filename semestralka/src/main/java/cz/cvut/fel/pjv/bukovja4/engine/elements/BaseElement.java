package cz.cvut.fel.pjv.bukovja4.engine.elements;

import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.box.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.pos.*;

public abstract class BaseElement<T extends Dim> {
    public Box<T> bounds;

    public BaseElement(Box<T> bounds) {
        this.bounds = bounds;
    }

    public BaseElement(@SuppressWarnings("unchecked") Pos<T>... corners) {
        this.bounds = new Box<T>(corners[0], corners[1]);
    }

    public BaseElement(float x1, float x2, float y1, float y2) {
        this.bounds = new Box<T>(x1, x1, y1, y2);
    }

    public BaseElement(float x1, float x2, float y1, float y2, float z1, float z2) {
        this.bounds = new Box<T>(x1, x2, y1, y2, z1, z2);
    }

    public BaseElement(float x1, float x2, float y1, float y2, float z1, float z2, float special) {
        this.bounds = new Box<T>(x1, x2, y1, y2, z1, z2, special);
    }

    public abstract void render();
}