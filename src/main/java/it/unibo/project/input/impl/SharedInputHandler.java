package it.unibo.project.input.impl;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.input.api.Action;
import it.unibo.project.input.api.InputHandler;

/**
 * class {@code SharedInputHandler} is to be {@code decorated} and use to handle
 * common {@linkplain Action}:
 * <b>
 * {@code Action.CHANGE_SCENE_TO_*}, {@code Action.EXIT_APP}.
 * </b>
 */
public class SharedInputHandler implements InputHandler {

    @Override
    public final void executeAction(final Action action) {
        switch (action) {
            case EXIT_APP:
                LauncherImpl.LAUNCHER.saveAndCloseWindow();
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
    public final void storeAction(final Action action) {
        throw new UnsupportedOperationException("not needed method");
    }

    @Override
    public final void executeStoredAction() {
        throw new UnsupportedOperationException("not needed method");
    }

    @Override
    public final void clearStoredAction() {
        throw new UnsupportedOperationException("not needed method");
    }

}
