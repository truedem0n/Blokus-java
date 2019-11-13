import javax.swing.*;
import java.awt.*;

class customButton extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int x;
	private final int y;

	public Color getColor() {
		return this.getBackground();
	}

	private boolean taken = false;

	public customButton(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int[] getPos() {
		return new int[]{this.x, this.y};
	}

	public boolean isTaken() {
		return this.taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int d = 0;
		int h = 0;
		int lineWeight = 1;
		for (int i = 0; i < lineWeight; i++) {
			g.setColor(Color.gray);
			g.drawLine(0, h, getWidth(), h);
			g.drawLine(d, 0, d, getHeight());
			h += getHeight() / lineWeight;
			d += getWidth() / lineWeight;
		}
	}
}
