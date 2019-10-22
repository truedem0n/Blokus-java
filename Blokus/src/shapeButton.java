import javax.swing.JPanel;

public class shapeButton extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int index;

	public shapeButton(int i) {
		this.index = i;
	}

	public int getIndex() {
		return this.index;
	}
}