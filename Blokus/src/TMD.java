import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TMD extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String m1[] = { "10 minutes", "15 minutes", "20 minutes"}; 
	private String tl[] = { "YES","NO"}; 
	private String df[] = { "Easy","Medium","Hard"}; 
	private TMD tmd;private colors color;
	private JPanel close_panel;
	/**
	 * Create the panel.
	 * @param close_panel 
	 * @param loginPage 
	 * @param frame 
	 */
	public TMD(JPanel close_panel, JPanel loginPage, GUI frame) {
		this.close_panel=close_panel;
		tmd=this;
		
		setBackground(Color.GREEN);
		setBounds(342, 0, 345, 478);
		setLayout(null);
		
		JLabel lblTimeLimit = new JLabel("Time Limit:");
		lblTimeLimit.setBounds(61, 152, 63, 14);
		add(lblTimeLimit);
		
		JComboBox<?> comboBox = new JComboBox<Object>(tl);
		comboBox.setBounds(134, 148, 143, 22);
		add(comboBox);
		
		JComboBox<?> comboBox_1 = new JComboBox<Object>(m1);
		comboBox_1.setBounds(134, 176, 143, 22);
		add(comboBox_1);
		
		JLabel lblMinutes = new JLabel("Minutes:");
		lblMinutes.setBounds(61, 180, 97, 14);
		add(lblMinutes);
		
		JLabel lblDifficulty = new JLabel("Difficulty: ");
		lblDifficulty.setBounds(61, 211, 97, 14);
		add(lblDifficulty);
		
		JComboBox<?> comboBox_2 = new JComboBox<Object>(df);
		comboBox_2.setBounds(134, 207, 143, 22);
		add(comboBox_2);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				color =new colors(close_panel,loginPage,frame);
				tmd.removeAll();
				color.setBounds(0,0,tmd.getWidth(),tmd.getHeight());
				color.add(close_panel);
				tmd.add(color);
				tmd.revalidate();
				tmd.repaint();
			}
		});
		panel.setLayout(null);
		panel.setBackground(Color.ORANGE);
		panel.setBounds(61, 298, 216, 49);
		add(panel);
		
		JLabel label_3 = new JLabel("Continue");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_3.setBounds(66, 11, 79, 25);
		panel.add(label_3);

	}
}
