package it.unibo.project.input.impl;

import it.unibo.project.input.api.Action;
import it.unibo.project.input.api.InputHandler;

public class MenuInputHandler implements InputHandler {

    @Override
    public void executeAction(final Action action) {
        // TODO Auto-generated method stub
        switch(action){
            case CHANGE_SCENE_TO_GAME:
                new SharedInputHandler().executeAction(Action.CHANGE_SCENE_TO_GAME);
                break;
            case CHANGE_SCENE_TO_MENU:
                new SharedInputHandler().executeAction(Action.CHANGE_SCENE_TO_MENU);
                break;
            //case CHANGE_SCENE_TO_OVER:
                //new SharedInputHandler().executeAction(Action.CHANGE_SCENE_TO_OVER);
                //break;
            case EXIT_APP:
                new SharedInputHandler().executeAction(Action.EXIT_APP);
                break;
            default:
                break;
        }
        throw new UnsupportedOperationException("Unimplemented method 'executeAction'");
    }

    @Override
    public void storeAction(final Action action) {
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
