package com.pixelart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    private final PixelGrid grid = new PixelGrid();
    private final Rectangle[][] cells = new Rectangle[8][8];

    @Override
    public void start(Stage stage) {
        GridPane gridPane = new GridPane();

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Rectangle r = new Rectangle(40, 40, Color.WHITE);
                r.setStroke(Color.GRAY);
                cells[y][x] = r;
                gridPane.add(r, x, y);
            }
        }

        Button generateButton = new Button("Create Code");
        generateButton.setOnAction(e -> new GenerateCodeCommand(grid).execute());

        VBox root = new VBox(gridPane, generateButton);
        Scene scene = new Scene(root, 400, 450);

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP -> new MoveCursorUpCommand(grid).execute();
                case DOWN -> new MoveCursorDownCommand(grid).execute();
                case LEFT -> new MoveCursorLeftCommand(grid).execute();
                case RIGHT -> new MoveCursorRightCommand(grid).execute();
                case SPACE -> new TogglePixelCommand(grid).execute();
            }
            refreshGrid();
        });

        stage.setScene(scene);
        stage.setTitle("Pixel Art Editor");
        stage.show();

        refreshGrid();
    }

    private void refreshGrid() {
        int[][] g = grid.getGrid();

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                cells[y][x].setFill(g[y][x] == 1 ? Color.BLACK : Color.WHITE);

                // Reset stroke for all cells
                cells[y][x].setStroke(Color.GRAY);
                cells[y][x].setStrokeWidth(1);
            }
        }

        // Highlight cursor
        Rectangle cursorCell = cells[grid.getCursorY()][grid.getCursorX()];
        cursorCell.setStroke(Color.RED);
        cursorCell.setStrokeWidth(3);
    }


    public static void main(String[] args) {
        launch();
    }
}
