package it.unibo.project.view.impl;

import it.unibo.project.controller.core.api.SceneType;

/**
 * Represents the game over scene displayed when the player loses the game.
 * Extends {@link AbstractGameEndScene}.
 */
public class GameOverScene extends AbstractGameEndScene {

    /**
     * Creates a new instance of the GameOverScene.
     * Sets the scene title as "GAME OVER!" and the scene type as {@link SceneType#OVER}.
     */
    public GameOverScene() {
        super("GAME OVER!", SceneType.OVER);
    }

}
