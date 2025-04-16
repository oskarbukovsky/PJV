package cz.cvut.fel.pjv.bukovja4.engine.elements.specific;

import static org.lwjgl.opengl.GL11.*;

import cz.cvut.fel.pjv.bukovja4.engine.elements.BaseElement;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
import cz.cvut.fel.pjv.bukovja4.utils.engine.*;

/**
 * Represents a clickable button in the UI.
 * Extends {@link BaseElement} to provide button functionality.
 * 
 * @param <D> The dimensional type of the space the button exists in
 */
public class Button<D extends Dim> extends BaseElement<D> {

    /**
     * Renders the button to the screen.
     * Implementation of the abstract method from {@link BaseElement}.
     */
    @Override
    public void render() {
        // Example: Draw a simple filled rectangle as the button background
        // You can customize the color and add border/text as needed

        // Set color (RGBA) for the button background
        glColor4f(0.2f, 1f, 0.2f, 1.0f); // ~Lime

        // Draw filled rectangle using OpenGL immediate mode
        glBegin(GL_QUADS);
        glVertex2f(bounds.x1, bounds.y1);
        glVertex2f(bounds.x2, bounds.y1);
        glVertex2f(bounds.x2, bounds.y2);
        glVertex2f(bounds.x1, bounds.y2);
        glEnd();

        // Optional: Draw border
        // glColor4f(1f, 1f, 1f, 1f); // White border
        // glLineWidth(2f);
        // glBegin(GL_LINE_LOOP);
        // glVertex2f(bounds.x1, bounds.y1);
        // glVertex2f(bounds.x2, bounds.y1);
        // glVertex2f(bounds.x2, bounds.y2);
        // glVertex2f(bounds.x1, bounds.y2);
        // glEnd();
        // LOG.debug("Button rendered at: " + bounds.toString());
    }

    /**
     * Updates the label state for the current frame
     */
    @Override
    public void tick() {

    }

    /**
     * Creates a button with specified bounding box.
     * 
     * @param bounds The predefined bounding box
     */
    public Button(Box<D> bounds) {
        super(bounds);
    }

    /**
     * Creates a button using two corner positions.
     * 
     * @param corner1 First corner position
     * @param corner2 Second corner position
     * @see BaseElement#BaseElement(Pos, Pos)
     */
    public Button(Pos<D> corner1, Pos<D> corner2) {
        super(corner1, corner2);
    }

    /**
     * Creates a 2D button using coordinate values.
     * 
     * @param x1 First x coordinate
     * @param x2 Second x coordinate
     * @param y1 First y coordinate
     * @param y2 Second y coordinate
     * @see BaseElement#BaseElement(float, float, float, float)
     */
    public Button(float x1, float x2, float y1, float y2) {
        super(x1, x1, y1, y2);
    }

    /**
     * Creates a 3D button using coordinate values.
     * 
     * @param x1 First x coordinate
     * @param x2 Second x coordinate
     * @param y1 First y coordinate
     * @param y2 Second y coordinate
     * @param z1 First z coordinate
     * @param z2 Second z coordinate
     * @see BaseElement#BaseElement(float, float, float, float, float, float)
     */
    public Button(float x1, float x2, float y1, float y2, float z1, float z2) {
        super(x1, x2, y1, y2, z1, z2);
    }

    /**
     * Creates a 4D button using coordinate values.
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
    public Button(float x1, float x2, float y1, float y2, float z1, float z2, float special) {
        super(x1, x2, y1, y2, z1, z2, special);
    }
}
