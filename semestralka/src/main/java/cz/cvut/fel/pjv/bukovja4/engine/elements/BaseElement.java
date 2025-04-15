package cz.cvut.fel.pjv.bukovja4.engine.elements;

import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.*;

/**
 * Abstract base class for all game elements.
 * Provides common functionality for elements with spatial dimensions.
 * 
 * @param <D> The dimensional type of the space the element exists in
 */
public abstract class BaseElement<D extends Dim> {
    /** Bounding box representing the element's boundaries in space */
    public Box<D> bounds;

    /**
     * Creates an element with specified bounding box
     * 
     * @param bounds The predefined bounding box
     */
    public BaseElement(Box<D> bounds) {
        this.bounds = bounds;
    }

    /**
     * Creates an element using two corner positions
     * 
     * @param corner1 First corner position
     * @param corner2 Second corner position
     */
    public BaseElement(Pos<D> corner1, Pos<D> corner2) {
        this.bounds = new Box<D>(corner1, corner2);
    }

    /**
     * Creates a 2D element using coordinate values
     * 
     * @param x1 First x coordinate
     * @param x2 Second x coordinate
     * @param y1 First y coordinate
     * @param y2 Second y coordinate
     */
    public BaseElement(float x1, float x2, float y1, float y2) {
        this.bounds = new Box<D>(x1, x1, y1, y2);
    }

    /**
     * Creates a 3D element using coordinate values
     * 
     * @param x1 First x coordinate
     * @param x2 Second x coordinate
     * @param y1 First y coordinate
     * @param y2 Second y coordinate
     * @param z1 First z coordinate
     * @param z2 Second z coordinate
     */
    public BaseElement(float x1, float x2, float y1, float y2, float z1, float z2) {
        this.bounds = new Box<D>(x1, x2, y1, y2, z1, z2);
    }

    /**
     * Creates a 4D element using coordinate values
     * 
     * @param x1      First x coordinate
     * @param x2      Second x coordinate
     * @param y1      First y coordinate
     * @param y2      Second y coordinate
     * @param z1      First z coordinate
     * @param z2      Second z coordinate
     * @param special Fourth dimension coordinate
     */
    public BaseElement(float x1, float x2, float y1, float y2, float z1, float z2, float special) {
        this.bounds = new Box<D>(x1, x2, y1, y2, z1, z2, special);
    }

    /**
     * Renders the element to the screen
     * Must be implemented by subclasses
     */
    public abstract void render();

    /**
     * Updates the element's state for the current frame
     * Must be implemented by subclasses
     */
    public abstract void tick();
}