package cz.cvut.fel.pjv.bukovja4.engine.scenes;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

public class SceneFactory {
    @SuppressWarnings("unchecked")
    public <S extends BaseScene> S create(SceneTypes sceneType, String name) throws Throwable {
        try {
            return (S) sceneType.getSceneClass().getConstructor(String.class).newInstance(name);
        } catch (Exception ex) {
            LOG.error("Error creating scene " + sceneType.name(), ex);
        }
        return null;
    }
}
