package it.unibo.project.game.model.api;

import java.util.List;

import it.unibo.project.controller.core.api.Difficulty;
import it.unibo.project.game.logic.api.GameLogic;

public interface GameWorld {
    Player getPlayer();

    List<Obstacle> getObstacles();

    List<Collectable> getCollectables();

    List<BackgroundCell> getBackgroundCells();

    GameLogic getGameLogic();

    GameStat getGameStat();

    void loadMap(Difficulty difficulty);    
}
