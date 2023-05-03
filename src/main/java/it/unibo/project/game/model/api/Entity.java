package it.unibo.project.game.model.api;

import it.unibo.project.utility.Vector2D;

/**
 * Class {@code Entity}, contain the position method for the entities.
 * 
 */
public interface Entity {
    /**
     * Called for get the position of the relative entity.
     * @return Vector2D that represent the position of the entity
     */
    Vector2D getPosition();

    /**
     * Called for control if the relative entity is movable.
     * @return boolean that represent whether an entity can move
     */
    boolean isMovable();

    /**
     * Called for move an entity.
     * @return a new Vector2D with the new coordinates if the entity can move
     */
    void move(int x, int y);
}
