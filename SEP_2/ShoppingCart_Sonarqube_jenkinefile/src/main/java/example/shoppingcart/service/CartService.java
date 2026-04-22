package example.shoppingcart.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class CartService {

    private static final Logger LOGGER = Logger.getLogger(CartService.class.getName());
    private final Connection connection;

    public CartService(Connection connection) {
        this.connection = connection;
    }

    public int saveCartRecord(String customerName) {
        final String sql = "INSERT INTO cart (customer_name) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, customerName);
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            LOGGER.severe("Failed to save cart record: " + ex.getMessage());
            throw new IllegalStateException("Error saving cart record", ex);
        }

        throw new IllegalStateException("No generated ID returned");
    }

    public void saveCartItem(int cartId, String itemName, int quantity, double price) {
        final String sql = "INSERT INTO cart_items (cart_id, item_name, quantity, price) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cartId);
            stmt.setString(2, itemName);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, price);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.severe("Failed to save cart item: " + ex.getMessage());
            throw new IllegalStateException("Error saving cart item", ex);
        }
    }
}
