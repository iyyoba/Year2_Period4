
package recommendation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Recommendation> recommendations = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedData();

        while (true) {
            System.out.println("\n--- Book Recommendation System ---");
            System.out.println("1. View Recommendations");
            System.out.println("2. Clone Recommendation");
            System.out.println("3. Create New Recommendation");
            System.out.println("4. Modify Recommendation");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewRecommendations();
                case 2 -> cloneRecommendation();
                case 3 -> createRecommendation();
                case 4 -> modifyRecommendation();
                case 5 -> System.exit(0);
            }
        }
    }

    private static void seedData() {
        Recommendation sciFi = new Recommendation("Sci-Fi Fans");
        sciFi.addBook(new Book("Dune", "Frank Herbert", "Sci-Fi", 1965));
        sciFi.addBook(new Book("Neuromancer", "William Gibson", "Cyberpunk", 1984));

        recommendations.add(sciFi);
    }

    private static void viewRecommendations() {
        for (int i = 0; i < recommendations.size(); i++) {
            System.out.println("\n[" + i + "]");
            recommendations.get(i).display();
        }
    }

    private static void cloneRecommendation() {
        viewRecommendations();
        System.out.print("Select index to clone: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        Recommendation cloned = recommendations.get(index).clone();

        System.out.print("Enter new audience: ");
        cloned.setTargetAudience(scanner.nextLine());

        recommendations.add(cloned);
        System.out.println("Cloned successfully!");
    }

    private static void createRecommendation() {
        System.out.print("Enter audience: ");
        String audience = scanner.nextLine();

        Recommendation rec = new Recommendation(audience);
        addBooks(rec);

        recommendations.add(rec);
    }

    private static void modifyRecommendation() {
        viewRecommendations();
        System.out.print("Select index: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        Recommendation rec = recommendations.get(index);

        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Change Audience");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> addBooks(rec);
            case 2 -> {
                System.out.print("Book index: ");
                int i = scanner.nextInt() - 1;
                rec.removeBook(i);
            }
            case 3 -> {
                System.out.print("New audience: ");
                rec.setTargetAudience(scanner.nextLine());
            }
        }
    }

    private static void addBooks(Recommendation rec) {
        while (true) {
            System.out.print("Title (or 'done'): ");
            String title = scanner.nextLine();
            if (title.equalsIgnoreCase("done")) break;

            System.out.print("Author: ");
            String author = scanner.nextLine();

            System.out.print("Genre: ");
            String genre = scanner.nextLine();

            System.out.print("Year: ");
            int year = scanner.nextInt();
            scanner.nextLine();

            rec.addBook(new Book(title, author, genre, year));
        }
    }
}