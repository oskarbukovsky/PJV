package cz.cvut.fel.pjv.bukovja4.engine.scenes.types;

import cz.cvut.fel.pjv.bukovja4.engine.scenes.BaseScene;

/**
 * 4D game scene implementation that loads from YAML files.
 * Extends {@link BaseScene} to provide 4D game-specific functionality.
 */
public class Game4d extends BaseScene {
    /**
     * Creates a 4D game level with the specified name
     * 
     * @param name The level name used to locate the level YAML file
     * @throws Throwable if level loading fails
     * @see BaseScene#Load(String)
     */
    public Game4d(String name) throws Throwable {
        super.Load("levels/" + name);
    }

    /**
     * Updates the 4D game state for the current frame
     * {@inheritDoc}
     */
    @Override
    public void Tick() {
    }
}
