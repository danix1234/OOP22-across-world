package it.unibo.project.game.logic.impl;

import java.util.Optional;
import java.util.TimerTask;

import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.game.logic.api.HandlePowerup;
import it.unibo.project.game.model.api.CollectableType;

/**
 * class {@code HandlePowerupImpl} implements {@linkplain HandlePowerup}.
 */
public class HandlePowerupImpl implements HandlePowerup {
    private Optional<CollectableType> powerUpType = Optional.empty();

    @Override
    public synchronized void addPowerUp(final CollectableType type) {
        this.powerUpType = Optional.of(type);
        TimerTask timerPowerUp = new TimerTask() {

            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    clearPowerUp();
                } catch (final InterruptedException e) {
                    LauncherImpl.LAUNCHER.closeWindow();
                }
            }
        };

        new Thread(timerPowerUp).start();
    }

    @Override
    public synchronized Optional<CollectableType> getCurrentPowerUp() {
        return powerUpType;
    }

    @Override
    public synchronized void clearPowerUp() {
        powerUpType = Optional.empty();
    }
}
