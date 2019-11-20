import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

class AudioManager {
    private static Clip clip;
    private static final URL placedSound = AudioManager.class.getResource("sounds/placed.wav");
    private static final URL selectedSound = AudioManager.class.getResource("sounds/selected.wav");

    @SuppressWarnings("DuplicatedCode")
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

    @SuppressWarnings("DuplicatedCode")
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
