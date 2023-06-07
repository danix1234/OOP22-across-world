package it.unibo.project.view.impl;

import it.unibo.project.controller.core.api.SceneType;

public class VictoryScene extends AbstractGameEndScene {

    public VictoryScene() {
        super("YOU WIN!", SceneType.VICTORY);
    }
}
