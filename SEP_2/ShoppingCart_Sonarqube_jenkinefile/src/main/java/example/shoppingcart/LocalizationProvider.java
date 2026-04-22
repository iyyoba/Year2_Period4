package example.shoppingcart;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocalizationProvider {

    private static final Logger LOGGER =
            Logger.getLogger(LocalizationProvider.class.getName());

    private static final List<String> SUPPORTED_LANGUAGES =
            Arrays.asList("en", "fi", "sv");

    private static final String DEFAULT_LANGUAGE = "en";

    public LocalizationProvider() {
        // No initialization needed
    }

    public List<String> getSupportedLanguages() {
        return SUPPORTED_LANGUAGES;
    }

    public String getDefaultLanguage() {
        return DEFAULT_LANGUAGE;
    }

    public Locale toLocale(int index) {
        if (index < 0 || index >= SUPPORTED_LANGUAGES.size()) {
            LOGGER.log(Level.WARNING,
                    "Invalid language index: {0}, using default",
                    index);
            return Locale.ENGLISH;
        }

        return Locale.forLanguageTag(SUPPORTED_LANGUAGES.get(index));
    }
}