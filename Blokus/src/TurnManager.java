/**
 * @author: Atul Mehla
 */
import javax.swing.*;
class TurnManager {
    private final shapesList[] currentlyPlayingPlayers;
    private final JLabel turnLabel;
    private final String[] playerLable;
    private final Game gui;
    private int currentPlayer = 0;


    TurnManager(shapesList[] players, String[] playerLabels, JLabel lblTurn, Game gui) {
        this.currentlyPlayingPlayers = players;
        this.turnLabel = lblTurn;
        this.playerLable = playerLabels;
        this.gui = gui;
    }

    public String getSurrenderingTextForCurrentPlayer() {
        String player;
        if (getCurrentPlayer().getClass() == Player.class)
            player = "Player";
        else
            player = "AI";
        return (player + " " + ((this.currentPlayer % currentlyPlayingPlayers.length) + 1) + " surrenders.");
    }

    public void setPlayingAtBoard(GameBoard currentBoard) {
        for (shapesList s : currentlyPlayingPlayers) {
            s.setPlayingAtBoard(currentBoard);
        }
    }

    public void nextPlayer() {
        this.currentPlayer++;
        int timesAdded = 0;
        while (true) {
            if (timesAdded >= currentlyPlayingPlayers.length) {
                //GameOver
                JOptionPane.showMessageDialog(null, "Game Over");
                gui.gameOver();

                break;
            } else if (!getCurrentPlayer().isStillPlaying()) {
                this.currentPlayer++;
                timesAdded++;
            } else {
                break;
            }
        }
        this.turnLabel.setText(playerLable[this.currentPlayer % currentlyPlayingPlayers.length]);
    }

    public shapesList getCurrentPlayer() {
        for (shapesList s : currentlyPlayingPlayers) {
            if (s.equals(currentlyPlayingPlayers[this.currentPlayer % currentlyPlayingPlayers.length])) {
                s.setVisible(true);
            } else {
                s.setVisible(false);
            }
        }
        return currentlyPlayingPlayers[this.currentPlayer % currentlyPlayingPlayers.length];
    }
}
