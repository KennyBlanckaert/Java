package tictactoeclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Kenny Blanckaert
 */
public class TicTacToeClient implements Observer, AutoCloseable {
    
    private static final int PORT = 9001;
    private final TicTacToeFrame frame;
    private final String serverAddress;
    private final Socket socket;
    private final BufferedReader commandReceiver;
    private final PrintWriter serverChannel;
    private boolean gameStarted = false;
    
    // Constructor
    private TicTacToeClient(String serverAddress, TicTacToeFrame frame) throws IOException {
        this.frame = frame;
        this.serverAddress = serverAddress;
        this.socket = new Socket(serverAddress, PORT);
        this.commandReceiver = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.serverChannel = new PrintWriter(this.socket.getOutputStream(), true);
    }
    
    // Functions
    public void play() throws IOException {
        
        boolean playing = true;
        while (playing) {
            String command = this.commandReceiver.readLine();
            
            if (command.startsWith("WELCOME")) {
                frame.message("Connection successful: waiting for opponent...");
            }
            else if (command.startsWith("SYMBOL")) {
                frame.setSymbol(command.charAt(7));
                gameStarted = true;
            }
            else if (command.startsWith("MESSAGE")) {
                frame.message(command.substring(8));
            }
            else if (command.startsWith("VALID_MOVE")) {
                frame.validMove();
            }
            else if (command.startsWith("OPPONENT_MOVED")) {
                int location = Integer.parseInt(command.substring(14));
                frame.opponentMoved(location);
            }
            else if (command.startsWith("VICTORY")) {
                playing = gameOver("You win!");
                gameStarted = false;
            }               
            else if (command.startsWith("TIE")) {
                playing = gameOver("Draw!");
                gameStarted = false;
            }
            else if (command.startsWith("DEFEAT")) {
                playing = gameOver("You lose!");
                gameStarted = false;
            }
            else if (command.startsWith("QUIT")) {
                playing = gameOver("Your opponent has left the game");
                gameStarted = false;
            }
        }
        this.serverChannel.println("QUIT");
        this.frame.dispose();
    }
    
    public boolean gameOver(String message) {
        boolean replay = this.frame.end(message);
        if (replay) {
            this.serverChannel.println("REPLAY");
        }
        
        return replay;
    }

    // Overriden functions
    @Override
    public void update(Observable o, Object arg) {
        // Called while playing when JFrame changes
        if (gameStarted) {
            this.serverChannel.println("MOVE " + this.frame.getPosition());
        }
    }

    @Override
    public void close() {
        try {
            this.socket.close();
        } 
        catch (IOException ex) {
            frame.message("Lost the connection with the server");
        }
    }
    
    // Start method
    public static void main(String[] args) {
        TicTacToeFrame frame = new TicTacToeFrame();
        new Thread(() -> {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setVisible(true);
            frame.setResizable(false);
        }).start();

        String serverAddress = ( args.length == 0 ? "localhost" : args[1] );
        try (TicTacToeClient client = new TicTacToeClient(serverAddress, frame)) {
            frame.addListener(client);
            client.play();
        } 
        catch (Exception ex) {
            System.exit(0);
        }
    }
}
