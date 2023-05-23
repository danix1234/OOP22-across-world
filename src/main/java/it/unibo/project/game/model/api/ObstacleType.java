package it.unibo.project.game.model.api;

import it.unibo.project.controller.core.impl.LauncherImpl;

public enum ObstacleType {
    TREE(0, 0, 0, 0, false),
    BUSH(0, 0, 0, 0, false),
    FENCE(0, 0, 0, 0, false),
    STOPLIGHT(0, 0, 0, 0, false),
    TRANSPARENT_OBSTACLE(0, 0, 0, 0, false),
    SANDCASTLE(0, 0, 0, 0, false),
    PALM(0, 0, 0, 0, false),
    BEACHUMBRELLA(0, 0, 0, 0, false),
    STARFISH(0, 0, 0, 0, false),
    CAR_DX(5, 1, 8, LauncherImpl.ORIZ_CELL + 1, false),
    CAR_SX(-5, -1, -8, LauncherImpl.ORIZ_CELL + 1, false),
    TRAIN_DX(30, 20, 50, LauncherImpl.ORIZ_CELL * 4, false),
    TRAIN_SX(-30, -20, -50, LauncherImpl.ORIZ_CELL * 4, false),
    WAGON_DX(30, 20, 50, LauncherImpl.ORIZ_CELL * 4, false),
    WAGON_SX(-30, -20, -50, LauncherImpl.ORIZ_CELL * 4, false),
    TRUNK_START_DX(2, 2, 6, LauncherImpl.ORIZ_CELL + 1, true),
    TRUNK_DX(2, 2, 6, LauncherImpl.ORIZ_CELL + 1, true),
    TRUNK_FINISH_DX(2, 2, 6, LauncherImpl.ORIZ_CELL + 1, true),
    TRUNK_START_SX(-2, -2, -6, LauncherImpl.ORIZ_CELL + 1, true),
    TRUNK_SX(-2, -2, -6, LauncherImpl.ORIZ_CELL + 1, true),
    TRUNK_FINISH_SX(-2, -2, -6, LauncherImpl.ORIZ_CELL + 1, true),
    BIKE_DX(3, 1, 4, LauncherImpl.ORIZ_CELL + 1, false),
    BIKE_SX(-3, -1, -4, LauncherImpl.ORIZ_CELL + 1, false),
    BEACHMATTRESS_SX(-2, -2, -6, LauncherImpl.ORIZ_CELL + 1, true),
    BEACHMATTRESS_DX(2, 2, 6, LauncherImpl.ORIZ_CELL + 1, true),
    BALL_SX(-5, -1, -8, LauncherImpl.ORIZ_CELL + 1, false),
    BALL_DX(5, 1, 8, LauncherImpl.ORIZ_CELL + 1, false),
    FIRETREE(0, 0, 0, 0, false),
    FIRE(0, 0, 0, 0, true),
    ROCK_DX(2, 2, 6, LauncherImpl.ORIZ_CELL + 1, true),
    ROCK_SX(-2, -2, -6, LauncherImpl.ORIZ_CELL + 1, true),
    JET_DX(30, 20, 50, LauncherImpl.ORIZ_CELL * 4, false),
    JET_SX(-30, -20, -50, LauncherImpl.ORIZ_CELL * 4, false),
    MONSTER_DX(5, 1, 8, LauncherImpl.ORIZ_CELL + 1, false),
    MONSTER_SX(-5, -1, -8, LauncherImpl.ORIZ_CELL + 1, false);

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

    public double getSpeed() {
        return this.speed;
    }

    public boolean isMoveable() {
        return this.speed != 0;
    }

    public int getWrapAroundDim() {
        return this.wrapAroundDim;
    }

    public double getMinSpeed() {
        return this.minSpeed;
    }

    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    public boolean isWalkableOn() {
        return this.isWalkableOn;
    }
}
