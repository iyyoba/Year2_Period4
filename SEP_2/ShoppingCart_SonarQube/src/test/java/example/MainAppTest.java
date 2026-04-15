package example;

import example.shoppingcart.MainApp;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainAppTest {

    private Connection connection;
    private MainApp app;

    @BeforeAll
    void setup() throws Exception {
        Class.forName("org.h2.Driver");

        connection = DriverManager.getConnection(
                "jdbc:h2:mem:mainapptest;DB_CLOSE_DELAY=-1"
        );

        try (Statement stmt = connection.createStatement()) {

            // Create tables with safe (non-reserved) column names
            stmt.execute("""
                CREATE TABLE localization_strings (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    string_key VARCHAR(255),
                    string_value VARCHAR(255),
                    language VARCHAR(10)
                );
            """);

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

            // Insert test data
            stmt.execute("""
                INSERT INTO localization_strings (string_key, string_value, language)
                VALUES ('welcome', 'Welcome', 'en');
            """);
        }

        app = new MainApp(connection);
    }

    @AfterAll
    void cleanup() {
        // Close app safely
        if (app != null) {
            app.close();
        }

        // Close DB connection safely
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (Exception ignored) {
                // Intentionally ignored (acceptable for cleanup phase)
            }
        }
    }

    @Test
    void testMainAppRunCreatesCartAndItems() throws Exception {
        app.run("en");

        // Verify cart creation
        try (var stmt = connection.prepareStatement("SELECT COUNT(*) FROM cart");
             var rs = stmt.executeQuery()) {

            assertTrue(rs.next());
            assertEquals(1, rs.getInt(1));
        }

        // Verify cart items creation
        try (var stmt = connection.prepareStatement("SELECT COUNT(*) FROM cart_items");
             var rs = stmt.executeQuery()) {

            assertTrue(rs.next());
            assertEquals(1, rs.getInt(1));
        }
    }
}