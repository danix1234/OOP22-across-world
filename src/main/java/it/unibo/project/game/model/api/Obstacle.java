package it.unibo.project.game.model.api;

import it.unibo.project.utility.Vector2D;

public interface Obstacle extends Entity {
    ObstacleType getType();

    Vector2D getPixelPosition();

    Vector2D movePixelPosition(int x, int y);
}
