package it.unibo.project.game.model.api;

import it.unibo.project.utility.Vector2D;

public interface Obstacle extends Entity {
    public ObstacleType getType();

    public Vector2D getPixelPosition();
}
