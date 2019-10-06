import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static GUI frame;
	private JPanel LoginPage,close_panel,settings_panel,logo_panel,play_panel;
	int xy,xx;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GUI();
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
		// antialiasing for font smoothing 
		System.setProperty("awt.useSystemAAFontSettings","on");
		System.setProperty("swing.aatext", "true");
		// creating routes
		
		// our frame
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
		
		logo_panel = new JPanel();
		logo_panel.setBackground(new Color(138, 43, 226));
		logo_panel.setBounds(0, 0, 343, 478);
		LoginPage.add(logo_panel);
		logo_panel.setLayout(null);
		JLabel lblBlokus = new JLabel("<html><span style='font-size:40px'>"+"Blokus"+"</span></html>");
		lblBlokus.setForeground(new Color(255, 255, 255));
		lblBlokus.setBounds(84, 80, 152, 95);
		logo_panel.add(lblBlokus);
		lblBlokus.setFont(new Font("Century Gothic", Font.PLAIN, 33));
		
		settings_panel = new JPanel();
		settings_panel.setBackground(Color.CYAN);
		settings_panel.setBounds(342, 0, 345, 478);
		LoginPage.add(settings_panel);
		settings_panel.setLayout(null);
		
		play_panel = new JPanel();
		play_panel.setBounds(101, 190, 140, 42);
		settings_panel.add(play_panel);
		play_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				settings_panel.removeAll();
				settings_panel.add(close_panel);
				GPC gpc=new GPC();
				gpc.setBounds(0,0,settings_panel.getWidth(),settings_panel.getHeight());
				settings_panel.add(gpc);
				settings_panel.repaint();
				settings_panel.revalidate();
				

			}
		});
		play_panel.setForeground(Color.WHITE);
		play_panel.setBackground(Color.GREEN);
		
		JLabel lblPlay = new JLabel("PLAY");
		lblPlay.setForeground(Color.WHITE);
		lblPlay.setFont(new Font("Century Gothic", Font.BOLD, 20));
		play_panel.add(lblPlay);
		
		JPanel load_panel = new JPanel();
		load_panel.setBounds(101, 252, 140, 42);
		settings_panel.add(load_panel);
		load_panel.setBackground(new Color(128, 0, 0));
		load_panel.setForeground(new Color(0, 0, 0));
		
		JLabel lblLoad = new JLabel("LOAD");
		lblLoad.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblLoad.setForeground(Color.WHITE);
		load_panel.add(lblLoad);
		
		JPanel exit_panel = new JPanel();
		exit_panel.setBounds(101, 314, 140, 42);
		settings_panel.add(exit_panel);
		exit_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		exit_panel.setBackground(new Color(255, 0, 0));
		
		JLabel lblExit = new JLabel("EXIT");
		lblExit.setForeground(new Color(255, 255, 255));
		lblExit.setFont(new Font("Century Gothic", Font.BOLD, 20));
		exit_panel.add(lblExit);
		
		close_panel = new JPanel();
		close_panel.setOpaque(false);
		close_panel.setBounds(307, 11, 28, 36);
		settings_panel.add(close_panel);
		close_panel.setBorder(null);
		close_panel.setBackground(Color.CYAN);
		
		JLabel lblX = new JLabel("<html><span style='font-size:15px'>"+"X"+"</span></html>");
		close_panel.add(lblX);
		lblX.setIcon(null);
		lblX.setBackground(Color.RED);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblX.setForeground(Color.RED);
	}
}
