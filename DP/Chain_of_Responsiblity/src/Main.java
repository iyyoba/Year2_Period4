public class Main {
    public static void main(String[] args) {

        // Create handlers
        Handler compensation = new CompensationHandler();
        Handler contact = new ContactHandler();
        Handler development = new DevelopmentHandler();
        Handler general = new GeneralHandler();

        // Chain them
        compensation.setNextHandler(contact);
        contact.setNextHandler(development);
        development.setNextHandler(general);

        // Create test messages
        Message m1 = new Message(
                MessageType.COMPENSATION,
                "I want a refund for my order.",
                "user1@email.com"
        );

        Message m2 = new Message(
                MessageType.CONTACT_REQUEST,
                "Please call me regarding my issue.",
                "user2@email.com"
        );

        Message m3 = new Message(
                MessageType.DEVELOPMENT,
                "Add dark mode to the app.",
                "user3@email.com"
        );

        Message m4 = new Message(
                MessageType.GENERAL,
                "Great service, keep it up!",
                "user4@email.com"
        );

        // Process messages
        compensation.handle(m1);
        System.out.println("-----");

        compensation.handle(m2);
        System.out.println("-----");

        compensation.handle(m3);
        System.out.println("-----");

        compensation.handle(m4);
    }
}
