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
            case NO_SAVE_EXIT_APP:
                LauncherImpl.LAUNCHER.closeWindow();
                break;
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
                LauncherImpl.LAUNCHER.getHandlePowerup().clearPowerUp();
                LauncherImpl.LAUNCHER.loadMap();
                LauncherImpl.LAUNCHER.startEngine();
                LauncherImpl.LAUNCHER.setScene(SceneType.GAME);
                break;
            case CHANGE_SCENE_TO_OVER:
                LauncherImpl.LAUNCHER.setScene(SceneType.OVER);
                break;
            case CHANGE_SCENE_TO_VICTORY:
                LauncherImpl.LAUNCHER.setScene(SceneType.VICTORY);
                break;
            default:
                break;
        }
    }

    @Override
    public final void storeAction(final Action action) {
    }

    @Override
    public final void executeStoredAction() {
    }

    @Override
    public final void clearStoredAction() {
    }

}
