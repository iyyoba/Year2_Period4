package example.shoppingcart;

import javafx.application.Platform;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartControllerTest {

    private ShoppingCartController controller;

    @BeforeAll
    static void initJavaFX() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setup() {
        controller = new ShoppingCartController();

        controller.languageComboBox = new javafx.scene.control.ComboBox<>();
        controller.itemCountField = new javafx.scene.control.TextField();
        controller.itemsContainer = new VBox();
        controller.totalLabel = new javafx.scene.control.Label();

        controller.initialize();
    }

    @Test
    void testGenerateItemFields() {
        controller.itemCountField.setText("3");
        controller.generateItemFields();

        assertEquals(3, controller.itemsContainer.getChildren().size());
    }

    @Test
    void testCalculateTotal() {
        controller.itemCountField.setText("2");
        controller.generateItemFields();

        controller.itemQuantityFields.get(0).setText("2");
        controller.itemPriceFields.get(0).setText("5");

        controller.itemQuantityFields.get(1).setText("1");
        controller.itemPriceFields.get(1).setText("10");

        controller.calculateTotal();

        assertEquals("20.00", controller.totalLabel.getText());
    }

    @Test
    void testLanguageChange() {
        controller.languageComboBox.getSelectionModel().select(1);
        controller.handleLanguageChange();

        assertNotNull(controller.languageComboBox.getValue());
    }
}
