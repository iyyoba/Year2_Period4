package adapter;

public class Main {
    public static void main(String[] args) {

        NewDateInterface date = new CalendarToNewDateAdapter();

        // Set initial date
        date.setDay(10);
        date.setMonth(4);
        date.setYear(2026);

        System.out.println("Initial Date:");
        printDate(date);

        // Advance by 5 days
        date.advanceDays(5);
        System.out.println("\nAfter advancing 5 days:");
        printDate(date);

        // Advance by 30 days
        date.advanceDays(30);
        System.out.println("\nAfter advancing 30 days:");
        printDate(date);
    }

    private static void printDate(NewDateInterface date) {
        System.out.println(
                date.getDay() + "/" +
                        date.getMonth() + "/" +
                        date.getYear()
        );
    }
}