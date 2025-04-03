package cz.cvut.fel.pjv.bukovja4.engine.elements;

import cz.cvut.fel.pjv.bukovja4.engine.elements.elements.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.pos.*;

import java.lang.reflect.Constructor;

public class ElementFactory<P extends Pos> {
    // Factory method for creating 2D elements
    public static <E extends Element<Dim2d>> E create(
            Class<E> elementClass, Pos2d... positions) {
        try {
            // Get constructor that takes a Dim and variable number of Pos
            Constructor<E> constructor = elementClass.getConstructor(Dim.class, Pos[].class);

            // Create the element with 2D dimension
            return constructor.newInstance(new Dim2d(), positions);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot create 2D element: " + e.getMessage(), e);
        }
    }

    // Factory method for creating 3D elements
    public static <E extends Element<Dim3d>> E create(
            Class<E> elementClass, Pos3d... positions) {
        try {
            // Get constructor that takes a Dim and variable number of Pos
            Constructor<E> constructor = elementClass.getConstructor(Dim.class, Pos[].class);

            // Create the element with 3D dimension
            return constructor.newInstance(new Dim3d(), positions);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot create 3D element: " + e.getMessage(), e);
        }
    }

    // Type-specific factory methods (alternative syntax)
    public static Button<Dim2d> createButton2D(Pos2d... positions) {
        return new Button<>(new Dim2d(), positions);
    }

    public static Button<Dim3d> createButton3D(Pos3d... positions) {
        return new Button<>(new Dim3d(), positions);
    }
}
