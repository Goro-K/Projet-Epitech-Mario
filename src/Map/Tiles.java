package Map;


import Map.Obstacle.Obstacle;
import Map.Obstacle.Ground;
import Map.Obstacle.Sky;
import Map.Obstacle.Pipe;
import Map.Obstacle.Cloud;
import javax.swing.ImageIcon;

public class Tiles {
    public static final int GROUND = 0;
    public static final int SKY = 1;
    public static final int PLAYER = 2;
    public static final int PIPE = 3;
    public static final int CLOUD = 5;
    public static final int SIMPLEBLOCK = 4;
    public static final int MYSTERYBLOCK = 6;

    public static Obstacle createObstacle(int type, int x, int y, int width, int height) {
        ImageIcon icon;
        switch (type) {
            case GROUND:
                icon = new ImageIcon("../assets/map/bloc32.png");
                return new Ground(x, y, width, height, icon);
            case SKY:
                icon = new ImageIcon("../assets/map/sky.png");
                return new Sky(x, y, width, height, icon);
            case PIPE:
                icon = new ImageIcon("../assets/map/tuyau.png");
                return new Pipe(x, y, width, height, icon);
            case CLOUD:
                icon = new ImageIcon("../assets/map/cloud.png");
                return new Cloud(x, y, width, height, icon);
            case SIMPLEBLOCK:
                icon = new ImageIcon("../assets/item/block_simple.png");
                return new Cloud(x, y, width, height, icon);
            case MYSTERYBLOCK:
                icon = new ImageIcon("../assets/item/block_mystery.png");
                return new Cloud(x, y, width, height, icon);
            default:
                throw new IllegalArgumentException("Type d'obstacle inconnu : " + type);
        }
    }
}
