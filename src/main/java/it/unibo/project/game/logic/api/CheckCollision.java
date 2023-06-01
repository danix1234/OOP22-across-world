package it.unibo.project.game.logic.api;

import java.util.List;
import java.util.Optional;

import it.unibo.project.game.model.api.Collectable;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.ObstacleType;
import it.unibo.project.utility.Vector2D;

/**
 * Interface {@code CheckCollision}, contains methods to check all possible
 * collisions between entities.
 */
public interface CheckCollision {
    /**
     * called to check if there is a collectable in the position that the player
     * wants to go.
     * 
     * @param playerPos next player position (where he wants to go)
     * @return List<Collectable> that contains collectables collected from player
     *         movement
     */
    List<Collectable> checkCollectableCollision(Vector2D playerPos);

    /**
     * called to check if there is a static obstacle in the position that the player
     * wants to go.
     * 
     * @param playerPos next player position (where he wants to go)
     * @return Optional<ObstacleType> if valued contains the obstacle type that
     *         coincides with the playerPos
     */
    Optional<ObstacleType> checkStaticObstacleCollision(Vector2D playerPos);

    /**
     * called to check if there is a dynamic obstacle in the position that the
     * player wants to go.
     * 
     * @param playerPos next player position (where he wants to go)
     * @return Optional<Obstacle> if valued contains the obstacle that player will
     *         collides in position playerPos
     */
    Optional<Obstacle> checkDynamicObstacleCollision(Vector2D playerPos);

    /**
     * called to check if there is the finish line in the position that the player
     * wants to go.
     * 
     * @param playerPos next player position (where he wants to go)
     * @return boolean that is set to true if collision is detected
     */
    boolean checkFinishLineCollision(Vector2D playerPos);

    /**
     * called to check if there is the border of screen (wall) in the position that
     * the player wants to go.
     * 
     * @param playerPos next player position (where he wants to go)
     * @return boolean that is set to true if collision is detected
     */
    boolean checkWallCollision(Vector2D playerPos);

    /**
     * called to check if there is a trunk in the position that the player wants to
     * go.
     * 
     * @param playerPos next player position (where he wants to go)
     * @return Optional<Obstacle> if valued contains the obstacle (in that case
     *         trunk) that player will collides in position playerPos
     */
    Optional<Obstacle> checkTrunkCollision(Vector2D playerPos);
}
