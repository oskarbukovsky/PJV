package cz.cvut.fel.pjv.bukovja4.utils.engine;

import java.util.ArrayList;

import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;

public class Box<D extends Dim> {
    public float x1 = 0;
    public float y1 = 0;

    public float x2 = 0;
    public float y2 = 0;

    public float z1 = 0;
    public float z2 = 0;

    public float special = 0;

    private float width = 0;

    public float getWidth() {
        return this.width;
    }

    private float height = 0;

    public float getHeight() {
        return this.height;
    }

    private float depth = 0;

    public float getDepth() {
        return this.depth;
    }

    public ArrayList<Pos<Dim4d>> getCorners4d() {
        ArrayList<Pos<Dim4d>> corners = new ArrayList<Pos<Dim4d>>();
        corners.add(new Pos<Dim4d>(x1, y1, z1, special));
        corners.add(new Pos<Dim4d>(x2, y1, z1, special));

        corners.add(new Pos<Dim4d>(x1, y2, z1, special));
        corners.add(new Pos<Dim4d>(x2, y2, z1, special));

        corners.add(new Pos<Dim4d>(x1, y1, z2, special));
        corners.add(new Pos<Dim4d>(x2, y1, z2, special));

        corners.add(new Pos<Dim4d>(x1, y2, z2, special));
        corners.add(new Pos<Dim4d>(x2, y2, z2, special));

        return corners;
    }

    public ArrayList<Pos<Dim3d>> getCorners3d() {
        ArrayList<Pos<Dim3d>> corners = new ArrayList<Pos<Dim3d>>();
        corners.add(new Pos<Dim3d>(x1, y1, z1));
        corners.add(new Pos<Dim3d>(x2, y1, z1));

        corners.add(new Pos<Dim3d>(x1, y2, z1));
        corners.add(new Pos<Dim3d>(x2, y2, z1));

        corners.add(new Pos<Dim3d>(x1, y1, z2));
        corners.add(new Pos<Dim3d>(x2, y1, z2));

        corners.add(new Pos<Dim3d>(x1, y2, z2));
        corners.add(new Pos<Dim3d>(x2, y2, z2));

        return corners;
    }

    public ArrayList<Pos<Dim2d>> getCorners2d() {
        ArrayList<Pos<Dim2d>> corners = new ArrayList<Pos<Dim2d>>();
        corners.add(new Pos<Dim2d>(x1, y1));
        corners.add(new Pos<Dim2d>(x2, y1));

        corners.add(new Pos<Dim2d>(x1, y2));
        corners.add(new Pos<Dim2d>(x2, y2));
        return corners;
    }

    public Box(float x1, float x2, float y1, float y2, float z1, float z2, float special) {
        this.x1 = x1;
        this.x2 = x2;

        this.y1 = y1;
        this.y2 = y2;

        this.z1 = z1;
        this.z2 = z2;

        this.special = special;
        this.width = Math.abs(x2 - x1);
        this.height = Math.abs(y2 - y1);
        this.depth = Math.abs(z2 - z1);
    }

    public Box(float x1, float x2, float y1, float y2, float z1, float z2) {
        this.x1 = x1;
        this.x2 = x2;

        this.y1 = y1;
        this.y2 = y2;

        this.z1 = z1;
        this.z2 = z2;

        this.special = 0;
        this.width = Math.abs(x2 - x1);
        this.height = Math.abs(y2 - y1);
        this.depth = Math.abs(z2 - z1);
    }

    public Box(float x1, float x2, float y1, float y2) {
        this.x1 = x1;
        this.x2 = x2;

        this.y1 = y1;
        this.y2 = y2;

        this.height = Math.abs(y2 - y1);
        this.depth = Math.abs(z2 - z1);
    }

    public Box(Pos<D> first, Pos<D> second) {
        this.x1 = first.x;
        this.y1 = first.y;

        this.x2 = second.x;
        this.y2 = second.y;

        this.z1 = first.z;
        this.z2 = second.z;

        this.special = first.special;
        this.width = Math.abs(x2 - x1);
        this.height = Math.abs(y2 - y1);
        this.depth = Math.abs(z2 - z1);
    }
}
