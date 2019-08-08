package chatserver;

import entities.User;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


class ChatRoom extends Thread {
    
    // Fields
    private List<User> users;
    
    // Constructor 
    public ChatRoom() {
        this.users = new ArrayList<>();
    }
    
    // Methods
    public void add(User user) {
        this.users.add(user);

        // Announce the new user
        spread(user, "<System>: " + user.getUsername() + " added to the chatroom.");

        // Welcome new user
        user.send("<System>: Welcome to the chatroom!");
        user.send("<System>: There are currently " + (this.users.size() - 1) + " user(s) in this room");
    }
    
    /* Message for all users */
    public void broadcast(String message) {
        for (User user : this.users) {
            user.send(message);
        }
    }
    
    /* Spread a message */
    public void spread(User sender, String message) {
        for (User user : this.users) {
            if (!user.getUsername().equals(sender.getUsername())) {
                user.send(message);
            }
        }
    }

    // Task
    @Override
    public void run() {
        boolean active = true;
        while(active) {
            try {
                for (User user : this.users) {

                    String message = user.receive();
                    if (!message.equals("")) {
                        spread(user, "<" + user.getUsername() + ">: " + message);
                    }
                }
            }
            catch (ConcurrentModificationException ex) {
                // This is needed to catch the ConcurrentException. Actually, this is the behaviour we want.
                // The loop should break when a new user is added to the list, to make sure every user (also the new one) receives possible messages!
                Logger.getLogger(ChatRoom.class.getName()).log(Level.SEVERE, "User joined the chatroom, resetting loop...");
            }
        }
    }
    
}
