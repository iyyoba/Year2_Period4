package example.shoppingcart;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.*;

public class ShoppingCartController {

    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    private TextField itemCountField;

    @FXML
    private VBox itemsContainer;

    @FXML
    private Label totalLabel;

    private ResourceBundle messages;

    private Locale currentLocale = new Locale("en", "US");

    @FXML
    public void initialize() {
        // Language options
        languageComboBox.getItems().addAll(
                "English", "Finnish", "Swedish", "Japanese"
        );

        languageComboBox.setValue("English");
        loadBundle();
    }

    private void loadBundle() {
        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }

    @FXML
    private void handleLanguageChange() {
        int index = languageComboBox.getSelectionModel().getSelectedIndex();
        currentLocale = ShoppingCart.selectLocale(index + 1);
        loadBundle();
    }

    @FXML
    private void generateItemFields() {
        itemsContainer.getChildren().clear();

        int count;
        try {
            count = Integer.parseInt(itemCountField.getText());
        } catch (Exception e) {
            return;
        }

        for (int i = 0; i < count; i++) {
            TextField priceField = new TextField();
            priceField.setPromptText(messages.getString("enter.price"));

            TextField quantityField = new TextField();
            quantityField.setPromptText(messages.getString("enter.quantity"));

            itemsContainer.getChildren().addAll(
                    new Label("Item " + (i + 1)),
                    priceField,
                    quantityField
            );
        }
    }

    @FXML
    private void calculateTotal() {
        List<Double> prices = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();

        List<javafx.scene.Node> children = itemsContainer.getChildren();

        for (int i = 0; i < children.size(); i += 3) {
            TextField priceField = (TextField) children.get(i + 1);
            TextField quantityField = (TextField) children.get(i + 2);

            try {
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                prices.add(price);
                quantities.add(quantity);
            } catch (Exception e) {
                totalLabel.setText("Invalid input");
                return;
            }
        }

        double total = example.shoppingcart.ShoppingCart.calculateCartTotal(prices, quantities);

        totalLabel.setText(messages.getString("total.cost") + " " + total);
    }
}