import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameBoard extends JPanel {

    /**
     * All the declarations
     */
    private static final long serialVersionUID = 1L;
    private int[][] actions = {{0, 0}};
    private MouseEvent event;
    private Player players;
    private int GRID_SIZE = 16;
    private customButton[][] button;
    private Dictionary<String, String> map;
    AudioInputStream audioInputStream;
    Clip clip;

    /**
     * Create the panel.
     * call the constructor
     */
    public GameBoard(int gridSize, Player players, String[][][] savedArray) {
        this.GRID_SIZE = gridSize;
        button = new customButton[GRID_SIZE][GRID_SIZE];
        map = new Hashtable<String, String>();
        this.players = players;
        players.setPlayingAtBoard(this);
        setUpBoard(savedArray);
    }


    public String[][][] getBoard() {
        String[][][] boardState = new String[GRID_SIZE][GRID_SIZE][3];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Color color = button[i][j].getColor();
                if (color.getRGB() == Color.red.getRGB()) {
                    boardState[i][j][0] = "r";
                    boardState[i][j][1] = String.valueOf(i);
                    boardState[i][j][2] = String.valueOf(j);
                } else if (color.getRGB() == Color.green.getRGB()) {
                    boardState[i][j][0] = "g";
                    boardState[i][j][1] = String.valueOf(i);
                    boardState[i][j][2] = String.valueOf(j);
                } else if (color.getRGB() == Color.blue.getRGB()) {
                    boardState[i][j][0] = "b";
                    boardState[i][j][1] = String.valueOf(i);
                    boardState[i][j][2] = String.valueOf(j);
                } else if (color.getRGB() == Color.yellow.getRGB()) {
                    boardState[i][j][0] = "y";
                    boardState[i][j][1] = String.valueOf(i);
                    boardState[i][j][2] = String.valueOf(j);
                } else {
                    boardState[i][j][0] = "0";
                    boardState[i][j][1] = String.valueOf(i);
                    boardState[i][j][2] = String.valueOf(j);
                }
            }
            //boardState += "\n";
        }
        return boardState;
    }

    public void setActions(int[][] actions) {
        this.actions = actions;
    }

    private void setUpBoard(String[][][] savedArray) {
        // loading the audio files
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File("src/sounds/placed.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < GRID_SIZE; i++)
            for (int j = 0; j < GRID_SIZE; j++) {
                button[j][i] = new customButton("", j, i);
                button[j][i].setBackground(Color.white);
                button[j][i].setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
                if (savedArray != null) {
                    if(savedArray[j][i][0]=="r"){
                        button[j][i].setBackground(Color.red);
                        button[j][i].setTaken(true);
                    }
                }
                button[j][i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        event = e;

                        if (SwingUtilities.isLeftMouseButton(e)) {
                            placeShapeOnGrid();
                        }
                        if (SwingUtilities.isMiddleMouseButton(e)) {
                            clearGrid();
                            players.rightClickFlipV();
                            drawOnGrid();
                        }
                        if (SwingUtilities.isRightMouseButton(e)) {
                            clearGrid();
                            players.rightClickFlipH();
                            drawOnGrid();

                        }
                    }


                    @Override
                    public void mouseEntered(MouseEvent e) {
                        event = e;
                        drawOnGrid();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        event = e;
                        clearGrid();
                    }
                });
                add(button[j][i]);
            }
        // testing blokus rules with use of this block
        button[10][10].setTaken(true);
        button[10][10].setBackground(Color.black);
    }

    public void clearCurrentAction() {
        clearGrid();
    }

    public void drawCurrentAction() {
        drawOnGrid();
    }

    private void placeShapeOnGrid() {
        MouseEvent e = event;
        try {
            getBoard();
        } catch (Exception s) {
            s.printStackTrace();

        }

        customButton thisButton = ((customButton) e.getSource());
        int x = thisButton.getPos()[0];
        int y = thisButton.getPos()[1];
        try {
            if (notPlaceableNWSE(x, y, actions))
                if (isDiagonallyPlaceable(x, y, actions))
                    for (int i = 0; i < actions.length; i++) {
                        if (!button[x + actions[i][0]][y + actions[i][1]].isTaken()
                                && isPlaceable(x, y, actions)) {
                            clip.setFramePosition(0);
                            clip.start();
                            thisButton.setBackground(Color.red);
                            button[x + actions[i][0]][y + actions[i][1]].setBackground(Color.red);
                            button[x + actions[i][0]][y + actions[i][1]].setTaken(true);
                            thisButton.setTaken(true);
                            for (int j = 0; j < actions.length; j++) {
                                map.put(x + actions[j][0] + "_" + y + actions[j][1], "true");
                                button[x + actions[j][0]][y + actions[j][1]].setTaken(true);
                            }
                            actions = new int[0][0];
                        }

                    }
        } catch (Exception s) {
            button[x][y].setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }


    }

    private void drawOnGrid() {
        MouseEvent e = event;
        customButton thisButton = ((customButton) e.getSource());
        int x = thisButton.getPos()[0];
        int y = thisButton.getPos()[1];
        try {
            for (int i = 0; i < actions.length; i++) {
                if (!button[x + actions[i][0]][y + actions[i][1]].isTaken()
                        && isPlaceable(x, y, actions)) {
                    button[x + actions[i][0]][y + actions[i][1]].setBackground(Color.red);
                } else {
                    button[x][y].setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                }
            }
        } catch (Exception s) {
            button[x][y].setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    }

    private void clearGrid() {
        MouseEvent e = event;
        customButton thisButton = ((customButton) e.getSource());
        int x = thisButton.getPos()[0];
        int y = thisButton.getPos()[1];
        try {
            for (int i = 0; i < actions.length; i++) {
                if (!button[x + actions[i][0]][y + actions[i][1]].isTaken())
                    button[x + actions[i][0]][y + actions[i][1]].setBackground(Color.white);

            }
        } catch (Exception s) {
            button[x][y].setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    }

    // checks if piece is placeable or not
    private boolean isPlaceable(int x, int y, int[][] actions) {
        try {
            for (int i = 0; i < actions.length; i++) {
                if (button[x + actions[i][0]][y + actions[i][1]].isTaken()) {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // this method checks if from a given location on the board
    // if one step North/West/South/East has any block or not
    // if yes return  false otherwise true
    private boolean notPlaceableNWSE(int x, int y, int[][] actions) {
        int[][] cardinalActions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        for (int i = 0; i < actions.length; i++) {
            for (int j = 0; j < cardinalActions.length; j++) {
                // if check to make sure does not go below zero and above the grid size in both x and y directions
                if ((x + actions[i][0] + cardinalActions[j][0] > 0 && x + actions[i][0] + cardinalActions[j][0] < this.GRID_SIZE)
                        && (y + actions[i][1] + cardinalActions[j][1] > 0 && y + actions[i][1] + cardinalActions[j][1] < this.GRID_SIZE)) {
                    if (button[x + actions[i][0] + cardinalActions[j][0]][y + actions[i][1] + cardinalActions[j][1]].isTaken()) {
                        return false;
                    }
                }
            }

        }
        return true;
    }

    private boolean isDiagonallyPlaceable(int x, int y, int[][] actions) {
        int[][] cardinalActions = {{1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
        for (int i = 0; i < actions.length; i++) {
            for (int j = 0; j < cardinalActions.length; j++) {
                // if check to make sure does not go below zero and above the grid size in both x and y directions
                //if((x+actions[i][0]+cardinalActions[j][0]>0 && x+actions[i][0]+cardinalActions[j][0]<this.GRID_SIZE)
                //		&&(y+actions[i][1]+cardinalActions[j][1]>0 && y+actions[i][1]+cardinalActions[j][1]<this.GRID_SIZE)){
                if (button[x + actions[i][0] + cardinalActions[j][0]][y + actions[i][1] + cardinalActions[j][1]].isTaken()) {
                    return true;
                    //}
                }
            }

        }
        System.out.println("Working");
        if ((x == 0 && y == 0 && !button[x][y].isTaken()) || (x == 0 && y == this.GRID_SIZE - 1 && !button[x][y].isTaken())
                || (x == this.GRID_SIZE - 1 && y == 0 && !button[x][y].isTaken()) || (x == this.GRID_SIZE - 1 && y == this.GRID_SIZE - 1 && !button[x][y].isTaken()))
            return true;
        return false;
    }
}
