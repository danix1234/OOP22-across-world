package it.unibo.project.game.logic.impl;

import it.unibo.project.game.logic.api.MovementLogic;

public class MovementLogicImpl implements MovementLogic {

    @Override
    public void movePlayer(final int x, final int y) {
        // TODO check collision
        
        // do this only after it is sure player won't collide with obstacles
        // LauncherImpl.LAUNCHER.getPlayer().move(x,y);  
    }

    @Override
    public void moveObstacle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveObstacle'");
    }

}
