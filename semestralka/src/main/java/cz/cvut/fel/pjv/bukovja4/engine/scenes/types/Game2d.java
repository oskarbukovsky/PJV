package cz.cvut.fel.pjv.bukovja4.engine.scenes.types;

import cz.cvut.fel.pjv.bukovja4.engine.actions.Actions;
import cz.cvut.fel.pjv.bukovja4.engine.elements.BaseElement;
// import java.util.ArrayList;
import cz.cvut.fel.pjv.bukovja4.engine.elements.specific.*;
import cz.cvut.fel.pjv.bukovja4.engine.logic.GameState;
import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.ControlTypes;
import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.Selector;
import cz.cvut.fel.pjv.bukovja4.engine.scenes.BaseScene;
import cz.cvut.fel.pjv.bukovja4.utils.engine.Box;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
// import cz.cvut.fel.pjv.bukovja4.utils.maze.Maze;
import cz.cvut.fel.pjv.bukovja4.utils.maze.CellTypes;
import cz.cvut.fel.pjv.bukovja4.utils.maze.MazeCell;

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
        GameState.controls.register(new Selector(null, ControlTypes.KEY_PRESS), (event) -> {
            int keyCode = (int) ((Object[]) event)[0];

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j <= 7; j++) {
                for (int i = 0; i <= 7; i++) {
                    sb.append("[" + (i) + "," + (j) + "]"
                            + GameState.maze[i][j]
                                    .getCellType().name()
                            + "\t");
                }
                sb.append("\n");
            }
            // for (int i = -1; i <= 1; i++) {
            // for (int j = -1; j <= 1; j++) {
            // sb.append("[" + (GameState.playerPos[0] + i) + "," + (GameState.playerPos[1]
            // + j) + "]"
            // + GameState.maze[GameState.playerPos[0] + i][GameState.playerPos[1] + j]
            // .getCellType().name()
            // + "\t");
            // }
            // sb.append("\n");
            // }
            LOG.warn("Player pos: [" + GameState.playerPos[0] + ", " + GameState.playerPos[1] + "]");
            LOG.warn("Around: \n" + sb.toString());

            switch (keyCode) {
                // Up
                case 265 -> {
                    if (GameState.maze[GameState.playerPos[0]][GameState.playerPos[1] - 1].getCellType()
                            .equals(CellTypes.GROUND)) {
                        LOG.warn("Moving up");
                        BaseElement<?> playerElement = (BaseElement<?>) GameState.maze[GameState.playerPos[0]][GameState.playerPos[1]]
                                .getElement();
                        BaseElement<?> groundElement = (BaseElement<?>) GameState.maze[GameState.playerPos[0]][GameState.playerPos[1]
                                - 1].getElement();
                        GameState.maze[GameState.playerPos[0]][GameState.playerPos[1]].setCellType(CellTypes.GROUND);
                        GameState.maze[GameState.playerPos[0]][GameState.playerPos[1]].setElement(groundElement);
                        GameState.maze[GameState.playerPos[0]][GameState.playerPos[1] - 1]
                                .setCellType(CellTypes.PLAYER);
                        GameState.maze[GameState.playerPos[0]][GameState.playerPos[1] - 1].setElement(playerElement);

                        playerElement.move(MoveEnum.UP, 20 * BaseScene.scale);
                        groundElement.move(MoveEnum.DOWN, 20 * BaseScene.scale);
                        GameState.playerPos[1] -= 1;
                    } else if (GameState.maze[GameState.playerPos[0]][GameState.playerPos[1] - 1].getCellType()
                            .equals(CellTypes.GOAL)) {
                        LOG.warn("Goal reached");
                        Actions.next_level();
                    }
                }
                // Down
                case 264 -> {
                    if (GameState.maze[GameState.playerPos[0]][GameState.playerPos[1] + 1].getCellType()
                            .equals(CellTypes.GROUND)) {
                        LOG.warn("Moving down");
                        BaseElement<?> playerElement = (BaseElement<?>) GameState.maze[GameState.playerPos[0]][GameState.playerPos[1]]
                                .getElement();
                        BaseElement<?> groundElement = (BaseElement<?>) GameState.maze[GameState.playerPos[0]][GameState.playerPos[1]
                                + 1].getElement();
                        GameState.maze[GameState.playerPos[0]][GameState.playerPos[1]].setCellType(CellTypes.GROUND);
                        GameState.maze[GameState.playerPos[0]][GameState.playerPos[1]].setElement(groundElement);
                        GameState.maze[GameState.playerPos[0]][GameState.playerPos[1] + 1]
                                .setCellType(CellTypes.PLAYER);
                        GameState.maze[GameState.playerPos[0]][GameState.playerPos[1] + 1].setElement(playerElement);

                        playerElement.move(MoveEnum.DOWN, 20 * BaseScene.scale);
                        groundElement.move(MoveEnum.UP, 20 * BaseScene.scale);
                        GameState.playerPos[1] += 1;
                    } else if (GameState.maze[GameState.playerPos[0]][GameState.playerPos[1] + 1].getCellType()
                            .equals(CellTypes.GOAL)) {
                        LOG.warn("Goal reached");
                        Actions.next_level();
                    }
                }
                // Left
                case 263 -> {
                    if (GameState.maze[GameState.playerPos[0] - 1][GameState.playerPos[1]].getCellType()
                            .equals(CellTypes.GROUND)) {
                        LOG.warn("Moving right");
                        BaseElement<?> playerElement = (BaseElement<?>) GameState.maze[GameState.playerPos[0]][GameState.playerPos[1]]
                                .getElement();
                        BaseElement<?> groundElement = (BaseElement<?>) GameState.maze[GameState.playerPos[0]
                                - 1][GameState.playerPos[1]].getElement();
                        GameState.maze[GameState.playerPos[0]][GameState.playerPos[1]].setCellType(CellTypes.GROUND);
                        GameState.maze[GameState.playerPos[0]][GameState.playerPos[1]].setElement(groundElement);
                        GameState.maze[GameState.playerPos[0] - 1][GameState.playerPos[1]]
                                .setCellType(CellTypes.PLAYER);
                        GameState.maze[GameState.playerPos[0] - 1][GameState.playerPos[1]].setElement(playerElement);

                        playerElement.move(MoveEnum.LEFT, 20 * BaseScene.scale);
                        groundElement.move(MoveEnum.RIGHT, 20 * BaseScene.scale);
                        GameState.playerPos[0] -= 1;
                    } else if (GameState.maze[GameState.playerPos[0] - 1][GameState.playerPos[1]].getCellType()
                            .equals(CellTypes.GOAL)) {
                        LOG.warn("Goal reached");
                        Actions.next_level();
                    }
                }
                // Right
                case 262 -> {

                    // LOG.warn("Type: " + GameState.maze[GameState.playerPos[0] +
                    // 1][GameState.playerPos[1]]
                    // .getCellType().name());

                    if (GameState.maze[GameState.playerPos[0] + 1][GameState.playerPos[1]].getCellType()
                            .equals(CellTypes.GROUND)) {
                        LOG.warn("Moving right");
                        BaseElement<?> playerElement = (BaseElement<?>) GameState.maze[GameState.playerPos[0]][GameState.playerPos[1]]
                                .getElement();
                        BaseElement<?> groundElement = (BaseElement<?>) GameState.maze[GameState.playerPos[0]
                                + 1][GameState.playerPos[1]].getElement();
                        GameState.maze[GameState.playerPos[0]][GameState.playerPos[1]].setCellType(CellTypes.GROUND);
                        GameState.maze[GameState.playerPos[0]][GameState.playerPos[1]].setElement(groundElement);
                        GameState.maze[GameState.playerPos[0] + 1][GameState.playerPos[1]]
                                .setCellType(CellTypes.PLAYER);
                        GameState.maze[GameState.playerPos[0] + 1][GameState.playerPos[1]].setElement(playerElement);

                        playerElement.move(MoveEnum.RIGHT, 20 * BaseScene.scale);
                        groundElement.move(MoveEnum.LEFT, 20 * BaseScene.scale);
                        GameState.playerPos[0] += 1;
                    } else if (GameState.maze[GameState.playerPos[0] + 1][GameState.playerPos[1]].getCellType()
                            .equals(CellTypes.GOAL)) {
                        LOG.warn("Goal reached");
                        Actions.next_level();
                    }
                }
                // Escape
                case 256 -> {
                    Actions.pause_game();
                }
            }

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
