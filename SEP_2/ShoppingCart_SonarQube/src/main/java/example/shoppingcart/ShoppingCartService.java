package example.shoppingcart;

import java.util.List;

public class ShoppingCartService {

    public double calculateTotal(List<Double> prices, List<Integer> quantities) {
        double total = 0;
        for (int i = 0; i < prices.size(); i++) {
            total += prices.get(i) * quantities.get(i);
        }
        return total;
    }
}
