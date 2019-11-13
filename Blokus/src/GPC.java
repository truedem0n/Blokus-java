import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

class GPC extends JPanel {
	/**
	 *  All the declarations
	 *  
	 *  g1,p1,c1 are the options for grid sizing
	 *  number of players, and computers
	 *  
	 *  close_panel is the same panel defined in GUI class but being
	 *  passed down to GPC class
	 *  
	 *  JCombo box is used for the dropdown
	 */
	private static final long serialVersionUID = 1L;
    private final GPC gpc;
	private TMD tmd;
    private final JPanel close_panel;
    private final JComboBox<?> gridSize_comboBox;
    private final JComboBox<?> playerNumber_comboBox;
    private final JComboBox<?> cpuNumber_comboBox;


    /**
     * Create the panel.
     *
     * @param close_panel2
     * @param loginPage
     * @param frame
     * @param GAME_SETTINGS class constructor
     */


	GPC(JPanel close_panel2, JPanel loginPage, GUI frame, Map<String, String> GAME_SETTINGS) {

		this.close_panel=close_panel2;
		setBackground(new Color(63, 71, 204));
		setBounds(342, 0, 345, 478);
		setLayout(null);
		gpc=this;
		
		
		JLabel lblNewLabel = new JLabel("Grid size:");
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setBounds(59, 159, 63, 14);
		add(lblNewLabel);
		
		JLabel lblPlayers = new JLabel("Players:");
		lblPlayers.setForeground(Color.white);
		lblPlayers.setBounds(59, 187, 97, 14);
		add(lblPlayers);

        JLabel lblCpus = new JLabel("AI(s):");
		lblCpus.setForeground(Color.white);
		lblCpus.setBounds(59, 218, 97, 14);
		add(lblCpus);
        String[] g1 = {"16x16", "20x20", "24x24"};
		gridSize_comboBox = new JComboBox<Object>(g1);
		gridSize_comboBox.setBounds(132, 155, 143, 22);
		add(gridSize_comboBox);

        String[] p1 = {"1", "2", "3", "4"};
		playerNumber_comboBox = new JComboBox<Object>(p1);
		playerNumber_comboBox.setBounds(132, 183, 143, 22);
		add(playerNumber_comboBox);

        String[] c1 = {"0", "1", "2", "3"};
		cpuNumber_comboBox = new JComboBox<Object>(c1);
		cpuNumber_comboBox.setBounds(132, 214, 143, 22);
		add(cpuNumber_comboBox);
		
		JPanel continue_panel = new JPanel();
		continue_panel.addMouseListener(new MouseAdapter() {
            @SuppressWarnings("unchecked")
            @Override
            public void mouseClicked(MouseEvent e) {

                // when continue is clicked it should check the number of players playing the game is valid
                // condition is true a new panel TMD is created
                // passing login panel and frame so that we can so that we can change them
                // the  sub class
                if ((Integer.parseInt(String.valueOf(playerNumber_comboBox.getSelectedItem())) + Integer.parseInt(String.valueOf(cpuNumber_comboBox.getSelectedItem()))) > 1
                        && (Integer.parseInt(String.valueOf(playerNumber_comboBox.getSelectedItem())) + Integer.parseInt(String.valueOf(cpuNumber_comboBox.getSelectedItem()))) < 5) {
					if (gridSize_comboBox.getSelectedIndex() == 0) {
						GAME_SETTINGS.put("gridSize", "16");
					} else if (gridSize_comboBox.getSelectedIndex() == 1) {
						GAME_SETTINGS.put("gridSize", "20");
					} else if (gridSize_comboBox.getSelectedIndex() == 2) {
						GAME_SETTINGS.put("gridSize", "24");
					}

					GAME_SETTINGS.put("players", (String) playerNumber_comboBox.getSelectedItem());
					GAME_SETTINGS.put("AI", (String) cpuNumber_comboBox.getSelectedItem());

                    tmd=new TMD(close_panel,loginPage,frame,GAME_SETTINGS);
                    gpc.removeAll();
                    tmd.setBounds(0,0,gpc.getWidth(),gpc.getHeight());
                    tmd.add(close_panel);
                    gpc.add(tmd);
                    gpc.revalidate();
                    gpc.repaint();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Please make valid selections.");
                }
            }
		});
		continue_panel.setBackground(Color.ORANGE);
		continue_panel.setBounds(59, 305, 216, 49);
		add(continue_panel);
		continue_panel.setLayout(null);
		
		JLabel lblContinue = new JLabel("Continue");
		lblContinue.setForeground(Color.white);
		lblContinue.setBounds(66, 11, 140, 25);
		lblContinue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		continue_panel.add(lblContinue);
		
		

	}
}
