package example.shoppingcart;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class ShoppingCartController {

    private static final Logger LOGGER = Logger.getLogger(ShoppingCartController.class.getName());

    @FXML
    public ComboBox<String> languageComboBox;

    @FXML
    public TextField itemCountField;

    @FXML
    public VBox itemsContainer;

    @FXML
    public Label totalLabel;

    private final LocalizationProvider localizationProvider = new LocalizationProvider();

    // Lists to store dynamically generated item fields
    public final List<TextField> itemNameFields = new ArrayList<>();
    public final List<TextField> itemQuantityFields = new ArrayList<>();
    public final List<TextField> itemPriceFields = new ArrayList<>();

    @FXML
    public void initialize() {
        languageComboBox.getItems().addAll(localizationProvider.getSupportedLanguages());
        languageComboBox.setValue(localizationProvider.getDefaultLanguage());
    }

    @FXML
    public void generateItemFields() {
        itemsContainer.getChildren().clear();
        itemNameFields.clear();
        itemQuantityFields.clear();
        itemPriceFields.clear();

        int count;
        try {
            count = Integer.parseInt(itemCountField.getText());
        } catch (NumberFormatException ex) {
            LOGGER.warning("Invalid item count");
            return;
        }

        for (int i = 0; i < count; i++) {
            TextField name = new TextField();
            name.setPromptText("Item name");

            TextField qty = new TextField();
            qty.setPromptText("Quantity");

            TextField price = new TextField();
            price.setPromptText("Price");

            itemNameFields.add(name);
            itemQuantityFields.add(qty);
            itemPriceFields.add(price);

            VBox row = new VBox(name, qty, price);
            itemsContainer.getChildren().add(row);
        }
    }

    @FXML
    public void calculateTotal() {
        double total = 0.0;

        for (int i = 0; i < itemNameFields.size(); i++) {
            try {
                int qty = Integer.parseInt(itemQuantityFields.get(i).getText());
                double price = Double.parseDouble(itemPriceFields.get(i).getText());
                total += qty * price;
            } catch (NumberFormatException ex) {
                LOGGER.warning("Invalid input in item row " + i);
            }
        }

        totalLabel.setText(String.format(Locale.US, "%.2f", total));
    }

    @FXML
    public void handleLanguageChange() {
        int index = languageComboBox.getSelectionModel().getSelectedIndex();
        Locale locale = localizationProvider.toLocale(index);
        LOGGER.info("Language changed to: " + locale.getLanguage());
    }
}
