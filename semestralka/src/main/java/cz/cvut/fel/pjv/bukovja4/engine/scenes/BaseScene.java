package cz.cvut.fel.pjv.bukovja4.engine.scenes;

import cz.cvut.fel.pjv.bukovja4.Main;
import cz.cvut.fel.pjv.bukovja4.engine.logic.GameState;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

public abstract class BaseScene {
    private static String file;
    public static void Load(String sceneName) throws Throwable {
        file = sceneName;
        Main.class.getResourceAsStream("/" + sceneName);
        LOG.info("Loaded scene: " + sceneName);
    }

    public void Unload() {
        GameState.getControls().unRegisterAll();
        LOG.info("Unloaded scene: " + file);
    }

    public abstract void Tick();
}
