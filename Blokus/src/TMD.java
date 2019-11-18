/**
 * @author: Atul Mehla
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
class TMD extends JPanel {
	/**
	 *   All the declarartions
	 */
	private static final long serialVersionUID = 1L;
	private final JComboBox<String> difficulty_comboBox;
    // --Commented out by Inspection (11/17/2019 2:11 PM):private colors color;
	private JComboBox<String> timeLimit_comboBox;
	private JComboBox<String> minutes_comboBox;

	/**
	 * Create the panel.
	 * @param gAME_SETTINGS
	 * @param gui
	 */
	public TMD(Map<String, String> gAME_SETTINGS, GUI gui) {

		/*
		  This is the back button Image
		 */
		ImageIcon closeButtonImg = new ImageIcon(GUI.class.getResource("images/closeButton.png"));
		Image scaledCloseButtonImg = closeButtonImg.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

		// adding the blokus logo to the panel via jlabel
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

		// adding the blokus logo to the panel via jlabel
		JButton backButtonPanel = new JButton();
		JLabel backButtonLabel = new JLabel(new ImageIcon(scaledBackButtonImg));
		backButtonPanel.setLayout(null);
		backButtonLabel.setBounds(0, 0, 40, 40);
		backButtonPanel.add(backButtonLabel);
		backButtonPanel.setBounds(10, 10, 40, 40);
		backButtonPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gui.setWindow(1);
			}
		});
		add(backButtonPanel);

        TMD tmd = this;
		setBackground(new Color(63, 71, 204));
		setBounds(342, 0, 345, 478);
		setLayout(null);

		JLabel lblTimeLimit = new JLabel("Time Limit:");
		lblTimeLimit.setForeground(Color.white);
		lblTimeLimit.setBounds(59, 152, 63, 14);
		add(lblTimeLimit);


		ActionListener timeLimit_comboBoxListener= e->{
			if (timeLimit_comboBox.getSelectedItem() == "YES") {
				minutes_comboBox.setEnabled(true);
			}else {
				minutes_comboBox.setEnabled(false);
			}
		};
		String[] tl = {"NO", "YES"};
		timeLimit_comboBox = new JComboBox<>(tl);
		timeLimit_comboBox.setBounds(132, 148, 143, 22);
		timeLimit_comboBox.addActionListener(timeLimit_comboBoxListener);
		add(timeLimit_comboBox);

		String[] m1 = {"10", "15", "20"};
		minutes_comboBox = new JComboBox<>(m1);
		minutes_comboBox.setEnabled(false);
		minutes_comboBox.setBounds(132, 176, 143, 22);
		add(minutes_comboBox);

		JLabel lblMinutes = new JLabel("Minutes:");
		lblMinutes.setForeground(Color.white);
		lblMinutes.setBounds(59, 180, 97, 14);
		add(lblMinutes);

		JLabel lblDifficulty = new JLabel("Difficulty: ");
		lblDifficulty.setForeground(Color.white);
		lblDifficulty.setBounds(59, 211, 97, 14);
		add(lblDifficulty);

		String[] df = {"Easy", "Medium", "Hard"};
		difficulty_comboBox = new JComboBox<>(df);
		difficulty_comboBox.setBounds(132, 207, 143, 22);
		add(difficulty_comboBox);

		JButton continue_panel = new JButton();
		continue_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gAME_SETTINGS.put("timeLimit", (String.valueOf(timeLimit_comboBox.getSelectedIndex())));
				if (timeLimit_comboBox.getSelectedIndex() == 1) {
					gAME_SETTINGS.put("minutes", (String) minutes_comboBox.getSelectedItem());
				} else {
					gAME_SETTINGS.put("minutes", "0");
				}

				gAME_SETTINGS.put("difficulty", String.valueOf(difficulty_comboBox.getSelectedIndex()));
				((colors) gui.getWindowObjectAccordingToIndex(3)).setUpPlayersVariables();
				gui.setWindow(3);
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
