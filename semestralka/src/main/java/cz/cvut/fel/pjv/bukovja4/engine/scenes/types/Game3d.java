package cz.cvut.fel.pjv.bukovja4.engine.scenes.types;

import cz.cvut.fel.pjv.bukovja4.engine.scenes.BaseScene;

/**
 * 3D game scene implementation that loads from YAML files.
 * Extends {@link BaseScene} to provide 3D game-specific functionality.
 */
public class Game3d extends BaseScene {
    /**
     * Creates a 3D game level with the specified name
     * 
     * @param name The level name used to locate the level YAML file
     * @throws Throwable if level loading fails
     * @see BaseScene#Load(String)
     */
    public Game3d(String name) throws Throwable {
        super.Load("levels/" + name);
    }

    /**
     * Updates the 3D game state for the current frame
     * {@inheritDoc}
     */
    @Override
    public void Tick() {
    }
}
