package it.unibo.project.game.model.impl;

import it.unibo.project.game.model.api.Player;
import it.unibo.project.utility.Vector2D;

/**
 * Class {@code PlayerImpl}, contains methods to manage the player.
 * extends {@link EntityImpl} and implements {@link Player}
 */
public final class PlayerImpl extends EntityImpl implements Player {
    private int maxDistance = 0;

    /**
    * constructor of PlayerImpl, set the initial player postition with the value of
    * the given param.
    * 
    * @param initialPos Vector2D that contains the initial position to give to the
    *                   player
    */
    public PlayerImpl(final Vector2D initialPos) {
        super(initialPos, true);

        // force calculation of max distance
        move(initialPos.getX(), initialPos.getY());
    }

    @Override
    public int getMaxDistance() {
        return this.maxDistance;
    }

    /**
     * Called for move the player.
     * Call the super move and compare the actual distance of the player
     * with the max distance which he achieved.
     */
    @Override
    public void move(final int x, final int y) {
        super.move(x, y);
        maxDistance = Integer.max(maxDistance, y);
    }

}
