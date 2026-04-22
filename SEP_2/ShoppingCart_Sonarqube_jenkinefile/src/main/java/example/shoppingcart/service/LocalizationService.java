package example.shoppingcart.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LocalizationService {

    private static final String QUERY =
            "SELECT string_key, string_value FROM localization_strings WHERE language = ?";

    private final Connection connection;

    public LocalizationService(Connection connection) {
        this.connection = Objects.requireNonNull(connection, "Connection must not be null");
    }

    public Map<String, String> getLocalizedStrings(String language) {
        if (language == null || language.isBlank()) {
            throw new IllegalArgumentException("Language must not be null or empty");
        }

        Map<String, String> localized = new HashMap<>();

        try (PreparedStatement stmt = connection.prepareStatement(QUERY)) {
            stmt.setString(1, language);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    localized.put(
                            rs.getString("string_key"),
                            rs.getString("string_value")
                    );
                }
            }

        } catch (SQLException ex) {
            throw new IllegalStateException(
                    "Failed to load localization strings for language: " + language,
                    ex
            );
        }

        return localized;
    }
}