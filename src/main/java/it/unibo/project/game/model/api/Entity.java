package it.unibo.project.game.model.api;

import it.unibo.project.utility.Vector2D;

public interface Entity {
    Vector2D getPosition();

    boolean isMovable();

    void move(int x, int y);
}
