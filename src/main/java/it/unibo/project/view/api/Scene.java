package it.unibo.project.view.api;

import javax.swing.JPanel;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.input.api.InputHandler;

/**
 * class {@code Scene} draw the various different scenes.
 * 
 * @see {@linkplain Window}
 */
public interface Scene {
    /**
     * @return {@linkplain JPanel} needed by {@linkplain Window} to display scene.
     * 
     * @implNote {@code [WARNING]}: to be used {@code ONLY} in the {@code View},
     *           since it's implementation dependent (<b>wouldn't work if java swing
     *           was changed with javaFX</b>)
     */
    JPanel getPanel();

    /**
     * redraw the scene panel.
     */
    void update();

    /**
     * @param sceneType of inputHandler
     * @return {@linkplain InputHandler}
     */
    InputHandler getInputHandler(SceneType sceneType);
}
