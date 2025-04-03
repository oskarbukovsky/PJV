package cz.cvut.fel.pjv.bukovja4.engine.elements.elements;

import cz.cvut.fel.pjv.bukovja4.engine.elements.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.pos.*;

public class Button<D extends Dim> extends Element<D> {
    private Pos[] positions;

    public Button(D dimension, Pos... positions) {
        super(dimension);
        this.positions = positions;
    }

    @Override
    public void render() {
        // Rendering logic based on dimension type
        if (dimension instanceof Dim2d) {
            // 2D rendering
        } else if (dimension instanceof Dim3d) {
            // 3D rendering
        }
    }
}
