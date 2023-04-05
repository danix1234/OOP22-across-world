package it.unibo.project.game.logic.api;

import java.util.List;
import java.util.Optional;

import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.ObstacleType;

public interface CheckCollision {
    public Optional<CollectableType> checkCollectableCollision(); // true -> coin or powerup collected

    public boolean checkCoinLessDistantThen(int distance); // true -> coin magnet powerup collected

    public Optional<ObstacleType> checkStaticObstacleCollision(); // Optional not empty -> can't move in that direction

    public List<ObstacleType> checkDynamicObstacleCollision(); // list not empty -> you lose (unless you hit water & trunk)

    public boolean checkFinishLineCollision(); //true -> you win

    public boolean checkWallCollision(); // true -> can't move in that direction
}
