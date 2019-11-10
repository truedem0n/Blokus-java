import javax.swing.*;

class shapeButton extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int index;
	
	
	// Shape constructor sets the index of shape button in shape list
	public shapeButton(int i) {
		this.index = i;
	}
	
	
	// getIndex 
	public int getIndex() {
		return this.index;
	}
}