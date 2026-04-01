package example.shoppingcart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                MainApp.class.getResource("/example/shoppingcart/shoppingcart-view.fxml")
        );



        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Shopping Cart App");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
