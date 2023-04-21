package it.unibo.project.game.model.impl;

import java.util.List;

import it.unibo.project.game.logic.api.GameLogic;
import it.unibo.project.game.model.api.BackgroundCell;
import it.unibo.project.game.model.api.Collectable;
import it.unibo.project.game.model.api.GameStat;
import it.unibo.project.game.model.api.GameWorld;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.Player;

public class GameWorldImpl implements GameWorld {

    @Override
    public Player getPlayer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayer'");
    }

    @Override
    public List<Obstacle> getObstacles() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getObstacles'");
    }

    @Override
    public List<Collectable> getCollectables() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCollectables'");
    }

    @Override
    public List<BackgroundCell> getBackgroundCells() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBackgroundCells'");
    }

    @Override
    public GameLogic getGameLogic() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGameLogic'");
    }

    @Override
    public void clearMap() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clearMap'");
    }

    @Override
    public void loadMap(final String mapName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadMap'");
    }

    @Override
    public GameStat getGameStat() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGameStat'");
    }

}
