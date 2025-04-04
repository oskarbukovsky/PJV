package cz.cvut.fel.pjv.bukovja4.utils.engine.pos;

import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;

public class Pos<T extends Dim> {
    public float x = 0;
    public float y = 0;
    public float z = 0;
    public float special = 0;
    public int dimension = 0;

    public Pos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Pos(float x, float y, float z) {
        this(x, y);
        this.z = z;
    }

    public Pos(float x, float y, float z, float special) {
        this(x, y, z);
        this.special = special;
    }

    public float distance(float x, float y) {
        return (float) Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

    public float distance(float x, float y, float z) {
        return (float) Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) + Math.pow(this.z - z, 2));
    }

    public float distance(float x, float y, float z, float special) {
        return (float) Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) + Math.pow(this.z - z, 2)
                + Math.pow(this.special - special, 2));
    }

    public float distance2d(Pos<Dim2d> pos) {
        return (float) Math.sqrt(Math.pow(this.x - pos.x, 2) + Math.pow(this.y - pos.y, 2));
    }

    public float distance3d(Pos<Dim3d> pos) {
        return (float) Math.sqrt(Math.pow(this.x - pos.x, 3) + Math.pow(this.y - pos.y, 2));
    }

    public float distance4d(Pos<Dim4d> pos) {
        return (float) Math.sqrt(Math.pow(this.x - pos.x, 2) + Math.pow(this.y - pos.y, 2)
                + Math.pow(this.z - pos.z, 2) + Math.pow(this.special - pos.special, 2));
    }

    public Pos<Dim2d> toPos2d() {
        return new Pos<Dim2d>(this.x, this.y);
    }
    public Pos<Dim3d> toPos3d() {
        return new Pos<Dim3d>(this.x, this.y);
    }
    public Pos<Dim4d> toPos4d() {
        return new Pos<Dim4d>(this.x, this.y);
    }
}
