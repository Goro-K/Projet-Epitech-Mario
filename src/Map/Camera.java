package Map;
import Main.Game;
import Character.Entity;

public class Camera {
    private float x, y;

    public Camera(float x) {
        this.x = x;
    }

    public void update(Entity target) {
        x = target.getX() - Game.WINDOW_WIDTH / 2 + target.getWidth() / 2;
        x = Math.max(0, Math.min(x, Game.MAP_WIDTH - Game.WINDOW_WIDTH));
    }
    

    // Getters et setters
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }
}
