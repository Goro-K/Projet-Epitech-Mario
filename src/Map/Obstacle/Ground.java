package Map.Obstacle;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Ground extends Obstacle {
    public Ground(int x, int y, int width, int height, ImageIcon icon) {
        super(x, y, width, height, icon);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(imageIcon.getImage(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
