package it.unibo.project.game.model.api;

/**
 * {@code factory} for creating {@linkplain GameWorld}.
 */
public interface GameWorldFactory {

    /**
     * @return a new {@linkplain GameWorld} object
     */
    GameWorld createGameWorld();
}
