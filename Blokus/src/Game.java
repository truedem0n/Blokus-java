import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Dictionary;
import java.util.Hashtable;

public class Game extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private int GRID_SIZE = 16, seconds;
	private shapesList shapelist;
	JLabel lblTimeLeft;
	private Dictionary<Object,Object> map;
	Timer timer;
	GameBoard GAME_BOARD;
	private customButton[][] button = new customButton[GRID_SIZE][GRID_SIZE];
	
	private boolean isPlaceable(int x, int y, int[][] actions) {
		try {
			for (int i = 0; i < actions.length; i++) {
				if (button[x + actions[i][0]][y + actions[i][1]].isTaken()) {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}


	

	

	/**
	 * Create the panel.
	 *
	 * @param frame
	 */
	public Game(GUI frame)  {


		map = new Hashtable<Object, Object>();

		setBackground(Color.LIGHT_GRAY);
		frame.setBounds(0, 0, 709, 608);
		frame.setLocationRelativeTo(null);
		setBounds(0, 0, 709, 608);
		setLayout(null);


		
		shapelist=new shapesList();
		shapelist.setBounds(10, 114, 169, 241);
		add(shapelist);
		
		JPanel surrender = new JPanel();
		surrender.setBounds(20, 366, 148, 40);
		add(surrender);
		surrender.setLayout(null);
		
		JLabel lblSurrender = new JLabel("Surrender");
		lblSurrender.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblSurrender.setBounds(36, 11, 77, 18);
		surrender.add(lblSurrender);

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

		GAME_BOARD = new GameBoard(GRID_SIZE);
		GAME_BOARD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				GAME_BOARD.setAction(shapelist.getAction());
			}
		});
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
		lblTimeLeft.setBounds(608, 64, 89, 14);
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
