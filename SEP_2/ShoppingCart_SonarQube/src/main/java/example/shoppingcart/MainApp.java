package example.shoppingcart;

import example.shoppingcart.service.CartService;
import example.shoppingcart.service.LocalizationService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;

public class MainApp {

    private static final Logger LOGGER = Logger.getLogger(MainApp.class.getName());

    private final Connection connection;
    private final LocalizationService localizationService;
    private final CartService cartService;

    public MainApp(Connection connection) {
        this.connection = connection;
        this.localizationService = new LocalizationService(connection);
        this.cartService = new CartService(connection);
    }

    public void run(String language) {
        Map<String, String> strings = localizationService.getLocalizedStrings(language);

        LOGGER.log(java.util.logging.Level.INFO,
                "Loaded localization strings: {0}",
                strings.size());

        int cartId = cartService.saveCartRecord("DefaultUser");
        cartService.saveCartItem(cartId, "Sample Item", 1, 9.99);

        LOGGER.info("Application run completed");
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            LOGGER.severe("Failed to close connection: " + ex.getMessage());
        }
    }
}
