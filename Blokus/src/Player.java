import java.awt.*;

class Player extends shapesList {
	private final Color color;
	private boolean hasTakenCorner = false;

	/**
	 * Create the panel.
	 */
	Player() {
		super(Color.ORANGE);
		this.color = Color.ORANGE;
		this.setLayout(null);
		shapesList shapes = new shapesList(Color.black);
		shapes.setBounds(0, 0, 169, 259);
		add(shapes);
	}

	Color getColor() {
		return color;
	}

	public boolean hasTakenCorner() {
		return hasTakenCorner;
	}

	public void setHasTakenCorner(boolean hasTakenCorner) {
		this.hasTakenCorner = hasTakenCorner;
	}
}
