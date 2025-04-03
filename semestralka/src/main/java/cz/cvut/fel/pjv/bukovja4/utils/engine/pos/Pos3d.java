package cz.cvut.fel.pjv.bukovja4.utils.engine.pos;

import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.Dim3d;

public class Pos3d implements Pos<Dim3d> {
    public float x;
    public float y;
    public float z;
    public float special;

    public Pos3d(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.special = 0;
    }

    public Pos3d(float x, float y, float z, float special) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.special = special;
    }

    public float distance(float x, float y, float z) {
        return (float) Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) + Math.pow(this.z - z, 2));
    }

    public float distance(Pos3d pos) {
        if (this.special == 0 && pos.special == 0) {
            return (float) Math
                    .sqrt(Math.pow(this.x - pos.x, 2) + Math.pow(this.y - pos.y, 2) + Math.pow(this.z - pos.z, 2));
        } else {
            return (float) Math.sqrt(Math.pow(this.x - pos.x, 2) + Math.pow(this.y - pos.y, 2)
                    + Math.pow(this.z - pos.z, 2) + Math.pow(this.special - pos.special, 2));
        }
    }
}
