package it.unibo.project.game.model.api;

/**
 * Enum {@code BackgroundCellType}, to define every type of background cell that can be used in the map.
 */
public enum BackgroundCellType {
    /**
     * grass background cell.
     */
    GRASS,
    /**
     * road background cell.
     */
    ROAD,
    /**
     * water background cell.
     */
    WATER,
    /**
     * rail background cell.
     */
    RAIL,
    /**
     * bicyclelane (with grass on border) background cell.
     */
    BICYCLELANE_GRASS,
    /**
     * bicyclelane (with sand on border) background cell.
     */
    BICYCLELANE_SAND,
    /**
     * sand background cell.
     */
    SAND,
    /**
     * hard road background cell.
     */
    HARDROAD,
    /**
     * hard rail background cell.
     */
    HARDRAIL,
    /**
     * dirt background cell.
     */
    DIRT,
    /**
     * lava background cell.
     */
    LAVA,
    /**
     * fire background cell.
     */
    FIRE,
    /**
     * finish line background cell.
     */
    FINISHLINE;
}
