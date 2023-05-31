package it.unibo.project.view.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.swing.JPanel;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.input.api.InputHandler;
import it.unibo.project.input.impl.GameInputHandler;
import it.unibo.project.input.impl.GameOverInputHandler;
import it.unibo.project.input.impl.MenuInputHandler;
import it.unibo.project.input.impl.ShopInputHandler;
import it.unibo.project.input.impl.VictoryInputHandler;
import it.unibo.project.view.api.Scene;

/**
 * class {@code AbstractScene} for respecting {@code D.R.Y.} principle and
 * implementing methods common for all Scene Implementation.
 */
public abstract class AbstractScene implements Scene {
    private JPanel scenePanel;
    private final Map<SceneType, InputHandler> inputHandlers = new HashMap<>();

    /**
     * AbstractScene {@code constructor}.
     */
    public AbstractScene() {
        this.inputHandlers.put(SceneType.MENU, new MenuInputHandler());
        this.inputHandlers.put(SceneType.SHOP, new ShopInputHandler());
        this.inputHandlers.put(SceneType.GAME, new GameInputHandler());
        this.inputHandlers.put(SceneType.OVER, new GameOverInputHandler());
        this.inputHandlers.put(SceneType.VICTORY, new VictoryInputHandler());
    }

    @Override
    public final JPanel getPanel() {
        return Optional.ofNullable(this.scenePanel).orElseThrow();
    }

    @Override
    public final InputHandler getInputHandler(final SceneType sceneType) {
        return this.inputHandlers.get(sceneType);
    }

    /**
     * setter method to change {@code panel} which will be store, and given to
     * {@linkplain Window} throw the {@link #getPanel()} method.
     * 
     * @param newPanel new panel, replacing old one
     */
    protected final void setPanel(final JPanel newPanel) {
        this.scenePanel = newPanel;
    }
}
