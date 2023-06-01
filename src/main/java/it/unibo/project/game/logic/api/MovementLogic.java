package it.unibo.project.game.logic.api;

/**
 * Interface {@code MovementLogic}, manage the movement of the movement
 * entities.
 */
public interface MovementLogic {
    // called by input handler when it recieves an input
    /**
     * called to move the entity player.
     * 
     * @param x value that will have the x coordinate of player
     * @param y value that will have the y coordinate of player
     */
    void movePlayer(int x, int y);

    // called by the game engine every single time
    // also defines the direction and speed of the obstacles
    // need to change location inside the obstacle object
    /**
     * called to move all obstales that are movables.
     */
    void moveObstacle();

    /**
     * called to set the speed multipier for the obstacles speed.
     * 
     * @param value that will be multipied with the normal speed of the obstacles
     */
    void setSpeedMultiplier(double value);
}
