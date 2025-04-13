package cz.cvut.fel.pjv.bukovja4.engine.scenes.types;

import cz.cvut.fel.pjv.bukovja4.engine.scenes.BaseScene;

/**
 * 1D game scene implementation that loads from YAML files.
 * Extends {@link BaseScene} to provide 1D game-specific functionality.
 */
public class Game1d extends BaseScene {
    /**
     * Creates a 1D game level with the specified name
     * 
     * @param name The level name used to locate the level YAML file
     * @throws Throwable if level loading fails
     * @see BaseScene#Load(String)
     */
    public Game1d(String name) throws Throwable {
        super.Load("levels/" + name);
    }

    /**
     * Updates the 1D game state for the current frame
     * {@inheritDoc}
     */
    @Override
    public void Tick() {
    }
}
