import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Dictionary;

public class TMD extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String m1[] = { "10", "15", "20"}; 
	private String tl[] = { "YES","NO"}; 
	private String df[] = { "Easy","Medium","Hard"}; 
	private TMD tmd;private colors color;
	private JComboBox<String> timeLimit_comboBox,minutes_comboBox,difficulty_comboBox;
	/**
	 * Create the panel.
	 * @param close_panel 
	 * @param loginPage 
	 * @param frame 
	 * @param gAME_SETTINGS 
	 */
	public TMD(JPanel close_panel, JPanel loginPage, GUI frame, Dictionary<Object,Object> gAME_SETTINGS) {
		tmd=this;
		setBackground(Color.GREEN);
		setBounds(342, 0, 345, 478);
		setLayout(null);
		
		JLabel lblTimeLimit = new JLabel("Time Limit:");
		lblTimeLimit.setBounds(61, 152, 63, 14);
		add(lblTimeLimit);
		
		
		ActionListener timeLimit_comboBoxListener=e->{
			if(timeLimit_comboBox.getSelectedItem()=="NO") {
				minutes_comboBox.setEnabled(false);
			}else {
				minutes_comboBox.setEnabled(true);
			}
		};
		timeLimit_comboBox = new JComboBox<String>(tl);
		timeLimit_comboBox.setBounds(134, 148, 143, 22);
		timeLimit_comboBox.addActionListener(timeLimit_comboBoxListener);
		add(timeLimit_comboBox);
		
		minutes_comboBox = new JComboBox<String>(m1);
		minutes_comboBox.setBounds(134, 176, 143, 22);
		add(minutes_comboBox);
		
		JLabel lblMinutes = new JLabel("Minutes:");
		lblMinutes.setBounds(61, 180, 97, 14);
		add(lblMinutes);
		
		JLabel lblDifficulty = new JLabel("Difficulty: ");
		lblDifficulty.setBounds(61, 211, 97, 14);
		add(lblDifficulty);
		
		difficulty_comboBox = new JComboBox<String>(df);
		difficulty_comboBox.setBounds(134, 207, 143, 22);
		add(difficulty_comboBox);
		
		JPanel continue_panel = new JPanel();
		continue_panel.addMouseListener(new MouseAdapter() {
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
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_3.setBounds(66, 11, 79, 25);
		continue_panel.add(label_3);

	}
}
