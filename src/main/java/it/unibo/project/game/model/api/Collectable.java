package it.unibo.project.game.model.api;

/**
 * Interface {@code Collectable}, contain the get type of an collectavle.
 * 
 */
public interface Collectable extends Entity {
    /**
     * Called for get the type of a collectable.
     * @return a CollectableType that represent the type of a collectable.
     */
    CollectableType getType();
}
