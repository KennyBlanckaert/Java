package tictactoeclient;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToeFrame extends JFrame {
    
    private final JLabel messageLabel = new JLabel("");
    
    private static char PlayerSymbol;
    private static char OpponentSymbol;
    
    private Color PlayerColor;
    private Color OpponentColor;
    
    private Square[] board = new Square[9];   
    private Square currentSquare;
    
    private int currentPosition;
    
    private final Font labelFont = new Font("Arial", Font.BOLD, 16);
    private final ArrayList<Observer> observerList = new ArrayList<>();
    
    public TicTacToeFrame() throws HeadlessException {
        super("Tic Tac Toe");
        
        // Layout GUI
        messageLabel.setBackground(Color.lightGray);
        getContentPane().add(messageLabel, "South");
        
        JPanel boardPanel = new JPanel();
        boardPanel.setBackground(Color.black);
        boardPanel.setLayout(new GridLayout(3, 3, 2, 2));
        
        for (int i = 0; i < board.length; i++) {
            final int j = i;
            board[i] = new Square();
            board[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    // current square is the square clicked by the mouse
                    currentSquare = board[j];
                    currentPosition = j;
                    for(Observer ob : observerList) {
                        ob.update(null, null);
                    }
                }
            });
            boardPanel.add(board[i]);
        }
        getContentPane().add(boardPanel, "Center");
    }
    
    protected boolean wantsToPlayAgain() {
        int response = JOptionPane.showConfirmDialog(this, "Want to play again?", "Tic Tac Toe is Fun", JOptionPane.YES_NO_OPTION);
        return (response == JOptionPane.YES_OPTION);
    }
    
    protected void setSymbol(char symbol) {
        PlayerSymbol = (symbol == 'X' ? 'X' : 'O');
        OpponentSymbol= (symbol == 'X' ? 'O' : 'X');        
        PlayerColor = (symbol == 'X' ? Color.RED : Color.BLUE);
        OpponentColor = (symbol == 'X' ? Color.BLUE : Color.RED);
        
        setTitle("Tic Tac Toe - Player " + symbol);
    }
    
    protected void validMove() {
        messageLabel.setText("Valid move --> PLEASE WAIT");
        currentSquare.label.setFont(labelFont);
        currentSquare.label.setForeground(PlayerColor);
        currentSquare.label.setText(Character.toString(PlayerSymbol));
    }
    
    protected void opponentMoved(int loc) {
        messageLabel.setText("Opponent moved --> YOUR TURN");
        board[loc].label.setFont(labelFont);
        board[loc].label.setForeground(OpponentColor);
        board[loc].label.setText(Character.toString(OpponentSymbol));
    }
    
    protected boolean end(String text) {
        messageLabel.setText(text);
        boolean nog = wantsToPlayAgain();
        clear();
        return nog;
    }
    
    protected void message(String text) {
        messageLabel.setText(text);
    }
    
    protected int getPosition() {
        return currentPosition;
    }
    
    protected void addListener(Observer observer) {
        observerList.add(observer);
    }

    private void clear() {
        for(Square s : board) {
            s.label.setText("");
        }
    }

    /**
     * Graphical square in the client window. Each square is a white panel
     * containing an X or O.
     */
    public class Square extends JPanel {
        
        JLabel label = new JLabel();
        
        public Square() {
            setBackground(Color.WHITE);
            add(label);
        }
    }
}
