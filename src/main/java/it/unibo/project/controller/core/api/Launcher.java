package it.unibo.project.controller.core.api;

import it.unibo.project.controller.engine.api.GameEngine;
import it.unibo.project.game.model.api.GameWorld;
import it.unibo.project.view.api.Window;

// launcher is a singleton, so everything can be easily accessed globally
public interface Launcher {
    // method called in main function, which runs the program, and handle the scene
    public void start();

    public Window getWindow();

    public GameEngine getGameEngine();

    public GameWorld getGameWorld();

    public SceneType getSceneType();
}
