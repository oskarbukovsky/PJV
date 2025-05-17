package cz.cvut.fel.pjv.bukovja4.engine.scenes.types;

import cz.cvut.fel.pjv.bukovja4.engine.logic.GameState;
import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.ControlTypes;
import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.Selector;
import cz.cvut.fel.pjv.bukovja4.engine.scenes.BaseScene;

/**
 * 2D game scene implementation that loads from YAML files.
 * Extends {@link BaseScene} to provide 2D game-specific functionality.
 */
public class Game2d extends BaseScene {
    /**
     * Creates a 2D game level with the specified name
     * 
     * @param name The level name used to locate the level YAML file
     * @throws Throwable if level loading fails
     * @see BaseScene#Load(String)
     */
    public Game2d(String name) throws Throwable {
        super.Load("levels/" + name);
        GameState.controls.register(new Selector(null, ControlTypes.MOUSE_MOVE), (event) -> {
            Object[] data = (Object[]) event;
            double x = ((Number) data[0]).doubleValue();
            double y = ((Number) data[1]).doubleValue();

            GameState.mouseX = (int) x;
            GameState.mouseY = (int) y;

            return null;
        });
    }

    /**
     * Updates the 2D game state for the current frame
     * {@inheritDoc}
     */
    @Override
    public void Tick() {
    }
}
