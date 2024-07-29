package Character.Enemy;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Character.Entity;
import java.util.Random;

public class Goomba extends Entity {
    private Image image;
    BufferedImage[] goomba;
    private int animationTick, animationIndex, animationSpeed = 25;

    private float speedX;

    public Goomba(int x, int y, int width, int height, float gravity) {
        super(x, y, width, height, gravity);
        loadAnimations();
        speedX = -0.5f;
    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("Goomba.png");

        try {
            BufferedImage image = ImageIO.read(is); // lire l'image

            goomba = new BufferedImage[2];

            for (int i = 0; i < goomba.length; i++) {
                goomba[i] = image.getSubimage(i * 0, 5, 17, 17);
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
        g.drawImage(goomba[1], (int) x, (int) y, 30, 30, null);
    }

    public void update() {
        x += speedX;
        updateAnimationTick();
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