import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GPC extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GPC gpc;
	private TMD tmd;
	private JPanel close_panel;
	private String g1[] = { "16x16", "20x20", "24x24"}; 
	private String p1[] = { "1", "2", "3","4"}; 
	private String c1[] = { "0", "1", "2","3"}; 
	

	/**
	 * Create the panel.
	 * @param close_panel2 
	 * @param loginPage 
	 * @param frame 
	 * 
	 */
	
	public GPC(JPanel close_panel2, JPanel loginPage, GUI frame) {
		this.close_panel=close_panel2;
		setBackground(Color.ORANGE);
		setBounds(342, 0, 345, 478);
		setLayout(null);
		gpc=this;
		
		JLabel lblNewLabel = new JLabel("Grid size:");
		lblNewLabel.setBounds(59, 159, 63, 14);
		add(lblNewLabel);
		
		JLabel lblPlayers = new JLabel("Players:");
		lblPlayers.setBounds(59, 187, 97, 14);
		add(lblPlayers);
		
		JLabel lblCpus = new JLabel("CPU(s):");
		lblCpus.setBounds(59, 218, 97, 14);
		add(lblCpus);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tmd=new TMD(close_panel,loginPage,frame);
				gpc.removeAll();
				tmd.setBounds(0,0,gpc.getWidth(),gpc.getHeight());
				tmd.add(close_panel);
				gpc.add(tmd);
				gpc.revalidate();
				gpc.repaint();
			}
		});
		panel.setBackground(Color.GREEN);
		panel.setBounds(59, 305, 216, 49);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblContinue = new JLabel("Continue");
		lblContinue.setBounds(66, 11, 79, 25);
		lblContinue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblContinue);
		
		JComboBox<?> comboBox = new JComboBox<Object>(g1);
		comboBox.setBounds(132, 155, 143, 22);
		add(comboBox);
		
		JComboBox<?> comboBox_1 = new JComboBox<Object>(p1);
		comboBox_1.setBounds(132, 183, 143, 22);
		add(comboBox_1);
		
		JComboBox<?> comboBox_2 = new JComboBox<Object>(c1);
		comboBox_2.setBounds(132, 214, 143, 22);
		add(comboBox_2);

	}
}
