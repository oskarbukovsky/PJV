package cz.cvut.fel.pjv.bukovja4.utils.engine.box;

import java.util.ArrayList;

import cz.cvut.fel.pjv.bukovja4.utils.engine.pos.Pos3d;

public class Box3d extends Box2d {
    public float z1;
    public float z2;

    public float special;

    private float depth;

    public float getDepth() {
        return this.depth;
    }

    public ArrayList<Pos3d> getCorners3d() {
        ArrayList<Pos3d> corners = new ArrayList<Pos3d>();
        corners.add(new Pos3d(x1, y1, z1, special));
        corners.add(new Pos3d(x2, y1, z1, special));

        corners.add(new Pos3d(x1, y2, z1, special));
        corners.add(new Pos3d(x2, y2, z1, special));

        corners.add(new Pos3d(x1, y1, z2, special));
        corners.add(new Pos3d(x2, y1, z2, special));

        corners.add(new Pos3d(x1, y2, z2, special));
        corners.add(new Pos3d(x2, y2, z2, special));

        return corners;
    }

    Box3d(float x1, float x2, float z1, float y1, float y2, float z2) {
        super(x1, x2, y1, y2);
        this.z1 = z1;
        this.z2 = z2;
        this.special = 0;
        this.depth = Math.abs(z2 - z1);
    }

    Box3d(float x1, float x2, float z1, float y1, float y2, float z2, float special) {
        super(x1, x2, y1, y2);
        this.z1 = z1;
        this.z2 = z2;
        this.special = special;
        this.depth = Math.abs(z2 - z1);
    }

    Box3d(Pos3d first, Pos3d second) {
        super(first, second);
        this.z1 = first.z;
        this.z2 = second.z;
        this.special = first.special;
        this.depth = Math.abs(z2 - z1);
    }

    Box3d(Pos3d first, Pos3d second, float special) {
        super(first, second);
        this.z1 = first.z;
        this.z2 = second.z;
        this.special = special;
        this.special = first.special;
        this.depth = Math.abs(z2 - z1);
    }
}
