import java.awt.*;
import java.util.ArrayList;

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

    private void doEasyAction() {

    }

    private void doMediumAction() {
    }

    public void doAction() {
        if (difficulty == "easy")
            System.out.println("Working");
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
