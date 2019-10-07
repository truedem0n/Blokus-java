import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class colors extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game game;

	/**
	 * Create the panel.
	 * @param close_panel 
	 * @param loginPage 
	 * @param frame 
	 */
	public colors(JPanel close_panel, JPanel loginPage, GUI frame) {
		
		setBackground(Color.BLUE);
		setBounds(342, 0, 345, 478);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				game=new Game(frame);
				loginPage.removeAll();
				loginPage.add(game);
				close_panel.setBounds(1245-500, 11, 28, 36);
				game.add(close_panel);
				loginPage.revalidate();
				loginPage.repaint();
			}
		});
		panel.setLayout(null);
		panel.setBackground(Color.ORANGE);
		panel.setBounds(61, 283, 216, 49);
		add(panel);
		
		JLabel label = new JLabel("Continue");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(66, 11, 79, 25);
		panel.add(label);
		
		JComboBox comboBox = new JComboBox(new Object[]{});
		comboBox.setBounds(134, 192, 143, 22);
		add(comboBox);
		
		JLabel label_1 = new JLabel("Difficulty: ");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(61, 196, 97, 14);
		add(label_1);
		
		JLabel label_2 = new JLabel("Minutes:");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(61, 165, 97, 14);
		add(label_2);
		
		JComboBox comboBox_1 = new JComboBox(new Object[]{});
		comboBox_1.setBounds(134, 161, 143, 22);
		add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox(new Object[]{});
		comboBox_2.setBounds(134, 133, 143, 22);
		add(comboBox_2);
		
		JLabel label_3 = new JLabel("Time Limit:");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(61, 137, 63, 14);
		add(label_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds(61, 353, 216, 49);
		add(panel_1);
		
		JLabel lblRandomize = new JLabel("Randomize");
		lblRandomize.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRandomize.setBounds(58, 11, 107, 25);
		panel_1.add(lblRandomize);

	}
}
