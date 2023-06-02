package it.unibo.project.input.impl;

import java.util.Optional;

import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.input.api.Action;
import it.unibo.project.input.api.InputHandler;

/** {@linkplain InputHandler} for {@code GameScene}. */
public final class GameInputHandler implements InputHandler {
    private Optional<Action> storedAction = Optional.empty();

    @Override
    public void executeAction(final Action action) {
        // to be changed if some action aren't already handled in SharedInputHandler
        new SharedInputHandler().executeAction(action);
    }

    @Override
    public void storeAction(final Action action) {
        if (this.storedAction.isEmpty()) {
            this.storedAction = Optional.ofNullable(action);
        }
    }

    @Override
    public void executeStoredAction() {
        this.storedAction.ifPresent(action -> {
            int x = LauncherImpl.LAUNCHER.getPlayer().getPosition().getX();
            int y = LauncherImpl.LAUNCHER.getPlayer().getPosition().getY();
            switch (action) {
                case MOVE_PLAYER_UP:
                    y++;
                    break;
                case MOVE_PLAYER_DOWN:
                    y--;
                    break;
                case MOVE_PLAYER_LEFT:
                    x--;
                    break;
                case MOVE_PLAYER_RIGHT:
                    x++;
                    break;
                default:
                    break;
            }
            LauncherImpl.LAUNCHER.movePlayerIfPossible(x, y);
        });
        clearStoredAction();
    }

    @Override
    public void clearStoredAction() {
        this.storedAction = Optional.empty();
    }

}
