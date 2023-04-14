package it.unibo.project.view.api;

import it.unibo.project.controller.core.api.SceneType;

public interface SceneFactory {
    Scene createScene(SceneType sceneType);
}
