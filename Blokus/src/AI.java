import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The type Ai.
 */
public class AI extends shapesList {
    private final Color color;
    private boolean hasTakenCorner = false, stillPlaying = true, stillTakingAction = true;
    private ArrayList<Integer> availableShapes = new ArrayList<>();
    private String difficulty;

    /**
     * Create the panel.
     * shapeList Constructor
     *
     * @param color the color
     */
    AI(Color color, String difficulty) {
        super(color);
        this.color = color;
        this.difficulty = difficulty;
        setUPShapesIndex();
    }

    private int getRandom(int min, int max) {
        return (int) (Math.random() * max + min);
    }

    private int[][] convertArrayListTo2dIntArray(ArrayList s) {
        int[][] returnArray = new int[s.size()][2];
        for (int i = 0; i < s.size(); i++) {
            returnArray[i] = (int[]) s.get(i);
        }
        return returnArray;
    }

    private boolean getLegalActionsByRotatingAndFlippingTheShape(GameBoard gameBoard) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                rotateClockWise();
                if (gameBoard.getLegalActionAi().size() > 0 && gameBoard.getLegalActionAi().get(0).length > 0) {
                    return true;
                }
            }
            rightClickFlipV();
        }
        return false;
    }

    private void delayActionByNMilliSeconds() {
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (!doEasyAction()) {
                    JOptionPane.showMessageDialog(null, getColorName() + " surrenders");
                    setStillPlaying(false);
                    getPlayingAtBoard().surrenderAI();
                }
            }
        };
        Timer timer = new Timer(150, taskPerformer);
        timer.setRepeats(false);
        timer.start();
    }

    private boolean doEasyAction() {
        GameBoard gameBoard = getPlayingAtBoard();
        for (int i = 0; i < availableShapes.size(); i++) {
//            System.out.println(availableShapes.size());
            int shapeIndex = availableShapes.remove(getRandom(0, availableShapes.size()));
            int[][] shapeCoordinates = getShapeFromIndex(shapeIndex);
            gameBoard.setActions(shapeCoordinates);
            gameBoard.setCurrentPlayingPlayerColor(this.getColor());
            int[][] legalPlacesOnBoard = convertArrayListTo2dIntArray(gameBoard.getLegalActionAi());
//            System.out.println(Arrays.deepToString(legalPlacesOnBoard));
            if (legalPlacesOnBoard.length > 0 && legalPlacesOnBoard[0].length == 0) {
                System.out.println("working");
                if (getLegalActionsByRotatingAndFlippingTheShape(gameBoard))
                    legalPlacesOnBoard = convertArrayListTo2dIntArray(gameBoard.getLegalActionAi());
            }
            if (legalPlacesOnBoard.length > 0 && legalPlacesOnBoard[0].length > 0) {
                int randomSHapeIndex = getRandom(0, gameBoard.getLegalActionAi().size());
//                System.out.println(Arrays.toString(gameBoard.getLegalActionAi().get(randomSHapeIndex)));
                int[] randomLegalPlace = gameBoard.getLegalActionAi().get(randomSHapeIndex);
                if (legalPlacesOnBoard.length > 0) {
                    gameBoard.placeShapeOnGridByAI(randomLegalPlace[0], randomLegalPlace[1]);
                    return true;

                }
            } else if (i == availableShapes.size() - 1) {
                return false;
            } else {
                availableShapes.add(shapeIndex);
            }
        }
        return false;
    }

    private void doMediumAction() {
    }

    private String getColorName() {
        Color color = getColor();

        if (Color.red.toString().equals(color.toString()))
            return "Red";
        else if (Color.green.toString().equals(color.toString()))
            return "Green";
        else if (Color.blue.toString().equals(color.toString()))
            return "Blue";
        else if (Color.orange.toString().equals(color.toString()))
            return "Yellow";
        return "";
    }

    public void doAction() {
        if (difficulty.equals("0")) {
            delayActionByNMilliSeconds();
        }

    }

    /**
     * This function sets up every shape index i.e. 0 to 20.
     */
    private void setUPShapesIndex() {
        for (int i = 0; i < 21; i++)
            availableShapes.add(i);
    }

    /**
     * @return
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * @return
     */
    @Override
    public boolean hasTakenCorner() {
        return !hasTakenCorner;
    }

    /**
     * @param hasTakenCorner
     */
    @Override
    public void setHasTakenCorner(boolean hasTakenCorner) {
        this.hasTakenCorner = hasTakenCorner;
    }

    /**
     * @return
     */
    @Override
    public boolean isStillPlaying() {
        return stillPlaying;
    }

    /**
     * @param stillPlaying
     */
    @Override
    public void setStillPlaying(boolean stillPlaying) {
        this.stillPlaying = stillPlaying;
    }

}
