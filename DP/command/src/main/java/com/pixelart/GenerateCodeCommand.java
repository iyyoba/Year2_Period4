package com.pixelart;

public class GenerateCodeCommand implements Command {
    private final PixelGrid grid;

    public GenerateCodeCommand(PixelGrid grid) {
        this.grid = grid;
    }

    @Override
    public void execute() {
        int[][] g = grid.getGrid();
        System.out.println("int[][] pixelArt = {");
        for (int y = 0; y < 8; y++) {
            System.out.print("    {");
            for (int x = 0; x < 8; x++) {
                System.out.print(g[y][x]);
                if (x < 7) System.out.print(", ");
            }
            System.out.println("},");
        }
        System.out.println("};");
    }
}
