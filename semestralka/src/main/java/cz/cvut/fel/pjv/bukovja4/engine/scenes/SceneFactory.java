package cz.cvut.fel.pjv.bukovja4.engine.scenes;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

/**
 * Factory for creating game scenes.
 * Provides functionality to instantiate different scene types.
 */
public class SceneFactory {
    /**
     * Creates a scene of the specified type with the given name.
     * 
     * @param <S>       The type of scene to create
     * @param sceneType The scene type to create
     * @param name      The name of the scene
     * @return The newly created scene
     * @throws Throwable if scene creation fails
     * @see SceneTypes
     * @see BaseScene
     */
    @SuppressWarnings("unchecked")
    public static <S extends BaseScene> S create(SceneTypes sceneType, String name) throws Throwable {
        try {
            return (S) sceneType.getSceneClass().getConstructor(String.class).newInstance(name);
        } catch (Exception ex) {
            LOG.error("Error creating scene " + sceneType.name() + "@" + name, ex);
        }
        return null;
    }
}
