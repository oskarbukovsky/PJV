package cz.cvut.fel.pjv.bukovja4.utils.engine.dim;

/**
 * Implementation for four-dimensional space in the game engine.
 * Used for advanced 4D game mechanics and hyperspatial positioning.
 * 
 * @see Dim The base dimensional interface
 * @see Dim3d For lower dimensionality
 */
public class Dim4d implements Dim {
    /**
     * Returns the dimension count for 4D space.
     * 
     * @return Always returns 4
     */
    @Override
    public int getDimensionCount() {
        return 4;
    }
}