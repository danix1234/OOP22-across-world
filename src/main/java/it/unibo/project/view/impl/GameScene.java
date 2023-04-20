package it.unibo.project.view.impl;

import java.awt.Toolkit;
import java.util.List;

import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.game.model.api.Entity;
import it.unibo.project.utility.Vector2D;
import it.unibo.project.view.api.AbstractScene;

public class GameScene extends AbstractScene {
    public static final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public static final int ORIZ_CELL = 15;
    public static final int VERT_CELL = (HEIGHT / (WIDTH / ORIZ_CELL)) + 1;
    public static final int CELL_DIM = WIDTH / ORIZ_CELL;
    public static final int TOP_CELL_DELTA = VERT_CELL / 2;
    public static final int BOTTOM_CELL_DELTA = VERT_CELL - TOP_CELL_DELTA - 1;

    private int minCell() {
        return LauncherImpl.LAUNCHER.getPlayer().getPosition().getY() - BOTTOM_CELL_DELTA;
    }

    private int maxCell() {
        return LauncherImpl.LAUNCHER.getPlayer().getPosition().getY() + TOP_CELL_DELTA;
    }

    private boolean checkVertPos(Vector2D vector) {
        return vector.getY() >= minCell() && vector.getY() <= maxCell();
    }

    private <X extends Entity> List<X> filterEntityToDraw(final List<X> entityList) {
        return entityList
                .stream()
                .filter(coll -> checkVertPos(coll.getPosition()))
                .toList();
    }

    @Override
    public void update() {

    }

}
