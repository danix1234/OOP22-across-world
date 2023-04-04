package it.unibo.project.game.model.api;

import java.util.List;

public interface GameWorld {
    public Player getPlayer();

    public List<Obstacle> getObstacles();

    public List<Collectable> getCollectables();

    public List<BackgroundCell> getBackgroundCells();

    public GameLogic getGameLogic();

    public void clearMap();

    public void loadMap(String mapName);
}
