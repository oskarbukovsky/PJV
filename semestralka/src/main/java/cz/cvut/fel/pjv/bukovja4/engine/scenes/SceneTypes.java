package cz.cvut.fel.pjv.bukovja4.engine.scenes;

import cz.cvut.fel.pjv.bukovja4.engine.scenes.types.*;

public enum SceneTypes {
    MENU(Menu.class),
    GAME1D(Game1d.class),
    GAME2D(Game1d.class),
    GAME3D(Game1d.class),
    GAME4D(Game1d.class);

    <T extends BaseScene> SceneTypes(Class<T> sceneClass) {
    }
}
