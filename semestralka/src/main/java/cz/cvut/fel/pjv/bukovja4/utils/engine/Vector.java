package cz.cvut.fel.pjv.bukovja4.utils.engine;

import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;

/**
 * A generic vector class that can handle different dimensions.
 * 
 * @param <D> The dimension type (Dim2d, Dim3d, etc.)
 */
public class Vector<D extends Dim> {
    public float[] components;
    private final int dimensionCount;

    public Vector(int dimensionCount) {
        this.dimensionCount = dimensionCount;
        this.components = new float[dimensionCount];
    }
    public Vector(float... components) {
        this.dimensionCount = components.length;
        this.components = components.clone();
    }
    public float getComponents(int index) {
        if (index < 0 || index >= dimensionCount) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Dimension: " + dimensionCount);
        }
        return components[index];
    }

    public void set(int index, float value) {
        if (index < 0 || index >= dimensionCount) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Dimension: " + dimensionCount);
        }
        components[index] = value;
    }

    public int getDimensionCount() {
        return dimensionCount;
    }

    public Vector<D> add(Vector<D> other) throws IllegalArgumentException {
        if (this.dimensionCount != other.dimensionCount) {
            throw new IllegalArgumentException("Vectors must have same dimension");
        }

        Vector<D> result = new Vector<>(dimensionCount);
        for (int i = 0; i < dimensionCount; i++) {
            result.components[i] = this.components[i] + other.components[i];
        }
        return result;
    }

    public Vector<D> subtract(Vector<D> other) throws IllegalArgumentException {
        if (this.dimensionCount != other.dimensionCount) {
            throw new IllegalArgumentException("Vectors must have same dimension");
        }

        Vector<D> result = new Vector<>(dimensionCount);
        for (int i = 0; i < dimensionCount; i++) {
            result.components[i] = this.components[i] - other.components[i];
        }
        return result;
    }

    public Vector<D> scale(float scalar) {
        Vector<D> result = new Vector<>(dimensionCount);
        for (int i = 0; i < dimensionCount; i++) {
            result.components[i] = this.components[i] * scalar;
        }
        return result;
    }

    public float dot(Vector<D> other) {
        if (this.dimensionCount != other.dimensionCount) {
            throw new IllegalArgumentException("Vectors must have same dimension");
        }

        float result = 0;
        for (int i = 0; i < dimensionCount; i++) {
            result += this.components[i] * other.components[i];
        }
        return result;
    }

    public float magnitude() {
        float sumOfSquares = 0;
        for (float component : components) {
            sumOfSquares += component * component;
        }
        return (float) Math.sqrt(sumOfSquares);
    }

    public Vector<D> normalize() {
        float mag = magnitude();
        if (mag == 0) {
            throw new ArithmeticException("Cannot normalize zero vector");
        }

        return scale(1.0f / mag);
    }

    public Vector<D> cross(Vector<D> other) {
        if (this.dimensionCount != 3 || other.dimensionCount != 3) {
            throw new UnsupportedOperationException("Cross product only defined for 3D vectors");
        }

        return new Vector<>(
                this.components[1] * other.components[2] - this.components[2] * other.components[1],
                this.components[2] * other.components[0] - this.components[0] * other.components[2],
                this.components[0] * other.components[1] - this.components[1] * other.components[0]);
    }

    public float angle(Vector<D> other) {
        float dot = this.dot(other);
        float magProduct = this.magnitude() * other.magnitude();

        if (magProduct == 0) {
            throw new ArithmeticException("Cannot calculate angle with zero vector");
        }

        // Clamp to avoid floating point errors
        float cosAngle = Math.max(-1.0f, Math.min(1.0f, dot / magProduct));
        return (float) Math.acos(cosAngle);
    }


    // @SuppressWarnings("unchecked")
    // public static <D extends Dim> Vector<D> create2D(float x, float y) {
    //     return (Vector<D>) new Vector<Dim2d>(x, y);
    // }


    // @SuppressWarnings("unchecked")
    // public static <D extends Dim> Vector<D> create3D(float x, float y, float z) {
    //     return (Vector<D>) new Vector<Dim3d>(x, y, z);
    // }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Vec<" + this.dimensionCount + ">(");
        for (int i = 0; i < this.dimensionCount; i++) {
            sb.append(components[i]);
            if (i < this.dimensionCount - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
