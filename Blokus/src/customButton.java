import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class customButton extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int x, y, linien = 1;

	public Color getColor() {
		return this.getBackground();
	}

	public void setColor(Color color) {
		this.setBackground(color);
	}
	private boolean taken = false;

	public customButton(String name, int x, int y) {
		// super(name);
		this.x = x;
		this.y = y;
	}

	public int[] getPos() {
		int[] pos = {this.x, this.y};
		return pos;
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
		for (int i = 0; i < this.linien; i++) {
			g.setColor(Color.gray);
			g.drawLine(0, h, getWidth(), h);
			g.drawLine(d, 0, d, getHeight());
			h += getHeight() / this.linien;
			d += getWidth() / this.linien;
		}
	}
}
