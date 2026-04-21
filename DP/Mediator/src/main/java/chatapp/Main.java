package chatapp;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        ChatRoom mediator = new ChatRoom();

        ChatClientController user1 = new ChatClientController("Alice", mediator);
        ChatClientController user2 = new ChatClientController("Bob", mediator);
        ChatClientController user3 = new ChatClientController("Charlie", mediator);

        // Update user lists
        user1.updateUserList(mediator.getClients().keySet());
        user2.updateUserList(mediator.getClients().keySet());
        user3.updateUserList(mediator.getClients().keySet());
    }

    public static void main(String[] args) {
        launch(args);
    }
}