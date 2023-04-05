package it.unibo.project.controller.core.api;

import it.unibo.project.controller.engine.api.GameEngine;
import it.unibo.project.view.api.Window;

public interface Launcher {
    // method called in main function, which runs the program, and handle the scene
    public void start();

    public Window getWindow();

    public GameEngine getGameEngine();

    public SceneType getSceneType();
}
