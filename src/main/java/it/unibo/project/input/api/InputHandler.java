package it.unibo.project.input.api;

/**
 * The InputHandler interface defines methods for handling input actions.
 */
public interface InputHandler {

    /**
     * Executes the specified action immediately.
     *
     * @param action The action to be executed.
     */
    void executeAction(Action action);

    /**
     * Stores the specified action.
     * This method is typically used for player movements in the GameScene.
     * It stores the action only if none is already stored.
     *
     * @param action The action to be stored.
     */
    void storeAction(Action action);

    /**
     * Executes the stored action.
     */
    void executeStoredAction();

    /**
     * Clears the stored action.
     */
    void clearStoredAction();
}
