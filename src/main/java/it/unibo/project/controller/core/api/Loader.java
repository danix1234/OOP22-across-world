package it.unibo.project.controller.core.api;

import java.awt.Image;
import java.util.List;

import it.unibo.project.game.model.api.BackgroundCellType;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.GameStat;
import it.unibo.project.game.model.api.ObstacleType;

/**
 * class {@code Loader} interacts with filesystem, abstracting it to the other
 * classes, which can directly obtain the needed data.
 */
public interface Loader {

    /**
     * loads everything from files, and keeps it in memory.
     * 
     * @implNote to use after starting the application, or to refresh what was
     *           loaded from file
     * 
     * @implSpec this method is not meant to be called externally, but internally
     *           whenever data isn't present. Call this externally only if a reload
     *           of the data is needed!
     */
    void loadAllFromFile();

    /**
     * save statistics and variable informations in user home.
     * 
     * @param stats which contains updated game stats
     */
    void saveStatOnFile(GameStat stats);

    /**
     * @return {@linkplain GameStat} initialized using the file
     */
    GameStat getGameStat();

    /**
     * @return {@code collection of images} representing player sprites
     */
    List<Image> getPlayerSprites();

    /**
     * @param collectableType the type of {@linkplain Collectable} needed
     * @return {@code collection of images} representing collectable sprites
     */
    List<Image> getCollectablesSprites(CollectableType collectableType);

    /**
     * @param backgroundCellType the type of {@linkplain BackgroundCell} needed
     * @return {@code collection of images} representing background sprites
     */
    List<Image> getBackgroundCellSprites(BackgroundCellType backgroundCellType);

    /**
     * @param obstacleType the type of {@linkplain Obstacle} needed
     * @return {@code collection of images} representing background sprites
     */
    List<Image> getObstacleSprites(ObstacleType obstacleType);

    /**
     * get random element from the collection passed as argoment.
     * 
     * To be used as a wrapper for
     * {@link #getBackgroundCellSprites(BackgroundCellType)},
     * {@link #getObstacleSprites(ObstacleType)},
     * {@link #getPlayerSprites()},
     * {@link #getCollectablesSprites(CollectableType)},
     * in orded to obtain a random image to be rendered by the view
     * 
     * @param collection
     * @return {@code random} element from the list
     */
    Image getElementRandom(List<Image> collection);
}
