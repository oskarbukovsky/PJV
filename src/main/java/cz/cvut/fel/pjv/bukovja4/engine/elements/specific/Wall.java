package cz.cvut.fel.pjv.bukovja4.engine.elements.specific;

import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.*;

/**
 * Player element
 */
public class Wall<D extends Dim> extends Image<D> {
    public void dispatchInit(float scale) {
        super.init("imgs/wall.png", scale);
    }

    public Wall(Box<D> bounds) {
        super(bounds);
    }

    public Wall(Pos<D> corner1, Pos<D> corner2) {
        super(corner1, corner2);
    }

    public Wall(float x1, float x2, float y1, float y2) {
        super(x1, x1, y1, y2);
    }

    public Wall(float x1, float x2, float y1, float y2, float z1, float z2) {
        super(x1, x2, y1, y2, z1, z2);
    }

    public Wall(float x1, float x2, float y1, float y2, float z1, float z2, float special) {
        super(x1, x2, y1, y2, z1, z2, special);
    }
}
