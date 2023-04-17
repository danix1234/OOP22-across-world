package it.unibo.project.controller.core.impl;

import it.unibo.project.controller.core.api.Difficulty;
import it.unibo.project.controller.core.api.Launcher;
import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.input.api.InputHandler;
import it.unibo.project.view.api.Scene;
import it.unibo.project.view.api.Window;
import it.unibo.project.view.impl.SceneFactoryImpl;
import it.unibo.project.view.impl.WindowFactoryImpl;

public final class LauncherImpl implements Launcher {
    public static final Launcher LAUNCHER = new LauncherImpl();

    private final Window window = new WindowFactoryImpl().createWindow();
    // uncomment when are gonna be needed
    // private final GameEngine gameEngine = new
    // GameEngineFactoryImpl().createGameEngine();
    // private final GameWorld gameWorld = new
    // GameWorldFactoryImpl().createGameWorld();
    private SceneType sceneType = SceneType.MENU;
    private Difficulty difficulty = Difficulty.NORMAL;

    private LauncherImpl() {
    }

    @Override
    public synchronized SceneType getSceneType() {
        return this.sceneType;
    }

    @Override
    public Scene getScene() {
        return this.window.getScene();
    }

    @Override
    public synchronized void setScene(final SceneType sceneType) {
        this.sceneType = sceneType;
        this.window.setScene(new SceneFactoryImpl().createScene(this.sceneType));
    }

    @Override
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;

    }

    @Override
    public void start() {
        setScene(this.sceneType);
    }

    @Override
    public void closeWindow() {
        this.window.close();
    }

    @Override
    public InputHandler geInputHandler(SceneType sceneType) {
        return getScene().getInputHandler(sceneType);
    }

}
