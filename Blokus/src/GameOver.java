/**
 * @author: Atul Mehla
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GameOver extends JPanel {

    int[] playerScores = new int[4];
    String winner = "";
    int winnerIndex;

    GameOver(String[] placedBlocks, GUI frame) {
        calGameState(placedBlocks);
        findWinner();
        System.out.println(Arrays.toString(playerScores));
        setBackground(new Color(63, 71, 204));
        setBounds(0, 0, 500, 500);
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Game Over");
        lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 30));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(136, 43, 189, 64);
        add(lblNewLabel);

        JLabel lblPlayerWins = new JLabel("This game is a draw.");
        if (!winner.equals("draw"))
            lblPlayerWins.setText(String.format("%s wins the game with %d points.", winner, playerScores[winnerIndex]));
        lblPlayerWins.setForeground(new Color(255, 255, 255));
        lblPlayerWins.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblPlayerWins.setHorizontalAlignment(SwingConstants.CENTER);
        lblPlayerWins.setBounds(121, 118, 250, 26);
        add(lblPlayerWins);

        JLabel lblWouldLikeTo = new JLabel("Would like to play again?");
        lblWouldLikeTo.setForeground(new Color(255, 255, 255));
        lblWouldLikeTo.setHorizontalAlignment(SwingConstants.CENTER);
        lblWouldLikeTo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblWouldLikeTo.setBounds(113, 155, 212, 24);
        add(lblWouldLikeTo);

        JButton btnNewButton = new JButton("Yes");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI gui = new GUI();
                frame.setVisible(false);
                gui.setVisible(true);
            }
        });
        btnNewButton.setBounds(136, 190, 82, 36);
        btnNewButton.setBackground(new Color(255, 200, 0));
        add(btnNewButton);

        JButton btnNo = new JButton("No");
        btnNo.setBackground(new Color(255, 200, 0));
        btnNo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnNo.setBounds(243, 190, 82, 36);
        add(btnNo);
    }

    //taken from geeksforgeeks.org
    public static int findIndex(int[] arr, int t) {

        // if array is Null
        if (arr == null) {
            return -1;
        }

        // find length of array
        int len = arr.length;
        int i = 0;

        // traverse in the array
        while (i < len) {

            // if the i-th element is t
            // then return the index
            if (arr[i] == t) {
                return i;
            } else {
                i = i + 1;
            }
        }
        return -1;
    }

    private void calGameState(String[] placedBlocksArray) {
        for (int i = 0; i < placedBlocksArray.length; i++) {
            if (placedBlocksArray[i] != null)
                playerScores[i] = placedBlocksArray[i].length();
        }
    }

    private void findWinner() {
        int max = 0;
        for (int i : playerScores) {
            if (max < i) {
                max = i;
                winnerIndex = findIndex(playerScores, i);
                switch (winnerIndex) {
                    case 0:
                        winner = "Red";
                        break;
                    case 1:
                        winner = "Green";
                        break;
                    case 2:
                        winner = "Blue";
                        break;
                    case 3:
                        winner = "Yellow";
                        break;
                }
            } else if (max == i && max != 0) {
                winner = "draw";
                break;
            }
        }
    }
}
