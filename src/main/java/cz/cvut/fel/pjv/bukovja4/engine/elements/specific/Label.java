package cz.cvut.fel.pjv.bukovja4.engine.elements.specific;

import cz.cvut.fel.pjv.bukovja4.engine.elements.BaseElement;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.*;

/**
 * Represents a text label element in the UI.
 * Extends {@link BaseElement} to provide text display functionality.
 * 
 * @param <D> The dimensional type of the space the label exists in
 */
public class Label<D extends Dim> extends BaseElement<D> {

    private String text;

    private float scale = 1f;

    /**
     * Renders the label to the screen.
     * Initialize label with text
     */
    @Override
    public void init(Object... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Label requires at least one argument: the text to display.");
        }
        text = (String) args[0];

        if (args.length > 1) {
            this.scale = (float) args[1];
        }
    }

    /**
     * Renders the label to the screen
     */
    @Override
    public void render() {
        // Draw the text at the specified position
        TextUtils.drawText(text, (int) bounds.x1, (int) bounds.y1, this.scale, 0xFFFFFF, 0xff);
    }

    /**
     * Updates the label state for the current frame
     */
    @Override
    public void tick() {

    }

    /**
     * Creates a label with specified bounding box
     * 
     * @param bounds The predefined bounding box
     * @see BaseElement#BaseElement(Box)
     */
    public Label(Box<D> bounds) {
        super(bounds);
    }

    /**
     * Creates a label using two corner positions
     * 
     * @param corner1 First corner position
     * @param corner2 Second corner position
     * @see BaseElement#BaseElement(Pos, Pos)
     */
    public Label(Pos<D> corner1, Pos<D> corner2) {
        super(corner1, corner2);
    }

    /**
     * Creates a 2D label using coordinate values
     * 
     * @param x1 First x coordinate
     * @param x2 Second x coordinate
     * @param y1 First y coordinate
     * @param y2 Second y coordinate
     * @see BaseElement#BaseElement(float, float, float, float)
     */
    public Label(float x1, float x2, float y1, float y2) {
        super(x1, x1, y1, y2);
    }

    /**
     * Creates a 3D label using coordinate values
     * 
     * @param x1 First x coordinate
     * @param x2 Second x coordinate
     * @param y1 First y coordinate
     * @param y2 Second y coordinate
     * @param z1 First z coordinate
     * @param z2 Second z coordinate
     * @see BaseElement#BaseElement(float, float, float, float, float, float)
     */
    public Label(float x1, float x2, float y1, float y2, float z1, float z2) {
        super(x1, x2, y1, y2, z1, z2);
    }

    /**
     * Creates a 4D label using coordinate values
     * 
     * @param x1      First x coordinate
     * @param x2      Second x coordinate
     * @param y1      First y coordinate
     * @param y2      Second y coordinate
     * @param z1      First z coordinate
     * @param z2      Second z coordinate
     * @param special Fourth dimension coordinate
     * @see BaseElement#BaseElement(float, float, float, float, float, float, float)
     */
    public Label(float x1, float x2, float y1, float y2, float z1, float z2, float special) {
        super(x1, x2, y1, y2, z1, z2, special);
    }
}
