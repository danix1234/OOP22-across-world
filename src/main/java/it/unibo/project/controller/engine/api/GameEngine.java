package it.unibo.project.controller.engine.api;

public interface GameEngine {
    void start();

    // should be used only inside game engine class
    void processInput();

    void updateGame();

    void render();
}
