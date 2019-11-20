/*
  @author: Atul Mehla
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

/**
 * The type Game.
 */
@SuppressWarnings("DuplicatedCode")
class Game extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // --Commented out by Inspection (11/17/2019 2:11 PM):private String[] playerScore;
    private final GUI frame;
    private JLabel lblBlokus;
    private JLabel lblTimeLeft;
    private TurnManager turnHandler;
    private GameBoard GAME_BOARD;
    private Timer timer;
    private JButton close_panel;
    private ActionListener askBeforeClosing;
    private shapesList[] Players;
    private String[] playerLabels;
    private ActionListener dontAskBeforeClosing;
    private int seconds;
    private int GRID_SIZE = 16;
    /**
     *
     */
    private int numberOfAI;
    private int minutes;
    private int numberOfPlayers;
    private final Map<String, String> GAME_SETTINGS;


    /**
     * Create the panel.
     *
     * @param frame         the frame
     * @param savedArray    the saved array
     * @param GAME_SETTINGS the game settings
     */
    Game(GUI frame, String[][] savedArray, Map<String, String> GAME_SETTINGS) {
        this.frame = frame;
        this.GAME_SETTINGS = GAME_SETTINGS;

        setUpActionListeners();
        setUpGameVariables(GAME_SETTINGS);
        setUpPlayers(GAME_SETTINGS);
        setUpSwingComponents(savedArray);

        //Set frame properties
        setBackground(new Color(63, 71, 204));
        frame.setBounds(0, 0, 735, 608);
        frame.setLocationRelativeTo(null);
        setBounds(0, 0, 736, 608);
        setLayout(null);

    }

    /**
     *
     */
    private void setUpActionListeners() {
        askBeforeClosing = new ActionListener() {
            private JPanel getPanel() {
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Save game before exiting?");
                label.setIcon(null);
                panel.add(label);
                return panel;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                int value = (JOptionPane.showConfirmDialog(null,
                        getPanel(), "Blokus", JOptionPane.YES_NO_CANCEL_OPTION));
                // Yes=0, No=1, Cancel=2
                if (value == 0) {
                    DataManager.save(GAME_BOARD.getBoard());
                    String whoIsNotPlaying = "";
                    for (shapesList players : Players) {
                        GAME_SETTINGS.put(players.getColorName(), players.getPlacedShapesIndexes());
                        if (!players.isStillPlaying())
                            whoIsNotPlaying += players.getColorName() + ",";
                    }
                    GAME_SETTINGS.put("notPlaying", whoIsNotPlaying);
                    GAME_SETTINGS.put("turn", turnHandler.getCurrentPlayer().getColorName());
                    DataManager.updateGameSettings(GAME_SETTINGS);
                    System.exit(0);
                } else if (value == 1) {
                    System.exit(0);
                }
            }
        };
        dontAskBeforeClosing = e -> System.exit(0);
    }

    /**
     * Game over.
     */
    public void gameOver() {
        this.removeAll();
        this.add(lblBlokus);
        close_panel.removeActionListener(askBeforeClosing);
        close_panel.addActionListener(dontAskBeforeClosing);
        this.add(close_panel);
        GameOver gameOver = new GameOver(GAME_BOARD.getPlacedBlocks(), frame);
        gameOver.setLocation((int) (this.getWidth() * 0.2), (int) (this.getHeight() * 0.2));
        this.add(gameOver);
        this.revalidate();
        this.repaint();

    }

    private void setUpGameVariables(Map<String, String> gAME_SETTINGS) {

        for (String key : gAME_SETTINGS.keySet()) {
            switch (key) {
                case "gridSize":
                    GRID_SIZE = Integer.parseInt(gAME_SETTINGS.get(key));
                    break;
                case "AI":
                    numberOfAI = Integer.parseInt(gAME_SETTINGS.get(key));
                    break;
                case "difficulty":
                case "colorDistributionType":
                case "timeLimit":
                    //noinspection unused
                    int difficulty = Integer.parseInt(gAME_SETTINGS.get(key));
                    break;
                case "minutes":
                    minutes = Integer.parseInt(gAME_SETTINGS.get(key));
                    break;
                case "players":
                    numberOfPlayers = Integer.parseInt(gAME_SETTINGS.get(key));
                    break;
            }
        }
    }

    private void setUpSwingComponents(String[][] savedArray) {
        /*
		  This is the close button Image
		 */
        ImageIcon closeButtonImg = new ImageIcon(GUI.class.getResource("images/closeButton.png"));
        Image scaledCloseButtonImg = closeButtonImg.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        // adding the  logo to the panel via jlabel
        close_panel = new JButton();
        JLabel close_panelLabel = new JLabel(new ImageIcon(scaledCloseButtonImg));
        close_panel.setLayout(null);
        close_panel.setBounds(735 - 50, 0, 50, 50);
        close_panelLabel.setBounds(0, 0, 50, 50);
        close_panel.add(close_panelLabel);
        close_panel.addActionListener(askBeforeClosing);

        add(close_panel);


        JButton surrender = new JButton();
        surrender.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, turnHandler.getSurrenderingTextForCurrentPlayer());
                shapesList currentPlayer = turnHandler.getCurrentPlayer();
                currentPlayer.setStillPlaying(false);
                turnHandler.nextPlayer();
                GAME_BOARD.setSurrenderForCurrentPlayer();

            }
        });
        surrender.setForeground(Color.WHITE);
        surrender.setBounds(33, 557, 148, 40);
        surrender.setBackground(new Color(2, 40, 89));
        add(surrender);
        surrender.setLayout(null);

        JLabel lblSurrender = new JLabel("Surrender");
        lblSurrender.setForeground(Color.WHITE);
        lblSurrender.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        lblSurrender.setBounds(36, 11, 130, 18);
        surrender.add(lblSurrender);

        lblBlokus = new JLabel(" Blokus");
        lblBlokus.setForeground(Color.white);
        lblBlokus.setFont(new Font("Century Gothic", Font.PLAIN, 25));
        lblBlokus.setBounds(0, 1, 148, 32);
        add(lblBlokus);

        JLabel lblTurn = new JLabel("Turn: Player 1");
        lblTurn.setHorizontalAlignment(SwingConstants.LEADING);
        lblTurn.setForeground(Color.white);
        lblTurn.setBounds(20, 89, 161, 14);
        add(lblTurn);

        turnHandler = new TurnManager(Players, playerLabels, lblTurn, this);
        String notPlaying = GAME_SETTINGS.get("notPlaying");
        if (notPlaying != null && !notPlaying.equals("")) {
            String[] notPlayingColors = notPlaying.split(",");
            for (shapesList p : Players) {
                for (String r : notPlayingColors) {
                    if (p.getColorName().equals(r)) {
                        p.setStillPlaying(false);
                    }
                }
            }
        }
        for (shapesList p : Players) {
            if (GAME_SETTINGS.get("turn") != null &&
                    !GAME_SETTINGS.get("turn").equals(p.getColorName()) &&
                    p.getClass() == Player.class &&
                    turnHandler.getCurrentPlayer().getColorName().equals(p.getColorName())) {
                turnHandler.nextPlayer();
            } else {
                break;
            }
        }

        GAME_BOARD = new GameBoard(GRID_SIZE, turnHandler, savedArray);
        GAME_BOARD.addMouseWheelListener(e -> {
            GAME_BOARD.clearCurrentAction();
            turnHandler.getCurrentPlayer().scrollActionHide();
            turnHandler.getCurrentPlayer().rotateClockWise();
            turnHandler.getCurrentPlayer().getColor();
            turnHandler.getCurrentPlayer().scrollActionDraw();
            GAME_BOARD.drawCurrentAction();

        });
        GAME_BOARD.setBounds(218, 89, 508, 508);
        add(GAME_BOARD);
        GAME_BOARD.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        lblTimeLeft = new JLabel(String.format("Time left:%02d:00", minutes));
        lblTimeLeft.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTimeLeft.setForeground(Color.white);
        seconds = 0;
        timer = new Timer(1000, e -> {
            if (seconds >= minutes * 60) {
                timer.stop();
            } else {
                seconds += 1;
                lblTimeLeft.setText("Time left:" + (String.format("%02d", (minutes * 60 - seconds) / 60)) + ":" + (String.format("%02d", (minutes * 60 - seconds) % 60)));
            }
        });
        timer.setRepeats(true);
        timer.start();
        lblTimeLeft.setBounds(590, 64, 136, 14);
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
        mntmOption.addActionListener(e -> {
            DataManager.save(GAME_BOARD.getBoard());
            String whoIsNotPlaying = "";
            for (shapesList players : Players) {
                GAME_SETTINGS.put(players.getColorName(), players.getPlacedShapesIndexes());
                if (!players.isStillPlaying())
                    whoIsNotPlaying += players.getColorName() + ",";
            }
            GAME_SETTINGS.put("notPlaying", whoIsNotPlaying);
            GAME_SETTINGS.put("turn", turnHandler.getCurrentPlayer().getColorName());
            DataManager.updateGameSettings(GAME_SETTINGS);
            JOptionPane.showMessageDialog(new JFrame(), "Successfully saved");
        });
        mntmOption.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        mnOptions.add(mntmOption);
    }

    private Color[] getColorsArray(String colors, int totalPlayers) {
        Color[] playerColors = new Color[totalPlayers];
        for (int i = 0; i < totalPlayers; i++) {
            char currentColor = colors.charAt(i);
            switch (currentColor) {
                case 'G':
                    playerColors[i] = Color.GREEN;
                    break;
                case 'R':
                    playerColors[i] = Color.red;
                    break;
                case 'B':
                    playerColors[i] = Color.blue;
                    break;
                case 'Y':
                    playerColors[i] = Color.orange;
                    break;
            }
        }
        return playerColors;
    }

    private void setUpPlayers(Map<String, String> gAME_SETTINGS) {
        int totalPlayers = numberOfAI + numberOfPlayers;
        Color[] playerColors = getColorsArray(gAME_SETTINGS.get("playerColors") + gAME_SETTINGS.get("AIColors"), totalPlayers);
        Players = new shapesList[totalPlayers];
        playerLabels = new String[totalPlayers];
        for (int i = 0; i < Players.length; i++) {
            if (i < numberOfPlayers) {
                Players[i] = new Player(playerColors[i]);
                if (GAME_SETTINGS.get(Players[i].getColorName()) != null )
                    Players[i].removePanelsBasedOnString(GAME_SETTINGS.get(Players[i].getColorName()));
                playerLabels[i] = "Turn: Player " + Players[i].getColorName();
            } else {
                Players[i] = new AI(playerColors[i], gAME_SETTINGS.get("difficulty"));
                if (GAME_SETTINGS.get(Players[i].getColorName()) != null)
                    Players[i].removePanelsBasedOnString(GAME_SETTINGS.get(Players[i].getColorName()));
                playerLabels[i] = "Turn: AI " + Players[i].getColorName();
            }
            Players[i].setVisible(false);
            if (i == 0)
                Players[i].setVisible(true);
            Players[i].setBounds(15, 114, 187, 430);
            this.add(Players[i]);
        }
    }
}
