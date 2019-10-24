import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

public class shapesList extends JPanel {
	
	private shapeButton[][][] SHAPE_LIST = new shapeButton[21][7][7];
	private int[][] actions = {{0, 0}};
	JPanel ssp1;
	private int[][][] shapes = {
			{{0, 0}},

			{{0, 0}, {1, 0}},

			{{-1, 0}, {0, 0}, {1, 0}},
			{{-1, 0}, {0, 0}, {0, 1}},

			{{-2, 0}, {-1, 0}, {0, 0}, {1, 0}},
			{{-1, 0}, {0, 0}, {1, 0}, {1, 1}},
			{{-1, 0}, {0, 0}, {1, 0}, {0, -1}},
			{{0, 0}, {1, 0}, {0, -1}, {1, -1}},
			{{-1, 1}, {-1, 0}, {0, 0}, {0, -1}},

			{{-2, 0}, {-1, 0}, {0, 0}, {1, 0}, {2, 0}},
			{{-2, 0}, {-1, 0}, {0, 0}, {1, 0}, {1, 1}},
			{{-1, 1}, {-1, 0}, {0, 0}, {0, -1}, {0, -2}},
			{{-1, 0}, {0, 0}, {-1, -1}, {0, -1}, {0, -2}},
			{{1, -1}, {0, -1}, {1, 1}, {0, 1}, {0, 0}},
			{{-1, 1}, {0, 1}, {0, 0}, {0, -1}, {1, -1}},
			{{-1, 1}, {-1, 0}, {0, 0}, {0, -1}, {1, -1}},
			{{-2, 0}, {-1, 0}, {0, 0}, {0, -1}, {0, -2}},
			{{-2, 0}, {-1, 0}, {0, 0}, {0, -1}, {0, 1}},
			{{-1, 1}, {0, 1}, {0, 0}, {1, 0}, {0, -1}},
			{{-1, 0}, {1, 0}, {0, 0}, {0, 1}, {0, -1}},
			{{-1, 0}, {0, -1}, {0, 0}, {1, 0}, {2, 0}}
	};
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		// flip horizontally
		private void flipH(int[][] actions) {
			for (int i = 0; i < actions.length; i++)
				actions[i][0] = -actions[i][0];
		}

		//flip vertically
		private void flipV(int[][] actions) {
			for (int i = 0; i < actions.length; i++)
				actions[i][1] = -actions[i][1];
		}
		//rotate clockwise

		private void rotateCoordinatesCW() {
			for (int i = 0; i < actions.length; i++) {
				if (actions[i][0] == 0) {
					if (actions[i][1] == 0) {
						actions[i][0] = 0;
						actions[i][1] = 0;
					} else if (actions[i][1] == 1) {
						actions[i][0] = -1;
						actions[i][1] = 0;
					} else if (actions[i][1] == -1) {
						actions[i][0] = 1;
						actions[i][1] = 0;
					} else if (actions[i][1] == 2) {
						actions[i][0] = -2;
						actions[i][1] = 0;
					} else if (actions[i][1] == -2) {
						actions[i][0] = 2;
						actions[i][1] = 0;
					}
				} else if (actions[i][0] == 1) {
					if (actions[i][1] == 0) {
						actions[i][0] = 0;
						actions[i][1] = 1;
					} else if (actions[i][1] == 1) {
						actions[i][0] = -1;
						actions[i][1] = 1;
					} else if (actions[i][1] == -1) {
						actions[i][0] = 1;
						actions[i][1] = 1;
					} else if (actions[i][1] == 2) {
						actions[i][0] = 1;
						actions[i][1] = 2;
					} else if (actions[i][1] == -2) {
						actions[i][0] = 1;
						actions[i][1] = 2;
					}
				} else if (actions[i][0] == -1) {
					if (actions[i][1] == 0) {
						actions[i][0] = 0;
						actions[i][1] = -1;
					} else if (actions[i][1] == 1) {
						actions[i][0] = -1;
						actions[i][1] = -1;
					} else if (actions[i][1] == -1) {
						actions[i][0] = 1;
						actions[i][1] = -1;
					} else if (actions[i][1] == 2) {
						actions[i][0] = -1;
						actions[i][1] = -2;
					} else if (actions[i][1] == -2) {
						actions[i][0] = 1;
						actions[i][1] = -2;
					}
				} else if (actions[i][0] == 2) {
					if (actions[i][1] == 0) {
						actions[i][0] = 0;
						actions[i][1] = 2;
					} else if (actions[i][1] == 1) {
						actions[i][0] = -2;
						actions[i][1] = 1;
					} else if (actions[i][1] == -1) {
						actions[i][0] = 2;
						actions[i][1] = 1;
					} else if (actions[i][1] == 2) {
						actions[i][0] = -2;
						actions[i][1] = 2;
					} else if (actions[i][1] == -2) {
						actions[i][0] = 2;
						actions[i][1] = 2;
					}
				} else if (actions[i][0] == -2) {
					if (actions[i][1] == 0) {
						actions[i][0] = 0;
						actions[i][1] = -2;
					} else if (actions[i][1] == 1) {
						actions[i][0] = -2;
						actions[i][1] = -1;
					} else if (actions[i][1] == -1) {
						actions[i][0] = 2;
						actions[i][1] = -1;
					} else if (actions[i][1] == 2) {
						actions[i][0] = -2;
						actions[i][1] = -2;
					} else if (actions[i][1] == -2) {
						actions[i][0] = 2;
						actions[i][1] = -2;
					}

				}
			}
		}

		private void rotateCoordinatesCCW() {
			rotateCoordinatesCW();
			for (int i = 0; i < actions.length; i++) {
				actions[i][0] = -actions[i][0];
				actions[i][1] = -actions[i][1];
			}
		}
	
	private void hideShape(MouseEvent e) {
		int index = ((shapeButton) e.getSource()).getIndex();
		for (int j = 0; j < SHAPE_LIST[index].length; j++)
			for (int k = 0; k < SHAPE_LIST[index][j].length; k++) {
				SHAPE_LIST[index][j][k].setVisible(false);
			}
	}

	private void drawShapes() {
		for (int i = 0; i < SHAPE_LIST.length; i++)
			for (int j = 0; j < SHAPE_LIST.length; j++)
				for (int k = 0; k < SHAPE_LIST.length; k++) {
					if (j == 3 && k == 3) {
						for (int l = 0; l < shapes[i].length; l++) {
							SHAPE_LIST[i][shapes[i][l][1] + k][shapes[i][l][0] + j].setVisible(true);
						}
					}
					if ((j == 6 && k == 0) || (j == 6 && (k == 2 || k == 4)) || (j == 6 && k == 6)) {
						SHAPE_LIST[i][j][k].setVisible(true);
						SHAPE_LIST[i][j][k].setBackground(Color.white);
					}
				}
	}
	
	

	/**
	 * Create the panel.
	 */
	public shapesList() {
		setLayout(null);
		JPanel SHAPES_LIST = new JPanel();
		SHAPES_LIST.setBounds(0, 0, 170, 265);
		SHAPES_LIST.setLayout(null);
		add(SHAPES_LIST);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setAutoscrolls(true);
		SHAPES_LIST.add(panel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(8);
		scrollPane.setBounds(-1, -1, 190, 265);

		JPanel contentPane = new JPanel(null);
		contentPane.setBounds(-1, -1, 175, 265);
		contentPane.add(scrollPane);

		for (int i = 1; i <= 21; i++) {
			JSeparator separator = new JSeparator();
			separator.setBackground(Color.RED);
			separator.setPreferredSize(new Dimension(175, 5));
			separator.setBounds(-1, 0, 175, 10);

			JPanel sp1 = new JPanel();
			sp1.setLayout(new FlowLayout());
			sp1.setBackground(Color.WHITE);
			sp1.setPreferredSize(new Dimension(170, 170));

			ssp1 = new JPanel();
			ssp1.setLayout(new GridLayout(7, 7));
			ssp1.setBackground(Color.WHITE);
			ssp1.setPreferredSize(new Dimension(150, 150));

			// sp1.add(separator);
			for (int x = 0; x <= 6; x++) {
				for (int y = 0; y <= 6; y++) {
					shapeButton button = new shapeButton(i - 1);
					button.setPreferredSize(new Dimension(15, 15));
					if (x != 4 || y != 4)
						button.setVisible(false);
					if (!((x == 6 && y == 0) || (x == 6 && y == 6))) {
						button.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								actions = shapes[((shapeButton) e.getSource()).getIndex()];
							}
						});
					}
					if (x == 6 && y == 0) {
						ImageIcon img = new ImageIcon(GUI.class.getResource("images/flipVertical.png"));	
						Image dimg = img.getImage().getScaledInstance(15, 15,Image.SCALE_SMOOTH);
						JLabel label = new JLabel(new ImageIcon(dimg));
						label.setBounds(-10, -10, 14, 14);
						button.add(label);
						button.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								actions = shapes[((shapeButton) e.getSource()).getIndex()];
								flipV(actions);
								hideShape(e);
								drawShapes();
								ssp1.repaint();
								ssp1.revalidate();
							}
						});
					}
					if (x == 6 && y == 6) {
						ImageIcon img = new ImageIcon(GUI.class.getResource("images/flipHorizontal.png"));	
						Image dimg = img.getImage().getScaledInstance(15, 15,Image.SCALE_SMOOTH);
						JLabel label = new JLabel(new ImageIcon(dimg));
						label.setBounds(-10, -10, 15, 15);
						button.add(label);
						button.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								actions = shapes[((shapeButton) e.getSource()).getIndex()];
								flipH(actions);
								hideShape(e);
								drawShapes();
								ssp1.repaint();
								ssp1.revalidate();
							}
						});
					}
					if (x == 6 && y == 4) {
						//Setting up rotateCW
						ImageIcon img = new ImageIcon(GUI.class.getResource("images/rotateClockWise.png"));
						Image dimg = img.getImage().getScaledInstance(17, 17,Image.SCALE_SMOOTH);
						
						JLabel label = new JLabel(new ImageIcon(dimg));
						label.setBounds(-10, -10, 15, 15);
						button.add(label);
						button.setBackground(Color.white);
						button.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {

								shapeButton thisButton = ((shapeButton) e.getSource());
								actions = shapes[thisButton.getIndex()];
								rotateCoordinatesCW();
								hideShape(e);
								drawShapes();
							}
						});
					}
					if (x == 6 && y == 2) {
						//Setting up rotateCW
						ImageIcon img = new ImageIcon(GUI.class.getResource("images/rotateAntiClockWise.png"));
						Image dimg = img.getImage().getScaledInstance(17, 17,Image.SCALE_SMOOTH);
						JLabel label = new JLabel(new ImageIcon(dimg));
						label.setBounds(-10, -10, 15, 15);
						button.add(label);
						button.setBackground(Color.white);
						button.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {

								shapeButton thisButton = ((shapeButton) e.getSource());
								actions = shapes[thisButton.getIndex()];
								rotateCoordinatesCCW();
								hideShape(e);
								drawShapes();
							}
						});
					}
					button.setBorder(null);
					button.setVisible(false);
					button.setFocusable(false);
					button.setBackground(Color.red);
					SHAPE_LIST[i - 1][x][y] = button;
					
					ssp1.add(button);
				}
			}
			
			// ssp1.add(t1);
			if (i != 0)
				sp1.add(separator);

			sp1.add(ssp1);
			panel.add(sp1);

		}
		drawShapes();	
		SHAPES_LIST.add(contentPane);

	}

	public int[][] getAction() {
		// TODO Auto-generated method stub
		return this.actions;
	}
}
