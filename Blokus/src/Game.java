import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JList;
import java.awt.GridBagLayout;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import java.awt.ScrollPane;



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
		SHAPES_LIST.setLayout(null);
		add(SHAPES_LIST);
		
		JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAutoscrolls(true);
        SHAPES_LIST.add(panel,BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(8);
        scrollPane.setBounds(-1, -1, 190, 241);

        JPanel contentPane = new JPanel(null);
        contentPane.setBounds(-1,-1,175,241);
        contentPane.add(scrollPane);
        
        

        for(int i = 1; i <= 5; i++) {

        	JSeparator separator = new JSeparator();
            separator.setBackground(Color.RED);
    		separator.setPreferredSize(new Dimension(175, 20));
    		separator.setBounds(-1,0,175,20);
        	
        	
            JPanel sp1 = new JPanel();
            sp1.setLayout(new FlowLayout());
            sp1.setBackground(Color.WHITE);
            sp1.setPreferredSize(new Dimension(170, 170));

            JPanel ssp1 = new JPanel();
            ssp1.setLayout(null);
            ssp1.setBackground(Color.WHITE);
            ssp1.setPreferredSize(new Dimension(170, 170));


            JLabel l3 = new JLabel("Title: ");
            l3.setForeground(Color.BLACK);
            l3.setPreferredSize(new Dimension(100, 20));
            JTextField t1 = new JTextField("Electronic Basics");
            t1.setPreferredSize(new Dimension(170, 20));

            
    		

            //sp1.add(separator);
            for (int x=0;x<i;x++) {
            	JButton button=new JButton();
            	button.setBounds(20+x*20,50,20,20);
            	ssp1.add(button);
            }
            //ssp1.add(t1);
            if(i!=0)
            	sp1.add(separator);
            


            sp1.add(ssp1);
            panel.add(sp1);

        }
		
        SHAPES_LIST.add(contentPane);
        
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
