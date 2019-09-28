import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

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
		try {
			InputStream is = GUI.class.getResourceAsStream("LatoRegular.ttf");
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			sizedFont = font.deriveFont(12f);
			
		}catch (Exception e){
//			e.printStackTrace();
		}
		
		setUndecorated(true);
		setResizable(false);
		setShape(new RoundRectangle2D.Double(100, 100, getWidth(), getHeight(), 80, 80));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 364, 475);
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
		LoginPage.setBackground(Color.WHITE);
		LoginPage.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(LoginPage);
		LoginPage.setLayout(null);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		
		JLabel lblBlokus = new JLabel("<html><span style='font-size:20px'>"+"Blokus"+"</span></html>");
		lblBlokus.setFont(sizedFont);
		lblBlokus.setBounds(94, 101, 152, 70);
		LoginPage.add(lblBlokus);
		
		JLabel lblX = new JLabel("<html><span style='font-size:15px'>"+"X"+"</span></html>");
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
		lblX.setBounds(339, 11, 15, 21);
		LoginPage.add(lblX);
	}
}
