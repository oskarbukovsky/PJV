package cz.cvut.fel.pjv.bukovja4.utils.engine.dim;

/**
 * Base interface for dimensional spaces in the game engine.
 * Provides a common interface for all dimensional types (1D, 2D, 3D, etc.).
 */
public interface Dim {
    /**
     * Gets the number of dimensions in this dimensional space.
     * 
     * @return The dimension count (1 for 1D, 2 for 2D, etc.)
     */
    int getDimensionCount();
}