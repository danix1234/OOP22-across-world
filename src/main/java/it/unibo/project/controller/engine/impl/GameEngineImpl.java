package it.unibo.project.controller.engine.impl;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.controller.engine.api.GameEngine;

public class GameEngineImpl implements GameEngine {
    public static final int FPS = 60;
    public static final int SEC_IN_NANO_SEC = 1_000_000_000;
    public static final int MILLI_SEC_IN_NANO_SEC = 1_000_000;
    public static final int FRAME_PER_PLAYER_MOVEMENT = 12;

    private Thread gameEngineThread = new Thread(this::engine);
    private int playerFrameCounter = 0;

    @Override
    public void start() {
        this.gameEngineThread.start();
    }

    private void engine() {
        double drawInterval = SEC_IN_NANO_SEC / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        double timeLeft;

        while (true) {
            // exit thread if not in game scene
            if (LauncherImpl.LAUNCHER.getSceneType() != SceneType.GAME) {
                return;
            }

            // engine
            processInput();
            updateGame();
            render();

            // handles fps
            try {
                timeLeft = nextDrawTime - System.nanoTime();
                timeLeft = Double.max(0, timeLeft);
                Thread.sleep((long) (timeLeft / MILLI_SEC_IN_NANO_SEC), (int) (timeLeft % MILLI_SEC_IN_NANO_SEC));
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // limits movement player to every n frames
            this.playerFrameCounter = (this.playerFrameCounter + 1) % FRAME_PER_PLAYER_MOVEMENT;
        }
    }

    @Override
    public void processInput() {
        if (this.playerFrameCounter == 0){
            LauncherImpl.LAUNCHER.geInputHandler(SceneType.GAME).executeStoredAction();
        }
    }

    @Override
    public void updateGame() {
        
    }

    @Override
    public void render() {
        LauncherImpl.LAUNCHER.getScene().update();
    }

}
