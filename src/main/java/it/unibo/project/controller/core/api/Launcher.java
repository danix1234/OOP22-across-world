package it.unibo.project.controller.core.api;

// launcher is a singleton, with methods to call to do comunicate between model & view
public interface Launcher {
    // VIEW methods
    SceneType getSceneType();

    void setScene(SceneType sceneType);

    void closeWindow();

    // MODEL methods

    // CONTROLLER methods
    void start();

    // TODO: feel free to create all the methods you want, since only the launcher
    // can interact with everything
}
