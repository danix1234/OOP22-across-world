package it.unibo.project.game.logic.impl;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.game.logic.api.MovementLogic;
import it.unibo.project.utility.Vector2D;

public class MovementLogicImpl implements MovementLogic {

    @Override
    public void movePlayer(final int x, final int y) {
        CheckCollisionImpl checker = new CheckCollisionImpl();

        if (checker.checkDynamicObstacleCollision().isEmpty()
                && checker.checkStaticObstacleCollision(new Vector2D(x, y)).isEmpty()
                && !checker.checkWallCollision()
                && !checker.checkFinishLineCollision()) {

            LauncherImpl.LAUNCHER.getPlayer().move(x, y);
        } else if (checker.checkFinishLineCollision()) {
            LauncherImpl.LAUNCHER.setScene(SceneType.MENU);
        } else if (checker.checkDynamicObstacleCollision().isPresent()) {
            LauncherImpl.LAUNCHER.setScene(SceneType.OVER);
        }
    }

    @Override
    public void moveObstacle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveObstacle'");
    }

}
