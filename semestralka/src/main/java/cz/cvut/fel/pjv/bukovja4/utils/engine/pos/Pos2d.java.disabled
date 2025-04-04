package cz.cvut.fel.pjv.bukovja4.utils.engine.pos;

import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.Dim2d;

public class Pos2d implements Pos<Dim2d> {
    public float x;
    public float y;

    public Pos2d(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float distance(float x, float y) {
        return (float) Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

    public float distance(Pos2d pos) {
        return (float) Math.sqrt(Math.pow(this.x - pos.x, 2) + Math.pow(this.y - pos.y, 2));
    }
}
