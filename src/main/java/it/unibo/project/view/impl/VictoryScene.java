package it.unibo.project.view.impl;

import it.unibo.project.controller.core.api.SceneType;

/**
 * Represents the victory scene displayed when the player wins the game.
 * Extends {@link AbstractGameEndScene}.
 */
public class VictoryScene extends AbstractGameEndScene {

    /**
     * Creates a new instance of the VictoryScene.
     * Sets the scene title as "YOU WIN!" and the scene type as {@link SceneType#VICTORY}.
     */
    public VictoryScene() {
        super("YOU WIN!", SceneType.VICTORY);
    }
}
