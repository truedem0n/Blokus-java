/**
 * @author: Atul Mehla
 */

import javax.sound.sampled.Clip;
import javax.swing.*;

class shapeButton extends JButton {
    private static final long serialVersionUID = 1L;
    private final int index;
    private static Clip clip;

    // Shape constructor sets the index of shape button in shape list
    public shapeButton(int i) {
        this.index = i;
//        try {
//            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/sounds/selected.wav").getAbsoluteFile());
//            clip = AudioSystem.getClip();
//            clip.open(audioInputStream);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //add(new JLabel(x+","+y));
//        this.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    clip.setFramePosition(0);
//                    clip.start();
//                } catch (Exception s) {
//                    s.printStackTrace();
//                }
//            }
//        });
    }

    // getIndex
    public int getIndex() {
        return this.index;
    }
}