import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Dictionary;

public class colors extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game game;
	private String colors[] = { "Red","Green","Blue","Yellow"}; 
	/**
	 * Create the panel.
	 * @param close_panel 
	 * @param loginPage 
	 * @param frame 
	 * @param gAME_SETTINGS 
	 */
	public colors(JPanel close_panel, JPanel loginPage, GUI frame, Dictionary gAME_SETTINGS) {
		
		setBackground(Color.BLUE);
		setBounds(342, 0, 345, 478);
		setLayout(null);
		
		JPanel continue_panel = new JPanel();
		continue_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				game=new Game(frame);
				frame.setVisible(true);
				loginPage.removeAll();
				loginPage.add(game);
				close_panel.setBounds(1215-500, 11, 28, 36);
				game.add(close_panel);
				loginPage.revalidate();
				loginPage.repaint();
			}
		});
		continue_panel.setLayout(null);
		continue_panel.setBackground(Color.ORANGE);
		continue_panel.setBounds(59, 305, 216, 49);
		add(continue_panel);
		
		JLabel label = new JLabel("Continue");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(66, 11, 79, 25);
		continue_panel.add(label);
		
		JComboBox player3_comboBox = new JComboBox(colors);
		player3_comboBox.setBounds(134, 196, 143, 22);
		add(player3_comboBox);
		
		JLabel lblPlayer_2 = new JLabel("Player 3");
		lblPlayer_2.setForeground(Color.WHITE);
		lblPlayer_2.setBounds(61, 200, 97, 14);
		add(lblPlayer_2);
		
		JLabel lblPlayer_1 = new JLabel("Player 2");
		lblPlayer_1.setForeground(Color.WHITE);
		lblPlayer_1.setBounds(61, 170, 97, 14);
		add(lblPlayer_1);
		
		JComboBox player2_comboBox = new JComboBox(colors);
		player2_comboBox.setBounds(134, 166, 143, 22);
		add(player2_comboBox);
		
		JComboBox player1_comboBox = new JComboBox(colors);
		player1_comboBox.setBounds(134, 137, 143, 22);
		add(player1_comboBox);
		
		JLabel lblPlayer = new JLabel("Player 1");
		lblPlayer.setForeground(Color.WHITE);
		lblPlayer.setBounds(61, 141, 63, 14);
		add(lblPlayer);
		
		JPanel randomize_panel = new JPanel();
		randomize_panel.setLayout(null);
		randomize_panel.setBackground(Color.ORANGE);
		randomize_panel.setBounds(59, 371, 216, 49);
		add(randomize_panel);
		
		JLabel lblRandomize = new JLabel("Randomize");
		lblRandomize.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRandomize.setBounds(58, 11, 107, 25);
		randomize_panel.add(lblRandomize);
		
		JLabel lblPlayer_3 = new JLabel("Player 4");
		lblPlayer_3.setForeground(Color.WHITE);
		lblPlayer_3.setBounds(61, 229, 97, 14);
		add(lblPlayer_3);
		
		JComboBox player4_comboBox = new JComboBox(colors);
		player4_comboBox.setBounds(134, 225, 143, 22);
		add(player4_comboBox);

	}
}
