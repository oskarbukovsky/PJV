package cz.cvut.fel.pjv.bukovja4.engine.scenes;

import cz.cvut.fel.pjv.bukovja4.engine.scenes.types.*;

/**
 * Enumeration of available scene types in the game.
 * Each type is associated with a specific scene implementation class.
 */
public enum SceneTypes {
    /** Main menu scene */
    MENU(Menu.class),
    /** Dialog/popup scene */
    DIALOG(Dialog.class),
    /** 1D game scene */
    GAME1D(Game1d.class),
    /** 2D game scene */
    GAME2D(Game1d.class),
    /** 3D game scene */
    GAME3D(Game1d.class),
    /** 4D game scene */
    GAME4D(Game1d.class);

    /** The class associated with this scene type */
    private final Class<? extends BaseScene> sceneClass;

    /**
     * Creates a scene type linked to a specific class
     * 
     * @param sceneClass The class implementing this scene type
     * @param <T> The specific BaseScene subclass type
     */
    <T extends BaseScene> SceneTypes(Class<T> sceneClass) {
        this.sceneClass = sceneClass;
    }

    /**
     * Gets the class associated with this scene type
     * 
     * @return The scene's implementing class
     */
    public Class<? extends BaseScene> getSceneClass() {
        return sceneClass;
    }
}
