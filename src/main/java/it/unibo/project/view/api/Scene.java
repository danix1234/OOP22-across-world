package it.unibo.project.view.api;

import javax.swing.JPanel;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.input.api.InputHandler;

public interface Scene {
    public JPanel getPanel();

    public void update();

    public InputHandler getInputHandler(SceneType sceneType);
}
