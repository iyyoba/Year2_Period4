package facade;

public class Main {
    public static void main(String[] args) {

        ApiFacade api = new ApiFacade();

        try {
            // Chuck Norris API
            String joke = api.getAttributeValueFromJson(
                    "https://api.chucknorris.io/jokes/random",
                    "value"
            );

            System.out.println("Chuck Norris joke:");
            System.out.println(joke);

            // Another API example
            String base = api.getAttributeValueFromJson(
                    "https://api.fxratesapi.com/latest",
                    "base"
            );

            System.out.println("\nFX API base currency:");
            System.out.println(base);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
