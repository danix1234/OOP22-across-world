package it.unibo.project.game.logic.api;

import java.util.List;
import java.util.Optional;

import it.unibo.project.game.model.api.Collectable;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.ObstacleType;
import it.unibo.project.utility.Vector2D;

public interface CheckCollision {
    List<Collectable> checkCollectableCollision(Vector2D playerPos); // true -> coin or powerup collected
    
    Optional<ObstacleType> checkStaticObstacleCollision(Vector2D playerPos); // Optional not empty -> can't move in that direction

    // !! REMEMBER TO HANDLE THE CASE OF COLLISION WITH TRUNK!!
    Optional<Obstacle> checkDynamicObstacleCollision(Vector2D playerPos); // list not empty -> you lose (unless you hit water & trunk)

    boolean checkFinishLineCollision(Vector2D playerPos); // true -> you win

    boolean checkWallCollision(Vector2D playerPos); // true -> can't move in that direction
}
