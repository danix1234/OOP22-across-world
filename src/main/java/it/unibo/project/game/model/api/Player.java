package it.unibo.project.game.model.api;

/**
 * Interface {@code Player}, contain the get max distance method that the player has reached.
 * 
 */
public interface Player extends Entity {
    /**
     * Called for get max distance that the player has reached.
     * @return an int that represent max distance.
     */
    int getMaxDistance();
}
