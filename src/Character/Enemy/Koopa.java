package Character.Enemy;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import Character.Entity;

public class Koopa extends Entity {
    private Image image;
    BufferedImage[] koopa;
    private int animationTick, animationIndex, animationSpeed = 25;

    private float speedX;
    private Random random; 
    private boolean isAppeared;

 
    public Koopa(int x, int y, int height, int width, float gravity) {
        super(x, y, height, width, gravity);
        loadAnimations();
        speedX = -0.7f;
        random = new Random();
        isAppeared = false;
    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("Koopa.png");

        try {
            BufferedImage image = ImageIO.read(is); // lire l'image

            koopa = new BufferedImage[3];

            for (int i = 0; i < koopa.length; i++) {
                koopa[i] = image.getSubimage(i * 0, 0, 24, 24);
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
            g.drawImage(koopa[1], (int) x, (int) y, 45, 45, null);
        }
    }


    public void setAppeared() {
        isAppeared = true;
    }

    public void update(){    
        if (isAppeared) {
            x += speedX;
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