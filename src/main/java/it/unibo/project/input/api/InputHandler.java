package it.unibo.project.input.api;

public interface InputHandler {

    // immediatly execute the action
    public void executeAction(Action action);

    // for player movements (GameScene)
    // this stores the action only if none is already stored
    public void storeAction(Action action);

    public void executeStoredAction();

    public void clearStoredAction();
}
