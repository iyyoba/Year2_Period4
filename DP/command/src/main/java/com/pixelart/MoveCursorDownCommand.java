package com.pixelart;

public class MoveCursorDownCommand implements Command {
    private final PixelGrid grid;

    public MoveCursorDownCommand(PixelGrid grid) {
        this.grid = grid;
    }

    @Override
    public void execute() {
        grid.moveCursor(0, 1);
    }
}
