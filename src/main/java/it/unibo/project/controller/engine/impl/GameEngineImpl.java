package it.unibo.project.controller.engine.impl;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.controller.engine.api.GameEngine;

public class GameEngineImpl implements GameEngine {
    public static final int FPS = 60;
    public static final int SEC_IN_NANO_SEC = 1_000_000_000;
    public static final int MILLI_SEC_IN_NANO_SEC = 1_000_000;
    public static final int FRAME_PER_PLAYER_MOVEMENT = 12;

    private int playerFrameCounter;

    @Override
    public void start() {
        new Thread(this::engine).start();
    }

    private void engine() {
        final double drawInterval = SEC_IN_NANO_SEC / FPS;
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
            }

            // limits movement player to every n frames
            this.playerFrameCounter = (this.playerFrameCounter + 1) % FRAME_PER_PLAYER_MOVEMENT;
        }
    }

    @Override
    public void processInput() {
        if (this.playerFrameCounter == 0) {
            LauncherImpl.LAUNCHER.geInputHandler(SceneType.GAME).executeStoredAction();
        }
    }

    @Override
    public void updateGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateGame'");
    }

    @Override
    public void render() {
        LauncherImpl.LAUNCHER.getScene().update();
    }

}
