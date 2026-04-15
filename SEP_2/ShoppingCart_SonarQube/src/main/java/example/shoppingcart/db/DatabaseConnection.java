package example.shoppingcart.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public final class DatabaseConnection {

    private static final String PROPERTIES_FILE = "db.properties";

    private static final String DB_URL_KEY = "db.url";
    private static final String DB_USER_KEY = "db.user";
    private static final String DB_PASSWORD_KEY = "db.password";

    private static final Properties PROPERTIES = new Properties();

    // Test override connection (H2)
    private static Connection testConnection;

    static {
        loadProperties();
    }

    private DatabaseConnection() {
        // Utility class
    }

    /**
     * Allows tests to inject an in-memory H2 connection.
     */
    public static void overrideConnectionForTests(Connection conn) {
        testConnection = conn;
    }

    /**
     * Clears the test connection after tests finish.
     */
    public static void clearTestConnection() {
        testConnection = null;
    }

    private static void loadProperties() {
        try (InputStream input = DatabaseConnection.class
                .getClassLoader()
                .getResourceAsStream(PROPERTIES_FILE)) {

            if (input == null) {
                throw new IllegalStateException(PROPERTIES_FILE + " not found in resources folder");
            }

            PROPERTIES.load(input);

        } catch (IOException e) {
            throw new IllegalStateException("Failed to load database configuration", e);
        }
    }

    public static Connection getConnection() throws SQLException {

        // If tests injected a connection → use it
        if (testConnection != null) {
            return testConnection;
        }

        // Normal production DB connection
        String url = getRequiredProperty(DB_URL_KEY);
        String user = getRequiredProperty(DB_USER_KEY);
        String password = getRequiredProperty(DB_PASSWORD_KEY);

        return DriverManager.getConnection(url, user, password);
    }

    private static String getRequiredProperty(String key) {
        String value = PROPERTIES.getProperty(key);
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalStateException("Missing required property: " + key);
        }
        return value;
    }
}
