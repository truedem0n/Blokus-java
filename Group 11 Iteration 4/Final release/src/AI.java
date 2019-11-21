import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Ai.
 */
public class AI extends shapesList {
    private final Color color;
    private boolean hasTakenCorner = false, stillPlaying = true;
    private final ArrayList<Integer> availableShapes = new ArrayList<>();
    private final String difficulty;
    private String placedShapesIndexes = "";

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

    private int getRandom(int max) {
        return (int) (Math.random() * max + 0);
    }

    public void removePanelsBasedOnString(String s) {
    	if(!s.equals("")) {
        String[] splitS = s.split(",");
        for (String es : splitS) {
            for (int i = 0; i < availableShapes.size(); i++) {
                if (availableShapes.get(i) == Integer.parseInt(es)) {
                    //noinspection SuspiciousListRemoveInLoop
                    availableShapes.remove(i);

                }
            }
            hideShapePanelFromIndex(Integer.parseInt(es));
        }
        }
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
            flipForAI();
        }
        return false;
    }

    public void setPlacedShapesIndexes(String s) {
        placedShapesIndexes=s;
    }

    private void delayActionByNMilliSeconds() {
        ActionListener taskPerformer = evt -> {
            switch (difficulty) {
                case "0":
                    if (!doEasyAction()) {
                        JOptionPane.showMessageDialog(null, getColorName() + " surrenders");
                        setStillPlaying(false);
                        getPlayingAtBoard().surrenderAI();
                    }
                    break;
                case "1":
                    if (!doMediumAction()) {
                        JOptionPane.showMessageDialog(null, getColorName() + " surrenders");
                        setStillPlaying(false);
                        getPlayingAtBoard().surrenderAI();
                    }
                    break;
                case "2":
                    if (!doHardAction()) {
                        JOptionPane.showMessageDialog(null, getColorName() + " surrenders");
                        setStillPlaying(false);
                        getPlayingAtBoard().surrenderAI();
                    }
                    break;
            }

        };
        Timer timer = new Timer(150, taskPerformer);
        timer.setRepeats(false);
        timer.start();
    }

    @SuppressWarnings("DuplicatedCode")
    private boolean doEasyAction() {
        GameBoard gameBoard = getPlayingAtBoard();
        for (int i = 0; i < availableShapes.size(); i++) {
            int shapeIndex = availableShapes.remove(getRandom(availableShapes.size()));
            int[][] shapeCoordinates = getShapeFromIndex(shapeIndex);
            gameBoard.setActions(shapeCoordinates);
            gameBoard.setCurrentPlayingPlayerColor(this.getColor());
            int[][] legalPlacesOnBoard = convertArrayListTo2dIntArray(gameBoard.getLegalActionAi());
            if (legalPlacesOnBoard.length == 0) {
                if (getLegalActionsByRotatingAndFlippingTheShape(gameBoard))
                    legalPlacesOnBoard = convertArrayListTo2dIntArray(gameBoard.getLegalActionAi());
            }
            if (legalPlacesOnBoard.length > 0 && legalPlacesOnBoard[0].length > 0) {
                int randomLegalPlaceIndex = getRandom(gameBoard.getLegalActionAi().size());
                int[] randomLegalPlace = gameBoard.getLegalActionAi().get(randomLegalPlaceIndex);
                placedShapesIndexes += shapeIndex + ",";
                gameBoard.placeShapeOnGridByAI(randomLegalPlace[0], randomLegalPlace[1]);
                return true;
            } else if (i == availableShapes.size() - 1) {
                return false;
            } else {
                availableShapes.add(shapeIndex);
            }
        }
        return false;
    }

    private int isClosestToCenter(int[][] legalPlacesOnBoard,int GRID_SIZE){
        int gridCenter=GRID_SIZE/2;
        double[] arr=new double[legalPlacesOnBoard.length];
        for(int i=0;i<legalPlacesOnBoard.length;i++){
            arr[i]=findClosest(legalPlacesOnBoard[i],gridCenter);
        }
        double minValue=Integer.MAX_VALUE;int minValueIndex=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]<minValue){
                minValue=arr[i];
                minValueIndex=i;
            }
        }
        return minValueIndex;
    }

    // Hard AI is a combination of easy and medium
    private boolean doHardAction() {
        int randInt = getRandom(10);
        if(randInt<3)
            return doEasyAction();
        else
            return doMediumAction();
    }

    @SuppressWarnings("DuplicatedCode")
    private boolean doMediumAction() {
        GameBoard gameBoard = getPlayingAtBoard();
        for (int i = 0; i < availableShapes.size(); i++) {

            int shapeIndex = availableShapes.remove((availableShapes.size()-1)-i);
            int[][] shapeCoordinates = getShapeFromIndex(shapeIndex);
            gameBoard.setActions(shapeCoordinates);
            gameBoard.setCurrentPlayingPlayerColor(this.getColor());
            int[][] legalPlacesOnBoard = convertArrayListTo2dIntArray(gameBoard.getLegalActionAi());
            if (legalPlacesOnBoard.length == 0) {
                if (getLegalActionsByRotatingAndFlippingTheShape(gameBoard))
                    legalPlacesOnBoard = convertArrayListTo2dIntArray(gameBoard.getLegalActionAi());
            }
            if (legalPlacesOnBoard.length > 0) {
                int shapeIndexToPlaceAt = getRandom(gameBoard.getLegalActionAi().size());
                if(legalPlacesOnBoard.length>1){
                    shapeIndexToPlaceAt=isClosestToCenter(legalPlacesOnBoard,gameBoard.getGRID_SIZE());
                }
                int[] randomLegalPlace = gameBoard.getLegalActionAi().get(shapeIndexToPlaceAt);
                placedShapesIndexes += shapeIndex + ",";
                gameBoard.placeShapeOnGridByAI(randomLegalPlace[0], randomLegalPlace[1]);
                return true;

            } else if (i == availableShapes.size() - 1) {
                return false;
            } else {
                availableShapes.add(shapeIndex);
            }
        }
        return false;
    }
    // using pythogeras theorem
    private double findClosest(int[] arr, int target)
    {
        int x = arr[0], y = arr[1];
        return Math.sqrt((x - target) * (x - target) + (y - target) * (y - target));
    }


    /**
     * @return
     */
    public String getPlacedShapesIndexes() {
        return placedShapesIndexes;
    }

    /**
     *
     */
    public void doAction() {
        delayActionByNMilliSeconds();

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
