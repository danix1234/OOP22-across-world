package it.unibo.project.controller.engine.api;

/**
 * {@code factory} for creating {@linkplain GameEngine} class.
 */
public interface GameEngineFactory {
    /**
     * @return a new {@linkplain GameEngine} object
     */
    GameEngine createGameEngine();
}
