package it.unibo.project.input.impl;

import it.unibo.project.input.api.Action;
import it.unibo.project.input.api.InputHandler;

//class to use in shared action type, like change of scene, or exit button
public class SharedInputHandler implements InputHandler {


    @Override
    public void executeAction(Action action) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeAction'");
    }

    @Override
    public void storeAction(Action action) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'storeAction'");
    }

    @Override
    public void executeStoredAction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeStoredAction'");
    }

    @Override
    public void clearStoredAction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clearStoredAction'");
    }
    
}