/**
 * @author: Atul Mehla
 */
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
    // --Commented out by Inspection (11/17/2019 2:11 PM):private TMD tmd;
	private final JComboBox<?> gridSize_comboBox;
	private final JComboBox<?> playerNumber_comboBox;
	private final JComboBox<?> cpuNumber_comboBox;


	/**
	 * Create the panel.
	 * @param GAME_SETTINGS class constructor
	 * @param gui
	 */


	GPC(Map<String, String> GAME_SETTINGS, GUI gui) {

		/*
		  This is the back button Image
		 */
		ImageIcon closeButtonImg = new ImageIcon(GUI.class.getResource("images/closeButton.png"));
		Image scaledCloseButtonImg = closeButtonImg.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

		// adding the logo to the panel via jlabel
		JButton close_panel = new JButton();
		JLabel close_panelLabel = new JLabel(new ImageIcon(scaledCloseButtonImg));
		close_panel.setLayout(null);
		close_panel.setBounds(345 - 50, 0, 50, 50);
		close_panelLabel.setBounds(0, 0, 50, 50);
		close_panel.add(close_panelLabel);
		close_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		add(close_panel);


		/*
		  This is the back button Image
		 */
		ImageIcon backButtonImg = new ImageIcon(GUI.class.getResource("images/backButton.png"));
		Image scaledBackButtonImg = backButtonImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

		// adding the  logo to the panel via jlabel
		JButton backButtonPanel = new JButton();
		JLabel backButtonLabel = new JLabel(new ImageIcon(scaledBackButtonImg));
		backButtonPanel.setLayout(null);
		backButtonLabel.setBounds(0, 0, 40, 40);
		backButtonPanel.add(backButtonLabel);
		backButtonPanel.setBounds(10, 10, 40, 40);
		backButtonPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gui.setWindow(0);
			}
		});
		add(backButtonPanel);


		setBackground(new Color(63, 71, 204));
		setBounds(342, 0, 345, 478);
		setLayout(null);
        GPC gpc = this;


		JLabel lblNewLabel = new JLabel("Grid size:");
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setBounds(59, 152, 63, 14);
		add(lblNewLabel);

		JLabel lblPlayers = new JLabel("Players:");
		lblPlayers.setForeground(Color.white);
		lblPlayers.setBounds(59, 180, 97, 14);
		add(lblPlayers);

		JLabel lblCpus = new JLabel("AI(s):");
		lblCpus.setForeground(Color.white);
		lblCpus.setBounds(59, 211, 97, 14);
		add(lblCpus);
		String[] g1 = {"16x16", "20x20", "24x24"};
		gridSize_comboBox = new JComboBox<Object>(g1);
		gridSize_comboBox.setBounds(132, 148, 143, 22);
		add(gridSize_comboBox);

		String[] p1 = {"1", "2", "3", "4"};
		playerNumber_comboBox = new JComboBox<Object>(p1);
		playerNumber_comboBox.setBounds(132, 176, 143, 22);
		add(playerNumber_comboBox);

		String[] c1 = {"0", "1", "2", "3"};
		cpuNumber_comboBox = new JComboBox<Object>(c1);
		cpuNumber_comboBox.setBounds(132, 207, 143, 22);
		add(cpuNumber_comboBox);

		JButton continue_panel = new JButton();
		continue_panel.addMouseListener(new MouseAdapter() {
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
					gui.setWindow(2);
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
