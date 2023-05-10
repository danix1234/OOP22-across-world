package it.unibo.project.game.logic.impl;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.game.logic.api.MovementLogic;
import it.unibo.project.utility.Vector2D;

public class MovementLogicImpl implements MovementLogic {

    @Override
    public void movePlayer(final int x, final int y) {
        CheckCollisionImpl checker = new CheckCollisionImpl();
        Vector2D nextPlayerPosition = new Vector2D(x, y);

        if (checker.checkDynamicObstacleCollision(nextPlayerPosition).isEmpty()
                && checker.checkStaticObstacleCollision(nextPlayerPosition).isEmpty()
                && !checker.checkWallCollision(nextPlayerPosition)
                && !checker.checkFinishLineCollision(nextPlayerPosition)) {

            LauncherImpl.LAUNCHER.getPlayer().move(x, y);
        } else if (checker.checkFinishLineCollision(nextPlayerPosition)) {
            LauncherImpl.LAUNCHER.setScene(SceneType.MENU);
        } else if (checker.checkDynamicObstacleCollision(nextPlayerPosition).isPresent()) {
            LauncherImpl.LAUNCHER.setScene(SceneType.OVER);
        }
    }

    @Override
    public void moveObstacle() {
        // TODO Auto-generated method stub
    }

}
