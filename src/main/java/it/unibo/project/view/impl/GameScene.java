package it.unibo.project.view.impl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import it.unibo.project.controller.core.api.Launcher;
import it.unibo.project.controller.core.api.Loader;
import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.input.api.Action;
import it.unibo.project.utility.RandomizeLine;
import it.unibo.project.utility.Vector2D;

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
        setPanel(this.panel);
        addKeyBindings();

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

    @Override
    public final void update() {
        this.panel.repaint();
    }

    private class Panel extends JPanel {
        private static final long serialVersionUID = 0L;

        private int minCell() {
            return LauncherImpl.LAUNCHER.getPlayer().getPosition().getY() - LauncherImpl.BOTTOM_CELL_DELTA;
        }

        private int maxCell() {
            return LauncherImpl.LAUNCHER.getPlayer().getPosition().getY() + LauncherImpl.TOP_CELL_DELTA;
        }

        private boolean checkVertPos(final Vector2D vector) {
            return vector.getY() >= minCell() && vector.getY() <= maxCell();
        }

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

            if (LauncherImpl.ENABLE_HITBOX) {
                drawHitBox(cellPos, g);
            }
        }

        private void drawHitBox(final Vector2D cellPos, final Graphics g) {
            final int x = LauncherImpl.CELL_DIM * cellPos.getX();
            final int y = LauncherImpl.CELL_DIM * (LauncherImpl.VERT_CELL - posRelativeToPlayer(cellPos));
            g.drawRect(x, y, LauncherImpl.CELL_DIM, LauncherImpl.CELL_DIM);
        }

        @Override
        protected final void paintComponent(final Graphics g) {
            super.paintComponent(g);

            final var player = launcher.getPlayer();
            final float fontSpec = 30.0F;
            final int playerImageX1 = 10;
            final int playerImageY1 = 10;
            final int playerImageX2 = 40;
            final int playerImageY2 = 40;
            final int scoreStringX = 50;
            final int scoreStringY = 40;
            final int collectablesImageX1 = 10;
            final int collectablesImageY1 = 60;
            final int collectablesImageX2 = 40;
            final int collectablesImageY2 = 40;
            final int coinCollectedX = 50;
            final int coinCollectedY = 90;
            final int collectableLogoCollectedX1 = 10;
            final int collectableLogoCollectedY1 = 110;
            final int collectableLogoCollectedX2 = 40;
            final int collectableLogoCollectedY2 = 40;
            final int pixelSpacing = 40;

            launcher.getBackgroundCells().stream()
                    .forEach(cell -> drawCell(
                            randomizeLine.getLineRandomElement(
                                    loader.getBackgroundCellSprites(cell.getType()),
                                    cell.getPosition().getY()),
                            cell.getPosition(),
                            g));

            launcher.getObstacles().stream()
                    .filter(Predicate.not(Obstacle::isMovable))
                    .forEach(cell -> drawCell(
                            randomizeLine.getLineRandomElement(
                                    loader.getObstacleSprites(cell.getType()),
                                    cell.getPosition().getY()),
                            cell.getPosition(),
                            g));

            launcher.getCollectables().stream()
                    .forEach(cell -> drawCell(
                            randomizeLine.getLineRandomElement(
                                    loader.getCollectablesSprites(cell.getType()),
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
                            LauncherImpl.LAUNCHER.getActualPixelX(cell.getPixelPosition()),
                            g));

            final var trunkCollided = launcher.getCheckCollision().checkTrunkCollision(player.getPosition());
            if (trunkCollided.isPresent()) {
                final var pixelPos = launcher.getActualPixelX(trunkCollided.get().getPixelPosition());
                drawPixels(playerSprite, player.getPosition(), pixelPos, g);
            } else {
                drawCell(playerSprite, player.getPosition(), g);
            }

            g.setFont(getFont().deriveFont(fontSpec));
            g.drawImage(playerSprite, playerImageX1, playerImageY1, playerImageX2, playerImageY2, null);
            g.drawString(Integer.toString(player.getMaxDistance() - 4), scoreStringX, scoreStringY);
            g.drawImage(loader.getCollectablesSprites(CollectableType.COIN).get(0), collectablesImageX1,
                    collectablesImageY1, collectablesImageX2, collectablesImageY2, null);
            g.drawString(Integer.toString(launcher.getGameStat().getCoins()), coinCollectedX, coinCollectedY);
            final var collectables = launcher.getHandlePowerup().getCurrentPowerUp().stream().distinct().toList();
            for (int i = 0; i < collectables.size(); i++) {
                g.drawImage(loader.getCollectablesSprites(collectables.get(i)).get(0),
                        collectableLogoCollectedX1 + pixelSpacing * i, collectableLogoCollectedY1,
                        collectableLogoCollectedX2, collectableLogoCollectedY2, null);
            }

            // needed because repaint method is draw on screen only when java swing wants
            Toolkit.getDefaultToolkit().sync();
        }

    }

    private void addKeyBindings() {
        final var inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        final var actionMap = panel.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "move up");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "move up");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "move down");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "move down");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "move left");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "move left");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "move right");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "move right");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "exit app");

        final var inputHandler = getInputHandler(SceneType.GAME);
        actionMap.put("move up", new GameAction(e -> inputHandler.storeAction(Action.MOVE_PLAYER_UP)));
        actionMap.put("move down", new GameAction(e -> inputHandler.storeAction(Action.MOVE_PLAYER_DOWN)));
        actionMap.put("move left", new GameAction(e -> inputHandler.storeAction(Action.MOVE_PLAYER_LEFT)));
        actionMap.put("move right", new GameAction(e -> inputHandler.storeAction(Action.MOVE_PLAYER_RIGHT)));
        actionMap.put("exit app", new GameAction(e -> inputHandler.executeAction(Action.EXIT_APP)));
    }

    @FunctionalInterface
    private interface GameActionFunctional {
        void actionPerformed(ActionEvent e);
    }

    private static class GameAction extends AbstractAction {
        private static final long serialVersionUID = 0L;
        private transient final GameActionFunctional gameActionFunctional;

        GameAction(final GameActionFunctional gameActionFunctional) {
            this.gameActionFunctional = gameActionFunctional;
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            this.gameActionFunctional.actionPerformed(e);
        }

    }
}
