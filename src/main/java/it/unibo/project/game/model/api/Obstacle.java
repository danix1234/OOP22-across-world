package it.unibo.project.game.model.api;

import it.unibo.project.utility.Vector2D;

/**
 * Class {@code Obstacle}, contain the type and the position method for the
 * obstacle.
 */
public interface Obstacle extends Entity {
    /**
     * Called for get the type of the relative obstacle.
     * 
     * @return ObstacleType.
     */
    ObstacleType getType();

    /**
     * Called for get the pixel position of the relative obstacle.
     * 
     * @return Vector2D that represent the pixel position of the obstacle
     */
    Vector2D getPixelPosition();

    /**
     * Called for move the pixel position of the relative obstacle.
     * 
     * @param x represent the new x of new position.
     * @param y represent the new y of new position.
     * @return a Vector2D that represent the new position.
     */
    Vector2D movePixelPosition(int x, int y);

    /**
     * called to know moving obstacle direction.
     * 
     * @return true if entity direction is toward left
     */
    boolean isMovableTowardLeft();
}
