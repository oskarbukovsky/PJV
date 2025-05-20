package cz.cvut.fel.pjv.bukovja4.engine.elements.specific;

import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
import cz.cvut.fel.pjv.bukovja4.utils.engine.*;

/**
 * Player element
 */
public class Player<D extends Dim> extends Image<D> {
    public void dispatchInit(float scale) {
        LOG.warn("Player.init()");
        super.init("imgs/player.png", scale);
    }

    public Player(Box<D> bounds) {
        super(bounds);
    }

    public Player(Pos<D> corner1, Pos<D> corner2) {
        super(corner1, corner2);
    }

    public Player(float x1, float x2, float y1, float y2) {
        super(x1, x1, y1, y2);
    }

    public Player(float x1, float x2, float y1, float y2, float z1, float z2) {
        super(x1, x2, y1, y2, z1, z2);
    }

    public Player(float x1, float x2, float y1, float y2, float z1, float z2, float special) {
        super(x1, x2, y1, y2, z1, z2, special);
    }
}
