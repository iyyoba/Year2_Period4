package example.shoppingcart;

import example.shoppingcart.service.CartService;
import example.shoppingcart.service.LocalizationService;

import java.util.Map;

public class MainApp {

    public static void main(String[] args) {

        String language = "en"; // change to "fr" to test

        LocalizationService localizationService = new LocalizationService();
        Map<String, String> messages = localizationService.getLocalizedStrings(language);

        System.out.println(messages.get("welcome_message"));

        // Sample cart data
        CartService cartService = new CartService();

        int totalItems = 2;
        double totalCost = 100.0;

        int cartId = cartService.saveCartRecord(totalItems, totalCost, language);

        cartService.saveCartItem(cartId, 1, 50.0, 1);
        cartService.saveCartItem(cartId, 2, 25.0, 2);

        System.out.println("Cart saved successfully!");
    }
}
