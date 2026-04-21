
package chatapp;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatClientController {

    private String username;
    private ChatMediator mediator;

    private TextArea chatArea;
    private TextField messageField;
    private ComboBox<String> userSelector;

    public ChatClientController(String username, ChatMediator mediator) {
        this.username = username;
        this.mediator = mediator;
        mediator.registerClient(this);

        createUI();
    }

    public String getUsername() {
        return username;
    }

    private void createUI() {
        Stage stage = new Stage();
        stage.setTitle(username);

        chatArea = new TextArea();
        chatArea.setEditable(false);

        messageField = new TextField();
        messageField.setPromptText("Enter message");

        userSelector = new ComboBox<>();

        Button sendButton = new Button("Send");

        sendButton.setOnAction(e -> {
            String message = messageField.getText();
            String receiver = userSelector.getValue();

            if (receiver != null && !message.isEmpty()) {
                mediator.sendMessage(message, this, receiver);
                chatArea.appendText("Me → " + receiver + ": " + message + "\n");
                messageField.clear();
            }
        });

        VBox layout = new VBox(10, chatArea, userSelector, messageField, sendButton);
        stage.setScene(new Scene(layout, 300, 400));
        stage.show();
    }

    public void receiveMessage(String message) {
        chatArea.appendText(message + "\n");
    }

    public void updateUserList(Iterable<String> users) {
        userSelector.getItems().clear();
        for (String user : users) {
            if (!user.equals(username)) {
                userSelector.getItems().add(user);
            }
        }
    }
}