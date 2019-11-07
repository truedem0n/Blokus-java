import java.awt.Color;

import javax.swing.JPanel;

public class Player extends JPanel {
	private Color color;
	private shapesList shapes;

	/**
	 * Create the panel.
	 */
	public Player(Color color) {
		this.color=color;
		this.setLayout(null);
		shapes=new shapesList(Color.black);
		shapes.setBounds(0, 0, 169, 259);
		add(shapes);
	}
	
	public void scrollActionHide() {
		shapes.scrollActionHide();
	}
	
	public void rotateClockWise() {
		shapes.rotateClockWise();
	}
	public void scrollActionDraw() {
		shapes.scrollActionDraw();
	}
	public void setPlayingAtBoard(GameBoard board) {
		shapes.setPlayingAtBoard(board);
	}
	public void rightClickFlipV() {
		shapes.rightClickFlipV();
	}
	public void rightClickFlipH() {
		shapes.rightClickFlipH();
	}
	public void removeShape() {
		
	}
}
