public class GeneralHandler extends Handler {

    @Override
    protected boolean canHandle(Message message) {
        return message.getType() == MessageType.GENERAL;
    }

    @Override
    protected void process(Message message) {
        System.out.println("Analyzing general feedback:");
        System.out.println(message.getContent());
    }
}