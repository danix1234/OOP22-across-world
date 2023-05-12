package it.unibo.project.controller.core.impl;

import java.awt.Toolkit;
import java.util.List;

import it.unibo.project.controller.core.api.Difficulty;
import it.unibo.project.controller.core.api.Launcher;
import it.unibo.project.controller.core.api.Loader;
import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.engine.api.GameEngine;
import it.unibo.project.controller.engine.impl.GameEngineFactoryImpl;
import it.unibo.project.game.model.api.BackgroundCell;
import it.unibo.project.game.model.api.Collectable;
import it.unibo.project.game.model.api.GameStat;
import it.unibo.project.game.model.api.GameWorld;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.Player;
import it.unibo.project.game.model.impl.GameWorldFactoryImpl;
import it.unibo.project.input.api.InputHandler;
import it.unibo.project.utility.Vector2D;
import it.unibo.project.view.api.Scene;
import it.unibo.project.view.api.Window;
import it.unibo.project.view.impl.SceneFactoryImpl;
import it.unibo.project.view.impl.WindowFactoryImpl;

/**
 * class {@linkplain LauncherImpl}, implements {@linkplain Launcher}, and it's a
 * {@code singleton} ({@link #LAUNCHER}).
 */
public final class LauncherImpl implements Launcher {
    /**
     * {@code singleton} of {@linkplain Launcher} class.
     */
    public static final Launcher LAUNCHER = new LauncherImpl();
    /** {@code width} in pixel of window. */
    public static final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    /** {@code height} in pixel of window. */
    public static final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    /** {@code quantity} of cell in {@code orizontal} direction. */
    public static final int ORIZ_CELL = 15;
    /** {@code quantity} of cell in {@code vertical} direction. */
    public static final int VERT_CELL = HEIGHT / (WIDTH / ORIZ_CELL) + 1;
    /** {@code dimension} of one side of each cell, in {@code pixel}. */
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

    /**
     * choose if you want to translate pixels to the left to avoid obstacle
     * "teleportation".
     * 
     * @implNote put it to false if it generates problems
     */
    public static final boolean TRANSLATE_PIXELS = true;

    /**
     * choose if you want to have random obstacle speed.
     * 
     * @implNote put it to false if it generates problems
     */
    public static final boolean RANDOMIZE_SPEED = true;

    /**
     * choose if you want to show the hitbox of all moving obstacles.
     * 
     * @implNote put it to false if it generates problems
     */
    public static final boolean ENABLE_HITBOX = !false;

    private static final int TRANSLATION_TO_SX = TRANSLATE_PIXELS ? -CELL_DIM : 0;

    private final Window window = new WindowFactoryImpl().createWindow();
    private final GameEngine gameEngine = new GameEngineFactoryImpl().createGameEngine();
    private final GameWorld gameWorld = new GameWorldFactoryImpl().createGameWorld();
    private final Loader loader = new LoaderImpl();

    private SceneType sceneType = SceneType.MENU;
    private Difficulty difficulty = Difficulty.NORMAL;

    private LauncherImpl() {
    }

    @Override
    public synchronized SceneType getSceneType() {
        return this.sceneType;
    }

    @Override
    public synchronized Scene getScene() {
        return this.window.getScene();
    }

    @Override
    public synchronized void setScene(final SceneType sceneType) {
        this.sceneType = sceneType;
        this.window.setScene(new SceneFactoryImpl().createScene(this.sceneType));
    }

    @Override
    public synchronized Difficulty getDifficulty() {
        return this.difficulty;
    }

    @Override
    public synchronized void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public synchronized void start() {
        setScene(this.sceneType);
    }

    @Override
    public synchronized void closeWindow() {
        this.window.close();
    }

    @Override
    public synchronized InputHandler getInputHandler(SceneType sceneType) {
        return getScene().getInputHandler(sceneType);
    }

    @Override
    public synchronized Player getPlayer() {
        return this.gameWorld.getPlayer();
    }

    @Override
    public synchronized List<BackgroundCell> getBackgroundCells() {
        return this.gameWorld.getBackgroundCells();
    }

    @Override
    public synchronized List<Collectable> getCollectables() {
        return this.gameWorld.getCollectables();
    }

    @Override
    public synchronized List<Obstacle> getObstacles() {
        return this.gameWorld.getObstacles();
    }

    @Override
    public synchronized Loader getLoader() {
        return this.loader;
    }

    @Override
    public synchronized void saveAndCloseWindow() {
        this.loader.saveStatOnFile(this.gameWorld.getGameStat());
        this.closeWindow();
    }

    @Override
    public synchronized void showWindow() {
        this.window.show();
    }

    @Override
    public synchronized void loadMap() {
        this.gameWorld.loadMap();
    }

    @Override
    public synchronized GameStat getGameStat() {
        return this.gameWorld.getGameStat();
    }

    @Override
    public synchronized void movePlayerIfPossible(int x, int y) {
        this.gameWorld.getGameLogic().getMovementLogic().movePlayer(x, y);
    }

    @Override
    public synchronized void startEngine() {
        this.gameEngine.start();
    }

    @Override
    public void moveDynamicObstacles() {
        this.gameWorld.getGameLogic().getMovementLogic().moveObstacle();
    }

    @Override
    public double convertCellToPixelPos(Vector2D cellPos) {
        return getObstaclePixelX(cellPos.getX() * CELL_DIM);
    }

    @Override
    public Vector2D convertPixelToCellPos(double pixelX, int cellY) {
        double x = getActualPixelX(pixelX) / CELL_DIM;
        if (pixelX % CELL_DIM > (CELL_DIM / 2)) {
            x++;
        }
        return new Vector2D((int) x, cellY);
    }

    @Override
    public double getActualPixelX(double obstaclePixelX) {
        return obstaclePixelX + TRANSLATION_TO_SX;
    }

    @Override
    public double getObstaclePixelX(double actualPixelX) {
        return actualPixelX - TRANSLATION_TO_SX;
    }
}
