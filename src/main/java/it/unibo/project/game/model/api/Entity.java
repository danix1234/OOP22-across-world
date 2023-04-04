package it.unibo.project.game.model.api;

import it.unibo.project.utility.Vector2D;

public interface Entity {
    public Vector2D getPosition();

    public boolean isMovable();

    public void move(int x, int y);
}
