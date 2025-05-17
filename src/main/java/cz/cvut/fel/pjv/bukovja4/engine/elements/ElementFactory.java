package cz.cvut.fel.pjv.bukovja4.engine.elements;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.*;

/**
 * Factory for creating game elements based on dimension and type.
 * Provides functionality to instantiate different elements with appropriate
 * dimensions.
 * 
 * @param <D> The dimensional type for created elements
 */
public class ElementFactory<D extends Dim> {
    /** Pre-configured factory for creating 2D elements */
    public static final ElementFactory<Dim2d> Factory2d = new ElementFactory<>();
    /** Pre-configured factory for creating 3D elements */
    public static final ElementFactory<Dim3d> Factory3d = new ElementFactory<>();

    /**
     * Creates a new element factory
     */
    public ElementFactory() {
    }

    /**
     * Gets the appropriate factory for the specified dimension
     * 
     * @param dim Dimension (1D, 2D, 3D, etc.)
     * @return Appropriate element factory for the dimension
     * @throws IllegalArgumentException if dimension is not supported
     */
    @SuppressWarnings("rawtypes")
    public static ElementFactory getFactory(int dim) throws IllegalArgumentException {
        return switch (dim) {
            case 1 -> new ElementFactory<Dim1d>();
            case 2 -> new ElementFactory<Dim2d>();
            case 3 -> new ElementFactory<Dim3d>();
            case 4 -> new ElementFactory<Dim4d>();
            default -> throw new IllegalArgumentException("Invalid dimension: " + dim);
        };
    }

    /**
     * Creates a game element of specified type with given bounding box
     * 
     * @param <E>         Type of element to create
     * @param elementType The element type enum value
     * @param box         The bounding box for the element
     * @return The newly created element
     * @throws Throwable if element creation fails
     * @see BaseElement
     */
    @SuppressWarnings("unchecked")
    public <E extends BaseElement<D>> E create(ElementTypes elementType, Box<D> box)
            throws Throwable {
        try {
            return (E) elementType.getElementClass().getConstructor(Box.class).newInstance(box);
        } catch (Exception ex) {
            LOG.error("Error creating element " + elementType.name(), ex);
        }
        return null;
    }

    /**
     * Creates a game element of specified type with two positions
     * 
     * @param <E>         Type of element to create
     * @param elementType The element type enum value
     * @param position1   First corner position
     * @param position2   Second corner position
     * @return The newly created element
     * @throws Throwable if element creation fails
     * @see #create(ElementTypes, Box)
     */
    public <E extends BaseElement<D>> E create(ElementTypes elementType, Pos<D> position1, Pos<D> position2)
            throws Throwable {
        return this.create(elementType, new Box<D>(position1, position2));
    }

    /**
     * Creates a 2D game element of specified type
     * 
     * @param <E>         Type of element to create
     * @param elementType The element type enum value
     * @param x1          First x coordinate
     * @param x2          Second x coordinate
     * @param y1          First y coordinate
     * @param y2          Second y coordinate
     * @return The newly created element
     * @throws Throwable if element creation fails
     * @see #create(ElementTypes, Pos, Pos)
     */
    public <E extends BaseElement<D>> E create(ElementTypes elementType, float x1, float x2, float y1, float y2)
            throws Throwable {
        return this.create(elementType, new Box<D>(new Pos<D>(x1, y1), new Pos<D>(x2, y2)));
    }

    /**
     * Creates a 3D game element of specified type
     * 
     * @param <E>         Type of element to create
     * @param elementType The element type enum value
     * @param x1          First x coordinate
     * @param x2          Second x coordinate
     * @param y1          First y coordinate
     * @param y2          Second y coordinate
     * @param z1          First z coordinate
     * @param z2          Second z coordinate
     * @return The newly created element
     * @throws Throwable if element creation fails
     * @see #create(ElementTypes, Pos, Pos)
     */
    public <E extends BaseElement<D>> E create(ElementTypes elementType, float x1, float x2, float y1, float y2,
            float z1,
            float z2) throws Throwable {
        return this.create(elementType, new Box<D>(new Pos<D>(x1, y1, z1), new Pos<D>(x2, y2, z2)));
    }

    /**
     * Creates a 4D game element of specified type
     * 
     * @param <E>         Type of element to create
     * @param elementType The element type enum value
     * @param x1          First x coordinate
     * @param x2          Second x coordinate
     * @param y1          First y coordinate
     * @param y2          Second y coordinate
     * @param z1          First z coordinate
     * @param z2          Second z coordinate
     * @param special     Fourth dimension coordinate
     * @return The newly created element
     * @throws Throwable if element creation fails
     * @see #create(ElementTypes, Pos, Pos)
     */
    public <E extends BaseElement<D>> E create(ElementTypes elementType, float x1, float x2, float y1, float y2,
            float z1,
            float z2, float special) throws Throwable {
        return this.create(elementType, new Box<D>(new Pos<D>(x1, y1, z1, special), new Pos<D>(x2, y2, z2, special)));
    }
}
