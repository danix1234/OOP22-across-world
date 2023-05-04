package it.unibo.project.game.model.impl;

import java.util.List;

import it.unibo.project.controller.core.api.Difficulty;
import it.unibo.project.controller.core.api.Loader;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.game.logic.api.GameLogic;
import it.unibo.project.game.logic.impl.GameLogicImpl;
import it.unibo.project.game.model.api.BackgroundCell;
import it.unibo.project.game.model.api.Collectable;
import it.unibo.project.game.model.api.GameStat;
import it.unibo.project.game.model.api.GameWorld;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.Player;

public class GameWorldImpl implements GameWorld {
    private Player player;
    private GameStat gameStat;
    private List<Obstacle> obstacleList;
    private List<Collectable> collectableList;
    private List<BackgroundCell> backgroundCellList;
    private GameLogic gameLogic = new GameLogicImpl();

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public List<Obstacle> getObstacles() {
        return this.obstacleList;
    }

    @Override
    public List<Collectable> getCollectables() {
        return this.collectableList;
    }

    @Override
    public List<BackgroundCell> getBackgroundCells() {
        return this.backgroundCellList;
    }

    @Override
    public GameLogic getGameLogic() {
        return this.gameLogic;
    }

    @Override
    public GameStat getGameStat() {
        return this.gameStat;
    }

    @Override
    public void loadMap() {
        final Difficulty difficulty = LauncherImpl.LAUNCHER.getDifficulty();
        final Loader loader = LauncherImpl.LAUNCHER.getLoader();

        this.backgroundCellList = loader.getBackgroundCells(difficulty);
        this.collectableList = loader.getCollectables(difficulty);
        this.obstacleList = loader.getObstacles(difficulty);
        this.player = loader.getPlayerCell(difficulty);
        if (this.gameStat == null) {
            this.gameStat = loader.getGameStat();
        }
    }

}
