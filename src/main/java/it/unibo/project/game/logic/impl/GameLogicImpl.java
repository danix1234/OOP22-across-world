package it.unibo.project.game.logic.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.project.game.logic.api.CheckCollision;
import it.unibo.project.game.logic.api.GameLogic;
import it.unibo.project.game.logic.api.HandlePowerup;
import it.unibo.project.game.logic.api.MovementLogic;

/**
 * class {@code GameLogicImpl} implements {@linkplain GameLogic}.
 */
public class GameLogicImpl implements GameLogic {
    private final HandlePowerup handlePowerup = new HandlePowerupImpl();

    @Override
    public final CheckCollision getCollisionChecker() {
        return new CheckCollisionImpl();
    }

    // cannot duplicate powerupHandler, because it store the current powerups
    @SuppressFBWarnings("EI")
    @Override
    public final HandlePowerup getPowerupHandler() {
        return this.handlePowerup;
    }

    @Override
    public final MovementLogic getMovementLogic() {
        return new MovementLogicImpl();
    }

}
