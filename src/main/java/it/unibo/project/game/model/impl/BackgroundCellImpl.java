package it.unibo.project.game.model.impl;

import it.unibo.project.game.model.api.BackgroundCell;
import it.unibo.project.game.model.api.BackgroundCellType;
import it.unibo.project.utility.Vector2D;

/**
 * Class {@code BackgroundCellImpl}, contains methods to manage background cells.
 * extends {@link EntityImpl} and implements {@link BackgroundCell}
 */
public final class BackgroundCellImpl extends EntityImpl implements BackgroundCell {
    private final BackgroundCellType type;

    /**
     * constructor of BackgroundCellImpl, set the initial postition and type of the background cell with the value of
     * the given param.
     * 
     * @param initialPos Vector2D that contains the initial position to give to the background cell
     * @param type BackgroundCellType that contains the type to attribute to the background cell
     */
    public BackgroundCellImpl(final Vector2D initialPos, final BackgroundCellType type) {
        super(initialPos, false);
        this.type = type;
    }

    @Override
    public BackgroundCellType getType() {
        return this.type;
    }

}
