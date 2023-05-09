package it.unibo.project.game.logic.impl;

import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.game.logic.api.MovementLogic;

public class MovementLogicImpl implements MovementLogic {

    @Override
    public void movePlayer(final int x, final int y) {
        LauncherImpl.LAUNCHER.getPlayer().move(x,y);  
    }

    @Override
    public void moveObstacle() {
        // TODO Auto-generated method stub
    }

}
