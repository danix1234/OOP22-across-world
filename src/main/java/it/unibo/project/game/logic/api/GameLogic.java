package it.unibo.project.game.logic.api;

public interface GameLogic {
    CheckCollision getCollisionChecker();

    HandlePowerup getPowerupHandler();

    MovementLogic getMovementLogic();
}
