package Utils;

import Character.Entity;
import Map.GameMap;
import Map.Obstacle.Obstacle;

public class CollisionManager {

    private GameMap gameMap;

    public CollisionManager(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void checkCollisionWithObstacles(Entity entity) {
        if (gameMap == null) {
            return;
        }

        // Calcul la taille d'une tuile en fonction de la taille de la fenetre
        int startRow = (int) (entity.getY() / 50);
        int endRow = (int) ((entity.getY() + entity.getHeight()) / 50);
        int startCol = (int) (entity.getX() / 50);
        int endCol = (int) ((entity.getX() + entity.getWidth()) / 50);

        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                if (i >= 0 && i < gameMap.getRows() && j >= 0 && j < gameMap.getCols()) {
                    // Recupere toutes les tuiles et les compare avec la hitbox du player
                    Obstacle obstacle = gameMap.getObstacleAt(i, j);
                    if (obstacle != null && entity.getHitbox().intersects(obstacle.getHitbox())) {
                        handleCollision(entity, obstacle);
                    }
                }
            }
        }
    }

    private void handleCollision(Entity entity, Obstacle obstacle) {
        float dx = centerDistance(entity.getX(), entity.getWidth(), obstacle.getX(), obstacle.getWidth());
        float dy = centerDistance(entity.getY(), entity.getHeight(), obstacle.getY(), obstacle.getHeight());

        float overlapX = getOverlap(entity.getWidth(), obstacle.getWidth(), dx);
        float overlapY = getOverlap(entity.getHeight(), obstacle.getHeight(), dy);

        if (overlapX > 0 && overlapY > 0) {
            resolveOverlap(entity, obstacle, dx, dy, overlapX, overlapY);
        }
    }

    private float centerDistance(float entityCoordinate, float entitySize, float obstacleCoordinate,
            float obstacleSize) {
        return entityCoordinate + entitySize / 2 - (obstacleCoordinate + obstacleSize / 2);
    }

    private float getOverlap(float entitySize, float obstacleSize, float distance) {
        return (entitySize / 2) + (obstacleSize / 2) - Math.abs(distance);
    }

    private void resolveOverlap(Entity entity, Obstacle obstacle, float dx, float dy, float overlapX, float overlapY) {
        if (overlapX >= overlapY) {
            entity.setVerticalSpeed(0);
            entity.setY(dy > 0 ? obstacle.getY() + obstacle.getHeight() : obstacle.getY() - entity.getHeight());
            entity.setOnGround(dy <= 0);
        } else {
            entity.setHorizontalSpeed(0);
            entity.setX(dx > 0 ? obstacle.getX() + obstacle.getWidth() : obstacle.getX() - entity.getWidth());
        }
    }
}