/**
 * @author: Atul Mehla
 */
import java.awt.*;
class Player extends shapesList {
    private final Color color;
    private boolean hasTakenCorner = false, stillPlaying = true;


    /**
     * Create the panel.
     */
    Player(Color color) {
        super(color);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean hasTakenCorner() {
        return hasTakenCorner;
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
}
