package cz.cvut.fel.pjv.bukovja4.engine.scenes;

public abstract class BaseScene {
    public void Load(String sceneName) throws Throwable {
        LoadScene.Load(sceneName);
    }

    public abstract void Tick();
}
