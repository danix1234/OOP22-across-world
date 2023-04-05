package it.unibo.project.game.logic.api;

import java.util.Optional;

import it.unibo.project.game.model.api.CollectableType;

public interface HandlePowerup {
    // save all collectables (but not coins, and not the one just picked up) inside a temporary list
    // then remove them from the GameWorld collectable list, 
    // then run the timer
    // then restore the saved collectables inside gameworld
    public void addPowerUp(CollectableType type); // add timer thread to remove powerup after 10 seconds

    public Optional<CollectableType> getCurrentPowerUp();

    public void clearPowerUp();
}
