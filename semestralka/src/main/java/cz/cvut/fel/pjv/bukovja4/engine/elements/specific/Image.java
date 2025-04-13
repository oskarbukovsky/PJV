package cz.cvut.fel.pjv.bukovja4.engine.elements.specific;

import cz.cvut.fel.pjv.bukovja4.engine.elements.BaseElement;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.*;

public class Image<D extends Dim> extends BaseElement<D> {

    @Override
    public void render() {

    }

    public Image(Box<D> bounds) {
        super(bounds);
    }

    public Image(Pos<D> corner1, Pos<D> corner2) {
        super(corner1, corner2);
    }

    public Image(float x1, float x2, float y1, float y2) {
        super(x1, x1, y1, y2);
    }

    public Image(float x1, float x2, float y1, float y2, float z1, float z2) {
        super(x1, x2, y1, y2, z1, z2);
    }

    public Image(float x1, float x2, float y1, float y2, float z1, float z2, float special) {
        super(x1, x2, y1, y2, z1, z2, special);
    }
}
