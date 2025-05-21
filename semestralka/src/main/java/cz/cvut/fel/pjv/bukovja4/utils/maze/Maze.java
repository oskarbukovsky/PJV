package cz.cvut.fel.pjv.bukovja4.utils.maze;

import java.util.ArrayList;
// import java.util.List;
// import java.util.Random;

import cz.cvut.fel.pjv.bukovja4.engine.elements.BaseElement;
import cz.cvut.fel.pjv.bukovja4.engine.elements.ElementTypes;
import cz.cvut.fel.pjv.bukovja4.engine.elements.specific.*;
import cz.cvut.fel.pjv.bukovja4.engine.logic.GameState;
import cz.cvut.fel.pjv.bukovja4.utils.engine.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.Dim2d;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

import static cz.cvut.fel.pjv.bukovja4.engine.elements.ElementFactory.Factory2d;

public class Maze {
    @SuppressWarnings("unused")
    private static int seed;
    private static int width;
    private static int height;
    private static ArrayList<BaseElement<?>> elements;

    public static void prepareMaze(int seed, int width, int height) {
        Maze.seed = seed;
        Maze.width = width;
        Maze.height = height;
        GameState.maze = new MazeCell[width][height];
        LOG.warn("Prepare maze with seed: " + seed);
    }

    public static void generateMaze(ArrayList<BaseElement<?>> elements, int startX, int startY, int goalX, int goalY) {

        Maze.elements = elements;

        LOG.warn("Generating maze");

        //  Všechno je podlaha
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                GameState.maze[x][y] = new MazeCell(CellTypes.GROUND, null);
            }
        }

        // Ohraničení hranic walls
        for (int x = 0; x < width; x++) {
            GameState.maze[x][0] = new MazeCell(CellTypes.WALL, null);
            GameState.maze[x][height - 1] = new MazeCell(CellTypes.WALL, null);
        }
        for (int y = 0; y < height; y++) {
            GameState.maze[0][y] = new MazeCell(CellTypes.WALL, null);
            GameState.maze[width - 1][y] = new MazeCell(CellTypes.WALL, null);
        }


        GameState.maze[startX][startY] = new MazeCell(CellTypes.PLAYER, null);
        GameState.maze[goalX][goalY] = new MazeCell(CellTypes.GOAL, null);
    }

    public static void finishMaze(float scale) throws Throwable {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < Maze.width; x++) {
            for (int y = 0; y < Maze.height; y++) {
                Box<Dim2d> bounds = new Box<>(
                        new Pos<>(x * scale * 20, y * scale * 20),
                        new Pos<>((x + 1) * scale * 20, (y + 1) * scale * 20));
                switch (GameState.maze[x][y].getCellType()) {
                    case WALL -> {
                        Wall<?> element = (Wall<?>) Factory2d
                                .create(ElementTypes.WALL, bounds);
                        GameState.maze[x][y].setElement(element);
                        element.dispatchInit(scale);
                        Maze.elements.add(element);
                        sb.append("█");
                    }
                    case GROUND -> {
                        Ground<?> element = (Ground<?>) Factory2d
                                .create(ElementTypes.GROUND, bounds);
                        GameState.maze[x][y].setElement(element);
                        element.dispatchInit(scale);
                        Maze.elements.add(element);
                        sb.append(" ");
                    }
                    case PLAYER -> {
                        Player<?> element = (Player<?>) Factory2d
                                .create(ElementTypes.PLAYER, bounds);
                        GameState.maze[x][y].setElement(element);
                        element.dispatchInit(scale);
                        Maze.elements.add(element);
                        GameState.playerPos[0] = x;
                        GameState.playerPos[1] = y;
                        sb.append("P");
                    }
                    case GOAL -> {
                        Goal<?> element = (Goal<?>) Factory2d
                                .create(ElementTypes.GOAL, bounds);
                        GameState.maze[x][y].setElement(element);
                        element.dispatchInit(scale);
                        Maze.elements.add(element);
                        sb.append("G");
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
