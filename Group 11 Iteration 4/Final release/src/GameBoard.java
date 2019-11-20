/*
  @author: Atul Mehla
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class GameBoard extends JPanel {
    /**
     * All the declarations
     */
    private static final long serialVersionUID = 1L;
    private int[][] actions = {};
    private MouseEvent event;
    //private final Player players;
    private final int GRID_SIZE;
    private final TurnManager turnHandler;
    private final customButton[][] button;
    private Color currentPlayingPlayerColor = Color.white;
    // private final Dictionary<String, String> map;

    /**
     * Create the panel.
     * call the constructor
     */
    public GameBoard(int gridSize, TurnManager turnHandler, String[][] savedArray) {
        this.GRID_SIZE = gridSize;
        button = new customButton[GRID_SIZE][GRID_SIZE];
        this.turnHandler = turnHandler;
        this.turnHandler.setPlayingAtBoard(this);
        setUpBoard(savedArray);
    }

    // --Commented out by Inspection START (11/17/2019 2:11 PM):
    public ArrayList<int[]> getLegalActionAi() {
        ArrayList<int[]> legalPlaces = new ArrayList<>();
        for (int x = 0; x < this.GRID_SIZE; x++) {
            for (int y = 0; y < this.GRID_SIZE; y++) {
                if (isShapeInsideGrid(x, y)) {
                    if (((notPlaceableNWSE(x, y) && (isDiagonallyPlaceable(x, y)) && isPlaceable(x, y)) ||
                            (isOnGridCorner(x, y) && notPlaceableNWSE(x, y) && turnHandler.getCurrentPlayer().hasTakenCorner()) && isPlaceable(x, y))) {
                        int[] currentPosInArray = {x, y};
                        legalPlaces.add(currentPosInArray);
                        //button[x][y].setBackground(Color.GRAY);
                    }
                }
            }
        }
        return legalPlaces;
    }
// --Commented out by Inspection STOP (11/17/2019 2:11 PM)

    public String[][] getBoard() {
        String[][] boardState = new String[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Color color = button[i][j].getColor();
                if (color.getRGB() == Color.red.getRGB()) {
                    boardState[i][j] = "r";
                } else if (color.getRGB() == Color.green.getRGB()) {
                    boardState[i][j] = "g";
                } else if (color.getRGB() == Color.blue.getRGB()) {
                    boardState[i][j] = "b";
                } else if (color.getRGB() == Color.orange.getRGB()) {
                    boardState[i][j] = "y";
                } else {
                    boardState[i][j] = "0";
                }
            }
        }
        return boardState;
    }

    public void setActions(int[][] actions) {
        this.actions = actions;
    }

    // This function sets up the board.
    private void setUpBoard(String[][] savedArray) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                button[j][i] = new customButton(j, i);
                button[j][i].setBackground(Color.white);
                button[j][i].setBorder(BorderFactory.createMatteBorder(5, 10, 10, 10, Color.black));

                button[j][i].addMouseListener(new MouseAdapter() {
                    // Placing a shape when mouse left is clicked
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        event = e;
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            customButton thisButton = ((customButton) e.getSource());
                            int x = thisButton.getPos()[0];
                            int y = thisButton.getPos()[1];
                            placeShapeOnGrid(x, y);
                        }
                        if (SwingUtilities.isMiddleMouseButton(e)) {
                            clearShapeOnGrid();
                            turnHandler.getCurrentPlayer().rightClickFlipV();
                            drawShapeOnGrid();
                        }
                        if (SwingUtilities.isRightMouseButton(e)) {
                            clearShapeOnGrid();
                            turnHandler.getCurrentPlayer().rightClickFlipH();
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
                button[j][i].setBorder(null);
                add(button[j][i]);

                // if a previous session is being loaded then this part of code would place
                // the specific color at the given coordinate
                if (savedArray != null) {
                    if ("r".equals(savedArray[j][i])) {
                        button[j][i].setBackground(Color.red);
                        button[j][i].setTaken(true);
                    } else if ("y".equals(savedArray[j][i])) {
                        button[j][i].setBackground(Color.orange);
                        button[j][i].setTaken(true);
                    } else if ("b".equals(savedArray[j][i])) {
                        button[j][i].setBackground(Color.blue);
                        button[j][i].setTaken(true);
                    } else if ("g".equals(savedArray[j][i])) {
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

    public String[] getPlacedBlocks() {
        String[] rgby = new String[4];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Color color = button[i][j].getColor();
                if (color.getRGB() == Color.red.getRGB()) {
                    if (rgby[0] == null)
                        rgby[0] = "";
                    rgby[0] += "r";
                } else if (color.getRGB() == Color.green.getRGB()) {
                    if (rgby[1] == null)
                        rgby[1] = "";
                    rgby[1] += "g";
                } else if (color.getRGB() == Color.blue.getRGB()) {
                    if (rgby[2] == null)
                        rgby[2] = "";
                    rgby[2] += "b";
                } else if (color.getRGB() == Color.orange.getRGB()) {
                    if (rgby[3] == null)
                        rgby[3] = "";
                    rgby[3] += "y";
                }
            }
        }
        return rgby;
    }

    public void setCurrentPlayingPlayerColor(Color color) {
        currentPlayingPlayerColor = color;
    }

    public void surrenderAI() {
        actions = new int[0][0];
        turnHandler.nextPlayer();
    }

    public void placeShapeOnGridByAI(int x, int y) {
        for (int[] action : actions) {
            button[x + action[0]][y + action[1]].setBackground(currentPlayingPlayerColor);
            button[x + action[0]][y + action[1]].setTaken(true);
        }
        turnHandler.nextPlayer();
        actions = new int[0][0];
    }

    private void placeShapeOnGrid(int x, int y) {
        currentPlayingPlayerColor = turnHandler.getCurrentPlayer().getColor();
        try {
            if (isShapeInsideGrid(x, y)) {
                if (((notPlaceableNWSE(x, y) && (isDiagonallyPlaceable(x, y))) ||
                        (isOnGridCorner(x, y) && notPlaceableNWSE(x, y) && turnHandler.getCurrentPlayer().hasTakenCorner()) && isPlaceable(x, y))) {
                    turnHandler.getCurrentPlayer().addPlacedShapeIndex();
                    for (int i = 0; i < actions.length; i++) {
                        AudioManager.playPlaced();
                        button[x + actions[i][0]][y + actions[i][1]].setBackground(turnHandler.getCurrentPlayer().getColor());
                        button[x + actions[i][0]][y + actions[i][1]].setTaken(true);
                        for (int[] action : actions) {
                            button[x + action[0]][y + action[1]].setTaken(true);
                        }

                        /*
                         * Set current action to empty array after a shape is placed
                         * remove the placed shape from shapelist
                         * the player has placed the shape on a corner
                         * set turn to the next player
                         * getColor is the hacky way to fix panel not being updated when
                         * next player is set
                         */
                        turnHandler.nextPlayer();
                        currentPlayingPlayerColor = turnHandler.getCurrentPlayer().getColor();
                        actions = new int[0][0];
                    }
                }

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
                            && isPlaceable(x, y)) {
                        button[x + action[0]][y + action[1]].setBackground(turnHandler.getCurrentPlayer().getColor());
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
                    if (!button[x + action[0]][y + action[1]].isTaken())
                        button[x + action[0]][y + action[1]].setBackground(Color.white);
                }
            }
        } catch (Exception s) {
            s.printStackTrace();
            button[x][y].setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    }

    // isPlaceable(x,y) checks if a place is yet taken by a shape or not
    private boolean isPlaceable(int x, int y) {
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

    public void setSurrenderForCurrentPlayer() {
        currentPlayingPlayerColor = turnHandler.getCurrentPlayer().getColor();
        actions = new int[0][0];
    }

    // this method checks if from a given location on the board
    // if one step North/West/South/East has any block or not
    // if yes return  false otherwise true
    private boolean notPlaceableNWSE(int x, int y) {
        int[][] cardinalActions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        for (int[] action : actions) {
            for (int[] cardinalAction : cardinalActions) {
                if (((x + action[0] + cardinalAction[0] >= 0) && (x + action[0] + cardinalAction[0] < this.GRID_SIZE)) &&
                        ((y + action[1] + cardinalAction[1] >= 0) && (y + action[1] + cardinalAction[1] < this.GRID_SIZE)))
                // if check to make sure does not go below zero and above the grid size in both x and y directions
                {
                    if (button[x + action[0] + cardinalAction[0]][y + action[1] + cardinalAction[1]].isTaken() &&
                            (button[x + action[0] + cardinalAction[0]][y + action[1] + cardinalAction[1]].getColor().toString().equals(currentPlayingPlayerColor.toString()))) {
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

    public int getGRID_SIZE(){
        return GRID_SIZE;
    }

    private boolean isOnGridCorner(int x, int y) {
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
    private boolean isDiagonallyPlaceable(int x, int y) {
        int[][] diagonalActions = {{1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
        for (int[] action : actions) {
            for (int[] diagonalAction : diagonalActions) {
                // if check to make sure does not go below zero and above the grid size in both x and y directions
                if ((((x + action[0] + diagonalAction[0]) >= 0 && (x + action[0] + diagonalAction[0]) < this.GRID_SIZE)) &&
                        (((y + action[1] + diagonalAction[1]) >= 0 && (y + action[1] + diagonalAction[1]) < this.GRID_SIZE))) {
                    if (button[x + action[0] + diagonalAction[0]][y + action[1] + diagonalAction[1]].isTaken() &&
                            (button[x + action[0] + diagonalAction[0]][y + action[1] + diagonalAction[1]].getColor().toString().equals(currentPlayingPlayerColor.toString()))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
