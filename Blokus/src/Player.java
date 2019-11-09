import java.awt.*;

public class Player extends shapesList {
	private Color color;
	private shapesList shapes;

	/**
	 * Create the panel.
	 */
	Player(Color color) {
		super(color);
		this.color=color;
		this.setLayout(null);
		shapes=new shapesList(Color.black);
		shapes.setBounds(0, 0, 169, 259);
		add(shapes);
	}
}
