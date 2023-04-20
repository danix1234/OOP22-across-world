package it.unibo.project.controller.core.api;


import java.util.List;

import it.unibo.project.game.model.api.BackgroundCell;
import it.unibo.project.game.model.api.Collectable;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.Player;
import it.unibo.project.input.api.InputHandler;
import it.unibo.project.view.api.Scene;

// launcher is a singleton, with methods to call to do comunicate between model & view
public interface Launcher {
    // VIEW methods
    SceneType getSceneType();

    void setScene(SceneType sceneType);

    void closeWindow();

    Scene getScene();

    // MODEL methods

    Difficulty getDifficulty();

    void setDifficulty(Difficulty difficulty);

    Player getPlayer();

    List<Obstacle> getObstacles();

    List<Collectable> getCollectables();

    List<BackgroundCell> getBackgroundCells();

    // CONTROLLER methods
    void start();

    InputHandler geInputHandler(SceneType sceneType);

    // TODO: feel free to create all the methods you want, since only the launcher
    // can interact with everything
}
