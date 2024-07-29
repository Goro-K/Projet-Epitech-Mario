package Character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {

    protected float x, y;
    protected int width, height;
    protected Rectangle hitbox;
    protected float verticalSpeed;
    protected float horizontalSpeed;
    private boolean isOnGround;
    private boolean isBelowObstacle;
    protected float gravity;

    public Entity(float x, float y, int width, int height, float gravity) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gravity = gravity;
        this.isOnGround = false;
        this.isBelowObstacle = false;
        initHitbox();
    }

    protected void drawHitbox(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    private void initHitbox() {
        hitbox = new Rectangle((int) x, (int) y, width, height);
    }

    protected void updateHitbox() {
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    public void update() {
        // Mise en place de la gravité
        if (!isOnGround()) {
            verticalSpeed += gravity;
        }

        setOnGround(false);
        setBelowObstacle(false);
    }

    public boolean isOnGround() {
        return isOnGround;
    }

    public void setOnGround(boolean onGround) {
        isOnGround = onGround;
    }

    public boolean isBelowObstacle() {
        return isBelowObstacle;
    }

    public void setBelowObstacle(boolean belowObstacle) {
        isBelowObstacle = belowObstacle;
    }

    public void setHorizontalSpeed(float speed) {
        this.horizontalSpeed = speed;
    }

    public void setVerticalSpeed(float verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public float getVerticalSpeed() {
        return verticalSpeed;
    }

    public float getHorizontalSpeed() {
        return horizontalSpeed;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setY(float newY) {
        this.y = newY;
    }

    public void setX(float newX) {
        this.x = newX;
    }

    public float getGravity() {
        return gravity;
    }
}