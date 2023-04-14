package it.unibo.project.input.api;

public interface InputHandler {

    // immediatly execute the action
    void executeAction(Action action);

    // for player movements (GameScene)
    // this stores the action only if none is already stored
    void storeAction(Action action);

    void executeStoredAction();

    void clearStoredAction();
}
