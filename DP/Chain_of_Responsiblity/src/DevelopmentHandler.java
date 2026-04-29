public class DevelopmentHandler extends Handler {

    @Override
    protected boolean canHandle(Message message) {
        return message.getType() == MessageType.DEVELOPMENT;
    }

    @Override
    protected void process(Message message) {
        System.out.println("Logging development suggestion:");
        System.out.println(message.getContent());
    }
}
