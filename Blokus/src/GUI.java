import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel LoginPage;
	int xy,xx;
	Font sizedFont;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public GUI() {
		  System.setProperty("awt.useSystemAAFontSettings","on");
		  System.setProperty("swing.aatext", "true");
		try {
			InputStream is = GUI.class.getResourceAsStream("LatoRegular.ttf");
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			sizedFont = font.deriveFont(12f);
			
		}catch (Exception e){
//			e.printStackTrace();
		}
		
		setUndecorated(true);
		setResizable(false);
		setBounds(100, 100, 687, 478);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 364, 475);
		setLocationRelativeTo(null);
		LoginPage = new JPanel();
		LoginPage.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
		        int y = e.getYOnScreen();
		        GUI.this.setLocation(x - xx, y - xy); 
			}
			
		});
		LoginPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx = e.getX();
		        xy = e.getY();
			}
		});
		LoginPage.setForeground(Color.WHITE);
		LoginPage.setBackground(Color.CYAN);
		LoginPage.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(LoginPage);
		LoginPage.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(138, 43, 226));
		panel.setBounds(0, 0, 343, 489);
		LoginPage.add(panel);
		panel.setLayout(null);
		JLabel lblBlokus = new JLabel("<html><span style='font-size:40px'>"+"Blokus"+"</span></html>");
		lblBlokus.setForeground(new Color(255, 255, 255));
		lblBlokus.setBounds(84, 80, 152, 95);
		panel.add(lblBlokus);
		lblBlokus.setFont(new Font("Century Gothic", Font.PLAIN, 33));
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Play clicked");
			}
		});
		panel_1.setForeground(Color.WHITE);
		panel_1.setBackground(Color.GREEN);
		panel_1.setBounds(443, 190, 140, 42);
		LoginPage.add(panel_1);
		
		JLabel lblPlay = new JLabel("PLAY");
		lblPlay.setForeground(Color.WHITE);
		lblPlay.setFont(new Font("Century Gothic", Font.BOLD, 20));
		panel_1.add(lblPlay);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(128, 0, 0));
		panel_2.setForeground(new Color(0, 0, 0));
		panel_2.setBounds(443, 252, 140, 42);
		LoginPage.add(panel_2);
		
		JLabel lblLoad = new JLabel("LOAD");
		lblLoad.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblLoad.setForeground(Color.WHITE);
		panel_2.add(lblLoad);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 0, 0));
		panel_3.setBounds(443, 314, 140, 42);
		LoginPage.add(panel_3);
		
		JLabel lblExit = new JLabel("EXIT");
		lblExit.setForeground(new Color(255, 255, 255));
		lblExit.setFont(new Font("Century Gothic", Font.BOLD, 20));
		panel_3.add(lblExit);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(null);
		panel_4.setBackground(Color.CYAN);
		panel_4.setBounds(649, 11, 28, 36);
		LoginPage.add(panel_4);
		
		JLabel lblX = new JLabel("<html><span style='font-size:15px'>"+"X"+"</span></html>");
		panel_4.add(lblX);
		lblX.setIcon(null);
		lblX.setBackground(Color.RED);
		lblX.setFont(sizedFont);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblX.setForeground(Color.RED);
	}
}
