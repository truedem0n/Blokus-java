import javax.swing.*;
import java.awt.*;

class ImageManager {
    private static ImageIcon rotateAntiClockWise, rotateClockWise, flipHorizontal, flipVertical;

    public static void setUpImages() {
        ImageIcon img = new ImageIcon(GUI.class.getResource("images/rotateAntiClockWise.png"));
        rotateAntiClockWise = new ImageIcon(img.getImage().getScaledInstance(17, 17, Image.SCALE_SMOOTH));
        ImageIcon img1 = new ImageIcon(GUI.class.getResource("images/rotateClockWise.png"));
        rotateClockWise = new ImageIcon(img1.getImage().getScaledInstance(17, 17, Image.SCALE_SMOOTH));
        ImageIcon img2 = new ImageIcon(GUI.class.getResource("images/flipHorizontal.png"));
        flipHorizontal = new ImageIcon(img2.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
        ImageIcon img3 = new ImageIcon(GUI.class.getResource("images/flipVertical.png"));
        flipVertical = new ImageIcon(img3.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
    }

    public static ImageIcon getRotateAntiClockWisePNG() {
        return rotateAntiClockWise;
    }

    public static ImageIcon getRotateClockWisePNG() {
        return rotateClockWise;
    }

    public static ImageIcon getFlipHorizontalPNG() {
        return flipHorizontal;
    }

    public static ImageIcon getFlipVerticalPNG() {
        return flipVertical;
    }
}
