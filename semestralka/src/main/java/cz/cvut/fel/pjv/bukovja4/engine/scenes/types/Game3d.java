package cz.cvut.fel.pjv.bukovja4.engine.scenes.types;

import cz.cvut.fel.pjv.bukovja4.engine.scenes.BaseScene;

public class Game3d extends BaseScene {
    public Game3d(String name) throws Throwable {
        super.Load("levels/" + name);
    }
    
    @Override
    public void Tick() {
    }
}
