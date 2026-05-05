package com.pixelart;

public class MoveCursorLeftCommand implements Command {
    private final PixelGrid grid;

    public MoveCursorLeftCommand(PixelGrid grid) {
        this.grid = grid;
    }

    @Override
    public void execute() {
        grid.moveCursor(-1, 0);
    }
}
