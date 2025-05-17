package cz.cvut.fel.pjv.bukovja4.utils.engine.dim;

/**
 * Implementation for three-dimensional space in the game engine.
 * Used for positioning and rendering objects in 3D environments.
 * 
 * @see Dim The base dimensional interface
 * @see Dim2d For lower dimensionality
 * @see Dim4d For higher dimensionality
 */
public class Dim3d implements Dim {
    /**
     * Returns the dimension count for 3D space.
     * 
     * @return Always returns 3
     */
    @Override
    public int getDimensionCount() {
        return 3;
    }
}