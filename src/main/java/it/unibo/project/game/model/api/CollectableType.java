package it.unibo.project.game.model.api;

import java.util.Optional;

public enum CollectableType {
    COIN(true, false, Optional.of(1)),
    POWERUP_COIN_MULTIPLIER(false, true, Optional.empty()),
    POWERUP_COIN_MAGNET(false, true, Optional.empty()),
    POWERUP_IMMORTALITY(false, true, Optional.empty());

    private final boolean isCoin;
    private final boolean isPowerUp;
    private final Optional<Integer> coinValue;

    private CollectableType(final boolean isCoin, final boolean isPowerUp, final Optional<Integer> coinValue) {
        this.isCoin = isCoin;
        this.isPowerUp = isPowerUp;
        this.coinValue = coinValue;
    }

    public boolean isCoin() {
        return this.isCoin;
    }

    public boolean isPowerUp() {
        return this.isPowerUp;
    }

    public Optional<Integer> getCoinValue() {
        return this.coinValue;
    }
}
