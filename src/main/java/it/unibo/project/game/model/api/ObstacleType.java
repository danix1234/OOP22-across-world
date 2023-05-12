package it.unibo.project.game.model.api;

import it.unibo.project.controller.core.impl.LauncherImpl;

public enum ObstacleType {
    TREE(0, 0),
    BUSH(0, 0),
    TRANSPARENT_WATER(0, 0),
    CAR_DX(5, LauncherImpl.ORIZ_CELL + 1),
    CAR_SX(-5, LauncherImpl.ORIZ_CELL + 1),
    TRAIN_DX(30, LauncherImpl.ORIZ_CELL * 4),
    TRAIN_SX(-30, LauncherImpl.ORIZ_CELL * 4),
    TRUNK_DX(2, LauncherImpl.ORIZ_CELL + 1),
    TRUNK_SX(-2, LauncherImpl.ORIZ_CELL + 1);

    private final double speed;
    private final int wrapAroundDim;

    private ObstacleType(final double speed, final int wrapAroundDim) {
        this.speed = speed;
        this.wrapAroundDim = wrapAroundDim;
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
}
