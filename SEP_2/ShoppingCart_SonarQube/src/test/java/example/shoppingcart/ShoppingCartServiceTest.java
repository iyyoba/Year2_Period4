package example.shoppingcart;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartServiceTest {

    private final ShoppingCartService service = new ShoppingCartService();

    @Test
    void calculateTotal_shouldReturnCorrectSum() {
        List<Double> prices = List.of(10.0, 5.0, 2.5);
        List<Integer> quantities = List.of(2, 3, 4);

        double result = service.calculateTotal(prices, quantities);

        assertEquals(10.0 * 2 + 5.0 * 3 + 2.5 * 4, result);
    }

    @Test
    void calculateTotal_shouldReturnZeroForEmptyLists() {
        List<Double> prices = List.of();
        List<Integer> quantities = List.of();

        double result = service.calculateTotal(prices, quantities);

        assertEquals(0.0, result);
    }

    @Test
    void calculateTotal_shouldHandleSingleItem() {
        List<Double> prices = List.of(7.5);
        List<Integer> quantities = List.of(3);

        double result = service.calculateTotal(prices, quantities);

        assertEquals(22.5, result);
    }
}