package cz.cvut.fel.pjv.bukovja4.engine.elements.specific;

import java.net.URISyntaxException;

import cz.cvut.fel.pjv.bukovja4.engine.elements.BaseElement;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
import cz.cvut.fel.pjv.bukovja4.utils.engine.*;

/**
 * Represents an image element in the UI.
 * Extends {@link BaseElement} to provide image display functionality.
 * 
 * @param <D> The dimensional type of the space the image exists in
 */
public class Image<D extends Dim> extends BaseElement<D> {

    private Sprite sprite;
    private float scale = 1f;

    /**
     * Renders the image to the screen.
     * Initialize image with texture
     */
    @Override
    public void init(Object... args){
        // LOG.warn("Image.init()");
        if (args.length == 0) {
            throw new IllegalArgumentException("Image requires at least one argument: the texture to display.");
        }
        String texture = (String) args[0];
        try {
            this.sprite = SpriteManager.loadSprite(texture);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to load image texture: " + texture, e);
        } catch (NullPointerException e) {
            throw new RuntimeException("Failed to load image texture: " + texture, e);
        }

        if (args.length > 1) {
            this.scale = (float) args[1];
        }
    }

    /**
     * Renders the image to the screen
     */
    @Override
    public void render() {
        try {
            this.sprite.draw(bounds.x1, bounds.y1, this.scale);
        } catch (NullPointerException e) {
            LOG.error("Missing image texture", e);
        }
    }

    /**
     * Updates the label state for the current frame
     */
    @Override
    public void tick() {

    }

    /**
     * Creates an image with specified bounding box
     * 
     * @param bounds The predefined bounding box
     * @see BaseElement#BaseElement(Box)
     */
    public Image(Box<D> bounds) {
        super(bounds);
    }

    /**
     * Creates an image using two corner positions
     * 
     * @param corner1 First corner position
     * @param corner2 Second corner position
     * @see BaseElement#BaseElement(Pos, Pos)
     */
    public Image(Pos<D> corner1, Pos<D> corner2) {
        super(corner1, corner2);
    }

    /**
     * Creates a 2D image using coordinate values
     * 
     * @param x1 First x coordinate
     * @param x2 Second x coordinate
     * @param y1 First y coordinate
     * @param y2 Second y coordinate
     * @see BaseElement#BaseElement(float, float, float, float)
     */
    public Image(float x1, float x2, float y1, float y2) {
        super(x1, x1, y1, y2);
    }

    /**
     * Creates a 3D image using coordinate values
     * 
     * @param x1 First x coordinate
     * @param x2 Second x coordinate
     * @param y1 First y coordinate
     * @param y2 Second y coordinate
     * @param z1 First z coordinate
     * @param z2 Second z coordinate
     * @see BaseElement#BaseElement(float, float, float, float, float, float)
     */
    public Image(float x1, float x2, float y1, float y2, float z1, float z2) {
        super(x1, x2, y1, y2, z1, z2);
    }

    /**
     * Creates a 4D image using coordinate values
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
    public Image(float x1, float x2, float y1, float y2, float z1, float z2, float special) {
        super(x1, x2, y1, y2, z1, z2, special);
    }
}
