package it.unibo.project.view.api;

import javax.swing.JPanel;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.input.api.InputHandler;

public interface Scene {
    JPanel getPanel();

    void update();

    InputHandler getInputHandler(SceneType sceneType);
}
