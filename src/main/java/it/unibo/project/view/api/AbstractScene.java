package it.unibo.project.view.api;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.input.api.InputHandler;
import it.unibo.project.input.impl.GameInputHandler;
import it.unibo.project.input.impl.GameOverInputHandler;
import it.unibo.project.input.impl.MenuInputHandler;
import it.unibo.project.input.impl.ShopInputHandler;

public abstract class AbstractScene implements Scene {
    private JPanel scenePanel;
    private Map<SceneType, InputHandler> inputHandlers = new HashMap<>();

    public AbstractScene() {
        this.inputHandlers.put(SceneType.MENU, new MenuInputHandler());
        this.inputHandlers.put(SceneType.SHOP, new ShopInputHandler());
        this.inputHandlers.put(SceneType.GAME, new GameInputHandler());
        this.inputHandlers.put(SceneType.OVER, new GameOverInputHandler());
    }

    @Override
    public JPanel getPanel() {
        return this.scenePanel;
    }

    @Override
    public InputHandler getInputHandler(SceneType sceneType) {
        return this.inputHandlers.get(sceneType);
    }

    protected void setPanel(JPanel newPanel) {
        this.scenePanel = newPanel;
    }
}
