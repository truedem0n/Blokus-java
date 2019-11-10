import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;

class GameBoard extends JPanel {

    /**
     * All the declarations
     */
    private static final long serialVersionUID = 1L;
    private int[][] actions = {};
    private MouseEvent event;
    private final Player players;
    private final int GRID_SIZE;
    private final customButton[][] button;
    private final Dictionary<String, String> map;

    /**
     * Create the panel.
     * call the constructor
     */
    public GameBoard(int gridSize, Player players, String[][][] savedArray) {
        this.GRID_SIZE = gridSize;
        button = new customButton[GRID_SIZE][GRID_SIZE];
        map = new Hashtable<>();
        this.players = players;
        players.setPlayingAtBoard(this);
        setUpBoard(savedArray);
    }

    public String[][][] getBoard() {
        String[][][] boardState = new String[GRID_SIZE][GRID_SIZE][1];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Color color = button[i][j].getColor();
                if (color.getRGB() == Color.red.getRGB()) {
                    boardState[i][j][0] = "r";
                } else if (color.getRGB() == Color.green.getRGB()) {
                    boardState[i][j][0] = "g";
                } else if (color.getRGB() == Color.blue.getRGB()) {
                    boardState[i][j][0] = "b";
                } else if (color.getRGB() == Color.orange.getRGB()) {
                    boardState[i][j][0] = "y";
                } else {
                    boardState[i][j][0] = "0";
                }
            }
        }
        return boardState;
    }

    public void setActions(int[][] actions) {
        this.actions = actions;
    }

    // This function sets up the board.
    private void setUpBoard(String[][][] savedArray) {
        // loading the audio files
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/sounds/placed.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                button[j][i] = new customButton(j, i);
                button[j][i].setBackground(Color.white);
                button[j][i].setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

                button[j][i].addMouseListener(new MouseAdapter() {
                    // Placing a shape when mouse left is clicked
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        event = e;
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            placeShapeOnGrid();
                        }
                        if (SwingUtilities.isMiddleMouseButton(e)) {
                            clearShapeOnGrid();
                            players.rightClickFlipV();
                            drawShapeOnGrid();
                        }
                        if (SwingUtilities.isRightMouseButton(e)) {
                            clearShapeOnGrid();
                            players.rightClickFlipH();
                            drawShapeOnGrid();
                        }
                    }

                    // When mouse enters a block draw the shape according to actions array
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        event = e;
                        drawShapeOnGrid();
                    }

                    // When mouse exits from a block clear the predrawn shape
                    @Override
                    public void mouseExited(MouseEvent e) {
                        event = e;
                        clearShapeOnGrid();
                    }
                });
                add(button[j][i]);

                // if a previous session is being loaded then this part of code would place
                // the specific color at the given coordinate
                if (savedArray != null) {
                    if (savedArray[j][i][0].equals("r")) {
                        button[j][i].setBackground(Color.red);
                        button[j][i].setTaken(true);
                    } else if (savedArray[j][i][0].equals("y")) {
                        button[j][i].setBackground(Color.orange);
                        button[j][i].setTaken(true);
                    } else if (savedArray[j][i][0].equals("b")) {
                        button[j][i].setBackground(Color.blue);
                        button[j][i].setTaken(true);
                    } else if (savedArray[j][i][0].equals("g")) {
                        button[j][i].setBackground(Color.green);
                        button[j][i].setTaken(true);
                    }
                }
            }
        }
    }

    public void clearCurrentAction() {
        clearShapeOnGrid();
    }

    public void drawCurrentAction() {
        drawShapeOnGrid();
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
            if (isShapeInsideGrid(x, y)) {
                if ((notPlaceableNWSE(x, y, actions) && (isDiagonallyPlaceable(x, y, actions))) || (isOnGridCorner(x, y, actions) && notPlaceableNWSE(x, y, actions) && !players.hasTakenCorner()))
                    for (int i = 0; i < actions.length; i++) {
                        if (!button[x + actions[i][0]][y + actions[i][1]].isTaken()
                                && isPlaceable(x, y, actions)) {
//                            clip.setFramePosition(0);
//                            clip.start();
                            //thisButton.setBackground(Color.red);
                            button[x + actions[i][0]][y + actions[i][1]].setBackground(players.getColor());
                            button[x + actions[i][0]][y + actions[i][1]].setTaken(true);
                            thisButton.setTaken(true);
                            for (int[] action : actions) {
                                map.put(x + action[0] + "_" + y + action[1], "true");
                                button[x + action[0]][y + action[1]].setTaken(true);
                            }
                            actions = new int[0][0];
                            players.removePanel();
                        }
                    }
                players.setHasTakenCorner(true);
            }
        } catch (Exception s) {
            s.printStackTrace();
            button[x][y].setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    }


    // This function draws the shape on the board according to the given coordinates of a shape
    //
    private void drawShapeOnGrid() {
        MouseEvent e = event;
        customButton thisButton = ((customButton) e.getSource());
        int x = thisButton.getPos()[0];
        int y = thisButton.getPos()[1];
        try {
            for (int[] action : actions) {
                if (isShapeInsideGrid(x, y)) {
                    if (!button[x + action[0]][y + action[1]].isTaken()
                            && isPlaceable(x, y, actions)) {
                        button[x + action[0]][y + action[1]].setBackground(players.getColor());
                    } else {
                        button[x][y].setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                    }
                }
            }
        } catch (Exception s) {
            s.printStackTrace();
            button[x][y].setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    }

    //This function clears the color of a shape when it is drawn out of the block
    private void clearShapeOnGrid() {
        MouseEvent e = event;
        customButton thisButton = ((customButton) e.getSource());
        int x = thisButton.getPos()[0];
        int y = thisButton.getPos()[1];
        try {
            for (int[] action : actions) {
                if (isShapeInsideGrid(x, y)) {
                    System.out.println((x + action[0]) + "," + y + action[1]);
                    if (!button[x + action[0]][y + action[1]].isTaken())
                        button[x + action[0]][y + action[1]].setBackground(Color.white);
                }
            }
        } catch (Exception s) {
            s.printStackTrace();
            button[x][y].setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    }

    // isPlaceable checks if a place is yet taken by a shape or not
    private boolean isPlaceable(int x, int y, int[][] actions) {
        try {
            for (int[] action : actions) {
                if (((x + action[0]) >= 0 &&
                        x + action[0] < this.GRID_SIZE) &&
                        (y + action[1] >= 0 &&
                                y + action[1] < this.GRID_SIZE))
                    if (button[x + action[0]][y + action[1]].isTaken()) {
                        return false;
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // this method checks if from a given location on the board
    // if one step North/West/South/East has any block or not
    // if yes return  false otherwise true
    private boolean notPlaceableNWSE(int x, int y, int[][] actions) {
        System.out.println("notPlaceableNWSE");
        int[][] cardinalActions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        for (int[] action : actions) {
            for (int[] cardinalAction : cardinalActions) {
                // if check to make sure does not go below zero and above the grid size in both x and y directions
                if ((x + action[0] + cardinalAction[0] > 0 && x + action[0] + cardinalAction[0] < this.GRID_SIZE)
                        && (y + action[1] + cardinalAction[1] > 0 && y + action[1] + cardinalAction[1] < this.GRID_SIZE)) {
                    if (button[x + action[0] + cardinalAction[0]][y + action[1] + cardinalAction[1]].isTaken()) {
                        return false;
                    }
                }
            }

        }
        return true;
    }

    private boolean isShapeInsideGrid(int x, int y) {
        boolean returnValue = false;
        for (int[] action : actions) {
            if (
                    (x + action[0] >= 0 && x + action[0] < this.GRID_SIZE) &&
                            (y + action[1] >= 0 && y + action[1] < this.GRID_SIZE)
            ) {
                returnValue = true;
            } else {
                return false;
            }
        }
        return returnValue;
    }

    private boolean isOnGridCorner(int x, int y, int[][] actions) {
        boolean isShapeOnGridCorner = false;
        for (int[] action : actions) {
            if (isShapeInsideGrid(x, y)) {
                if (((x + action[0]) == 0 && (y + action[1]) == 0) ||
                        ((x + action[0]) == 0 && (y + action[1]) == this.GRID_SIZE - 1) ||
                        ((x + action[0]) == this.GRID_SIZE - 1 && (y + action[1]) == 0) ||
                        ((x + action[0]) == this.GRID_SIZE - 1 && (y + action[1]) == this.GRID_SIZE - 1)
                ) {
                    isShapeOnGridCorner = true;
                }
            } else {
                return false;
            }
        }
        return isShapeOnGridCorner;
    }

    // this function checks if there is a shape of same color on the corner of a shape
    private boolean isDiagonallyPlaceable(int x, int y, int[][] actions) {
        int[][] cardinalActions = {{1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
        for (int[] action : actions) {
            for (int[] cardinalAction : cardinalActions) {
                // if check to make sure does not go below zero and above the grid size in both x and y directions
                if ((x + action[0] + cardinalAction[0] >= 0 && x + action[0] + cardinalAction[0] < this.GRID_SIZE)
                        && (y + action[1] + cardinalAction[1] >= 0 && y + action[1] + cardinalAction[1] < this.GRID_SIZE)) {
                    if (button[x + action[0] + cardinalAction[0]][y + action[1] + cardinalAction[1]].isTaken()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
