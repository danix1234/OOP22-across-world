package it.unibo.project.game.logic.impl;

import it.unibo.project.game.logic.api.CheckCollision;
import it.unibo.project.game.logic.api.GameLogic;
import it.unibo.project.game.logic.api.HandlePowerup;
import it.unibo.project.game.logic.api.MovementLogic;

/**
 * class {@code GameLogicImpl} implements {@linkplain GameLogic}.
 */
public class GameLogicImpl implements GameLogic {
    private final CheckCollision checkCollision = new CheckCollisionImpl();
    private final HandlePowerup handlePowerup = new HandlePowerupImpl();
    private final MovementLogic movementLogic = new MovementLogicImpl();

    @Override
    public final CheckCollision getCollisionChecker() {
        return this.checkCollision;
    }

    @Override
    public final HandlePowerup getPowerupHandler() {
        return this.handlePowerup;
    }

    @Override
    public final MovementLogic getMovementLogic() {
        return this.movementLogic;
    }

}
