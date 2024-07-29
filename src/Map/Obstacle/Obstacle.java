package Map.Obstacle;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public abstract class Obstacle {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Rectangle hitbox;
    protected ImageIcon imageIcon;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public Obstacle(int x, int y, int width, int height, ImageIcon imageIcon) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.imageIcon = imageIcon;
        initHitbox();
    }

    protected void initHitbox() {
        hitbox = new Rectangle((int) x, (int) y, width, height);
    }

    protected void updateHitbox() {
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    // public void drawHitbox(Graphics g) { // affiche une grille de carré qui représente chaque obstacle
    //     g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    // }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void render(Graphics g) {
        if (imageIcon != null) {
            imageIcon.paintIcon(null, g, x, y);
        }
    }
}