package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private boolean playerX = true; // X boshlaydi

    public TicTacToeGame() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 3));


        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (!clickedButton.getText().equals("")) return;


        clickedButton.setText(playerX ? "X" : "O");
        clickedButton.setForeground(playerX ? Color.RED : Color.BLUE);
        playerX = !playerX;


        if (checkWinner()) {
            JOptionPane.showMessageDialog(this, (playerX ? "O" : "X") + " wins!");
            resetGame();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "Draw!");
            resetGame();
        }
    }

    private boolean checkWinner() {
        // Yutuqni tekshirish
        for (int i = 0; i < 3; i++) {
            if (checkLine(buttons[i][0], buttons[i][1], buttons[i][2]) ||
                    checkLine(buttons[0][i], buttons[1][i], buttons[2][i])) return true;
        }
        return checkLine(buttons[0][0], buttons[1][1], buttons[2][2]) ||
                checkLine(buttons[0][2], buttons[1][1], buttons[2][0]);
    }

    private boolean checkLine(JButton b1, JButton b2, JButton b3) {
        return !b1.getText().equals("") &&
                b1.getText().equals(b2.getText()) &&
                b2.getText().equals(b3.getText());
    }

    private boolean isBoardFull() {
        for (JButton[] row : buttons) {
            for (JButton button : row) {
                if (button.getText().equals("")) return false;
            }
        }
        return true;
    }

    private void resetGame() {
        for (JButton[] row : buttons) {
            for (JButton button : row) {
                button.setText("");
            }
        }
        playerX = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGame().setVisible(true));
    }
}
