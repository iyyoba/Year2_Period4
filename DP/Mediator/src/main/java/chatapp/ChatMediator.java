package chatapp;

public interface ChatMediator {
    void registerClient(ChatClientController client);
    void sendMessage(String message, ChatClientController sender, String receiver);
}