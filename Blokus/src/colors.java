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
	 *  All the declarations
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
	public colors(JPanel close_panel, JPanel loginPage, GUI frame, Dictionary<Object,Object> gAME_SETTINGS) {
		
		setBackground(new Color(63, 71, 204));
		setBounds(342, 0, 345, 478);
		setLayout(null);
		
		JPanel continue_panel = new JPanel();
		continue_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				game=new Game(frame,null);
				frame.setVisible(true);
				loginPage.removeAll();
				loginPage.add(game);
				close_panel.setBounds(680, 2, 28, 36);
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
		label.setForeground(Color.white);
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(66, 11, 140, 25);
		continue_panel.add(label);
		
		JPanel randomize_panel = new JPanel();
		randomize_panel.setLayout(null);
		randomize_panel.setBackground(Color.ORANGE);
		randomize_panel.setBounds(59, 371, 216, 49);
		add(randomize_panel);
		
		JLabel lblRandomize = new JLabel("Randomize");
		lblRandomize.setForeground(Color.white);
		lblRandomize.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRandomize.setBounds(58, 11, 148, 25);
		randomize_panel.add(lblRandomize);
		
		JLabel lblPlayer = new JLabel("Player 1");
		lblPlayer.setForeground(Color.WHITE);
		lblPlayer.setBounds(61, 141, 63, 14);
		add(lblPlayer);
		
		JLabel lblPlayer_1 = new JLabel("Player 2");
		lblPlayer_1.setForeground(Color.WHITE);
		lblPlayer_1.setBounds(61, 170, 97, 14);
		add(lblPlayer_1);
		
		JLabel lblPlayer_2 = new JLabel("Player 3");
		lblPlayer_2.setForeground(Color.WHITE);
		lblPlayer_2.setBounds(61, 200, 97, 14);
		add(lblPlayer_2);
		
		JLabel lblPlayer_3 = new JLabel("Player 4");
		lblPlayer_3.setForeground(Color.WHITE);
		lblPlayer_3.setBounds(61, 229, 97, 14);
		add(lblPlayer_3);
		
		
		JComboBox<String> player1_comboBox = new JComboBox<String>(colors);
		player1_comboBox.setBounds(134, 137, 143, 22);
		add(player1_comboBox);
		
		JComboBox<String> player2_comboBox = new JComboBox<String>(colors);
		player2_comboBox.setBounds(134, 166, 143, 22);
		add(player2_comboBox);
		
		JComboBox<String> player3_comboBox = new JComboBox<String>(colors);
		player3_comboBox.setBounds(134, 196, 143, 22);
		add(player3_comboBox);
		
		JComboBox<String> player4_comboBox = new JComboBox<String>(colors);
		player4_comboBox.setBounds(134, 225, 143, 22);
		add(player4_comboBox);
	}
}
