/**
 * @author: Atul Mehla
 */

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 *
 */
class customButton extends JButton {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static Clip clip;
    private final int x;
    private final int y;

    /**
     * @param x
     * @param y
     */
    public customButton(int x, int y) {
        this.x = x;
        this.y = y;

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/sounds/placed.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //add(new JLabel(x+","+y));
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clip.setFramePosition(0);
                    clip.start();
                } catch (Exception s) {
                    s.printStackTrace();
                }
            }
        });
    }

    private boolean taken = false;

    /**
     * @return
     */
    public Color getColor() {
        return this.getBackground();
    }

    /**
     * @return
     */
    public int[] getPos() {
        return new int[]{this.x, this.y};
    }

    /**
     * @return
     */
    public boolean isTaken() {
        return this.taken;
    }

    /**
     * @param taken
     */
    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    /**
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int d = 0;
        int h = 0;
        int lineWeight = 1;
        for (int i = 0; i < lineWeight; i++) {
            g.setColor(Color.gray);
            g.drawLine(0, h, getWidth(), h);
            g.drawLine(d, 0, d, getHeight());
            h += getHeight() / lineWeight;
            d += getWidth() / lineWeight;
        }
    }

}
