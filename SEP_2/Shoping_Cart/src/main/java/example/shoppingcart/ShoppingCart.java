package example.shoppingcart;
import java.util.*;

public class ShoppingCart {


    public static double calculateItemTotal(double price, int quantity) {
        return price * quantity;
    }

    public static double calculateCartTotal(List<Double> prices, List<Integer> quantities) {
        double total = 0;
        for (int i = 0; i < prices.size(); i++) {
            total += calculateItemTotal(prices.get(i), quantities.get(i));
        }
        return total;
    }


    public static Locale selectLocale(int choice) {
        switch (choice) {
            case 2: return new Locale("fi", "FI");
            case 3: return new Locale("sv", "SE");
            case 4: return new Locale("ja", "JP");
            default: return new Locale("en", "US");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        scanner.useLocale(Locale.US);


        System.out.println("Select language / Valitse kieli / Välj språk / 言語を選択:");
        System.out.println("1. English\n2. Finnish\n3. Swedish\n4. Japanese");

        int choice = scanner.nextInt();
        Locale locale = selectLocale(choice);

        ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", locale);

        System.out.println(messages.getString("enter.items"));
        int itemCount = scanner.nextInt();

        List<Double> prices = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();

        for (int i = 0; i < itemCount; i++) {
            System.out.println(messages.getString("enter.price"));
            double price = scanner.nextDouble();
            prices.add(price);

            System.out.println(messages.getString("enter.quantity"));
            int quantity = scanner.nextInt();
            quantities.add(quantity);
        }

        double totalCost = calculateCartTotal(prices, quantities);

        System.out.println(messages.getString("total.cost") + " " + totalCost);
    }
}