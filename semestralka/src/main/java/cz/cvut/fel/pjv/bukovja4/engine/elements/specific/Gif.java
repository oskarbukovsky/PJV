package cz.cvut.fel.pjv.bukovja4.engine.elements.specific;

import java.net.URISyntaxException;
import java.util.ArrayList;

import cz.cvut.fel.pjv.bukovja4.engine.elements.BaseElement;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;
// import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
import cz.cvut.fel.pjv.bukovja4.utils.engine.*;

/**
 * Represents an Gif image element in the UI.
 * Extends {@link BaseElement} to provide Gif image display functionality.
 * 
 * @param <D> The dimensional type of the space the Gif exists in
 */
public class Gif<D extends Dim> extends BaseElement<D> {

    private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    private float scale = 1f;
    private int currentFrame = 0;
    private int timeOnScreenInMillis;
    private long lastFrameChangeTime = 0;

    /**
     * Renders the Gif to the screen.
     * Initialize Gif with texture
     */
    @Override
    // String[], int, (float)
    public void init(Object... args) {
        // LOG.warn("Typeof0: " + args[0].getClass().getSimpleName() + "-" + args[0] + ", Typeof1: " + args[1].getClass().getSimpleName() + "-" + args[1]
        //         + ", Typeof2: " + args[2].getClass().getSimpleName() +"-"+ args[2]);
        if (args.length < 2) {
            throw new IllegalArgumentException("Gif requires at least one argument: the texture to display.");
        }
        String[] textures = (String[]) args[0];
        this.timeOnScreenInMillis = (int) args[1];

        for (String texture : textures) {
            try {
                this.sprites.add(SpriteManager.loadSprite("imgs/gifs/" + texture));
            } catch (URISyntaxException e) {
                throw new RuntimeException("Failed to load gif texture: " + texture, e);
            } catch (NullPointerException e) {
                throw new RuntimeException("Failed to load gif texture: " + texture, e);
            }
        }

        if (args.length > 1) {
            this.scale = ((Number) args[2]).floatValue();
        }
    }

    /**
     * Renders the Gif to the screen
     */
    @Override
    public void render() {
        this.sprites.get(currentFrame).draw(bounds.x1, bounds.y1, this.scale);
    }

    /**
     * Updates the label state for the current frame
     */
    @Override
    public void tick() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameChangeTime >= timeOnScreenInMillis) {
            currentFrame = (currentFrame + 1) % sprites.size();
            lastFrameChangeTime = currentTime;
        }
    }

    /**
     * Creates an Gif with specified bounding box
     * 
     * @param bounds The predefined bounding box
     * @see BaseElement#BaseElement(Box)
     */
    public Gif(Box<D> bounds) {
        super(bounds);
    }

    /**
     * Creates an Gif using two corner positions
     * 
     * @param corner1 First corner position
     * @param corner2 Second corner position
     * @see BaseElement#BaseElement(Pos, Pos)
     */
    public Gif(Pos<D> corner1, Pos<D> corner2) {
        super(corner1, corner2);
    }

    /**
     * Creates a 2D Gif using coordinate values
     * 
     * @param x1 First x coordinate
     * @param x2 Second x coordinate
     * @param y1 First y coordinate
     * @param y2 Second y coordinate
     * @see BaseElement#BaseElement(float, float, float, float)
     */
    public Gif(float x1, float x2, float y1, float y2) {
        super(x1, x1, y1, y2);
    }

    /**
     * Creates a 3D Gif using coordinate values
     * 
     * @param x1 First x coordinate
     * @param x2 Second x coordinate
     * @param y1 First y coordinate
     * @param y2 Second y coordinate
     * @param z1 First z coordinate
     * @param z2 Second z coordinate
     * @see BaseElement#BaseElement(float, float, float, float, float, float)
     */
    public Gif(float x1, float x2, float y1, float y2, float z1, float z2) {
        super(x1, x2, y1, y2, z1, z2);
    }

    /**
     * Creates a 4D Gif using coordinate values
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
    public Gif(float x1, float x2, float y1, float y2, float z1, float z2, float special) {
        super(x1, x2, y1, y2, z1, z2, special);
    }
}
