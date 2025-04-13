package cz.cvut.fel.pjv.bukovja4.engine.scenes.types;

import cz.cvut.fel.pjv.bukovja4.engine.scenes.BaseScene;

public class Menu extends BaseScene {
    public Menu(String name) throws Throwable {
        super.Load("menus/" + name);
    }

    @Override
    public void Tick() {
    }
}
