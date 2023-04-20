package it.unibo.project.view.api;

import it.unibo.project.controller.core.api.SceneType;

/**
 * {@code factory} for creating {@linkplain Scene}.
 */
public interface SceneFactory {
    /**
     * @param sceneType specifies which type of {@linkplain Scene} to create
     * @return a new {@linkplain Scene} object
     */
    Scene createScene(SceneType sceneType);
}
