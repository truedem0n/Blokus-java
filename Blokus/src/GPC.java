import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;

public class GPC extends JPanel {

	/**
	 * Create the panel.
	 */
	public GPC() {
		setBackground(Color.ORANGE);
		setBounds(342, 0, 345, 478);
		setLayout(null);
		
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
		panel.setBackground(Color.GREEN);
		panel.setBounds(59, 305, 216, 49);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblContinue = new JLabel("Continue");
		lblContinue.setBounds(66, 11, 79, 25);
		lblContinue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblContinue);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(132, 155, 143, 22);
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(132, 183, 143, 22);
		add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(132, 214, 143, 22);
		add(comboBox_2);

	}
}
