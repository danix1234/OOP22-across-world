package it.unibo.project.controller.core.api;

import java.awt.Image;
import java.util.List;

import it.unibo.project.game.model.api.BackgroundCell;
import it.unibo.project.game.model.api.BackgroundCellType;
import it.unibo.project.game.model.api.Collectable;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.GameStat;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.ObstacleType;

/**
 * class {@code Loader} interacts with filesystem, abstracting it to the other
 * classes, which can directly obtain the needed data.
 */
public interface Loader {

    /**
     * load all images from memory.
     * 
     * @see #loadAllFromFile()
     */
    void loadImages();

    /**
     * load statistics from memory, or from user home if present.
     * 
     * @see #loadAllFromFile()
     */
    void loadStats();

    /**
     * load all maps from memory.
     * 
     * @see #loadAllFromFile()
     */
    void loadMaps();

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
     * remove the directory and the files saved in the user home directory.
     * 
     * @implNote {@code WARNING}: removes all player gameplay results,
     *           {@code use wisely!}
     */
    void deleteStatFile();

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

    /**
     * @param difficulty specify which map to load
     * @return collection of {@linkplain Collectable} of loaded map
     */
    List<Collectable> getCollectables(Difficulty difficulty);

    /**
     * @param difficulty specify which map to load
     * @return collection of {@linkplain Obstacle} of loaded map
     */
    List<Obstacle> getObstacles(Difficulty difficulty);

    /**
     * @param difficulty specify which map to load
     * @return collection of {@linkplain BackgroundCell} of loaded map
     */
    List<BackgroundCell> getBackgroundCells(Difficulty difficulty);
}
