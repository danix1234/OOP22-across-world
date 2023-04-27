package it.unibo.project.controller.core.api;

import java.util.List;

import it.unibo.project.game.model.api.BackgroundCell;
import it.unibo.project.game.model.api.Collectable;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.Player;
import it.unibo.project.input.api.InputHandler;
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

    // MODEL methods

    /**
     * @return {@linkplain Difficuly} for the current/next game
     */
    Difficulty getDifficulty();

    /**
     * @param difficulty difficulty for the next game
     */
    void setDifficulty(Difficulty difficulty);

    /**
     * @return {@linkplain Player}
     */
    Player getPlayer();

    /**
     * @return collection of all {@linkplain Obstacle} in the entire map
     */
    List<Obstacle> getObstacles();

    /**
     * @return collection of all {@linkplain Collectable} in the entire map
     */
    List<Collectable> getCollectables();

    /**
     * @return collection of all {@linkplain BackgroundCell} in the entire map
     */
    List<BackgroundCell> getBackgroundCells();

    /**
     * @return {@linkplain Loader} which manages save and load operation from file
     */
    Loader getLoader();

    // CONTROLLER methods
    /**
     * starts the application.
     * 
     * @implNote sets a Scene and lets java swing handle the flow of the application
     */
    void start();

    /**
     * @param sceneType of the inputHandler needed
     * @return {@linkplain InputHandler} which handles the {@linkplain Action}
     */
    InputHandler getInputHandler(SceneType sceneType);
}
