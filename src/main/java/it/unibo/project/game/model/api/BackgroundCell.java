package it.unibo.project.game.model.api;

/**
 * Class {@code BackgroundCell}, contain the get type of a background cell.
 * 
 */
public interface BackgroundCell extends Entity {
    /**
     * Called for get the type of the relative beackground cell.
     * @return a BackgroundCellType  that represent the type of a background cell.
     */
    BackgroundCellType getType();
}
