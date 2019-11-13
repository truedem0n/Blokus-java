import java.awt.*;

public class AI extends shapesList {
    private final Color color;
    private boolean hasTakenCorner = false, stillPlaying = true;

    /**
     * Create the panel.
     * shapeList Constructor
     *
     * @param color
     */
    AI(Color color) {
        super(color);
        this.color = color;
    }

    public void doAction() {
        System.out.println("Printing from  AI");
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
}
