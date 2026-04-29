package rpg;

import java.util.Random;

public abstract class Map {
    protected Tile[][] grid;
    protected int width;
    protected int height;
    protected Random random = new Random();

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Tile[height][width];
        generateMap();
    }

    protected abstract Tile createTile(); // Factory Method

    private void generateMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = createTile();
            }
        }
    }

    public void display() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(grid[i][j].getCharacter() + " ");
            }
            System.out.println();
        }
    }
}