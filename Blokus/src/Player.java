/*
  @author: Atul Mehla
 */
import java.awt.*;

/**
 * The type Player.
 */
class Player extends shapesList {
    private final Color color;
    private boolean hasTakenCorner = false, stillPlaying = true;
    private String placedShapesIndexes = "";


    /**
     * Create the panel.
     *
     * @param color the color
     */
    Player(Color color) {
        super(color);
        this.color = color;
    }

    public void addPlacedShapeIndex() {
        placedShapesIndexes += getCurrentSelectedShapePanel() + ",";
    }

    public Color getColor() {
        return color;
    }

    public boolean hasTakenCorner() {
        return !hasTakenCorner;
    }

    public String getPlacedShapesIndexes() {
        return placedShapesIndexes;
    }

    public void setHasTakenCorner(boolean hasTakenCorner) {
        this.hasTakenCorner = hasTakenCorner;
    }

    public boolean isStillPlaying() {
        return stillPlaying;
    }

    public void setStillPlaying(boolean stillPlaying) {
        this.stillPlaying = stillPlaying;
    }

    public void removePanelsBasedOnString(String s) {
        String[] splitS = s.split(",");
        for (String es : splitS) {
            hideShapePanelFromIndex(Integer.parseInt(es));
        }
    }
}
