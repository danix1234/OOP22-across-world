package it.unibo.project.game.logic.api;

import java.util.List;

import it.unibo.project.game.model.api.CollectableType;

/**
 * Interface {@code HandlePowerup} contain methods to manage power up.
 */
public interface HandlePowerup {
    /**
     * called to add powerup to the powerups list.
     * 
     * @param type of powerup that will be added to the list
     */
    void addPowerUp(CollectableType type);

    /**
     * called to get the powerups that are active at this moment.
     * 
     * @return List<CollectableType> that contains all collectables active now
     */
    List<CollectableType> getCurrentPowerUp();

    /**
     * called to clear the list of powerups.
     */
    void clearPowerUp();
}
