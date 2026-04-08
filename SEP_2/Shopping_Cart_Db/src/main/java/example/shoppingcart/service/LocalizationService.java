package example.shoppingcart.service;

import example.shoppingcart.db.DatabaseConnection;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class LocalizationService {

    public Map<String, String> getLocalizedStrings(String language) {
        Map<String, String> messages = new HashMap<>();

        String sql = "SELECT `key`, value FROM localization_strings WHERE language = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, language);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                messages.put(rs.getString("key"), rs.getString("value"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }
}