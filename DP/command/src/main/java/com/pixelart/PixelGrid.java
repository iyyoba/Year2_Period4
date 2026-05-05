package com.pixelart;

public class PixelGrid {
    private final int[][] grid = new int[8][8];
    private int cursorX = 0;
    private int cursorY = 0;

    public int[][] getGrid() {
        return grid;
    }

    public void moveCursor(int dx, int dy) {
        cursorX = Math.max(0, Math.min(7, cursorX + dx));
        cursorY = Math.max(0, Math.min(7, cursorY + dy));
    }

    public void togglePixel() {
        grid[cursorY][cursorX] = grid[cursorY][cursorX] == 0 ? 1 : 0;
    }

    public int getCursorX() { return cursorX; }
    public int getCursorY() { return cursorY; }
}
