package it.unibo.project.view.impl;

import it.unibo.project.controller.core.api.SceneType;

public class GameOverScene extends AbstractGameEndScene {

    public GameOverScene() {
        super("GAME OVER!", SceneType.OVER);
    }

}
