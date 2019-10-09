import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JList;
import java.awt.GridBagLayout;
import javax.swing.JSeparator;



public class Game extends JPanel {
	private class customButton extends JButton{
		private int x,y;
		private boolean taken=false;
		public customButton(String name,int x,int y) {
			super(name);
			this.x=x;
			this.y=y;
		}
		public int[] getPos() {
			int[] pos= {this.x,this.y};
			return pos;
		}
		public boolean isTaken() {
			return this.taken;
		}
		public void setTaken(boolean taken) {
			this.taken=taken;
		}
	}
	private int GRID_SIZE= 20;
	private customButton button[][]=new customButton[GRID_SIZE][GRID_SIZE];

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
				
		JPanel SHAPES_LIST = new JPanel();
		SHAPES_LIST.setBounds(10, 114, 170, 241);
		add(SHAPES_LIST);
		SHAPES_LIST.setLayout(null);
		
		JButton block1 = new JButton("");
		block1.setForeground(Color.RED);
		block1.setBackground(Color.RED);
		block1.setBounds(68, 43, 40, 40);
		SHAPES_LIST.add(block1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 133, 170, 2);
		SHAPES_LIST.add(separator);
		
		JButton button_1 = new JButton("");
		button_1.setForeground(Color.RED);
		button_1.setBackground(Color.RED);
		button_1.setBounds(48, 164, 80, 40);
		SHAPES_LIST.add(button_1);
		
		JPanel surrender = new JPanel();
		surrender.setBounds(20, 366, 148, 40);
		add(surrender);
		
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
		
		JPanel GAME_BOARD = new JPanel();
		GAME_BOARD.setBounds(190, 89, 545, 420);
		add(GAME_BOARD);
		GAME_BOARD.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
		
		
		for (int i=0;i<GRID_SIZE;i++)
			for (int j=0;j<GRID_SIZE;j++) {
				button[j][i] = new customButton("",j,i);
				button[j][i].setBackground(Color.white);
				button[j][i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						customButton thisButton=((customButton)e.getSource());
						int x=thisButton.getPos()[0];
						int y=thisButton.getPos()[1];
						if(!thisButton.isTaken()&&!button[x+1][y].isTaken()) {
							((customButton)e.getSource()).setBackground(Color.red);
							((customButton)e.getSource()).getPos();
							
							button[x+1][y].setBackground(Color.red);
							button[x+1][y].setTaken(true);
							thisButton.setTaken(true);
						}
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						customButton thisButton=((customButton)e.getSource());
						int x=thisButton.getPos()[0];
						int y=thisButton.getPos()[1];
						if(!thisButton.isTaken()&&!button[x+1][y].isTaken()) {
							thisButton.setBackground(Color.red);
							((customButton)e.getSource()).getPos();
							button[x+1][y].setBackground(Color.red);
						}
					}
					@Override
					public void mouseExited(MouseEvent e) {
						customButton thisButton=((customButton)e.getSource());
						int x=thisButton.getPos()[0];
						int y=thisButton.getPos()[1];
						if(!thisButton.isTaken()&&!button[x+1][y].isTaken()) {
							((customButton)e.getSource()).setBackground(Color.white);
							button[x+1][y].setBackground(Color.white);
						}
					}
				});
				GAME_BOARD.add(button[j][i]);
				
			}
		
		JLabel lblTimeLeft = new JLabel("Time left: 00:00");
		lblTimeLeft.setBounds(646, 64, 89, 14);
		add(lblTimeLeft);
	}
}
