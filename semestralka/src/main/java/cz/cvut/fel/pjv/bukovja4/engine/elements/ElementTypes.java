package cz.cvut.fel.pjv.bukovja4.engine.elements;

import cz.cvut.fel.pjv.bukovja4.engine.elements.specific.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.Dim;

/**
 * Enumeration of available element types that can be created in the game.
 * Each type is associated with a specific element class.
 */
public enum ElementTypes {
    /** Button UI element */
    BUTTON(Button.class),
    /** Image UI element */
    IMAGE(Image.class),
    /** Text label UI element */
    LABEL(Label.class),
    /** Gif image UI element */
    GIF(Gif.class),
    /** Goal element */
    GOAL(Goal.class),
    /** Player element */
    PLAYER(Player.class),
    /** Wall element */
    WALL(Wall.class),
    /** Ground element */
    GROUND(Ground.class);

    /** The class associated with this element type */
    private final Class<? extends BaseElement<?>> elementClass;

    /**
     * Creates an element type linked to a specific class
     * 
     * @param elementClass The class implementing this element type
     * @param <T>          The specific BaseElement subclass type
     */
    <T extends BaseElement<?>> ElementTypes(Class<T> elementClass) {
        this.elementClass = elementClass;
    }

    /**
     * Gets the class associated with this element type
     * 
     * @param <D> The dimensional type of the element
     * @return The element's implementing class
     */
    @SuppressWarnings("unchecked")
    public <D extends Dim> Class<? extends BaseElement<D>> getElementClass() {
        return (Class<? extends BaseElement<D>>) elementClass;
    }
}
