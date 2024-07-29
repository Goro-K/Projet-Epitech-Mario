package Music;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class mainMusic {

    public static Clip clip;

    public void musicTheme() {
        try {
            // Charger le fichier audio
            File audioFile = new File("Music/Theme.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

            // Configurer le clip audio
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Boucle de lecture continue

            // Démarrer la lecture
            clip.start();

        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}
