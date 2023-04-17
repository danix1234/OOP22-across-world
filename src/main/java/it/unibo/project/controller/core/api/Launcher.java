package it.unibo.project.controller.core.api;

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

    // CONTROLLER methods
    void start();

    // TODO: feel free to create all the methods you want, since only the launcher
    // can interact with everything
}
