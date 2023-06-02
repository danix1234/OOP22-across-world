package it.unibo.project.game.model.impl;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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

/**
 * Class {@code GameWorldImpl}, implements {@link GameWorld}.
 */
public final class GameWorldImpl implements GameWorld {
    private Player player;
    private GameStat gameStat;
    private List<Obstacle> obstacleList;
    private List<Collectable> collectableList;
    private List<BackgroundCell> backgroundCellList;
    private final GameLogic gameLogic = new GameLogicImpl();

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public List<Obstacle> getObstacles() {
        return List.copyOf(this.obstacleList);
    }

    @Override
    public List<Collectable> getCollectables() {
        return List.copyOf(this.collectableList);
    }

    @Override
    public List<BackgroundCell> getBackgroundCells() {
        return List.copyOf(this.backgroundCellList);
    }

    @Override
    public GameLogic getGameLogic() {
        return this.gameLogic;
    }

    // cannot duplicate gameStat, because it store the current statistics
    @SuppressFBWarnings("EI")
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

    @Override
    public void removeCollectable(final Collectable toRemove) {
        if (this.collectableList != null) {
            this.collectableList.remove(toRemove);
        }
    }

}
