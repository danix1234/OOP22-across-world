package it.unibo.project.controller.engine.api;

import it.unibo.project.view.api.Window;

public interface GameEngine {
    Window getWindow();

    void start();

    // should be used only inside game engine class
    void processInput();

    void updateGame();

    void render();
}
