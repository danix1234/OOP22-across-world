package it.unibo.project.game.logic.impl;

import it.unibo.project.game.logic.api.CheckCollision;
import it.unibo.project.game.logic.api.GameLogic;
import it.unibo.project.game.logic.api.HandlePowerup;
import it.unibo.project.game.logic.api.MovementLogic;

public class GameLogicImpl implements GameLogic {
    private final CheckCollision checkCollision = new CheckCollisionImpl();
    private final HandlePowerup handlePowerup = new HandlePowerupImpl();
    private final MovementLogic movementLogic = new MovementLogicImpl();

    @Override
    public CheckCollision getCollisionChecker() {
        return this.checkCollision;
    }

    @Override
    public HandlePowerup getPowerupHandler() {
        return this.handlePowerup;
    }

    @Override
    public MovementLogic getMovementLogic() {
        return this.movementLogic;
    }

}
