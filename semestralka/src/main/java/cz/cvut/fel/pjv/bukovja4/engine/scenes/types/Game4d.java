package cz.cvut.fel.pjv.bukovja4.engine.scenes.types;

import cz.cvut.fel.pjv.bukovja4.engine.scenes.BaseScene;

public class Game4d extends BaseScene {
    public Game4d(String name) throws Throwable {
        super.Load("levels\\" + name);
    }
    
    @Override
    public void Tick() {
    }
}
