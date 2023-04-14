package it.unibo.project.input.impl;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.input.api.Action;
import it.unibo.project.input.api.InputHandler;

//class to use in shared action type, like change of scene, or exit button
public class SharedInputHandler implements InputHandler {

    @Override
    public void executeAction(final Action action) {
        switch (action) {
            case EXIT_APP:
                System.exit(0);
                break;
            case CHANGE_SCENE_TO_MENU:
                LauncherImpl.LAUNCHER.setScene(SceneType.MENU);
                break;
            case CHANGE_SCENE_TO_SHOP:
                LauncherImpl.LAUNCHER.setScene(SceneType.SHOP);
                break;
            case CHANGE_SCENE_TO_GAME:
                LauncherImpl.LAUNCHER.setScene(SceneType.GAME);
                break;
            case CHANGE_SCENE_TO_OVER:
                LauncherImpl.LAUNCHER.setScene(SceneType.OVER);
                break;
            default:
                throw new UnsupportedOperationException("wrong action type passed");
        }
    }

    @Override
    public void storeAction(final Action action) {
        throw new UnsupportedOperationException("not needed method");
    }

    @Override
    public void executeStoredAction() {
        throw new UnsupportedOperationException("not needed method");
    }

    @Override
    public void clearStoredAction() {
        throw new UnsupportedOperationException("not needed method");
    }

}
