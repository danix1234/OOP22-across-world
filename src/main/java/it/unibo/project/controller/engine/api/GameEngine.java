package it.unibo.project.controller.engine.api;

/**
 * class {@code GameEngine} handles the {@code game loop}.
 */
public interface GameEngine {
    /**
     * starts {@code new thread} which runs the {@code game loop} :
     * {@link #processInput()}, {@link #updateGame()}, {@link #render()}.
     */
    void start();

    /**
     * process input from player.
     */
    void processInput();

    /**
     * update game entities.
     */
    void updateGame();

    /**
     * redraw window.
     */
    void render();

    /**
     * force stop game engine.
     */
    void forceStop();
}
