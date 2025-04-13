package cz.cvut.fel.pjv.bukovja4.engine.scenes.types;

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
    }

    /**
     * Updates the menu state for the current frame
     * {@inheritDoc}
     */
    @Override
    public void Tick() {
    }
}
