import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameBoard extends JPanel {

	/**
	 *   All the declarations
	 */
	private static final long serialVersionUID = 1L;
	private int[][] actions = {{0, 0}};
	private MouseEvent event;
	private shapesList shapes;
	private int GRID_SIZE=16;
	private customButton[][] button;
	private Dictionary<String,String> map;
	AudioInputStream audioInputStream;
	Clip clip;
	
	/**
	 * Create the panel.
	 * call the constructor
	 */
	public GameBoard(int gridSize,shapesList shapes) {
		this.GRID_SIZE=gridSize;
		button = new customButton[GRID_SIZE][GRID_SIZE];
		map=new Hashtable<String,String>();
		this.shapes=shapes;
		shapes.setPlayingAtBoard(this);
		setUpBoard();
	}

	
	public String getBoard() {
		return "";
	}
	
	public void setActions(int[][] actions) {
		this.actions = actions;
	}
	
	private void setUpBoard() {
		
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File("src/sounds/placed.wav").getAbsoluteFile());
			clip=AudioSystem.getClip();
			clip.open(audioInputStream);
			}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < GRID_SIZE; i++)
			for (int j = 0; j < GRID_SIZE; j++) {
				button[j][i] = new customButton("", j, i);
				button[j][i].setBackground(Color.white);
				button[j][i].setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
				button[j][i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						event=e;
						
						if (SwingUtilities.isLeftMouseButton(e)) {
							placeShapeOnGrid();
					        }
					        if (SwingUtilities.isMiddleMouseButton(e)) {
					        	clearGrid();
						          shapes.rightClickFlipV();
						          drawOnGrid();	
					        }
					        if (SwingUtilities.isRightMouseButton(e)) {
					        	clearGrid();
					          shapes.rightClickFlipH();
					          drawOnGrid();	
					          
					        }
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						event=e;
						drawOnGrid();
					}

					@Override
					public void mouseExited(MouseEvent e) {
						event=e;
						clearGrid();
					}
				});
				add(button[j][i]);
			}
	}
	
	public void clearCurrentAction() {
		clearGrid();		
	}
	public void drawCurrentAction() {
		drawOnGrid();		
	}
	
	private void placeShapeOnGrid() {
		MouseEvent e=event;
		try {
			
			}
		catch(Exception s) {
			s.printStackTrace();
			
		}
		
		customButton thisButton = ((customButton) e.getSource());
		int x = thisButton.getPos()[0];
		int y = thisButton.getPos()[1];
		try {
			for (int i = 0; i < actions.length; i++) {
				if (!button[x + actions[i][0]][y + actions[i][1]].isTaken()
						&& isPlaceable(x, y, actions)) {
					clip.setFramePosition(0);
					clip.start();
					thisButton.setBackground(Color.red);
					button[x + actions[i][0]][y + actions[i][1]].setBackground(Color.red);
					button[x + actions[i][0]][y + actions[i][1]].setTaken(true);
					thisButton.setTaken(true);
					for (int j = 0; j < actions.length; j++) {
						map.put(x + actions[j][0] + "_" + y + actions[j][1], "true");
						button[x + actions[j][0]][y + actions[j][1]].setTaken(true);
					}
					actions=new int[0][0];
				}
				
			}
		} catch (Exception s) {
			button[x][y].setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		
	}
	
	private void drawOnGrid() {
		MouseEvent e=event;
		customButton thisButton = ((customButton) e.getSource());
		int x = thisButton.getPos()[0];
		int y = thisButton.getPos()[1];
		try {
			for (int i = 0; i < actions.length; i++) {
				if (!button[x + actions[i][0]][y + actions[i][1]].isTaken()
						&& isPlaceable(x, y, actions)) {
					button[x + actions[i][0]][y + actions[i][1]].setBackground(Color.red);
				}
				else {
					button[x][y].setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				}
			}
		} catch (Exception s) {
			button[x][y].setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
	}
	
	private void clearGrid() {
		MouseEvent e=event;
		customButton thisButton = ((customButton) e.getSource());
		int x = thisButton.getPos()[0];
		int y = thisButton.getPos()[1];
		try {
			for (int i = 0; i < actions.length; i++) {
				if(!button[x + actions[i][0]][y + actions[i][1]].isTaken())
						button[x + actions[i][0]][y + actions[i][1]].setBackground(Color.white);

			}
		} catch (Exception s) {
			button[x][y].setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
	}
	
	// checks if piece is placeable or not
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

}
