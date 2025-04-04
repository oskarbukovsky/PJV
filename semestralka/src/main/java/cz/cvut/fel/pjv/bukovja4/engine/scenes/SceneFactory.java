package cz.cvut.fel.pjv.bukovja4.engine.scenes;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
import cz.cvut.fel.pjv.bukovja4.engine.scenes.types.*;

public class SceneFactory {
    @SuppressWarnings("unchecked")
    public <E extends BaseScene> E Create(SceneTypes elementClass, String name)
            throws IllegalArgumentException, Throwable {
        return switch (elementClass) {
            case MENU -> (E) new Menu(name);
            case GAME1D -> (E) new Game1d(name);
            case GAME2D -> (E) new Game2d(name);
            case GAME3D -> (E) new Game3d(name);
            case GAME4D -> (E) new Game4d(name);
            default -> {
                LOG.error("Unknown element type", new IllegalArgumentException(elementClass.name()));
                yield null;
            }
        };
    }
}
