package example.shoppingcart;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    // ----------------------------
    // calculateItemTotal
    // ----------------------------

    @Test
    void calculateItemTotal_shouldReturnCorrectValue() {
        assertEquals(20.0, ShoppingCart.calculateItemTotal(10.0, 2));
    }

    @Test
    void calculateItemTotal_shouldThrow_whenPriceNegative() {
        assertThrows(IllegalArgumentException.class, this::callNegativePrice);
    }

    @Test
    void calculateItemTotal_shouldThrow_whenQuantityNegative() {
        assertThrows(IllegalArgumentException.class, this::callNegativeQuantity);
    }

    // ----------------------------
    // calculateCartTotal
    // ----------------------------

    @Test
    void calculateCartTotal_shouldReturnCorrectSum() {
        double result = ShoppingCart.calculateCartTotal(
                List.of(10.0, 5.0),
                List.of(2, 3)
        );

        assertEquals(35.0, result);
    }

    @Test
    void calculateCartTotal_shouldThrow_whenNullPrices() {
        assertThrows(IllegalArgumentException.class, this::callNullPrices);
    }

    @Test
    void calculateCartTotal_shouldThrow_whenNullQuantities() {
        assertThrows(IllegalArgumentException.class, this::callNullQuantities);
    }

    @Test
    void calculateCartTotal_shouldThrow_whenSizeMismatch() {
        assertThrows(IllegalArgumentException.class, this::callSizeMismatch);
    }

    // ----------------------------
    // selectLocale
    // ----------------------------

    @Test
    void selectLocale_shouldReturnFinnish() {
        assertEquals(Locale.forLanguageTag("fi-FI"), ShoppingCart.selectLocale(2));
    }

    @Test
    void selectLocale_shouldReturnSwedish() {
        assertEquals(Locale.forLanguageTag("sv-SE"), ShoppingCart.selectLocale(3));
    }

    @Test
    void selectLocale_shouldReturnJapanese() {
        assertEquals(Locale.JAPAN, ShoppingCart.selectLocale(4));
    }

    @Test
    void selectLocale_shouldReturnDefaultEnglish() {
        assertEquals(Locale.US, ShoppingCart.selectLocale(999));
    }

    @Test
    void selectLocale_shouldReturnEnglishExplicitly() {
        assertEquals(Locale.US, ShoppingCart.selectLocale(1));
    }

    // ----------------------------
    // ⭐ NEW: MAIN METHOD COVERAGE BOOST (IMPORTANT)
    // ----------------------------

    @Test
    void main_shouldExecuteFullFlow_withoutCrashing() {
        String input = "1\n1\n10\n2\n"; // language, itemCount, price, quantity

        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        try {
            assertDoesNotThrow(() -> ShoppingCart.main(new String[]{}));
        } finally {
            System.setIn(originalIn);
        }
    }


    private void callNegativePrice() {
        ShoppingCart.calculateItemTotal(-1, 2);
    }

    private void callNegativeQuantity() {
        ShoppingCart.calculateItemTotal(10, -2);
    }

    private void callNullPrices() {
        ShoppingCart.calculateCartTotal(null, List.of(1));
    }

    private void callNullQuantities() {
        ShoppingCart.calculateCartTotal(List.of(1.0), null);
    }

    private void callSizeMismatch() {
        ShoppingCart.calculateCartTotal(
                List.of(1.0, 2.0),
                List.of(1)
        );
    }
}