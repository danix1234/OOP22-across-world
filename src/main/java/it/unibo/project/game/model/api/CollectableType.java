package it.unibo.project.game.model.api;

import java.util.Optional;

/**
 * Enum {@code CollectableType}, to define every type of collectable that can be used.
 */
public enum CollectableType {
    /**
     * coin collectable type.
     */
    COIN(true, false, Optional.of(1)),
    /**
     * powerup coin multiplier collectable type, multiply the number of coin collected when it's active.
     */
    POWERUP_COIN_MULTIPLIER(false, true, Optional.empty()),
    /**
     * powerup coin magnet collectable type, allows to get coins in a range of n (usually 1) cells around the player.
     */
    POWERUP_COIN_MAGNET(false, true, Optional.empty()),
    /**
     * powerup immortality collectable type, player will immortal (don't die if collides whit a moving obstacle).
     */
    POWERUP_IMMORTALITY(false, true, Optional.empty());

    private final boolean isCoin;
    private final boolean isPowerUp;
    private final Optional<Integer> coinValue;

    CollectableType(final boolean isCoin, final boolean isPowerUp, final Optional<Integer> coinValue) {
        this.isCoin = isCoin;
        this.isPowerUp = isPowerUp;
        this.coinValue = coinValue;
    }

    /**
     * called to know if is a coin type.
     * @return boolean that is set true if the collectable is a coin
     */
    public boolean isCoin() {
        return this.isCoin;
    }

    /**
     * called to know if is a powerup type.
     * @return boolean that is set true if the collectable is a powerup
     */
    public boolean isPowerUp() {
        return this.isPowerUp;
    }

    /**
     * called to get the value of coin.
     * @return Optional<Integer> containing the value of coin
     */
    public Optional<Integer> getCoinValue() {
        return this.coinValue;
    }
}
