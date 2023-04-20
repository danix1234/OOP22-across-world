package it.unibo.project.game.logic.api;

/**
 * {@code factory} for creating {@linkplain GameLogic} class.
 */
public interface GameLogicFactory {

    /**
     * @return a new {@linkplain GameLogic} object
     */
    GameLogic creatGameLogic();
}
