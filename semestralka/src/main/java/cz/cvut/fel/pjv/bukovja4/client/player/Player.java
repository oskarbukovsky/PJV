package cz.cvut.fel.pjv.bukovja4.client.player;

import cz.cvut.fel.pjv.bukovja4.utils.engine.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.Dim;

public class Player<D extends Dim> {
    public Pos<D> position;
    public Vector<D> viewAngle;
    public float fov;

    public Player(Pos<D> position, Vector<D> viewAngle, float fov) {
        this.position = position;
        this.viewAngle = viewAngle;
        this.fov = fov;
    }
}
