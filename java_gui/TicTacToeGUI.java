import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI {
    private JFrame frame;
    private JButton[][] buttons;
    private TicTacToeGame game;

    public TicTacToeGUI() {
        game = new TicTacToeGame();
        frame = new JFrame("Tic-Tac-Toe");
        buttons = new JButton[3][3];
        initializeUI();
    }

    private void initializeUI() {
        frame.setLayout(new GridLayout(3, 3));
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton(" ");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                frame.add(buttons[i][j]);
            }
        }
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (game.makeMove(row, col)) {
                buttons[row][col].setText(String.valueOf(game.getCurrentPlayer()));
                if (game.checkForWin()) {
                    JOptionPane.showMessageDialog(frame, "Player " + game.getCurrentPlayer() + " wins!");
                    resetGame();
                } else if (game.isBoardFull()) {
                    JOptionPane.showMessageDialog(frame, "It's a draw!");
                    resetGame();
                } else {
                    game.switchPlayer();
                }
            }
        }
    }

    private void resetGame() {
        game = new TicTacToeGame();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(" ");
            }
        }
    }
}
