import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

@SuppressWarnings("EmptyMethod")
class Game extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    TurnManager turnHandler;
    JLabel lblTurn;
    private JLabel lblTimeLeft;
    private GameBoard GAME_BOARD;
    private Timer timer;
    private shapesList[] Players;
    private String[] playerLabels;
    private String[] playerScore;
    private int seconds, GRID_SIZE = 16, numberOfAI, diffculty, minutes, numberOfPlayers, timeLimit, colorDistributionType;


    /**
     * Create the panel.
     *
     * @param frame
     * @param gAME_SETTINGS
     */
    Game(GUI frame, String[][][] savedArray, Map<String, String> gAME_SETTINGS) {

        setUpGameVariables(gAME_SETTINGS);
        setUpPlayers(gAME_SETTINGS);

        setUpSwingComponents(savedArray);

        //Set frame properties
        setBackground(new Color(63, 71, 204));
        frame.setBounds(0, 0, 735, 608);
        frame.setLocationRelativeTo(null);
        setBounds(0, 0, 736, 608);
        setLayout(null);

    }

    private void setUpGameVariables(Map<String, String> gAME_SETTINGS) {

        for (String key : gAME_SETTINGS.keySet()) {
            System.out.println(key + "," + gAME_SETTINGS.get(key));
            switch (key) {
                case "gridSize":
                    GRID_SIZE = Integer.parseInt(gAME_SETTINGS.get(key));
                    break;
                case "AI":
                    numberOfAI = Integer.parseInt(gAME_SETTINGS.get(key));
                    break;
                case "difficulty":
                    diffculty = Integer.parseInt(gAME_SETTINGS.get(key));
                    break;
                case "minutes":
                    minutes = Integer.parseInt(gAME_SETTINGS.get(key));
                    break;
                case "players":
                    numberOfPlayers = Integer.parseInt(gAME_SETTINGS.get(key));
                    break;
                case "timeLimit":
                    timeLimit = Integer.parseInt(gAME_SETTINGS.get(key));
                    break;
                case "colorDistributionType":
                    colorDistributionType = Integer.parseInt(gAME_SETTINGS.get(key));
                    break;
            }
        }
    }

    private void setUpSwingComponents(String[][][] savedArray) {
        JPanel surrender = new JPanel();
        surrender.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

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

        JLabel lblBlokus = new JLabel(" Blokus");
        lblBlokus.setForeground(Color.white);
        lblBlokus.setFont(new Font("Century Gothic", Font.PLAIN, 25));
        lblBlokus.setBounds(0, 1, 148, 32);
        add(lblBlokus);

        lblTurn = new JLabel("Turn: Player 1");
        lblTurn.setHorizontalAlignment(SwingConstants.CENTER);
        lblTurn.setForeground(Color.white);
        lblTurn.setBounds(20, 89, 161, 14);
        add(lblTurn);

        turnHandler = new TurnManager(Players, playerLabels, lblTurn);

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

            if (new DataManager().save(GAME_BOARD.getBoard()).equals("sucess")) {

                JOptionPane.showMessageDialog(new JFrame(), "Successfully saved");
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Failed to save");
            }
        });
        mntmOption.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        mnOptions.add(mntmOption);

    }

    private Color[] getColorsArray(String colors, int totalPlayers) {
        Color[] playerColors = new Color[totalPlayers];
        System.out.println(colors);
        for (int i = 0; i < totalPlayers; i++) {
            char currentColor = colors.charAt(i);
            System.out.println(currentColor);
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
                playerLabels[i] = "Turn: Player " + (i + 1);
            } else {
                Players[i] = new AI(playerColors[i]);
                playerLabels[i] = "Turn: AI ";
                //Players[i].setEnabled(false);
            }
            Players[i].setVisible(false);
            if (i == 0)
                Players[i].setVisible(true);
            Players[i].setBounds(15, 114, 187, 430);
            this.add(Players[i]);
        }


    }

// --Commented out by Inspection START (11/10/2019 1:04 PM):
//    private void settingsLoader() {
//
//    }
// --Commented out by Inspection STOP (11/10/2019 1:04 PM)
}
