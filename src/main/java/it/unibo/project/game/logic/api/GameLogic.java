package it.unibo.project.game.logic.api;

public interface GameLogic {
    public CheckCollision getCollisionChecker();

    public HandlePowerup getPowerupHandler();

    public MovementLogic getMovementLogic();
}
