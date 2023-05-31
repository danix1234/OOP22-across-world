package it.unibo.project.game.model.impl;

import it.unibo.project.game.model.api.Collectable;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.utility.Vector2D;

/**
 * Class {@code CollectableImpl}, contains methods to manage collectables.
 * extends {@link EntityImpl} and implements {@link Collectable}
 */
public final class CollectableImpl extends EntityImpl implements Collectable {
    private final CollectableType type;

    /**
     * constructor of CollectableImpl, set the initial postition and type of the collectable with the value of
     * the given param.
     * 
     * @param initialPos Vector2D that contains the initial position to give to the collectable
     * @param type CollectableType that contains the type to attribute to the collectable
     */
    public CollectableImpl(final Vector2D initialPos, final CollectableType type) {
        super(initialPos, false);
        this.type = type;
    }

    @Override
    public CollectableType getType() {
        return this.type;
    }

}
