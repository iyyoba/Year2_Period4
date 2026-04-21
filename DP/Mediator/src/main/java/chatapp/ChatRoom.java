package chatapp;

import java.util.HashMap;
import java.util.Map;

public class ChatRoom implements ChatMediator {

    private Map<String, ChatClientController> clients = new HashMap<>();

    @Override
    public void registerClient(ChatClientController client) {
        clients.put(client.getUsername(), client);
    }

    @Override
    public void sendMessage(String message, ChatClientController sender, String receiver) {
        ChatClientController target = clients.get(receiver);

        if (target != null) {
            target.receiveMessage(sender.getUsername() + ": " + message);
        }
    }

    public Map<String, ChatClientController> getClients() {
        return clients;
    }
}