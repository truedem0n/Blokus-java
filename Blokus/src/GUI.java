/**
 * @author: Atul Mehla
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Hashtable;
import java.util.Map;
class GUI extends JFrame {

    /**
     * serial version uid is just a class identifier (ignore this)
     * frame is the main Jframe  of this application
     * panels are being used to design the application
     * their layout is kept null because I (Atul) used
     * window builder tool of eclipse to drag and drop
     * components for faster development
     * <p>
     * int xy and xx are  just the vairables keeping care
     * of dragging functionality of the game
     * <p>
     * GAME_SETTINGS this will be used for saving game settings
     */
    private static final long serialVersionUID = 1L;
    private static GUI frame;
    private final JPanel containerPanel;
    private int currentActiveWindowIndex;
    private final Map<String, String> GAME_SETTINGS;
    private JPanel[] activeRWindow = new JPanel[4];
    private int xy, xx;

    /**
     * Create the frame.
     *
     * @throws FontFormatException
     */
    GUI() {
        //hashtable that will be used for load a game configuration or save a game configuration
        GAME_SETTINGS = new Hashtable<String, String>();

        // antialiasing for font smoothing
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        // creating routes

        // setting main application frame settings
        setUndecorated(true);
        setBounds(100, 100, 687, 478);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(100, 100, 364, 475);
        setLocationRelativeTo(null);


		/*
		  this is the main panel
		  containing the blokus logo panel
		  and play, load, and exit panel
		 */
        containerPanel = new JPanel();

		/*
		  This functionality allows us to drag our frame using these two listeners
		  we are basically grabbing cursor location and changing the frame location
		  according to the cursor
		 */
        containerPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                GUI.this.setLocation(x - xx, y - xy);
            }

        });
        containerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xx = e.getX();
                xy = e.getY();
            }
        });
        containerPanel.setForeground(Color.WHITE);
        containerPanel.setBackground(Color.CYAN);
        containerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(containerPanel);
        containerPanel.setLayout(null);

		/*
		  this is the blokus logo
		 */
        ImageIcon logoImg = new ImageIcon(GUI.class.getResource("images/logo.jpg"));
        Image scaledLogoImg = logoImg.getImage().getScaledInstance(343, 478, Image.SCALE_SMOOTH);

        // adding the blokus logo to the panel via jlabel
        JPanel logo_panel = new JPanel();
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogoImg));
        logo_panel.setLayout(null);
        logoLabel.setBounds(0, 0, 343, 478);
        logo_panel.add(logoLabel);
        logo_panel.setBounds(0, 0, 343, 478);
        containerPanel.add(logo_panel);


        /*
		  This is the back button Image
		 */
        ImageIcon backButtonImg = new ImageIcon(GUI.class.getResource("images/backButton.png"));
        Image scaledBackButtonImg = backButtonImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        // adding the blokus logo to the panel via jlabel
        JPanel backButtonPanel = new JPanel();
        JLabel backButtonLabel = new JLabel(new ImageIcon(scaledBackButtonImg));
        backButtonPanel.setLayout(null);
        backButtonLabel.setBounds(0, 0, 40, 40);
        backButtonPanel.add(backButtonLabel);
        backButtonPanel.setBounds(0, 0, 40, 40);




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


        // this panel would be storing our play load and exit buttons
        activeRWindow[0] = new JPanel();
        activeRWindow[0].setBackground(new Color(63, 71, 204));
        activeRWindow[0].setLayout(null);
        activeRWindow[0].setBounds(342, 0, 345, 478);
        activeRWindow[0].add(close_panel);
        containerPanel.add(activeRWindow[0]);


        activeRWindow[1] = new GPC(GAME_SETTINGS, this);
        activeRWindow[1].setVisible(false);
        activeRWindow[1].setBackground(new Color(63, 71, 204));
        activeRWindow[1].setBounds(342, 0, 345, 478);
        activeRWindow[1].setLayout(null);
        containerPanel.add(activeRWindow[1]);


        activeRWindow[2] = new TMD(GAME_SETTINGS, this);
        activeRWindow[2].setVisible(false);
        activeRWindow[2].setBackground(new Color(63, 71, 204));
        activeRWindow[2].setBounds(342, 0, 345, 478);
        activeRWindow[2].setLayout(null);
        containerPanel.add(activeRWindow[2]);

        activeRWindow[3] = new colors(containerPanel, this, GAME_SETTINGS);
        activeRWindow[3].setBackground(new Color(63, 71, 204));
        activeRWindow[3].setBounds(342, 0, 345, 478);
        activeRWindow[3].setVisible(false);
        containerPanel.add(activeRWindow[3]);
        activeRWindow[3].setLayout(null);


        JButton play_panel = new JButton();
        play_panel.setBounds(101, 190, 140, 42);
        activeRWindow[0].add(play_panel);
        play_panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setWindow(1);

                // if play is create a gpc panel and add that to welcome_panel container
                // also remove everything before adding
                //welcome.removeAll();

                //GPC gpc = new GPC(close_panel, containerPanel, frame, GAME_SETTINGS);
                //gpc.setBounds(0, 0, welcome.getWidth(), welcome.getHeight());
                //welcome.add(gpc);
                //welcome.repaint();
                //welcome.revalidate();


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

        JButton load_panel = new JButton();
        load_panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent s) {

                // sample arrary
                String[][][] data = new DataManager().load();
                frame.setVisible(false);
                Game game = new Game(frame, data, GAME_SETTINGS);
                frame.setVisible(true);
                containerPanel.removeAll();
                containerPanel.add(game);
                close_panel.setBounds(680, 2, 28, 36);
                game.add(close_panel);
                containerPanel.revalidate();
                containerPanel.repaint();
            }

        });
        load_panel.setBounds(101, 252, 140, 42);
        activeRWindow[0].add(load_panel);
        load_panel.setBackground(new Color(128, 0, 0));
        load_panel.setForeground(new Color(0, 0, 0));
        load_panel.setLayout(null);

        JLabel lblLoad = new JLabel("LOAD");
        lblLoad.setBounds(42, 5, 88, 26);
        lblLoad.setFont(new Font("Century Gothic", Font.BOLD, 20));
        lblLoad.setForeground(Color.WHITE);
        load_panel.add(lblLoad);

        JButton exit_panel = new JButton();
        exit_panel.setBounds(101, 314, 140, 42);
        activeRWindow[0].add(exit_panel);
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

    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(
                        UIManager.getCrossPlatformLookAndFeelClassName());
                frame = new GUI();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public JPanel getWindowObjectAccordingToIndex(int x) {
        return activeRWindow[x];
    }

    public void setWindow(int x) {
        currentActiveWindowIndex = x;
        for (int i = 0; i < activeRWindow.length; i++) {
            activeRWindow[i].setVisible(false);
            if (currentActiveWindowIndex == i) {
                activeRWindow[i].setVisible(true);
            }
        }
    }


}
