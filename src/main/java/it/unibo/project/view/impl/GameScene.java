package it.unibo.project.view.impl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JPanel;

import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.game.model.api.Entity;
import it.unibo.project.utility.Vector2D;
import it.unibo.project.view.api.AbstractScene;

/**
 * Class {@code GameScene} takes care of rendering all entities on window.
 */
public class GameScene extends AbstractScene {
    /**
     * {@code width} in pixel of window.
     */
    public static final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    /**
     * {@code height} in pixel of window.
     */
    public static final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    /**
     * {@code quantity} of cell in {@code orizontal} direction.
     */
    public static final int ORIZ_CELL = 15;
    /**
     * {@code quantity} of cell in {@code vertical} direction.
     */
    public static final int VERT_CELL = (HEIGHT / (WIDTH / ORIZ_CELL)) + 1;
    /**
     * {@code dimension} of one side of each cell, in {@code pixel}.
     */
    public static final int CELL_DIM = WIDTH / ORIZ_CELL;
    /**
     * {@code quantity} of lines of orizontal cell to be loaded {@code above} the
     * player current one.
     */
    public static final int TOP_CELL_DELTA = VERT_CELL / 2;
    /**
     * {@code quantity} of lines of orizontal cell to be loaded {@code below} the
     * player current one.
     */
    public static final int BOTTOM_CELL_DELTA = VERT_CELL - TOP_CELL_DELTA - 1;

    private final Panel panel = new Panel();

    /**
     * {@code GameScene} constructor.
     */
    public GameScene() {
        setPanel(this.panel);
    }

    private int minCell() {
        return LauncherImpl.LAUNCHER.getPlayer().getPosition().getY() - BOTTOM_CELL_DELTA;
    }

    private int maxCell() {
        return LauncherImpl.LAUNCHER.getPlayer().getPosition().getY() + TOP_CELL_DELTA;
    }

    private boolean checkVertPos(final Vector2D vector) {
        return vector.getY() >= minCell() && vector.getY() <= maxCell();
    }

    // TODO temporary suppress, to be removed
    @SuppressWarnings("unused")
    private <X extends Entity> List<X> filterEntityToDraw(final List<X> entityList) {
        return entityList
                .stream()
                .filter(coll -> checkVertPos(coll.getPosition()))
                .toList();
    }

    @Override
    public final void update() {
        this.panel.repaint();
    }

    private class Panel extends JPanel {

        // TODO temporary suppress, to be removed
        @SuppressWarnings("unused")
        private void drawCell(final Image image, final Vector2D cellPos, final Graphics g) {
            final int x = 128 * cellPos.getX();
            final int y = 128 * (VERT_CELL - cellPos.getY() - 1);
            g.drawImage(image, x, y, CELL_DIM, CELL_DIM, null);
        }

        @Override
        protected final void paintComponent(final Graphics g) {
            super.paintComponent(g);
        }
    }
}
