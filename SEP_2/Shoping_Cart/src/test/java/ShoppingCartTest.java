import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class ShoppingCartTest {

    @Test
    void testItemTotal() {
        assertEquals(20.0, ShoppingCart.calculateItemTotal(10, 2));
    }

    @Test
    void testCartTotal() {
        List<Double> prices = Arrays.asList(10.0, 5.0);
        List<Integer> quantities = Arrays.asList(2, 3);

        double result = ShoppingCart.calculateCartTotal(prices, quantities);

        assertEquals(35.0, result);
    }
}