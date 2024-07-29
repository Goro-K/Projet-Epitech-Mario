package Map.Obstacle;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Sky extends Obstacle {
    public Sky(int x, int y, int width, int height, ImageIcon icon) {
        super(x, y, width, height, icon);

    }

    @Override
    public Rectangle getHitbox() {
        return new Rectangle(0, 0, 0, 0); // hitbox null pour pouvoir traverser le ciel
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(imageIcon.getImage(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
