package cz.cvut.fel.pjv.bukovja4.engine.scenes.types;

import cz.cvut.fel.pjv.bukovja4.engine.scenes.BaseScene;

public class Game1d extends BaseScene{
    public Game1d(String name) throws Throwable {
        super.Load("levels/" + name);
    }
    
    @Override
    public void Tick() {
    }
}
