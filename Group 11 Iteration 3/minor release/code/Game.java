import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int GRID_SIZE = 20, seconds;
    private Player players;
    private shapesList shapelist;
    private JLabel lblTimeLeft;
    private Timer timer;
    private GameBoard GAME_BOARD;

    /**
     * Create the panel.
     *
     * @param frame
     */
    public Game(GUI frame, String[][][] savedArray) {
        setBackground(new Color(63, 71, 204));
        frame.setBounds(0, 0, 709, 608);
        frame.setLocationRelativeTo(null);
        setBounds(0, 0, 709, 608);
        setLayout(null);

        players = new Player(Color.red);
        players.setBounds(10, 114, 169, 259);
        add(players);


        JPanel surrender = new JPanel();
        surrender.setForeground(Color.WHITE);
        surrender.setBounds(20, 384, 148, 40);
        surrender.setBackground(new Color(2, 40, 89));
        add(surrender);
        surrender.setLayout(null);

        JLabel lblSurrender = new JLabel("Surrender");
        lblSurrender.setForeground(Color.WHITE);
        lblSurrender.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        lblSurrender.setBounds(36, 11, 130, 18);
        surrender.add(lblSurrender);

        JLabel lblScore = new JLabel("Score: 0");
        lblScore.setForeground(Color.white);
        lblScore.setBounds(20, 478, 128, 14);
        add(lblScore);

        JLabel lblBlokus = new JLabel(" Blokus");
        lblBlokus.setForeground(Color.white);
        lblBlokus.setFont(new Font("Century Gothic", Font.PLAIN, 25));
        lblBlokus.setBounds(0, 1, 148, 32);
        add(lblBlokus);

        JLabel lblTurn = new JLabel("Turn");
        lblTurn.setForeground(Color.white);
        lblTurn.setBounds(76, 89, 31, 14);
        add(lblTurn);

        GAME_BOARD = new GameBoard(GRID_SIZE, players, savedArray);
        GAME_BOARD.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                GAME_BOARD.clearCurrentAction();
                players.scrollActionHide();
                players.rotateClockWise();
                players.scrollActionDraw();
                GAME_BOARD.drawCurrentAction();

            }
        });
        GAME_BOARD.setBounds(189, 89, 508, 508);
        add(GAME_BOARD);
        GAME_BOARD.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        lblTimeLeft = new JLabel("Time left: 10:00");
        lblTimeLeft.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTimeLeft.setForeground(Color.white);
        seconds = 0;
        timer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (seconds > 600)
                    timer.stop();
                seconds += 1;
                lblTimeLeft.setText("Time left:" + (String.format("%02d", (600 - seconds) / 60)) + ":" + (String.format("%02d", (600 - seconds) % 60)));

            }
        });
        timer.setRepeats(true);
        timer.start();
        lblTimeLeft.setBounds(561, 64, 136, 14);
        add(lblTimeLeft);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(63, 71, 204));
        menuBar.setBounds(95, 7, 99, 22);
        menuBar.setBorder(null);
        add(menuBar);

        JMenu mnOptions = new JMenu("Options");
        mnOptions.setForeground(Color.white);
        mnOptions.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        menuBar.add(mnOptions);

        JMenuItem mntmOption = new JMenuItem("Save   ");
        mntmOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(new DataManager().save(GAME_BOARD.getBoard()).equals("sucess")){
                    JOptionPane.showMessageDialog(new JFrame(),"Successfully saved");
                }else{
                    JOptionPane.showMessageDialog(new JFrame(),"Failed to save");
                }
            }
        });
        mntmOption.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        mnOptions.add(mntmOption);

    }

    private void settingsLoader() {

    }
}
