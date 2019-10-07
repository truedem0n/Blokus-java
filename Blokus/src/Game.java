import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import javax.swing.JButton;

public class Game extends JPanel {

	/**
	 * Create the panel.
	 * @param frame 
	 */
	public Game(GUI frame) {
		setBackground(Color.LIGHT_GRAY);
		frame.setBounds(0, 0, 745, 720-200);
		frame.setLocationRelativeTo(null);
		setBounds(0, 0, 745, 520);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 114, 170, 241);
		add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 366, 148, 40);
		add(panel_1);
		
		JLabel lblScore = new JLabel("Score: 0");
		lblScore.setBounds(20, 478, 48, 14);
		add(lblScore);
		
		JLabel lblBlokus = new JLabel(" Blokus");
		lblBlokus.setFont(new Font("Century Gothic", Font.PLAIN, 25));
		lblBlokus.setBounds(0, 1, 82, 32);
		add(lblBlokus);
		
		JLabel lblTurn = new JLabel("Turn");
		lblTurn.setBounds(76, 89, 31, 14);
		add(lblTurn);
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setBounds(92, 11, 48, 14);
		add(lblOptions);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(190, 89, 545, 420);
		add(panel_2);
		panel_2.setLayout(new GridLayout(10, 10));
		
		JButton btnNewButton_1 = new JButton("New button");
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		panel_2.add(btnNewButton_2);
		
		JButton button = new JButton("New button");
		panel_2.add(button);
		
		JButton button_1 = new JButton("New button");
		panel_2.add(button_1);
		
		JButton button_2 = new JButton("New button");
		panel_2.add(button_2);
		
		JButton button_3 = new JButton("New button");
		panel_2.add(button_3);
		
		JButton button_4 = new JButton("New button");
		panel_2.add(button_4);
		
		JButton button_5 = new JButton("New button");
		panel_2.add(button_5);
		
		JButton button_6 = new JButton("New button");
		panel_2.add(button_6);
		
		JButton button_7 = new JButton("New button");
		panel_2.add(button_7);
		
		JButton btnNewButton = new JButton("New button");
		panel_2.add(btnNewButton);
		
		JButton button_9 = new JButton("New button");
		panel_2.add(button_9);
		
		JButton button_8 = new JButton("New button");
		panel_2.add(button_8);
		
		JButton button_11 = new JButton("New button");
		panel_2.add(button_11);
		
		JButton button_10 = new JButton("New button");
		panel_2.add(button_10);
		
		JButton button_13 = new JButton("New button");
		panel_2.add(button_13);
		
		JButton button_12 = new JButton("New button");
		panel_2.add(button_12);
		
		JButton button_17 = new JButton("New button");
		panel_2.add(button_17);
		
		JButton button_16 = new JButton("New button");
		panel_2.add(button_16);
		
		JButton button_15 = new JButton("New button");
		panel_2.add(button_15);
		
		JButton button_18 = new JButton("New button");
		panel_2.add(button_18);
		
		JButton button_14 = new JButton("New button");
		panel_2.add(button_14);
		
		JButton button_19 = new JButton("New button");
		panel_2.add(button_19);
		
		JButton button_21 = new JButton("New button");
		panel_2.add(button_21);
		
		JButton button_20 = new JButton("New button");
		panel_2.add(button_20);
		
		JButton button_22 = new JButton("New button");
		panel_2.add(button_22);
		
		JButton button_25 = new JButton("New button");
		panel_2.add(button_25);
		
		JButton button_24 = new JButton("New button");
		panel_2.add(button_24);
		
		JButton button_23 = new JButton("New button");
		panel_2.add(button_23);
		
		JButton button_27 = new JButton("New button");
		panel_2.add(button_27);
		
		JButton button_26 = new JButton("New button");
		panel_2.add(button_26);
		
		JButton button_29 = new JButton("New button");
		panel_2.add(button_29);
		
		JButton button_28 = new JButton("New button");
		panel_2.add(button_28);
		
		JButton button_30 = new JButton("New button");
		panel_2.add(button_30);
		
		JButton button_33 = new JButton("New button");
		panel_2.add(button_33);
		
		JButton button_32 = new JButton("New button");
		panel_2.add(button_32);
		
		JButton button_31 = new JButton("New button");
		panel_2.add(button_31);
		
		JButton button_34 = new JButton("New button");
		panel_2.add(button_34);
		
		JButton button_36 = new JButton("New button");
		panel_2.add(button_36);
		
		JButton button_35 = new JButton("New button");
		panel_2.add(button_35);
		
		JLabel lblTimeLeft = new JLabel("Time left: 00:00");
		lblTimeLeft.setBounds(646, 64, 89, 14);
		add(lblTimeLeft);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
