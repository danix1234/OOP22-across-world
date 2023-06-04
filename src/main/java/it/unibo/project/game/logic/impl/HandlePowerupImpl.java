package it.unibo.project.game.logic.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import it.unibo.project.game.logic.api.HandlePowerup;
import it.unibo.project.game.model.api.CollectableType;

/**
 * class {@code HandlePowerupImpl} implements {@linkplain HandlePowerup}.
 */
public final class HandlePowerupImpl implements HandlePowerup {
    private static final int POWERUP_DURATION = 10_000;

    private final List<CollectableType> powerupTypeList = new LinkedList<>();
    private long counter = Long.MIN_VALUE;

    @Override
    public synchronized void addPowerUp(final CollectableType type) {
        this.powerupTypeList.add(type);
        final TimerTask timerPowerUp = new TimerTask() {

            @Override
            public void run() {
                try {
                    final long clearId = getCounter();
                    Thread.sleep(POWERUP_DURATION);
                    SwingUtilities.invokeLater(() -> {
                        if (clearId == getCounter() && !powerupTypeList.isEmpty()) {
                            powerupTypeList.remove(0);
                        }
                    });
                } catch (final InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        };

        new Thread(timerPowerUp).start();
    }

    @Override
    public synchronized List<CollectableType> getCurrentPowerUp() {
        return List.copyOf(this.powerupTypeList);
    }

    @Override
    public synchronized void clearPowerUp() {
        increaseCounter();
        powerupTypeList.clear();
    }

    private synchronized long getCounter() {
        return this.counter;
    }

    private synchronized void increaseCounter() {
        this.counter++;
    }
}
