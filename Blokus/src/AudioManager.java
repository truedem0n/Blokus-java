import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

class AudioManager {
    private static Clip clip;

    public static void playPlaced() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/sounds/placed.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setFramePosition(0);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playSelected() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/sounds/selected.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setFramePosition(0);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
