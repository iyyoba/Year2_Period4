package memento.guistate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Gui extends Application {
    private Controller controller;

    @Override
    public void start(Stage primaryStage) {
        controller = new Controller(this);

        VBox root = new VBox(10);

        // Add a history button
        Button historyBtn = new Button("History");
        historyBtn.setOnAction(e -> controller.openHistoryWindow());
        root.getChildren().add(historyBtn);

        Scene scene = new Scene(root, 400, 300);

        // Add keyboard shortcuts
        scene.setOnKeyPressed(event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.Z) {
                controller.undo();
            } else if (event.isControlDown() && event.getCode() == KeyCode.Y) {
                controller.redo();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Memento App");
        primaryStage.show();
    }

    // Update GUI placeholder
    public void updateGui() {
        System.out.println("GUI updated: options and checkbox state refreshed");
    }

    public static void main(String[] args) {
        launch(args);
    }
}