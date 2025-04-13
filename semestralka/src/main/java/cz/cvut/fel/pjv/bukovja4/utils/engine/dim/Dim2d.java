package cz.cvut.fel.pjv.bukovja4.utils.engine.dim;

/**
 * Implementation for two-dimensional space in the game engine.
 * Used for standard 2D plane positioning and UI elements.
 * 
 * @see Dim The base dimensional interface
 * @see Dim1d For lower dimensionality
 * @see Dim3d For higher dimensionality
 */
public class Dim2d implements Dim {
    /**
     * Returns the dimension count for 2D space.
     * 
     * @return Always returns 2
     */
    @Override
    public int getDimensionCount() {
        return 2;
    }
}