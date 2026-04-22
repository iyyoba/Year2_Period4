package example.shoppingcart;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationProviderTest {

    private final LocalizationProvider provider = new LocalizationProvider();

    @Test
    void getSupportedLanguages_shouldReturnAllLanguages() {
        List<String> languages = provider.getSupportedLanguages();

        assertNotNull(languages);
        assertEquals(3, languages.size());
        assertTrue(languages.contains("en"));
        assertTrue(languages.contains("fi"));
        assertTrue(languages.contains("sv"));
    }

    @Test
    void getDefaultLanguage_shouldReturnEnglish() {
        assertEquals("en", provider.getDefaultLanguage());
    }

    @Test
    void toLocale_shouldReturnEnglish_whenIndexInvalidNegative() {
        Locale result = provider.toLocale(-1);
        assertEquals(Locale.ENGLISH, result);
    }

    @Test
    void toLocale_shouldReturnEnglish_whenIndexTooLarge() {
        Locale result = provider.toLocale(999);
        assertEquals(Locale.ENGLISH, result);
    }

    @Test
    void toLocale_shouldReturnFinnishLocale() {
        Locale result = provider.toLocale(1);
        assertEquals(Locale.forLanguageTag("fi"), result);
    }

    @Test
    void toLocale_shouldReturnSwedishLocale() {
        Locale result = provider.toLocale(2);
        assertEquals(Locale.forLanguageTag("sv"), result);
    }

    @Test
    void toLocale_shouldReturnEnglishLocale() {
        Locale result = provider.toLocale(0);
        assertEquals(Locale.forLanguageTag("en"), result);
    }
}