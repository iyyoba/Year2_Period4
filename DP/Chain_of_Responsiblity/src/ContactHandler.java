public class ContactHandler extends Handler {

    @Override
    protected boolean canHandle(Message message) {
        return message.getType() == MessageType.CONTACT_REQUEST;
    }

    @Override
    protected void process(Message message) {
        System.out.println("Forwarding contact request from " + message.getEmail());
        System.out.println("Result: Sent to customer service.");
    }
}
