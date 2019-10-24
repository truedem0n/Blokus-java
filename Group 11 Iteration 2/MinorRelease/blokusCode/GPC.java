import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Dictionary;

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
	private JComboBox<?> gridSize_comboBox,playerNumber_comboBox,cpuNumber_comboBox;
	

	/**
	 * Create the panel.
	 * @param close_panel2 
	 * @param loginPage 
	 * @param frame 
	 * @param GAME_SETTINGS 
	 * 
	 */
	
	public GPC(JPanel close_panel2, JPanel loginPage, GUI frame, Dictionary GAME_SETTINGS) {
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
		gridSize_comboBox = new JComboBox<Object>(g1);
		gridSize_comboBox.setBounds(132, 155, 143, 22);
		add(gridSize_comboBox);
		
		playerNumber_comboBox = new JComboBox<Object>(p1);
		playerNumber_comboBox.setBounds(132, 183, 143, 22);
		add(playerNumber_comboBox);
		
		cpuNumber_comboBox = new JComboBox<Object>(c1);
		cpuNumber_comboBox.setBounds(132, 214, 143, 22);
		add(cpuNumber_comboBox);
		
		JPanel continue_panel = new JPanel();
		continue_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if((Integer.valueOf(String.valueOf(playerNumber_comboBox.getSelectedItem()))+Integer.valueOf(String.valueOf(cpuNumber_comboBox.getSelectedItem())))>1&&(Integer.valueOf(String.valueOf(playerNumber_comboBox.getSelectedItem()))+Integer.valueOf(String.valueOf(cpuNumber_comboBox.getSelectedItem())))<5) {
					GAME_SETTINGS.put("gridSize",gridSize_comboBox.getSelectedIndex());
					GAME_SETTINGS.put("playerNumber",playerNumber_comboBox.getSelectedItem());
					GAME_SETTINGS.put("cpuNumber",cpuNumber_comboBox.getSelectedItem());
					
					tmd=new TMD(close_panel,loginPage,frame,GAME_SETTINGS);
					gpc.removeAll();
					tmd.setBounds(0,0,gpc.getWidth(),gpc.getHeight());
					tmd.add(close_panel);
					gpc.add(tmd);
					gpc.revalidate();
					gpc.repaint();
				}
			}
		});
		continue_panel.setBackground(Color.GREEN);
		continue_panel.setBounds(59, 305, 216, 49);
		add(continue_panel);
		continue_panel.setLayout(null);
		
		JLabel lblContinue = new JLabel("Continue");
		lblContinue.setBounds(66, 11, 79, 25);
		lblContinue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		continue_panel.add(lblContinue);
		
		

	}
}
