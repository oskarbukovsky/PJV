package cz.cvut.fel.pjv.bukovja4.utils.engine;

import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;

/**
 * A generic vector class that can handle different dimensions.
 * Provides standard vector operations like addition, scaling, dot product, etc.
 * 
 * @param <D> The dimension type (Dim2d, Dim3d, etc.)
 * @see Dim
 */
public class Vector<D extends Dim> {
    /** Array storing the vector components */
    public float[] components;
    /** Number of dimensions in this vector */
    private final int dimensionCount;

    /**
     * Creates a zero vector with the specified number of dimensions.
     * 
     * @param dimensionCount Number of dimensions
     */
    public Vector(int dimensionCount) {
        this.dimensionCount = dimensionCount;
        this.components = new float[dimensionCount];
    }
    
    /**
     * Creates a vector with the specified components.
     * 
     * @param components The vector components
     */
    public Vector(float... components) {
        this.dimensionCount = components.length;
        this.components = components.clone();
    }
    
    /**
     * Gets the value of a specific vector component.
     * 
     * @param index Component index (0-based)
     * @return The value of the component at the specified index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public float getComponents(int index) {
        if (index < 0 || index >= dimensionCount) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Dimension: " + dimensionCount);
        }
        return components[index];
    }

    /**
     * Sets the value of a specific vector component.
     * 
     * @param index Component index (0-based)
     * @param value The new value for the component
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public void set(int index, float value) {
        if (index < 0 || index >= dimensionCount) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Dimension: " + dimensionCount);
        }
        components[index] = value;
    }

    /**
     * Gets the number of dimensions in this vector.
     * 
     * @return The dimension count
     */
    public int getDimensionCount() {
        return dimensionCount;
    }

    /**
     * Adds another vector to this one.
     * 
     * @param other The vector to add
     * @return A new vector that is the sum of this vector and the other
     * @throws IllegalArgumentException if vectors have different dimensions
     */
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

    /**
     * Subtracts another vector from this one.
     * 
     * @param other The vector to subtract
     * @return A new vector that is the difference of this vector and the other
     * @throws IllegalArgumentException if vectors have different dimensions
     */
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

    /**
     * Scales this vector by a scalar value.
     * 
     * @param scalar The scaling factor
     * @return A new vector that is this vector scaled by the factor
     */
    public Vector<D> scale(float scalar) {
        Vector<D> result = new Vector<>(dimensionCount);
        for (int i = 0; i < dimensionCount; i++) {
            result.components[i] = this.components[i] * scalar;
        }
        return result;
    }

    /**
     * Calculates the dot product of this vector and another.
     * 
     * @param other The other vector
     * @return The dot product value
     * @throws IllegalArgumentException if vectors have different dimensions
     * @see #magnitude() For related operation
     */
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

    /**
     * Calculates the magnitude (length) of this vector.
     * 
     * @return The vector magnitude
     * @see #normalize() For getting a unit vector in this direction
     */
    public float magnitude() {
        float sumOfSquares = 0;
        for (float component : components) {
            sumOfSquares += component * component;
        }
        return (float) Math.sqrt(sumOfSquares);
    }

    /**
     * Creates a unit vector in the same direction as this vector.
     * 
     * @return A new normalized vector with magnitude 1
     * @throws ArithmeticException if this is a zero vector
     * @see #magnitude() For getting the vector length
     */
    public Vector<D> normalize() {
        float mag = magnitude();
        if (mag == 0) {
            throw new ArithmeticException("Cannot normalize zero vector");
        }

        return scale(1.0f / mag);
    }

    /**
     * Calculates the cross product of this vector and another.
     * Only defined for 3D vectors.
     * 
     * @param other The other vector
     * @return A new vector that is the cross product result
     * @throws UnsupportedOperationException if either vector is not 3D
     */
    public Vector<D> cross(Vector<D> other) {
        if (this.dimensionCount != 3 || other.dimensionCount != 3) {
            throw new UnsupportedOperationException("Cross product only defined for 3D vectors");
        }

        return new Vector<>(
                this.components[1] * other.components[2] - this.components[2] * other.components[1],
                this.components[2] * other.components[0] - this.components[0] * other.components[2],
                this.components[0] * other.components[1] - this.components[1] * other.components[0]);
    }

    /**
     * Calculates the angle between this vector and another.
     * 
     * @param other The other vector
     * @return The angle in radians
     * @throws ArithmeticException if either vector is a zero vector
     * @see #dot(Vector) For related operation
     */
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

    /**
     * Returns a string representation of this vector.
     * Format: Vec<dimensionCount>(x, y, ...)
     * 
     * @return String representation of the vector
     */
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
