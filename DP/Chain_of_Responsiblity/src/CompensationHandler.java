public class CompensationHandler extends Handler {

    @Override
    protected boolean canHandle(Message message) {
        return message.getType() == MessageType.COMPENSATION;
    }

    @Override
    protected void process(Message message) {
        System.out.println("Processing compensation claim from " + message.getEmail());
        System.out.println("Result: Claim reviewed and approved.");
    }
}
