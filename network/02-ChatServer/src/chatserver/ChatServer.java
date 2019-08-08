package chatserver;

public class ChatServer {

    public static void main(String[] args) {
        ConnectionListener listener = new ConnectionListener();
        new Thread(listener).start();
    } 
}
