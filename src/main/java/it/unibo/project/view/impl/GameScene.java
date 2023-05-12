package it.unibo.project.view.impl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import it.unibo.project.controller.core.api.Launcher;
import it.unibo.project.controller.core.api.Loader;
import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.input.api.Action;
import it.unibo.project.utility.RandomizeLine;
import it.unibo.project.utility.Vector2D;
import it.unibo.project.view.api.AbstractScene;

/**
 * Class {@code GameScene} takes care of rendering all entities on window.
 */
public class GameScene extends AbstractScene {
    private final Panel panel = new Panel();
    private final Launcher launcher = LauncherImpl.LAUNCHER;
    private final Loader loader = LauncherImpl.LAUNCHER.getLoader();
    private final Image playerSprite;
    private final RandomizeLine randomizeLine = new RandomizeLine();

    /**
     * {@code GameScene} constructor.
     */
    public GameScene() {
        this.panel.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent arg0) {
                switch (arg0.getKeyCode()) {
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        getInputHandler(SceneType.GAME).storeAction(Action.MOVE_PLAYER_UP);
                        break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        getInputHandler(SceneType.GAME).storeAction(Action.MOVE_PLAYER_LEFT);
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        getInputHandler(SceneType.GAME).storeAction(Action.MOVE_PLAYER_DOWN);
                        break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        getInputHandler(SceneType.GAME).storeAction(Action.MOVE_PLAYER_RIGHT);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent arg0) {
            }

            @Override
            public void keyTyped(KeyEvent arg0) {
            }

        });
        setPanel(this.panel);

        final List<Image> playerSkins = this.loader.getPlayerSprites();
        final List<Boolean> unlockedSkins = launcher.getGameStat().getUnlockedSkins();
        final List<Image> unlockedSkinsImg = new ArrayList<>();
        for (int i = 0; i < unlockedSkins.size(); i++) {
            if (unlockedSkins.get(i)) {
                unlockedSkinsImg.add(playerSkins.get(i));
            }
        }
        this.playerSprite = loader.getElementRandom(unlockedSkinsImg);
    }

    private int minCell() {
        return LauncherImpl.LAUNCHER.getPlayer().getPosition().getY() - LauncherImpl.BOTTOM_CELL_DELTA;
    }

    private int maxCell() {
        return LauncherImpl.LAUNCHER.getPlayer().getPosition().getY() + LauncherImpl.TOP_CELL_DELTA;
    }

    private boolean checkVertPos(final Vector2D vector) {
        return vector.getY() >= minCell() && vector.getY() <= maxCell();
    }

    @Override
    public final void update() {
        this.panel.repaint();
    }

    private class Panel extends JPanel {
        private static final long serialVersionUID = 0L;

        private int posRelativeToPlayer(final Vector2D cellPos) {
            return cellPos.getY() - launcher.getPlayer().getPosition().getY() + LauncherImpl.BOTTOM_CELL_DELTA + 1;
        }

        private void drawCell(final Image image, final Vector2D cellPos, final Graphics g) {
            if (!checkVertPos(cellPos)) {
                return;
            }
            final int x = LauncherImpl.CELL_DIM * cellPos.getX();
            final int y = LauncherImpl.CELL_DIM * (LauncherImpl.VERT_CELL - posRelativeToPlayer(cellPos));
            g.drawImage(image, x, y, LauncherImpl.CELL_DIM, LauncherImpl.CELL_DIM, null);
        }

        private void drawPixels(final Image image, final Vector2D cellPos, final double pixelX, final Graphics g) {
            if (!checkVertPos(cellPos)) {
                return;
            }
            final int y = LauncherImpl.CELL_DIM * (LauncherImpl.VERT_CELL - posRelativeToPlayer(cellPos));
            g.drawImage(image, (int) pixelX, y, LauncherImpl.CELL_DIM, LauncherImpl.CELL_DIM, null);
        }

        @Override
        protected final void paintComponent(final Graphics g) {
            super.paintComponent(g);

            final var player = launcher.getPlayer();

            launcher.getBackgroundCells().stream()
                    .forEach(cell -> drawCell(
                            randomizeLine.getLineRandomElement(
                                    loader.getBackgroundCellSprites(cell.getType()),
                                    cell.getPosition().getY()),
                            cell.getPosition(),
                            g));

            launcher.getObstacles().stream()
                    .filter(obstacle -> !obstacle.isMovable())
                    .forEach(cell -> drawCell(
                            randomizeLine.getLineRandomElement(
                                    loader.getObstacleSprites(cell.getType()),
                                    cell.getPosition().getY()),
                            cell.getPosition(),
                            g));

            launcher.getObstacles().stream()
                    .filter(Obstacle::isMovable)
                    .forEach(cell -> drawPixels(
                            randomizeLine.getLineRandomElement(
                                    loader.getObstacleSprites(cell.getType()),
                                    cell.getPosition().getY()),
                            cell.getPosition(),
                            cell.getPixelPosition() - ((LauncherImpl.TRANSLATE_PIXELS)
                                    ? (LauncherImpl.CELL_DIM)
                                    : (0)),
                            g));

            launcher.getCollectables().stream()
                    .forEach(cell -> drawCell(
                            randomizeLine.getLineRandomElement(
                                    loader.getCollectablesSprites(cell.getType()),
                                    cell.getPosition().getY()),
                            cell.getPosition(),
                            g));

            drawCell(playerSprite, player.getPosition(), g);

            // needed because repaint method is draw on screen only when java swing wants
            Toolkit.getDefaultToolkit().sync();
        }

        /* needed to allow keyListener to work. */
        @Override
        public final boolean isFocusable() {
            return true;
        }

    }
}
