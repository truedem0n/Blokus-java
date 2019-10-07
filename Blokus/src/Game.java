import javax.swing.JPanel;
import java.awt.Color;

public class Game extends JPanel {

	/**
	 * Create the panel.
	 * @param frame 
	 */
	public Game(GUI frame) {
		setBackground(Color.LIGHT_GRAY);
		frame.setBounds(0, 0, 1280-500, 720-200);
		frame.setLocationRelativeTo(null);
		setBounds(0, 0, frame.getWidth(), frame.getHeight());
		setLayout(null);

	}

}
