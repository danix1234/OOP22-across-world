package it.unibo.project.controller.core.api;

import java.util.List;

import it.unibo.project.game.logic.api.CheckCollision;
import it.unibo.project.game.logic.api.HandlePowerup;
import it.unibo.project.game.model.api.BackgroundCell;
import it.unibo.project.game.model.api.Collectable;
import it.unibo.project.game.model.api.GameStat;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.Player;
import it.unibo.project.input.api.InputHandler;
import it.unibo.project.utility.Vector2D;
import it.unibo.project.view.api.Scene;

/**
 * class {@code Launcher} is an {@code intermediary} between {@code View} and
 * {@code Model} of the {@code MVC} architecture.
 */
public interface Launcher {

    // VIEW methods

    /**
     * @return {@linkplain SceneType} used in the moment this method is called
     */
    SceneType getSceneType();

    /**
     * changes current {@linkplain SceneType}.
     * 
     * @param sceneType new type of scene to set instead of the current one
     */
    void setScene(SceneType sceneType);

    /**
     * method to {@code gracefully} close application.
     */
    void closeWindow();

    /**
     * method which wraps {@link #closeWindow()} but saves on file before closing.
     */
    void saveAndCloseWindow();

    /**
     * @return current {@linkplain Scene} being shown by the application
     */
    Scene getScene();

    /**
     * show window.
     */
    void showWindow();

    /**
     * converter position in cell to position in pixel.
     * @param cellPos Vector2D that contains cell position (x,y)
     * @return double that represent x value of cell in pixel pos
     */
    double convertCellToPixelPos(Vector2D cellPos);

   /**
    * converter position in pixel to position in cell.
    * @param pixelX double that represent pixel x that msut be converted to cell x
    * @param cellY int  cell y position
    * @return Vector2D that contains the cell position (x,y)
    */
    Vector2D convertPixelToCellPos(double pixelX, int cellY);

    /**
     * convert obstaclePixelX into the actual pixel on the screen.
     * 
     * @param obstaclePixelX pixel x value of the obstacle
     * @return actual pixel x position
     */
    double getActualPixelX(double obstaclePixelX);

    /**
     * inverse function of {@link #getActualPixelX(double)}.
     * 
     * @param actualPixelX
     * @return obstacle pixel x position
     */
    double getObstaclePixelX(double actualPixelX);

    // MODEL methods

    /**
     * @return {@linkplain Difficuly} for the current/next game.
     */
    Difficulty getDifficulty();

    /**
     * @param difficulty difficulty for the next game.
     */
    void setDifficulty(Difficulty difficulty);

    /**
     * @return {@linkplain Player}
     */
    Player getPlayer();

    /**
     * @return collection of all {@linkplain Obstacle} in the entire map.
     */
    List<Obstacle> getObstacles();

    /**
     * @return collection of all {@linkplain Collectable} in the entire map.
     */
    List<Collectable> getCollectables();

    /**
     * @return collection of all {@linkplain BackgroundCell} in the entire map.
     */
    List<BackgroundCell> getBackgroundCells();

    /**
     * @return {@linkplain Loader} which manages save and load operation from file.
     */
    Loader getLoader();

    /**
     * loads map from file in {@linkplain GameWorld}.
     */
    void loadMap();

    /**
     * @return updated {@linkplain GameStat}.
     */
    GameStat getGameStat();

    /**
     * suggests {@code MovementLogic} to move player to [x,y] if there are no
     * obstacles in the way.
     * 
     * @param x
     * @param y
     * 
     * @implNote {@code MovementLogic} takes care of doing all checks on the move
     */
    void movePlayerIfPossible(int x, int y);

    /**
     * force {@code MovementLogic} to move dynamic obstacles (cars, trains, trunks).
     */
    void moveDynamicObstacles();

    /**
     * call the remove method from gameWorld to remove the given collectable.
     * @param collectable to remove from the collectableList
     */
    void removeCollectable(Collectable collectable);

    // CONTROLLER methods
    /**
     * starts the application.
     * 
     * @implNote sets a Scene and lets java swing handle the flow of the application
     */
    void start();

    /**
     * @param sceneType of the inputHandler needed.
     * @return {@linkplain InputHandler} which handles the {@linkplain Action}
     */
    InputHandler getInputHandler(SceneType sceneType);

    /**
     * start the game engine.
     * 
     * @implNote game engine kills itself when not in game scene!
     */
    void startEngine();

    /**
     * get the collision checker.
     * 
     * @return {@linkplain CheckCollision} to check any collision between entities
     */
    CheckCollision getCheckCollision();

    /**
     * get the powerup handler.
     * 
     * @return {@linkplain HandlePowerup} to manage when a powerup is collected
     */
    HandlePowerup getHandlePowerup();

}
