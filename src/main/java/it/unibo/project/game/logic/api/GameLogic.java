package it.unibo.project.game.logic.api;

/**
 * class {@code GameLogic} contains objects correlated to the {@code logic} of
 * the game.
 */
public interface GameLogic {
    /**
     * @return {@linkplain CheckCollision} objects for handling collision
     */
    CheckCollision getCollisionChecker();

    /**
     * @return {@linkplain HandlePowerup} for handling powerups
     */
    HandlePowerup getPowerupHandler();

    /**
     * @return {@linkplain MovementLogic} for handling movement of all entities
     */
    MovementLogic getMovementLogic();
}
