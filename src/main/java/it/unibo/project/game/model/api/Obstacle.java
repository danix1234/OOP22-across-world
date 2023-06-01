package it.unibo.project.game.model.api;

/**
 * Interface {@code Obstacle}, contain the type and the position method for the
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
     * @return double that represent the x coordinate of the pixel position
     */
    double getPixelPosition();

    /**
     * Called for move the pixel position of the relative obstacle.
     * 
     * @param x represent the new x of new position.
     */
    void movePixelPosition(double x);

    /**
     * @return obstacle speed
     */
    double getSpeed();
}
