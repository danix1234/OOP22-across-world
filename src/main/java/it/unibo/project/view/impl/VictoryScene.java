package it.unibo.project.view.impl;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.input.api.Action;

public class VictoryScene extends AbstractGameEndScene {

    public VictoryScene() {
        super("YOU WIN!", Action.CHANGE_SCENE_TO_GAME, Action.CHANGE_SCENE_TO_MENU, Action.EXIT_APP, SceneType.VICTORY);
    }
}

