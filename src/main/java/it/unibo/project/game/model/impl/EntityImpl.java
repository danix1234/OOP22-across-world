package it.unibo.project.game.model.impl;

import it.unibo.project.game.model.api.Entity;
import it.unibo.project.utility.Vector2D;

/**
 * Class {@code EntityImpl}, implements {@link Entity}.
 */
public class EntityImpl implements Entity {
    private Vector2D entityPos;
    private final boolean movable;

    /**
     * Constructor of class Entity, set the initial postition and if movable of the
     * Entity with the value of
     * the given param.
     * 
     * @param initialPos Vector2D that contains the initial position to give to the
     *                   entity
     * @param movable    boolean that is set to true if the entity is movable
     */
    public EntityImpl(final Vector2D initialPos, final boolean movable) {
        this.entityPos = initialPos;
        this.movable = movable;
    }

    @Override
    public final Vector2D getPosition() {
        return this.entityPos;
    }

    @Override
    public final boolean isMovable() {
        return this.movable;
    }

    @Override
    public void move(final int x, final int y) {
        if (movable) {
            final Vector2D newPos = new Vector2D(x, y);
            this.entityPos = newPos;
        }
    }
}
