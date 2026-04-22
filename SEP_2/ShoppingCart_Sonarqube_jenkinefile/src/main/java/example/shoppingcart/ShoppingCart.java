package example.shoppingcart;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ShoppingCart {

    private static final Logger LOGGER = Logger.getLogger(ShoppingCart.class.getName());

    private static final int LANGUAGE_ENGLISH = 1;
    private static final int LANGUAGE_FINNISH = 2;
    private static final int LANGUAGE_SWEDISH = 3;
    private static final int LANGUAGE_JAPANESE = 4;

    private ShoppingCart() {
        // Utility class
    }

    public static double calculateItemTotal(double price, int quantity) {
        validatePrice(price);
        validateQuantity(quantity);
        return price * quantity;
    }

    public static double calculateCartTotal(List<Double> prices, List<Integer> quantities) {
        if (prices == null || quantities == null) {
            throw new IllegalArgumentException("Prices and quantities must not be null");
        }
        if (prices.size() != quantities.size()) {
            throw new IllegalArgumentException("Prices and quantities must have the same size");
        }

        double total = 0.0;
        for (int i = 0; i < prices.size(); i++) {
            total += calculateItemTotal(prices.get(i), quantities.get(i));
        }
        return total;
    }

    public static Locale selectLocale(int choice) {
        switch (choice) {
            case LANGUAGE_FINNISH:
                return Locale.forLanguageTag("fi-FI");
            case LANGUAGE_SWEDISH:
                return  Locale.forLanguageTag("sv-SE");
            case LANGUAGE_JAPANESE:
                return Locale.JAPAN;
            case LANGUAGE_ENGLISH:
            default:
                return Locale.US;
        }
    }

    public static void main(String[] args) {
        configureLogger();

        try (Scanner scanner = new Scanner(System.in)) {

            scanner.useLocale(Locale.US);

            printLanguageOptions();
            int choice = readInt(scanner);

            Locale locale = selectLocale(choice);
            ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", locale);

            int itemCount = promptForItemCount(scanner, messages);

            List<Double> prices = new ArrayList<>();
            List<Integer> quantities = new ArrayList<>();

            for (int i = 0; i < itemCount; i++) {
                prices.add(promptForPrice(scanner, messages));
                quantities.add(promptForQuantity(scanner, messages));
            }

            double totalCost = calculateCartTotal(prices, quantities);

            logInfo(() -> messages.getString("total.cost") + " " + totalCost);
        }
    }

    private static void configureLogger() {
        LOGGER.setLevel(Level.ALL);
    }

    private static void printLanguageOptions() {
        logInfo(() -> "Select language / Valitse kieli / Välj språk / 言語を選択:");
        logInfo(() -> "1. English\n2. Finnish\n3. Swedish\n4. Japanese");
    }

    private static int promptForItemCount(Scanner scanner, ResourceBundle messages) {
        logInfo(() -> messages.getString("enter.items"));
        return readInt(scanner);
    }

    private static double promptForPrice(Scanner scanner, ResourceBundle messages) {
        logInfo(() -> messages.getString("enter.price"));
        double price = readDouble(scanner);
        validatePrice(price);
        return price;
    }

    private static int promptForQuantity(Scanner scanner, ResourceBundle messages) {
        logInfo(() -> messages.getString("enter.quantity"));
        int quantity = readInt(scanner);
        validateQuantity(quantity);
        return quantity;
    }

    private static int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.next();
            logWarning(() -> "Invalid input. Please enter an integer.");
        }
        return scanner.nextInt();
    }

    private static double readDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            scanner.next();
            logWarning(() -> "Invalid input. Please enter a number.");
        }
        return scanner.nextDouble();
    }

    private static void validatePrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }

    private static void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
    }


    private static void logInfo(LogMessageSupplier supplier) {
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(supplier.get());
        }
    }

    private static void logWarning(LogMessageSupplier supplier) {
        if (LOGGER.isLoggable(Level.WARNING)) {
            LOGGER.warning(supplier.get());
        }
    }

    @FunctionalInterface
    private interface LogMessageSupplier {
        String get();
    }
}