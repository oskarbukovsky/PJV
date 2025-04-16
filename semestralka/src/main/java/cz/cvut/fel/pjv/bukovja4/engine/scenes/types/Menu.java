package cz.cvut.fel.pjv.bukovja4.engine.scenes.types;

import cz.cvut.fel.pjv.bukovja4.engine.logic.GameState;
import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.ControlTypes;
import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.Selector;
import cz.cvut.fel.pjv.bukovja4.engine.scenes.BaseScene;

/**
 * Menu scene implementation that loads from YAML files.
 * Extends {@link BaseScene} to provide menu-specific functionality.
 */
public class Menu extends BaseScene {

    /**
     * Creates a menu with the specified name
     * 
     * @param name The menu name used to locate the menu YAML file
     * @throws Throwable if menu loading fails
     * @see BaseScene#Load(String)
     */
    public Menu(String name) throws Throwable {
        super.Load("menus/" + name);
        GameState.controls.register(new Selector(null, ControlTypes.MOUSE_MOVE), (event) -> {
            Object[] data = (Object[]) event;
            double x = ((Number) data[0]).doubleValue();
            double y = ((Number) data[1]).doubleValue();
            
            GameState.mouseX = (int)x;
            GameState.mouseY = (int)y;
            
            return null;
        });
    }

    /**
     * Updates the menu state for the current frame
     * {@inheritDoc}
     */
    @Override
    public void Tick() {
    }
}
