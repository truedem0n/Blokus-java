import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

class AudioManager {
    private static Clip clip;
    private static URL placedSound = AudioManager.class.getResource("sounds/placed.wav"), selectedSound = AudioManager.class.getResource("sounds/selected.wav");

    public static void playPlaced() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(placedSound);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setFramePosition(0);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not find the audio files");
        }
    }

    public static void playSelected() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(selectedSound);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setFramePosition(0);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not find the audio files");
        }
    }
}
