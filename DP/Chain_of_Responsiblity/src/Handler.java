public abstract class Handler {
    protected Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handle(Message message) {
        if (canHandle(message)) {
            process(message);
        } else if (nextHandler != null) {
            nextHandler.handle(message);
        } else {
            System.out.println("No handler found for message: " + message.getContent());
        }
    }

    protected abstract boolean canHandle(Message message);
    protected abstract void process(Message message);
}