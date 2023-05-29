package it.unibo.project.controller.engine.impl;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.controller.engine.api.GameEngine;

/**
 * class {@code GameEngineImpl} implements {@linkplain GameEngine}.
 */
public class GameEngineImpl implements GameEngine {
    /**
     * frames per second.
     */
    public static final int FPS = 60;
    /**
     * quantity of frames to wait before handling player movement.
     */
    public static final int FRAME_PER_PLAYER_MOVEMENT = 4;

    private static final int SEC_IN_NANO_SEC = 1_000_000_000;
    private static final int MILLI_SEC_IN_NANO_SEC = 1_000_000;

    private int playerFrameCounter;
    private boolean forceStop;

    @Override
    public final void start() {
        new Thread(this::engine).start();
    }

    @Override
    public final void processInput() {
        if (this.playerFrameCounter == 0) {
            LauncherImpl.LAUNCHER.getInputHandler(SceneType.GAME).executeStoredAction();
        }
    }

    @Override
    public final void updateGame() {
        LauncherImpl.LAUNCHER.moveDynamicObstacles();
    }

    @Override
    public final void render() {
        LauncherImpl.LAUNCHER.getScene().update();
    }

    private void engine() {
        final double drawInterval = SEC_IN_NANO_SEC / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        double timeLeft;

        while (true) {
            // exit thread if not in game scene or if forceStop method is called
            if (isEngineToBeStopped() || LauncherImpl.LAUNCHER.getSceneType() != SceneType.GAME) {
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
                LauncherImpl.LAUNCHER.closeWindow();
            }

            // limits movement player to every n frames
            this.playerFrameCounter = (this.playerFrameCounter + 1) % FRAME_PER_PLAYER_MOVEMENT;
        }
    }

    @Override
    public final synchronized void forceStop() {
        this.forceStop = true;
    }

    private synchronized boolean isEngineToBeStopped() {
        return this.forceStop;
    }
}
