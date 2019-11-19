import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Ai.
 */
public class AI extends shapesList {
    private final Color color;
    private boolean hasTakenCorner = false, stillPlaying = true;
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
            flipForAI();
        }
        return false;
    }

    private void delayActionByNMilliSeconds() {
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (difficulty.equals("0")) {
                    if (!doEasyAction()) {
                        JOptionPane.showMessageDialog(null, getColorName() + " surrenders");
                        setStillPlaying(false);
                        getPlayingAtBoard().surrenderAI();
                    }
                }else if(difficulty.equals("1")){
                    if (!doMediumAction()) {
                        JOptionPane.showMessageDialog(null, getColorName() + " surrenders");
                        setStillPlaying(false);
                        getPlayingAtBoard().surrenderAI();
                    }
                }else if(difficulty.equals("2")){
                    if (!doHardAction()) {
                        JOptionPane.showMessageDialog(null, getColorName() + " surrenders");
                        setStillPlaying(false);
                        getPlayingAtBoard().surrenderAI();
                    }
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
            int shapeIndex = availableShapes.remove(getRandom(0, availableShapes.size()));
            int[][] shapeCoordinates = getShapeFromIndex(shapeIndex);
            gameBoard.setActions(shapeCoordinates);
            gameBoard.setCurrentPlayingPlayerColor(this.getColor());
            int[][] legalPlacesOnBoard = convertArrayListTo2dIntArray(gameBoard.getLegalActionAi());
            System.out.println(Arrays.deepToString(legalPlacesOnBoard));
            if (legalPlacesOnBoard.length == 0) {
                if (getLegalActionsByRotatingAndFlippingTheShape(gameBoard))
                    legalPlacesOnBoard = convertArrayListTo2dIntArray(gameBoard.getLegalActionAi());
            }
            if (legalPlacesOnBoard.length > 0 && legalPlacesOnBoard[0].length > 0) {
                int randomSHapeIndex = getRandom(0, gameBoard.getLegalActionAi().size());
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
        int randInt=getRandom(0,10);
        if(randInt<3)
            return doEasyAction();
        else
            return doMediumAction();
    }

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
                int shapeIndexToPlaceAt = getRandom(0, gameBoard.getLegalActionAi().size());
                if(legalPlacesOnBoard.length>1){
                    shapeIndexToPlaceAt=isClosestToCenter(legalPlacesOnBoard,gameBoard.getGRID_SIZE());
                }
                int[] randomLegalPlace = gameBoard.getLegalActionAi().get(shapeIndexToPlaceAt);
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
    // using pythogeras theorem
    public double findClosest(int arr[], int target)
    {
        int x=arr[0],y=arr[1],x2=target,y2=target;
        return Math.sqrt((x - x2)*(x - x2) + (y - y2)*(y - y2));
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
