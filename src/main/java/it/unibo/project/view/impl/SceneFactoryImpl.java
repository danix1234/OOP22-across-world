package it.unibo.project.view.impl;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.view.api.Scene;
import it.unibo.project.view.api.SceneFactory;

public class SceneFactoryImpl implements SceneFactory {

    @Override
    public Scene createScene(final SceneType sceneType) {
        return switch (sceneType) {
            case GAME -> new GameScene();
            case OVER -> new GameOverScene();
            case MENU -> new MenuScene();
            case SHOP -> new ShopScene();
        };
    }
}
