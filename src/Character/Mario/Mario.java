package Character.Mario;

import static Utils.Constants.PlayerConstants.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Character.Entity;
import Main.Game;

public class Mario extends Entity {
    private float speed = 2.0f;
    BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 25;
    private int playerAction = NOT_MOVING;
    private boolean moving = false, jumping = false;
    private boolean left, up, right, down;
    private static final float JUMP_STRENGTH = -8.0f;

    public Mario(Game game, float x, float y, int width, int height, float gravity) {
        super(x, y, width, height, gravity);
        loadAnimations();
    }

    public void update() {
        updatePos();
        updateHitbox();

        updateAnimationTick();
        setAnimation();

        // Reinitialisation  du sol et des obstacles
        setOnGround(false);
        setBelowObstacle(false);
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animationIndex], (int) x, (int) y, 60, 60, null);
        drawHitbox(g);
    }

    private void updateAnimationTick() {
        animationTick++; // ajoute 1 au compteur d'animations
        if (animationTick >= animationSpeed) {
            animationTick = 0; // reset le compteur d'animations
            animationIndex++; // ajoute 1 à l'index de l'animations
            if (animationIndex >= getSpriteAmount(playerAction)) { // lorsque l'animations est sur sa derniere frame,
                // reset l'animations
                animationIndex = 0;
            }
        }
    }

    private void setAnimation() {
        if (moving) {
            playerAction = RUNNING_RIGHT;
        } else {
            playerAction = NOT_MOVING;
        }

        if (jumping) {
            playerAction = JUMPING_RIGHT;
        }
    }
    
    private void updatePos() {
        moving = false;
        jumping = false;

        if (left && !right) {
            x -= speed;
            moving = true;
        } else if (right && !left) {
            x += speed;
            moving = true;
        }

        if (up && !down && isOnGround()) {
            verticalSpeed = JUMP_STRENGTH;
            jumping = true;
            setOnGround(false);
        }
        if (!isOnGround()) {
            verticalSpeed += gravity;
        }

        if (isBelowObstacle()) {
            moving = false;
        }
        y += verticalSpeed;
    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("mario_movements.png");

        try {
            BufferedImage image = ImageIO.read(is);

            animations = new BufferedImage[8][4];

            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    animations[j][i] = image.getSubimage(i * 28, j * 32, 28, 32);
                }
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

    // Getters & Setters

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean resetDirBooleans() {
        return left = up = right = down = false;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
