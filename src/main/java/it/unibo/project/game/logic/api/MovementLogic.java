package it.unibo.project.game.logic.api;

public interface MovementLogic {
    // called by input handler when it recieves an input
    public void movePlayer(int x, int y);

    // called by the game engine every single time
    // also defines the direction and speed of the obstacles
    // need to change location inside the obstacle object
    public void moveObstacle();
}
