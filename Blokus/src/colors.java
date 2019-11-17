/**
 * @author: Atul Mehla
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
class colors extends JPanel {
    /**
     * All the declarations
     */
    private static final long serialVersionUID = 1L;
    private final Map<String, String> gAME_SETTINGS;
    String selectedColorsForPlayers = "", selectedColorsForAI = "";
    private Game game;
    private JLabel[] playerLabels;
    private JComboBox<String>[] playerColorSelections;
    private boolean hasAI = false;


    /**
     * Create the panel.
     *  @param loginPage
     * @param frame
     * @param gAME_SETTINGS
     */
    public colors(JPanel loginPage, GUI frame, Map<String, String> gAME_SETTINGS) {

        setUpCloseAndBackButton(frame);
        this.gAME_SETTINGS = gAME_SETTINGS;

        setBackground(new Color(63, 71, 204));
        setBounds(342, 0, 345, 478);
        setLayout(null);

        JButton continue_panel = new JButton();
        continue_panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (validSelections()) {
                    gAME_SETTINGS.put("playerColors", selectedColorsForPlayers);
                    if (hasAI) {
                        setUpAIColors(Integer.parseInt(gAME_SETTINGS.get("AI")));
                    }
                    gAME_SETTINGS.put("AIColors", selectedColorsForAI);
                    frame.setVisible(false);
                    game = new Game(frame, null, gAME_SETTINGS);
                    frame.setVisible(true);
                    frame.setVisible(true);
                    loginPage.removeAll();
                    loginPage.add(game);
                    loginPage.revalidate();
                    loginPage.repaint();

                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Please make a valid color selection.");
                }
            }

        });
        continue_panel.setLayout(null);
        continue_panel.setBackground(Color.ORANGE);
        continue_panel.setBounds(59, 305, 216, 49);
        add(continue_panel);

        JLabel label = new JLabel("Continue");
        label.setForeground(Color.white);
        label.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label.setBounds(66, 11, 140, 25);
        continue_panel.add(label);

        JButton randomize_panel = new JButton();
        randomize_panel.setLayout(null);
        randomize_panel.setBackground(Color.ORANGE);
        randomize_panel.setBounds(59, 371, 216, 49);
        add(randomize_panel);

        JLabel lblRandomize = new JLabel("Randomize");
        lblRandomize.setForeground(Color.white);
        lblRandomize.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblRandomize.setBounds(58, 11, 148, 25);
        randomize_panel.add(lblRandomize);


    }

    private void setUpCloseAndBackButton(GUI frame) {
        /*
		  This is the close button Image
		 */
        ImageIcon closeButtonImg = new ImageIcon(GUI.class.getResource("images/closeButton.png"));
        Image scaledCloseButtonImg = closeButtonImg.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        // adding the  logo to the panel via jlabel
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
        add(close_panel);


        /*
		  This is the back button Image
		 */
        ImageIcon backButtonImg = new ImageIcon(GUI.class.getResource("images/backButton.png"));
        Image scaledBackButtonImg = backButtonImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        // adding the blokus logo to the panel via jlabel
        JButton backButtonPanel = new JButton();
        JLabel backButtonLabel = new JLabel(new ImageIcon(scaledBackButtonImg));
        backButtonPanel.setLayout(null);
        backButtonLabel.setBounds(0, 0, 40, 40);
        backButtonPanel.add(backButtonLabel);
        backButtonPanel.setBounds(10, 10, 40, 40);
        backButtonPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setWindow(2);
            }
        });
        add(backButtonPanel);
    }

    public void setUpPlayersVariables() {
        setUpPlayers(Integer.parseInt(gAME_SETTINGS.get("players")));
        if (Integer.parseInt(gAME_SETTINGS.get("AI")) > 0) {
            hasAI = true;
        }
    }

    /**
     * setUpPlayers: returns void
     * sets up the number of labels and comboboxes for players
     */
    private void setUpPlayers(int players) {
        String[] colors = {"Red", "Green", "Blue", "Yellow"};
        playerLabels = new JLabel[players];
        playerColorSelections = new JComboBox[players];
        for (int i = 0; i < players; i++) {
            switch (i) {
                case 0:
                    playerLabels[i] = new JLabel("Player 1");
                    playerLabels[i].setForeground(Color.WHITE);
                    playerLabels[i].setBounds(59, 152, 63, 14);
                    add(playerLabels[i]);

                    playerColorSelections[i] = new JComboBox<String>(colors);
                    playerColorSelections[i].setBounds(132, 148, 143, 22);
                    add(playerColorSelections[i]);
                    break;
                case 1:
                    playerLabels[i] = new JLabel("Player 2");
                    playerLabels[i].setForeground(Color.WHITE);
                    playerLabels[i].setBounds(59, 180, 97, 14);
                    add(playerLabels[i]);

                    playerColorSelections[i] = new JComboBox<String>(colors);
                    playerColorSelections[i].setBounds(132, 176, 143, 22);
                    add(playerColorSelections[i]);
                    break;
                case 2:
                    playerLabels[i] = new JLabel("Player 3");
                    playerLabels[i].setForeground(Color.WHITE);
                    playerLabels[i].setBounds(59, 211, 97, 14);
                    add(playerLabels[i]);

                    playerColorSelections[i] = new JComboBox<String>(colors);
                    playerColorSelections[i].setBounds(132, 207, 143, 22);
                    add(playerColorSelections[i]);
                    break;
                case 3:
                    playerLabels[i] = new JLabel("Player 4");
                    playerLabels[i].setForeground(Color.WHITE);
                    playerLabels[i].setBounds(59, 240, 97, 14);
                    add(playerLabels[i]);

                    playerColorSelections[i] = new JComboBox<String>(colors);
                    playerColorSelections[i].setBounds(132, 236, 143, 22);
                    add(playerColorSelections[i]);
                    break;
            }
        }
    }

    private boolean validSelections() {
        selectedColorsForPlayers = "";
        for (JComboBox<String> i : playerColorSelections) {
            if (selectedColorsForPlayers.contains(((String) i.getSelectedItem()).substring(0, 1)))
                return false;
            selectedColorsForPlayers += ((String) i.getSelectedItem()).charAt(0);
        }

        return true;
    }

    private void setUpAIColors(int AI) {
        for (int i = 0; i < AI; i++) {
            if (!selectedColorsForPlayers.contains("G") && !selectedColorsForAI.contains("G"))
                selectedColorsForAI += "G";
            else if (!selectedColorsForPlayers.contains("R") && !selectedColorsForAI.contains("R"))
                selectedColorsForAI += "R";
            else if (!selectedColorsForPlayers.contains("B") && !selectedColorsForAI.contains("B"))
                selectedColorsForAI += "B";
            else if (!selectedColorsForPlayers.contains("Y") && !selectedColorsForAI.contains("Y"))
                selectedColorsForAI += "Y";
        }
    }
}
