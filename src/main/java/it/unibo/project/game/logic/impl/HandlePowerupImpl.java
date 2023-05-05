package it.unibo.project.game.logic.impl;

import java.util.Optional;
import java.util.TimerTask;

import it.unibo.project.game.logic.api.HandlePowerup;
import it.unibo.project.game.model.api.CollectableType;

/**
 * class {@code HandlePowerupImpl} implements {@linkplain HandlePowerup}.
 */
public class HandlePowerupImpl implements HandlePowerup {
    private Optional<CollectableType> powerUpType;

    @Override
    public void addPowerUp(final CollectableType type) {
        this.powerUpType = Optional.of(type);
        TimerTask timerPowerUp = new TimerTask() {

            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
                clearPowerUp();
            }
        };
        timerPowerUp.run();
    }

    @Override
    public Optional<CollectableType> getCurrentPowerUp() {
        return powerUpType;
    }

    @Override
    public void clearPowerUp() {
        powerUpType = Optional.empty();
    }
}
