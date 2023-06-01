package it.unibo.project.input.impl;

import it.unibo.project.input.api.Action;
import it.unibo.project.input.api.InputHandler;

public final class MenuInputHandler implements InputHandler {

    @Override
    public void executeAction(final Action action) {
        // to be changed if some action aren't already handled in SharedInputHandler
        new SharedInputHandler().executeAction(action);
    }

    @Override
    public void storeAction(final Action action) {
    }

    @Override
    public void executeStoredAction() {
    }

    @Override
    public void clearStoredAction() {
    }

}
