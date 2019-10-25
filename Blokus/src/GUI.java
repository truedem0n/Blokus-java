import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static GUI frame;
	private JPanel LoginPage,close_panel,welcome_panel,logo_panel,play_panel;
	private int xy,xx;
	private Dictionary<Object, Object> GAME_SETTINGS; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(
				            UIManager.getCrossPlatformLookAndFeelClassName());
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
    public GUI() throws IOException {
		//Creating Game settings
		GAME_SETTINGS=new Hashtable<Object, Object>();
		
		// antialiasing for font smoothing 
		System.setProperty("awt.useSystemAAFontSettings","on");
		System.setProperty("swing.aatext", "true");
		// creating routes
		
		// our frame
		setUndecorated(true);
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

		
        ImageIcon img = new ImageIcon(GUI.class.getResource("images/logo.jpg"));
        Image dimg=img.getImage().getScaledInstance(343, 478,Image.SCALE_SMOOTH);
        
		logo_panel = new JPanel();
        JLabel label = new JLabel(new ImageIcon(dimg));
        label.setBounds(0, 0, 343, 478);


        logo_panel.add(label);
		logo_panel.setBounds(0, 0, 343, 478);
		LoginPage.add(logo_panel);
		logo_panel.setLayout(null);
		JLabel lblBlokus = new JLabel("<html><span style='font-size:40px'>"+"Blokus"+"</span></html>");
		lblBlokus.setForeground(new Color(255, 255, 255));
		lblBlokus.setBounds(84, 80, 152, 95);
		logo_panel.add(lblBlokus);
		lblBlokus.setFont(new Font("Century Gothic", Font.PLAIN, 33));
		
		welcome_panel = new JPanel();
		welcome_panel.setBackground(new Color(63, 71, 204));
		welcome_panel.setBounds(342, 0, 345, 478);
		LoginPage.add(welcome_panel);
		welcome_panel.setLayout(null);
		
		play_panel = new JPanel();
		play_panel.setBounds(101, 190, 140, 42);
		welcome_panel.add(play_panel);
		play_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				welcome_panel.removeAll();
				welcome_panel.add(close_panel);
				GPC gpc=new GPC(close_panel,LoginPage,frame,GAME_SETTINGS);
				gpc.setBounds(0,0,welcome_panel.getWidth(),welcome_panel.getHeight());
				welcome_panel.add(gpc);
				welcome_panel.repaint();
				welcome_panel.revalidate();
				

			}
		});
		play_panel.setForeground(Color.WHITE);
		play_panel.setBackground(Color.GREEN);
		play_panel.setLayout(null);
		
		JLabel lblPlay = new JLabel("PLAY");
		lblPlay.setBounds(46, 5, 84, 26);
		lblPlay.setForeground(Color.WHITE);
		lblPlay.setFont(new Font("Century Gothic", Font.BOLD, 20));
		play_panel.add(lblPlay);
		
		JPanel load_panel = new JPanel();
		load_panel.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void mouseClicked(MouseEvent s) {

				
				JSONParser jsonParser = new JSONParser();
		        try (FileReader reader = new FileReader("sessions/sessions.json"))
		        {
		            //Read JSON file
		            Object obj = jsonParser.parse(reader);
		            JSONArray employeeList = (JSONArray) obj;
		            System.out.println(employeeList);
		            //Iterate over employee array
		            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } catch (ParseException e) {
		            e.printStackTrace();
		        }
		    }
		
		});
		load_panel.setBounds(101, 252, 140, 42);
		welcome_panel.add(load_panel);
		load_panel.setBackground(new Color(128, 0, 0));
		load_panel.setForeground(new Color(0, 0, 0));
		load_panel.setLayout(null);
		
		JLabel lblLoad = new JLabel("LOAD");
		lblLoad.setBounds(42, 5, 88, 26);
		lblLoad.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblLoad.setForeground(Color.WHITE);
		load_panel.add(lblLoad);
		
		JPanel exit_panel = new JPanel();
		exit_panel.setBounds(101, 314, 140, 42);
		welcome_panel.add(exit_panel);
		exit_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		exit_panel.setBackground(new Color(255, 0, 0));
		exit_panel.setLayout(null);
		
		JLabel lblExit = new JLabel("EXIT");
		lblExit.setBounds(51, 5, 89, 26);
		lblExit.setForeground(new Color(255, 255, 255));
		lblExit.setFont(new Font("Century Gothic", Font.BOLD, 20));
		exit_panel.add(lblExit);
		
		close_panel = new JPanel();
		close_panel.setOpaque(false);
		close_panel.setBounds(307, 11, 28, 36);
		welcome_panel.add(close_panel);
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
    
    private static void parseEmployeeObject(JSONObject session)
    {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) session.get("employee");
         
        //Get employee first name
        String firstName = (String) employeeObject.get("firstName");   
        System.out.println(firstName);
         
        //Get employee last name
        String lastName = (String) employeeObject.get("lastName"); 
        System.out.println(lastName);
         
        //Get employee website name
        String website = (String) employeeObject.get("website");   
        System.out.println(website);
    }
}
