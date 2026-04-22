package example.shoppingcart.service;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CartServiceH2Test {

    private Connection connection;
    private CartService cartService;

    @BeforeAll
    void setupDatabase() throws Exception {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");

        try (Statement stmt = connection.createStatement()) {
            stmt.execute("""
                CREATE TABLE cart (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    customer_name VARCHAR(255)
                );
            """);

            stmt.execute("""
                CREATE TABLE cart_items (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    cart_id INT,
                    item_name VARCHAR(255),
                    quantity INT,
                    price DOUBLE
                );
            """);
        }

        cartService = new CartService(connection);
    }

    @AfterAll
    void cleanup() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    void testSaveCartRecordReturnsGeneratedId() {
        int id = cartService.saveCartRecord("Eyob");
        assertTrue(id > 0);
    }

    @Test
    void testSaveCartRecordPersistsData() throws Exception {
        int id = cartService.saveCartRecord("TestUser");

        try (var stmt = connection.prepareStatement("SELECT customer_name FROM cart WHERE id = ?")) {
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                assertTrue(rs.next());
                assertEquals("TestUser", rs.getString(1));
            }
        }
    }

    @Test
    void testSaveCartItemPersistsData() throws Exception {
        int cartId = cartService.saveCartRecord("UserA");
        cartService.saveCartItem(cartId, "Apple", 3, 1.50);

        try (var stmt = connection.prepareStatement("SELECT item_name FROM cart_items WHERE cart_id = ?")) {
            stmt.setInt(1, cartId);
            try (var rs = stmt.executeQuery()) {
                assertTrue(rs.next());
                assertEquals("Apple", rs.getString(1));
            }
        }
    }

    @Test
    void testSaveCartItemMultipleItems() throws Exception {
        int cartId = cartService.saveCartRecord("UserB");
        cartService.saveCartItem(cartId, "Banana", 2, 0.99);
        cartService.saveCartItem(cartId, "Orange", 5, 1.20);

        try (var stmt = connection.prepareStatement("SELECT COUNT(*) FROM cart_items WHERE cart_id = ?")) {
            stmt.setInt(1, cartId);
            try (var rs = stmt.executeQuery()) {
                assertTrue(rs.next());
                assertEquals(2, rs.getInt(1));
            }
        }
    }
}
