package it.unibo.project.game.model.api;

import it.unibo.project.controller.core.impl.LauncherImpl;

public enum ObstacleType {
    TREE(0, 0, 0, 0),
    BUSH(0, 0, 0, 0),
    TRANSPARENT_WATER(0, 0, 0, 0),
    CAR_DX(5, 1, 8, LauncherImpl.ORIZ_CELL + 1),
    CAR_SX(-5, -1, -8, LauncherImpl.ORIZ_CELL + 1),
    TRAIN_DX(30, 20, 50, LauncherImpl.ORIZ_CELL * 4),
    TRAIN_SX(-30, -20, -50, LauncherImpl.ORIZ_CELL * 4),
    TRUNK_DX(2, 2, 6, LauncherImpl.ORIZ_CELL + 1),
    TRUNK_SX(-2, -2, -6, LauncherImpl.ORIZ_CELL + 1);

    private final int wrapAroundDim;
    private final double speed;
    private final double minSpeed;
    private final double maxSpeed;

    private ObstacleType(final double speed, final double minSpeed, final double maxSpeed, final int wrapAroundDim) {
        this.speed = speed;
        this.wrapAroundDim = wrapAroundDim;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
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
}
