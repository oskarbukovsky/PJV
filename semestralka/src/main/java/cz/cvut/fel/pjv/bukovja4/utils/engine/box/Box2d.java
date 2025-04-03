package cz.cvut.fel.pjv.bukovja4.utils.engine.box;

import java.util.ArrayList;

import cz.cvut.fel.pjv.bukovja4.utils.engine.pos.Pos2d;

public class Box2d {
    public float x1;
    public float y1;

    public float x2;
    public float y2;

    private float width;

    public float getWidth() {
        return this.width;
    }

    private float height;

    public float getHeight() {
        return this.height;
    }

    public ArrayList<Pos2d> getCorners2d() {
        ArrayList<Pos2d> corners = new ArrayList<Pos2d>();

        corners.add(new Pos2d(x1, y1));
        corners.add(new Pos2d(x2, y1));
        corners.add(new Pos2d(x1, y2));
        corners.add(new Pos2d(x2, y2));

        return corners;
    }

    Box2d(float x1, float x2, float y1, float y2) {
        this.x1 = x1;
        this.x2 = x2;

        this.y1 = y1;
        this.y2 = y2;

        this.width = Math.abs(x2 - x1);
        this.height = Math.abs(y2 - y1);
    }

    Box2d(Pos2d first, Pos2d second) {
        this.x1 = first.x;
        this.y1 = first.y;

        this.x2 = second.x;
        this.y2 = second.y;

        this.width = Math.abs(x2 - x1);
        this.height = Math.abs(y2 - y1);
    }
}
