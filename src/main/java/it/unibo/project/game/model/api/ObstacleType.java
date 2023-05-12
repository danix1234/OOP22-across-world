package it.unibo.project.game.model.api;

public enum ObstacleType {
    TREE(0),
    BUSH(0),
    CAR_DX(1),
    CAR_SX(-1),
    TRAIN_DX(6),
    TRAIN_SX(-6),
    TRANSPARENT_WATER(0),
    TRUNK_DX(1),
    TRUNK_SX(-1);

    private final double speed;

    private ObstacleType(final double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return this.speed;
    }

    public boolean isMoveable() {
        return this.speed != 0;
    }
}
