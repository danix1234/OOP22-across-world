package it.unibo.project.controller.core.api;

import it.unibo.project.controller.engine.api.GameEngine;
import it.unibo.project.game.model.api.GameWorld;
import it.unibo.project.view.api.Window;

// launcher is a singleton, so everything can be easily accessed globally
public interface Launcher {
    // method called in main function, which runs the program, and handle the scene
    void start();

    Window getWindow();

    GameEngine getGameEngine();

    GameWorld getGameWorld();

    SceneType getSceneType();

    // shortcut for changing current shene
    // MUST BE USED FOR CHANGING SCENE, SINCE IT ALSO KEEP TRACK OF THE CURRENT
    // SCENE TYPE
    void setScene(SceneType sceneType);
}
