package it.unibo.project.game.model.impl;

import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.ObstacleType;
import it.unibo.project.utility.Vector2D;

/**
 * Class {@code ObstacleImpl}, contains methods to manage obstacles.
 * extends {@link EntityImpl} and implements {@link Obstacle}
 */
public final class ObstacleImpl extends EntityImpl implements Obstacle {
    private final ObstacleType type;
    private final double speed;
    private double pixelX;

    /**
     * constructor of ObstacleImpl, set the initial postition and type of the obstacle with the value of
     * the given param.
     * 
     * @param initialPos Vector2D that contains the initial position to give to the obstacle
     * @param type ObstacleType that contains the type to attribute to the object
     */
    public ObstacleImpl(final Vector2D initialPos, final ObstacleType type) {
        super(initialPos, type.isMoveable());
        this.type = type;
        this.speed = type.getSpeed();
        this.pixelX = LauncherImpl.LAUNCHER.convertCellToPixelPos(initialPos);
    }

    @Override
    public ObstacleType getType() {
        return this.type;
    }

    @Override
    public double getPixelPosition() {
        return this.pixelX;
    }

    @Override
    public void movePixelPosition(final double x) {
        this.pixelX = x;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }


}
