package it.unibo.project.controller.core.impl;

import it.unibo.project.controller.core.api.Launcher;
import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.engine.api.GameEngine;
import it.unibo.project.controller.engine.impl.GameEngineFactoryImpl;
import it.unibo.project.game.model.api.GameWorld;
import it.unibo.project.game.model.impl.GameWorldFactoryImpl;
import it.unibo.project.view.api.Window;
import it.unibo.project.view.impl.SceneFactoryImpl;
import it.unibo.project.view.impl.WindowFactoryImpl;

public class LauncherImpl implements Launcher {
    private final Window window = new WindowFactoryImpl().createWindow();
    private final GameEngine gameEngine = new GameEngineFactoryImpl().createGameEngine();
    private final GameWorld gameWorld = new GameWorldFactoryImpl().createGameWorld();
    private SceneType sceneType = SceneType.MENU;

    @Override
    public Window getWindow() {
        return this.window;
    }

    @Override
    public GameEngine getGameEngine() {
        return this.gameEngine;
    }

    @Override
    public GameWorld getGameWorld() {
        return this.gameWorld;
    }

    @Override
    public SceneType getSceneType() {
        return this.sceneType;
    }

    @Override
    public void setSceneType(SceneType newSceneType) {
        this.sceneType = newSceneType;
    }

    @Override
    public void start() {
        window.setScene(new SceneFactoryImpl().createScene(this.sceneType));
        window.show();
    }

}
