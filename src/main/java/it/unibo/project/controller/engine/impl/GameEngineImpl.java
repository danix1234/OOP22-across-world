package it.unibo.project.controller.engine.impl;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

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
    public static final int FRAME_PER_PLAYER_MOVEMENT = 12;

    private static final int SEC_IN_NANO_SEC = 1_000_000_000;
    private static final int MILLI_SEC_IN_NANO_SEC = 1_000_000;

    private int playerFrameCounter;

    @Override
    public final void start() {
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
            try {
                SwingUtilities.invokeAndWait(() -> {
                    processInput();
                    updateGame();
                    render();
                });
            } catch (InvocationTargetException | InterruptedException e) {
                LauncherImpl.LAUNCHER.closeWindow();
            }

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
    public final void processInput() {
        if (this.playerFrameCounter == 0) {
            LauncherImpl.LAUNCHER.getInputHandler(SceneType.GAME).executeStoredAction();
        }
    }

    @Override
    public final void updateGame() {

    }

    @Override
    public final void render() {
        LauncherImpl.LAUNCHER.getScene().update();
    }

}
