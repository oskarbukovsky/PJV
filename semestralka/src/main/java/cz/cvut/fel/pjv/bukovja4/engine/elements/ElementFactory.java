package cz.cvut.fel.pjv.bukovja4.engine.elements;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.pos.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.box.Box;
import cz.cvut.fel.pjv.bukovja4.engine.elements.elements.specific.*;

import java.lang.reflect.Constructor;
import java.util.Arrays;


public class ElementFactory<D extends Dim> {
    private final Class<D> dimensionClass;

    public static final ElementFactory<Dim2d> Factory2d = new ElementFactory<>(Dim2d.class);
    public static final ElementFactory<Dim3d> Factory3d = new ElementFactory<>(Dim3d.class);

    public ElementFactory(Class<D> dimensionClass) {
        this.dimensionClass = dimensionClass;
    }

    /**
     * Creates an element of the specified class with the given positions.
     * 
     * @param <T> The type of element to create
     * @param elementClass The class of the element to create
     * @param positions The positions for the element
     * @return The created element
     */
    @SuppressWarnings("unchecked")
    @SafeVarargs
    public final <T extends BaseElement<D>> T create(Class<T> elementClass, Pos<D>... positions) {
        try {
            LOG.debug("Creating " + elementClass.getSimpleName() + " with dimension " + 
                     dimensionClass.getSimpleName() + " and " + positions.length + " positions");
            
            // Try to find a constructor that takes Pos... parameters
            try {
                Constructor<T> constructor = elementClass.getConstructor(Pos[].class);
                return constructor.newInstance((Object) positions);
            } catch (NoSuchMethodException e) {
                // If no constructor with Pos[] is found, try to create a Box and use that
                LOG.debug("No constructor with Pos[] found, trying with Box");
                
                // Create a Box from the positions
                Box<D> bounds = new Box<D>(positions[0], positions[1]);
                
                // Try to find a constructor that takes a Box parameter
                try {
                    Constructor<T> constructor = elementClass.getConstructor(Box.class);
                    return constructor.newInstance(bounds);
                } catch (NoSuchMethodException e2) {
                    // If no constructor with Box is found, try to use the default constructor
                    LOG.debug("No constructor with Box found, trying default constructor");
                    
                    T instance = elementClass.getDeclaredConstructor().newInstance();
                    
                    // Set the bounds field using reflection if possible
                    try {
                        java.lang.reflect.Field boundsField = BaseElement.class.getDeclaredField("bounds");
                        boundsField.setAccessible(true);
                        boundsField.set(instance, bounds);
                    } catch (Exception fieldException) {
                        LOG.warn("Could not set bounds field: " + fieldException.getMessage());
                    }
                    
                    return instance;
                }
            }
        } catch (Exception e) {
            // LOG.error("Failed to create element: " + elementClass.getSimpleName(), e);
            throw new RuntimeException("Failed to create element: " + e.getMessage(), e);
        }
    }
    
    /**
     * Convenience method to create a Button.
     */
    @SuppressWarnings("unchecked")
    @SafeVarargs
    public final Button<D> createButton(Pos<D>... positions) {
        return this.create(Button.class, positions);
    }
    
    /**
     * Convenience method to create a Label.
     */

    public final Label<D> createLabel(Pos<D>... positions) {
        return this.create(Label.class, positions);
    }
}
