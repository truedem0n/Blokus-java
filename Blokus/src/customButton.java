/*
  @author: Atul Mehla
 */

import javax.swing.*;
import java.awt.*;

/**
 *
 */
class customButton extends JButton {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final int x;
    private final int y;

    /**
     * @param x
     * @param y
     */
    public customButton(int x, int y) {
        this.x = x;
        this.y = y;
        setLayout(null);
    }

    public String getColorName() {
        Color color = this.getBackground();
        if (Color.red.toString().equals(color.toString()))
            return "Red";
        else if (Color.green.toString().equals(color.toString()))
            return "Green";
        else if (Color.blue.toString().equals(color.toString()))
            return "Blue";
        else if (Color.orange.toString().equals(color.toString()))
            return "Yellow";
        return "";
    }

    public void setButtonColorTextActive() {
        String c = getColorName();
        if (!c.equals("")) {
            c = c.substring(0, 1);
            c = "  " + c;
            JLabel label = new JLabel(c);
            label.setFont(new Font("Tahoma", Font.BOLD, 15));
            label.setBounds(0, 0, getWidth(), getHeight());
            label.setHorizontalAlignment(SwingConstants.LEADING);
            add(label);
        }
        revalidate();
        repaint();
    }

    public void setButtonColorTextInActive() {
        removeAll();
        revalidate();
        repaint();
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
