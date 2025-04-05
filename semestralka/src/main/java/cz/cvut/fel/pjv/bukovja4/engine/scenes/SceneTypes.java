package cz.cvut.fel.pjv.bukovja4.engine.scenes;

import cz.cvut.fel.pjv.bukovja4.engine.scenes.types.*;

public enum SceneTypes {
    MENU(Menu.class),
    DIALOG(Dialog.class),
    GAME1D(Game1d.class),
    GAME2D(Game1d.class),
    GAME3D(Game1d.class),
    GAME4D(Game1d.class);

    private final Class<? extends BaseScene> sceneClass;

    <T extends BaseScene> SceneTypes(Class<T> sceneClass) {
        this.sceneClass = sceneClass;
    }

    public Class<? extends BaseScene> getSceneClass() {
        return sceneClass;
    }
}
