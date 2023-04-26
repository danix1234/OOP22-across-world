package it.unibo.project.game.model.api;

import java.util.List;

import it.unibo.project.game.logic.api.GameLogic;

public interface GameWorld {
    Player getPlayer();

    List<Obstacle> getObstacles();

    List<Collectable> getCollectables();

    List<BackgroundCell> getBackgroundCells();

    GameLogic getGameLogic();

    GameStat getGameStat();

    void setGameStat();

    void addObstacles(List<Obstacle> obstacles);

    void addCollectables(List<Collectable> collectables);

    void addBackgroundCell(List<BackgroundCell> backgroundCells);

    void clearAllEntity();
}

