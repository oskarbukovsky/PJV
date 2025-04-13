package cz.cvut.fel.pjv.bukovja4.utils.engine;

import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;

/**
 * Represents a position in n-dimensional space.
 * Supports 1D, 2D, 3D, and 4D positioning with appropriate dimension tracking.
 * 
 * @param <D> The dimension type for this position
 * @see Vector For vector operations in the same dimensional space
 * @see Dim The base dimensional interface
 */
public class Pos<D extends Dim> {
    /** X coordinate (first dimension) */
    public float x = 0;
    /** Y coordinate (second dimension) */
    public float y = 0;
    /** Z coordinate (third dimension) */
    public float z = 0;
    /** Special coordinate (fourth dimension) */
    public float special = 0;
    /** Number of dimensions in this position */
    public int dimension = 0;

    /**
     * Creates a 1D position with the specified X coordinate.
     * 
     * @param x The X coordinate
     */
    public Pos(float x) {
        this.x = x;
        this.dimension = 1;
    }
    
    /**
     * Creates a 2D position with the specified X and Y coordinates.
     * 
     * @param x The X coordinate
     * @param y The Y coordinate
     */
    public Pos(float x, float y) {
        this(x);
        this.y = y;
        this.dimension = 2;
    }

    /**
     * Creates a 3D position with the specified X, Y, and Z coordinates.
     * 
     * @param x The X coordinate
     * @param y The Y coordinate
     * @param z The Z coordinate
     */
    public Pos(float x, float y, float z) {
        this(x, y);
        this.z = z;
        this.dimension = 3;
    }

    /**
     * Creates a 4D position with the specified X, Y, Z, and special coordinates.
     * 
     * @param x The X coordinate
     * @param y The Y coordinate
     * @param z The Z coordinate
     * @param special The fourth dimensional coordinate
     */
    public Pos(float x, float y, float z, float special) {
        this(x, y, z);
        this.special = special;
        this.dimension = 4;
    }

    /**
     * Calculates the 2D Euclidean distance to another point.
     * 
     * @param x X coordinate of the other point
     * @param y Y coordinate of the other point
     * @return The distance between this position and the specified coordinates
     */
    public float distance(float x, float y) {
        return (float) Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

    /**
     * Calculates the 3D Euclidean distance to another point.
     * 
     * @param x X coordinate of the other point
     * @param y Y coordinate of the other point
     * @param z Z coordinate of the other point
     * @return The distance between this position and the specified coordinates
     */
    public float distance(float x, float y, float z) {
        return (float) Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) + Math.pow(this.z - z, 2));
    }

    /**
     * Calculates the 4D Euclidean distance to another point.
     * 
     * @param x X coordinate of the other point
     * @param y Y coordinate of the other point
     * @param z Z coordinate of the other point
     * @param special Fourth dimensional coordinate of the other point
     * @return The distance between this position and the specified coordinates
     */
    public float distance(float x, float y, float z, float special) {
        return (float) Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) + Math.pow(this.z - z, 2)
                + Math.pow(this.special - special, 2));
    }

    /**
     * Calculates the 2D Euclidean distance to another position.
     * 
     * @param pos The other 2D position
     * @return The distance between this position and the other position
     * @see #distance(float, float) For distance to raw coordinates
     */
    public float distance2d(Pos<Dim2d> pos) {
        return (float) Math.sqrt(Math.pow(this.x - pos.x, 2) + Math.pow(this.y - pos.y, 2));
    }

    /**
     * Calculates the 3D Euclidean distance to another position.
     * 
     * @param pos The other 3D position
     * @return The distance between this position and the other position
     * @see #distance(float, float, float) For distance to raw coordinates
     */
    public float distance3d(Pos<Dim3d> pos) {
        return (float) Math.sqrt(Math.pow(this.x - pos.x, 3) + Math.pow(this.y - pos.y, 2));
    }

    /**
     * Calculates the 4D Euclidean distance to another position.
     * 
     * @param pos The other 4D position
     * @return The distance between this position and the other position
     * @see #distance(float, float, float, float) For distance to raw coordinates
     */
    public float distance4d(Pos<Dim4d> pos) {
        return (float) Math.sqrt(Math.pow(this.x - pos.x, 2) + Math.pow(this.y - pos.y, 2)
                + Math.pow(this.z - pos.z, 2) + Math.pow(this.special - pos.special, 2));
    }

    /**
     * Converts this position to a 2D position.
     * 
     * @return A new 2D position with this position's X and Y coordinates
     */
    public Pos<Dim2d> toPos2d() {
        return new Pos<Dim2d>(this.x, this.y);
    }

    /**
     * Converts this position to a 3D position.
     * 
     * @return A new 3D position with this position's coordinates
     */
    public Pos<Dim3d> toPos3d() {
        return new Pos<Dim3d>(this.x, this.y);
    }

    /**
     * Converts this position to a 4D position.
     * 
     * @return A new 4D position with this position's coordinates
     */
    public Pos<Dim4d> toPos4d() {
        return new Pos<Dim4d>(this.x, this.y);
    }

    /**
     * Returns a string representation of this position.
     * Format varies based on dimension: {x}, {x,y}, {x,y,z}, or {x,y,z,special}.
     * 
     * @return String representation of the position
     */
    @Override
    public String toString() {
        if (this.dimension == 0) {
            return "{}";
        } else if (this.dimension == 1) {
            return String.format("{%f}", this.x);
        } else if (this.dimension == 2) {
            return String.format("{%f,%f}", this.x, this.y);
        } else if (this.dimension == 3) {
            return String.format("{%f,%f,%f}", this.x, this.y, this.z);
        } 
        return String.format("{%f,%f,%f,%f}", this.x, this.y, this.z, this.special);
    }
}
