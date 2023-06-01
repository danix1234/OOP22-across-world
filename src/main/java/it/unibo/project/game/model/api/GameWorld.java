package it.unibo.project.game.model.api;

import java.util.List;

import it.unibo.project.game.logic.api.GameLogic;

/**
 * Interface {@code GameWorld}, contain methods to get the instances of the "world" components and load the map.
 */
public interface GameWorld {
    /**
     * called to get the player instance.
     * @return Player that represents player in game
     */
    Player getPlayer();

    /**
     * called to get the list of obstacles that are presents in the map.
     * @return List<Obstacle> that contains all obstacles of the map
     */
    List<Obstacle> getObstacles();

    /**
     * called to get the list of collectables that are presents in the map.
     * @return List<Collectable> that contains all collectables of the map
     */
    List<Collectable> getCollectables();

    /**
     * called to remove the collectable passed as parameter.
     * @param toRemove collectabole to remove from list of all collectables
     */
    void removeCollectable(Collectable toRemove);

    /**
     * called to get the list of background cells that are presents in the map.
     * @return List<BackgroundCell> that contains all background cells of the map
     */
    List<BackgroundCell> getBackgroundCells();

    /**
     * called to get the game logic instance.
     * @return GameLogic instance
     */
    GameLogic getGameLogic();

    /**
     * called to get the game stat instance.
     * @return GameStat instance
     */
    GameStat getGameStat();

    /**
     * called to initialize (load) the components lists of the map and the player.
     */
    void loadMap();
}
