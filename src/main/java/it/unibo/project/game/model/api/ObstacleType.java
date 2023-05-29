package it.unibo.project.game.model.api;

import it.unibo.project.controller.core.impl.LauncherImpl;

/**
 * Enum {@code ObstacleType}, to define every obstacle that can be used with his specifics.
 */
public enum ObstacleType {
    /**
     * tree obstacle, not moveable and not walkable.
     */
    TREE(0, 0, 0, 0, false),
    /**
     * bush obstacle, not moveable and not walkable.
     */
    BUSH(0, 0, 0, 0, false),
    /**
     * fence obstacle, not moveable and not walkable.
     */
    FENCE(0, 0, 0, 0, false),
    /**
     * stop light, not moveable and not walkable.
     */
    STOPLIGHT(0, 0, 0, 0, false),
    /**
     * transparent obstacle, not moveable and not walkable.
     */
    TRANSPARENT_OBSTACLE(0, 0, 0, 0, false),
    /**
     * sand castle obstacle, not moveable and not walkable.
     */
    SANDCASTLE(0, 0, 0, 0, false),
    /**
     * plam obstacle, not moveable and not walkable.
     */
    PALM(0, 0, 0, 0, false),
    /**
     * beach umbrella obstacle, not moveable and not walkable.
     */
    BEACHUMBRELLA(0, 0, 0, 0, false),
    /**
     * star fish obstracle, not moveable and not walkable.
     */
    STARFISH(0, 0, 0, 0, false),
    /**
     * car that is moving from left to right, is moveable and not walkable.
     */
    CAR_DX(5, 2, 8, LauncherImpl.ORIZ_CELL + 1, false),
    /**
     * car that is moving from right to left, is moveable and not walkable.
     */
    CAR_SX(-5, -2, -8, LauncherImpl.ORIZ_CELL + 1, false),
    /**
     * train locomotive that is moving from left to right, is moveable and not walkable.
     */
    TRAIN_DX(30, 20, 50, LauncherImpl.ORIZ_CELL * 4, false),
    /**
     * train locomotive that is moving from right to left, is moveable and not walkable.
     */
    TRAIN_SX(-30, -20, -50, LauncherImpl.ORIZ_CELL * 4, false),
    /**
     * train wagon that is moving from left to right, is moveable and not walkable.
     */
    WAGON_DX(30, 20, 50, LauncherImpl.ORIZ_CELL * 4, false),
    /**
     * train wagon that is moving from right to left, is moveable and not walkable.
     */
    WAGON_SX(-30, -20, -50, LauncherImpl.ORIZ_CELL * 4, false),
    /**
     * first part of trunk that is moving from left to right, is moveable and also walkable.
     */
    TRUNK_START_DX(2, 2, 6, LauncherImpl.ORIZ_CELL + 1, true),
    /**
     * middle part of trunk that is moving from left to right, is moveable and also walkable.
     */
    TRUNK_DX(2, 2, 6, LauncherImpl.ORIZ_CELL + 1, true),
    /**
     * last part of trunk that is moving from left to right, is moveable and also walkable.
     */
    TRUNK_FINISH_DX(2, 2, 6, LauncherImpl.ORIZ_CELL + 1, true),
    /**
     * first part of trunk that is moving from right to left, is moveable and also walkable.
     */
    TRUNK_START_SX(-2, -2, -6, LauncherImpl.ORIZ_CELL + 1, true),
    /**
     * middle part of trunk that is moving from right to left, is moveable and also walkable.
     */
    TRUNK_SX(-2, -2, -6, LauncherImpl.ORIZ_CELL + 1, true),
    /**
     * last part of trunk that is moving from right to left, is moveable and also walkable.
     */
    TRUNK_FINISH_SX(-2, -2, -6, LauncherImpl.ORIZ_CELL + 1, true),
    /**
     * bike that is moving from left to right, is moveable and not walkable.
     */
    BIKE_DX(3, 1, 4, LauncherImpl.ORIZ_CELL + 1, false),
    /**
     * bike that is moving from right to left, is moveable and not walkable.
     */
    BIKE_SX(-3, -1, -4, LauncherImpl.ORIZ_CELL + 1, false),
    /**
     * beach mattress that is moving from right to left, is moveable and also walkable.
     */
    BEACHMATTRESS_SX(-2, -2, -6, LauncherImpl.ORIZ_CELL + 1, true),
    /**
     * beach mattress that is moving from left to right, is moveable and also walkable.
     */
    BEACHMATTRESS_DX(2, 2, 6, LauncherImpl.ORIZ_CELL + 1, true),
    /**
     * ball that is moving from right to left, is moveable and not walkable.
     */
    BALL_SX(-5, -1, -8, LauncherImpl.ORIZ_CELL + 1, false),
    /**
     * ball that is moving from left to right, is moveable and not walkable.
     */
    BALL_DX(5, 1, 8, LauncherImpl.ORIZ_CELL + 1, false),
    /**
     * burning tree obstacle, not moveable and not walkable.
     */
    FIRETREE(0, 0, 0, 0, false),
    /**
     * rock that is moving from left to right, is moveable and also walkable.
     */
    ROCK_DX(2, 2, 6, LauncherImpl.ORIZ_CELL + 1, true),
    /**
     * rock part of trunk that is moving from right to left, is moveable and also walkable.
     */
    ROCK_SX(-2, -2, -6, LauncherImpl.ORIZ_CELL + 1, true),
    /**
     * jet that is moving from left to right, is moveable and not walkable.
     */
    JET_DX(30, 20, 50, LauncherImpl.ORIZ_CELL * 4, false),
    /**
     * jet that is moving from right to left, is moveable and not walkable.
     */
    JET_SX(-30, -20, -50, LauncherImpl.ORIZ_CELL * 4, false),
    /** 
     * monster that is moving from left to right, is moveable and not walkable.
     */
    MONSTER_DX(5, 2, 8, LauncherImpl.ORIZ_CELL + 1, false),
    /**
     * monster that is moving from right to left, is moveable and not walkable.
     */
    MONSTER_SX(-5, -2, -8, LauncherImpl.ORIZ_CELL + 1, false);

    private final int wrapAroundDim;
    private final double speed;
    private final double minSpeed;
    private final double maxSpeed;
    private final boolean isWalkableOn;

    ObstacleType(final double speed, final double minSpeed, final double maxSpeed,
            final int wrapAroundDim, final boolean isWalkableOn) {
        this.speed = speed;
        this.wrapAroundDim = wrapAroundDim;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.isWalkableOn = isWalkableOn;
    }

    /**
     * Called to get speed value.
     * @return double that represent the speed of the obstacle
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Called to know if the obstacle is moveable.
     * @return boolean containing the value true if is moveable
     */
    public boolean isMoveable() {
        return this.speed != 0;
    }

    /**
     * Called to get wrap around dimention.
     * @return int that represent the wrap around dimention
     */
    public int getWrapAroundDim() {
        return this.wrapAroundDim;
    }

    /**
     * Called to get the minimum speed value.
     * @return double that represent the minimum speed of the obstacle
     */
    public double getMinSpeed() {
        return this.minSpeed;
    }

    /**
     * Called to get the full speed value.
     * @return double that represent the full speed of the obstacle
     */
    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    /**
     * Called to know if the obstacle is walkable.
     * @return boolean containing the value true if is walkable from the player
     */
    public boolean isWalkableOn() {
        return this.isWalkableOn;
    }
}
