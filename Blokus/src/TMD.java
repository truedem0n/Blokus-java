import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Dictionary;

class TMD extends JPanel {
	
	/**
	 *   All the declarartions
	 */
	private static final long serialVersionUID = 1L;
	private final TMD tmd;
	private final JComboBox<String> difficulty_comboBox;
	private colors color;
	private JComboBox<String> timeLimit_comboBox;
	private JComboBox<String> minutes_comboBox;
	/**
	 * Create the panel.
	 * @param close_panel 
	 * @param loginPage 
	 * @param frame 
	 * @param gAME_SETTINGS 
	 */
	public TMD(JPanel close_panel, JPanel loginPage, GUI frame, Dictionary gAME_SETTINGS) {
		tmd=this;
		setBackground(new Color(63, 71, 204));
		setBounds(342, 0, 345, 478);
		setLayout(null);
		
		JLabel lblTimeLimit = new JLabel("Time Limit:");
		lblTimeLimit.setForeground(Color.white);
		lblTimeLimit.setBounds(61, 152, 63, 14);
		add(lblTimeLimit);
		
		
		ActionListener timeLimit_comboBoxListener=e->{
			if(timeLimit_comboBox.getSelectedItem()=="NO") {
				minutes_comboBox.setEnabled(false);
			}else {
				minutes_comboBox.setEnabled(true);
			}
		};
		String[] tl = {"YES", "NO"};
		timeLimit_comboBox = new JComboBox<>(tl);
		timeLimit_comboBox.setBounds(134, 148, 143, 22);
		timeLimit_comboBox.addActionListener(timeLimit_comboBoxListener);
		add(timeLimit_comboBox);

		String[] m1 = {"10", "15", "20"};
		minutes_comboBox = new JComboBox<>(m1);
		minutes_comboBox.setBounds(134, 176, 143, 22);
		add(minutes_comboBox);
		
		JLabel lblMinutes = new JLabel("Minutes:");
		lblMinutes.setForeground(Color.white);
		lblMinutes.setBounds(61, 180, 97, 14);
		add(lblMinutes);
		
		JLabel lblDifficulty = new JLabel("Difficulty: ");
		lblDifficulty.setForeground(Color.white);
		lblDifficulty.setBounds(61, 211, 97, 14);
		add(lblDifficulty);

		String[] df = {"Easy", "Medium", "Hard"};
		difficulty_comboBox = new JComboBox<>(df);
		difficulty_comboBox.setBounds(134, 207, 143, 22);
		add(difficulty_comboBox);
		
		JPanel continue_panel = new JPanel();
		continue_panel.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void mouseClicked(MouseEvent e) {
				gAME_SETTINGS.put("timeLimit", timeLimit_comboBox.getSelectedItem());
				gAME_SETTINGS.put("minutes", minutes_comboBox.getSelectedItem());
				gAME_SETTINGS.put("difficulty", difficulty_comboBox.getSelectedItem());
				color =new colors(close_panel,loginPage,frame,gAME_SETTINGS);
				tmd.removeAll();
				color.setBounds(0,0,tmd.getWidth(),tmd.getHeight());
				color.add(close_panel);
				tmd.add(color);
				tmd.revalidate();
				tmd.repaint();
			}
		});
		continue_panel.setLayout(null);
		continue_panel.setBackground(Color.ORANGE);
		continue_panel.setBounds(59, 305, 216, 49);
		add(continue_panel);
		
		JLabel label_3 = new JLabel("Continue");
		label_3.setForeground(Color.white);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_3.setBounds(66, 11, 140, 25);
		continue_panel.add(label_3);

	}
}
