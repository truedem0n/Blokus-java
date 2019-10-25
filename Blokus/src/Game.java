import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int GRID_SIZE = 20, seconds;
	private shapesList shapelist;
	private JLabel lblTimeLeft;
	private Timer timer;
	private GameBoard GAME_BOARD;
	/**
	 * Create the panel.
	 *
	 * @param frame
	 */
	public Game(GUI frame)  {
		
		setBackground(Color.LIGHT_GRAY);
		frame.setBounds(0, 0, 709, 608);
		frame.setLocationRelativeTo(null);
		setBounds(0, 0, 709, 608);
		setLayout(null);


		
		shapelist=new shapesList();
		shapelist.setBounds(10, 114, 169, 259);
		add(shapelist);
		
		JPanel surrender = new JPanel();
		surrender.setBounds(20, 384, 148, 40);
		add(surrender);
		surrender.setLayout(null);
		
		JLabel lblSurrender = new JLabel("Surrender");
		lblSurrender.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblSurrender.setBounds(36, 11, 130, 18);
		surrender.add(lblSurrender);

		JLabel lblScore = new JLabel("Score: 0");
		lblScore.setBounds(20, 478, 128, 14);
		add(lblScore);

		JLabel lblBlokus = new JLabel(" Blokus");
		lblBlokus.setFont(new Font("Century Gothic", Font.PLAIN, 25));
		lblBlokus.setBounds(0, 1, 148, 32);
		add(lblBlokus);

		JLabel lblTurn = new JLabel("Turn");
		lblTurn.setBounds(76, 89, 31, 14);
		add(lblTurn);

		GAME_BOARD = new GameBoard(GRID_SIZE,shapelist);
		GAME_BOARD.setBounds(189, 89, 508, 508);
		add(GAME_BOARD);
		GAME_BOARD.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

		lblTimeLeft = new JLabel("Time left: 10:00");
		seconds=0;
		timer = new Timer(1000, new ActionListener() {

	        public void actionPerformed(ActionEvent e) {
	        	if (seconds>600)
	        		timer.stop();
	        	seconds+=1;
	        	lblTimeLeft.setText("Time left:"+( String.format("%02d", (600-seconds)/60))+":"+( String.format("%02d", (600-seconds)%60)));
	        	
	        }
	    });
	    timer.setRepeats(true);
	    timer.start();
		lblTimeLeft.setBounds(587, 64, 110, 14);
		add(lblTimeLeft);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		menuBar.setBounds(95, 11, 99, 22);
		menuBar.setBorder(null);
		add(menuBar);
		
		JMenu mnOptions = new JMenu("Options");
		mnOptions.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		menuBar.add(mnOptions);
		
		JMenuItem mntmOption = new JMenuItem("Option1");
		mntmOption.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		mnOptions.add(mntmOption);

	}
}
