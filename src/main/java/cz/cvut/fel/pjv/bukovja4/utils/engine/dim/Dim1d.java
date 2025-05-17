package cz.cvut.fel.pjv.bukovja4.utils.engine.dim;

/**
 * Implementation for one-dimensional space in the game engine.
 * Used for linear positioning and simple line-based game elements.
 * 
 * @see Dim The base dimensional interface
 * @see Dim2d For higher dimensionality
 */
public class Dim1d implements Dim {
    /**
     * Returns the dimension count for 1D space.
     * 
     * @return Always returns 1
     */
    @Override
    public int getDimensionCount() {
        return 1;
    }
}