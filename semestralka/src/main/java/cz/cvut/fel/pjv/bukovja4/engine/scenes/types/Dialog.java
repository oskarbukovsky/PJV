package cz.cvut.fel.pjv.bukovja4.engine.scenes.types;

import cz.cvut.fel.pjv.bukovja4.engine.scenes.BaseScene;

public class Dialog extends BaseScene {
    public Dialog(String name) throws Throwable {
        super.Load("dialog\\" + name);
    }

    @Override
    public void Tick() {
    }
}
