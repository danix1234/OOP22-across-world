package it.unibo.project.view.impl;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.input.api.Action;

public class GameOverScene extends AbstractGameEndScene {


    public GameOverScene() {
        super("GAME OVER!", Action.CHANGE_SCENE_TO_GAME, Action.CHANGE_SCENE_TO_MENU, Action.EXIT_APP, SceneType.OVER);
    }

 
}

