package example.shoppingcart;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {

    @Test
    public void testCalculateCartTotal() {
        List<Double> prices = List.of(10.0, 5.0);
        List<Integer> quantities = List.of(2, 3);

        double total = ShoppingCart.calculateCartTotal(prices, quantities);

        assertEquals(10.0 * 2 + 5.0 * 3, total);
    }
}
