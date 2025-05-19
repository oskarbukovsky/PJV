package cz.cvut.fel.pjv.bukovja4.utils.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cz.cvut.fel.pjv.bukovja4.engine.logic.GameState;

public class Maze {
    public static void generateMaze(int startX, int startY, int goalX, int goalY, int width, int height) {

        GameState.maze = new CellTypes[height][width];

        // 1. Inicializace vše jako WALL
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                GameState.maze[y][x] = CellTypes.WALL;
            }
        }

        // 2. Vygeneruj základní cestu od startu ke goal
        List<int[]> path = generatePath(startX, startY, goalX, goalY);
        for (int[] p : path) {
            GameState.maze[p[1]][p[0]] = CellTypes.EMPTY;
        }

        // 3. Umísti hráče a cíl
        GameState.maze[startY][startX] = CellTypes.PLAYER;
        GameState.maze[goalY][goalX] = CellTypes.GOAL;

        // 4. Náhodné otevření dalších buněk
        // Random rand = new Random();
        // for (int y = 0; y < height; y++) {
        //     for (int x = 0; x < width; x++) {
        //         if (GameState.maze[y][x] == CellTypes.WALL && rand.nextDouble() < 0.3) {
        //             GameState.maze[y][x] = CellTypes.EMPTY;
        //         }
        //     }
        // }
    }

    public static List<int[]> generatePath(int startX, int startY, int goalX, int goalY) {
        List<int[]> path = new ArrayList<>();
        int x = startX, y = startY;

        Random rand = new Random();

        while (x != goalX || y != goalY) {
            path.add(new int[] { x, y });
            boolean moveHorizontally = rand.nextBoolean();

            if (moveHorizontally && x != goalX) {
                x += (x < goalX) ? 1 : -1;
            } else if (y != goalY) {
                y += (y < goalY) ? 1 : -1;
            } else if (x != goalX) {
                x += (x < goalX) ? 1 : -1;
            }
        }

        path.add(new int[] { goalX, goalY });
        return path;
    }

    public static void printMaze() {
        for (int y = 0; y < GameState.maze.length; y++) {
            for (int x = 0; x < GameState.maze[0].length; x++) {
                switch (GameState.maze[y][x]) {
                    case WALL -> System.out.print("█");
                    case EMPTY -> System.out.print(" ");
                    case PLAYER -> System.out.print("P");
                    case GOAL -> System.out.print("G");
                }
            }
            System.out.println();
        }
    }

}
