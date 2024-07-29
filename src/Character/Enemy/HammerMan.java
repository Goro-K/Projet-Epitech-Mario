package Character.Enemy;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import Character.Entity;

public class HammerMan extends Entity{
    
    private Image image;
    BufferedImage[] hammerMan;
    private int animationTick, animationIndex, animationSpeed = 25;

    private Random random; 
    private boolean isAppeared;

    public HammerMan(int x, int y, int height, int width, float gravity) {
        super(x, y, height, width, gravity);
        loadAnimations();
        random = new Random();
        isAppeared = false;
    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("Hammer_man.png");

        try {
            BufferedImage image = ImageIO.read(is); // lire l'image

            hammerMan = new BufferedImage[3];

            for (int i = 0; i < hammerMan.length; i++) {
                hammerMan[i] = image.getSubimage(i * 0, 0, 20, 24);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close(); // ferme le fichier
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void render(Graphics g) {
        if (isAppeared) {
            g.drawImage(hammerMan[1], (int) x, (int) y, 45, 45, null);
        }
    }


    public void setAppeared() {
        isAppeared = true;
    }

    public void update(){    
        if (isAppeared) {
            updateAnimationTick();
        }
    }

    private void updateAnimationTick() {
        animationTick++; // ajoute 1 au compteur d'animations
        if (animationTick >= animationSpeed) {
            animationTick = 0; // reset le compteur d'animations
            animationIndex++; // ajoute 1 à l'index de l'animations
            if (animationIndex >= 2) { // lorsque l'animations est sur sa derniere frame,
                // reset l'animations
                animationIndex = 0;
            }
        }
    }
}
