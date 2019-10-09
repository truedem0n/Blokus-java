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
	private int GRID_SIZE= 10;

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
		panel_2.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
		
		for (int i=0;i<GRID_SIZE;i++)
			for (int j=0;j<GRID_SIZE;j++) {
				panel_2.add(new JButton());
			}
		
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
