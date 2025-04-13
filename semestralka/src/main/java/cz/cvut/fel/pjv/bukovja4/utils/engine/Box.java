package cz.cvut.fel.pjv.bukovja4.utils.engine;

import java.util.ArrayList;

import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;

/**
 * Represents a box in n-dimensional space with two corner points.
 * Supports 2D, 3D, and 4D positioning with appropriate corner coordinate tracking.
 * 
 * @param <D> The dimension type for this box
 * @see Pos For positional representation of corners
 * @see Dim The base dimensional interface
 */
public class Box<D extends Dim> {
    /** First X coordinate */
    public float x1 = 0;
    /** First Y coordinate */
    public float y1 = 0;
    /** Second X coordinate */
    public float x2 = 0;
    /** Second Y coordinate */
    public float y2 = 0;
    /** First Z coordinate */
    public float z1 = 0;
    /** Second Z coordinate */
    public float z2 = 0;
    /** Fourth dimension coordinate */
    public float special = 0;

    /** Width of the box (difference between x2 and x1) */
    private float width = 0;

    /**
     * Gets the width of the box.
     * 
     * @return Width in the X dimension
     */
    public float getWidth() {
        return this.width;
    }

    /** Height of the box (difference between y2 and y1) */
    private float height = 0;

    /**
     * Gets the height of the box.
     * 
     * @return Height in the Y dimension
     */
    public float getHeight() {
        return this.height;
    }

    /** Depth of the box (difference between z2 and z1) */
    private float depth = 0;

    /**
     * Gets the depth of the box.
     * 
     * @return Depth in the Z dimension
     */
    public float getDepth() {
        return this.depth;
    }

    /**
     * Gets all 8 corners of the box in 4D space.
     * 
     * @return List of the 8 corner positions in 4D
     * @see Pos Represents each corner position
     */
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

    /**
     * Gets all 8 corners of the box in 3D space.
     * 
     * @return List of the 8 corner positions in 3D
     * @see Pos Represents each corner position
     */
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

    /**
     * Gets all 4 corners of the box in 2D space.
     * 
     * @return List of the 4 corner positions in 2D
     * @see Pos Represents each corner position
     */
    public ArrayList<Pos<Dim2d>> getCorners2d() {
        ArrayList<Pos<Dim2d>> corners = new ArrayList<Pos<Dim2d>>();
        corners.add(new Pos<Dim2d>(x1, y1));
        corners.add(new Pos<Dim2d>(x2, y1));

        corners.add(new Pos<Dim2d>(x1, y2));
        corners.add(new Pos<Dim2d>(x2, y2));
        return corners;
    }

    /**
     * Creates a box with specified coordinates in 4D space.
     * 
     * @param x1 First X coordinate
     * @param x2 Second X coordinate
     * @param y1 First Y coordinate
     * @param y2 Second Y coordinate
     * @param z1 First Z coordinate
     * @param z2 Second Z coordinate
     * @param special Fourth dimension coordinate
     */
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

    /**
     * Creates a box with specified coordinates in 3D space.
     * Fourth dimension (special) is set to 0.
     * 
     * @param x1 First X coordinate
     * @param x2 Second X coordinate
     * @param y1 First Y coordinate
     * @param y2 Second Y coordinate
     * @param z1 First Z coordinate
     * @param z2 Second Z coordinate
     * @see #Box(float, float, float, float, float, float, float) For 4D box creation
     */
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

    /**
     * Creates a box with specified coordinates in 2D space.
     * Z and special dimensions are set to 0.
     * 
     * @param x1 First X coordinate
     * @param x2 Second X coordinate
     * @param y1 First Y coordinate
     * @param y2 Second Y coordinate
     * @see #Box(float, float, float, float, float, float) For 3D box creation
     */
    public Box(float x1, float x2, float y1, float y2) {
        this.x1 = x1;
        this.x2 = x2;

        this.y1 = y1;
        this.y2 = y2;

        this.height = Math.abs(y2 - y1);
        this.depth = Math.abs(z2 - z1);
    }

    /**
     * Creates a box using two positions as corners.
     * The dimension of the box is determined by the dimension type D.
     * 
     * @param first First corner position
     * @param second Second corner position
     * @see Pos For the position representation
     */
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
