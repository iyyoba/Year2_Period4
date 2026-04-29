package facade;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ApiFacade {

    public String getAttributeValueFromJson(String urlString, String attributeName)
            throws IllegalArgumentException, IOException {

        String json = getJsonFromApi(urlString);
        return extractAttribute(json, attributeName);
    }

    // Step 1: HTTP request
    private String getJsonFromApi(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        int status = con.getResponseCode();
        if (status != 200) {
            throw new IOException("HTTP request failed with status: " + status);
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        con.disconnect();

        return content.toString();
    }

    // Step 2: JSON parsing
    private String extractAttribute(String json, String attributeName) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(json);

            Object value = obj.get(attributeName);

            if (value == null) {
                throw new IllegalArgumentException(
                        "Attribute '" + attributeName + "' not found.");
            }

            return value.toString();

        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid JSON format");
        }
    }
}
