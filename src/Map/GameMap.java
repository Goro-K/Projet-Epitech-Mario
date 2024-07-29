package Map;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import Map.Obstacle.*;

public class GameMap {
    private Obstacle[][] obstacles;
    private int tileSize;



    public GameMap(int[][] tileTypes, int tileSize) {
        this.tileSize = tileSize;
        initializeObstacles(tileTypes);
    }

    private void initializeObstacles(int[][] tileTypes) {
        int rows = tileTypes.length;
        int cols = tileTypes[0].length;

        obstacles = new Obstacle[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int tileType = tileTypes[i][j];
                obstacles[i][j] = Tiles.createObstacle(tileType, j * tileSize, i * tileSize, tileSize, tileSize);
            }
        }
    }

    public int getRows() {
        return obstacles.length;
    }

    public int getCols() {
        return obstacles[0].length;
    }

    public Obstacle getObstacleAt(int row, int col) {
        return obstacles[row][col];
    }

    public ImageIcon getTileImageAt(int x, int y) {
        return new ImageIcon();
    }

    public void render(Graphics g) {
        for (int i = 0; i < obstacles.length; i++) {
            for (int j = 0; j < obstacles[0].length; j++) {
                Obstacle obstacle = obstacles[i][j];
                if (obstacle != null) {
                    obstacle.render(g);
                   // obstacle.drawHitbox(g);
                }
            }
        }
    }
}