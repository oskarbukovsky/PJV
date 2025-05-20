package cz.cvut.fel.pjv.bukovja4.engine.elements.specific;

import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.*;

/**
 * Goal element
 */
public class Goal<D extends Dim> extends Gif<D> {
    public void dispatchInit(float scale) {
        super.init(new String[] { "goal_t1.png", "goal_t2.png", "goal_t3.png", "goal_t4.png" }, 200, scale);
    }

    public Goal(Box<D> bounds) {
        super(bounds);
    }

    public Goal(Pos<D> corner1, Pos<D> corner2) {
        super(corner1, corner2);
    }

    public Goal(float x1, float x2, float y1, float y2) {
        super(x1, x1, y1, y2);
    }

    public Goal(float x1, float x2, float y1, float y2, float z1, float z2) {
        super(x1, x2, y1, y2, z1, z2);
    }

    public Goal(float x1, float x2, float y1, float y2, float z1, float z2, float special) {
        super(x1, x2, y1, y2, z1, z2, special);
    }
}
