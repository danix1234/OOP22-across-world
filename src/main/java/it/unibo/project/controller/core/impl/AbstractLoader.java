package it.unibo.project.controller.core.impl;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.project.controller.core.api.Difficulty;
import it.unibo.project.controller.core.api.Loader;
import it.unibo.project.game.model.api.BackgroundCell;
import it.unibo.project.game.model.api.BackgroundCellType;
import it.unibo.project.game.model.api.*;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.GameStat;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.ObstacleType;
import it.unibo.project.game.model.impl.PlayerImpl;
import it.unibo.project.utility.Vector2D;

/**
 * class with various methods for {@linkplain Loader} class.
 */
public abstract class AbstractLoader implements Loader {
    // UTILITY PATH

    /** file separator. */
    protected static final String FILE_SEP = "/";
    /** home directory of the user. */
    protected static final String USER_HOME_DIR = System.getProperty("user.home");
    /** resources directory. */
    protected static final String RESOURCE_DIR = "it" + FILE_SEP + "unibo" + FILE_SEP + "project";

    // RESOURCES DIRECTORIES

    /** sprite directory. */
    protected static final String SPRITE_DIR = RESOURCE_DIR + FILE_SEP + "sprite";
    /** maps directory. */
    protected static final String MAPS_DIR = RESOURCE_DIR + FILE_SEP + "maps";
    /** background sprites directory. */
    protected static final String BACKGROUND_DIR = SPRITE_DIR + FILE_SEP + "background";
    /** collectable sprites directory. */
    protected static final String COLLECTABLE_DIR = SPRITE_DIR + FILE_SEP + "collectable";
    /** obstacle sprites directory. */
    protected static final String OBSTACLE_DIR = SPRITE_DIR + FILE_SEP + "obstacle";
    /** player sprites directory. */
    protected static final String PLAYER_DIR = SPRITE_DIR + FILE_SEP + "player";
    /** default stat directory. */
    protected static final String DEFAULT_STAT_DIR = RESOURCE_DIR + FILE_SEP + "stats";
    /** stat directory in user home. */
    protected static final String STAT_DIR = USER_HOME_DIR + FILE_SEP + ".across_world";

    // RESOURCES FILES

    /** all collectable sprite file names. */
    protected static final Map<CollectableType, List<String>> COLLECTABLE_FILES = Map.of(
            CollectableType.COIN, List.of("coin.png"),
            CollectableType.POWERUP_COIN_MAGNET, List.of("powerup.png"),
            CollectableType.POWERUP_COIN_MULTIPLIER, List.of("powerup.png"),
            CollectableType.POWERUP_IMMORTALITY, List.of("powerup.png"));
    /** all background sprite file names. */
    protected static final Map<BackgroundCellType, List<String>> BACKGROUND_FILES = Map.of(
            BackgroundCellType.GRASS, List.of("grass.png"),
            BackgroundCellType.RAIL, List.of("rail.png"),
            BackgroundCellType.ROAD, List.of("road.png"),
            BackgroundCellType.WATER, List.of("water.png"),
            BackgroundCellType.FINISHLINE, List.of("finishline.png"));
    /** all obstacle sprite file names. */
    protected static final Map<ObstacleType, List<String>> OBSTACLE_FILES = Map.of(
            ObstacleType.BUSH, List.of("bush.png"),
            ObstacleType.TREE, List.of("tree.png"),
            ObstacleType.CAR_SX, List.of("carSX0.png", "carSX1.png", "carSX2.png"),
            ObstacleType.CAR_DX, List.of("carDX0.png", "carDX1.png", "carDX2.png"),
            ObstacleType.TRAIN_SX, List.of("trainSX.png"),
            ObstacleType.TRAIN_DX, List.of("trainDX.png"),
            ObstacleType.TRASPARENT_WATER, List.of("transparentwater.png"),
            ObstacleType.TRUNK_SX, List.of("trunkSX.png"),
            ObstacleType.TRUNK_DX, List.of("trunkDX.png"));
    /** all map file names. */
    protected static final Map<Difficulty, String> MAP_FILES = Map.of(
            Difficulty.EASY, "easy.txt",
            Difficulty.NORMAL, "normal.txt",
            Difficulty.HARD, "hard.txt");
    /** all player sprite file names. */
    protected static final List<String> PLAYER_FILES = List.of("player0.png", "player1.png", "player2.png");
    /** stats file name. */
    protected static final String STAT_FILE = "stats.txt";

    // LOADED DATA
    private Player player = new PlayerImpl(new Vector2D(7, 4));
    private Optional<GameStat> gameStat = Optional.empty();
    private Optional<Map<Difficulty, List<Obstacle>>> obstacles = Optional.empty();
    private Optional<Map<Difficulty, List<BackgroundCell>>> backgroundCells = Optional.empty();
    private Optional<Map<Difficulty, List<Collectable>>> collectables = Optional.empty();
    private Optional<List<Image>> playerImages = Optional.empty();
    private Optional<Map<CollectableType, List<Image>>> collectableImages = Optional.empty();
    private Optional<Map<BackgroundCellType, List<Image>>> backgroundCellImages = Optional.empty();
    private Optional<Map<ObstacleType, List<Image>>> obstacleImages = Optional.empty();

    // BUFFERS
    private Optional<Map<Difficulty, List<String>>> mapBuffer = Optional.empty();

    // METHODS

    @Override
    public final void loadAllFromFile() {
        loadImages();
        loadStats();
        loadMaps();
    }

    @Override
    public final void deleteStatFile() {
        try {
            Files.deleteIfExists(Paths.get(STAT_DIR + FILE_SEP + STAT_FILE));
            Files.deleteIfExists(Paths.get(STAT_DIR));
        } catch (IOException e) {
            LauncherImpl.LAUNCHER.closeWindow();
        }
    }

    @Override
    public final Player getPlayerCell(final Difficulty difficulty) {
        return this.player;
    }

    // UTILITY

    /**
     * load resource file passing classpath.
     * 
     * @param resourcePath classpath of resource file
     * @return collection of lines of the loaded resource file
     */
    protected final List<String> loadResourceFile(final String resourcePath) {
        try {
            final InputStream inputStream = ClassLoader.getSystemResourceAsStream(resourcePath);
            final String string = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            final List<String> lines = Arrays.asList(string.split(System.getProperty("line.separator")));
            return lines;
        } catch (IOException e) {
            LauncherImpl.LAUNCHER.closeWindow();
        }

        // will never be reached!
        return null;
    }

    // GETTERS

    /**
     * get gamestat loaded from file.
     * 
     * @return {@link #gameStat}
     */
    protected final Optional<GameStat> getGameStatOpt() {
        return this.gameStat;
    }

    /**
     * get obstacles loaded from file. difficulty chooses the map.
     * 
     * @return {@link #obstacles}
     */
    protected final Optional<Map<Difficulty, List<Obstacle>>> getObstaclesOpt() {
        return this.obstacles;
    }

    /**
     * get backgrounds loaded from file. difficulty chooses the map.
     * 
     * @return {@link #backgroundCells}
     */
    protected final Optional<Map<Difficulty, List<BackgroundCell>>> getBackgroundCellsOpt() {
        return this.backgroundCells;
    }

    /**
     * get collectables loaded from file. use difficulty to choose the map.
     * 
     * @return {@link #collectables}
     */
    protected final Optional<Map<Difficulty, List<Collectable>>> getCollectablesOpt() {
        return this.collectables;
    }

    /**
     * get all player images.
     * 
     * @return {@link #playerImages}
     */
    protected final Optional<List<Image>> getPlayerImagesOpt() {
        return this.playerImages;
    }

    /**
     * get all collectable images.
     * 
     * @return {@link #collectableImages}
     */
    protected final Optional<Map<CollectableType, List<Image>>> getCollectableImagesOpt() {
        return this.collectableImages;
    }

    /**
     * get all background images.
     * 
     * @return {@link #backgroundCellImages}
     */
    protected final Optional<Map<BackgroundCellType, List<Image>>> getBackgroundCellImagesOpt() {
        return this.backgroundCellImages;
    }

    /**
     * get all obstacles images.
     * 
     * @return {@link #obstacleImages}
     */
    protected final Optional<Map<ObstacleType, List<Image>>> getObstacleImagesOpt() {
        return this.obstacleImages;
    }

    /**
     * get map file, buffered as list of string.
     * 
     * @return {@link #mapBuffer}
     */
    protected final Optional<Map<Difficulty, List<String>>> getMapBufferOpt() {
        return this.mapBuffer;
    }

    // SETTERS

    /**
     * set gamestat.
     * 
     * @param gameStat
     */
    protected final void setGameStatOpt(final Optional<GameStat> gameStat) {
        this.gameStat = gameStat;
    }

    /**
     * set obstacles.
     * 
     * @param obstacles
     */
    protected final void setObstaclesOpt(final Optional<Map<Difficulty, List<Obstacle>>> obstacles) {
        this.obstacles = obstacles;
    }

    /**
     * set backgrounds.
     * 
     * @param backgroundCells
     */
    protected final void setBackgroundCellsOpt(final Optional<Map<Difficulty, List<BackgroundCell>>> backgroundCells) {
        this.backgroundCells = backgroundCells;
    }

    /**
     * set collectables.
     * 
     * @param collectables
     */
    protected final void setCollectablesOpt(final Optional<Map<Difficulty, List<Collectable>>> collectables) {
        this.collectables = collectables;
    }

    /**
     * set player images.
     * 
     * @param playerImages
     */
    protected final void setPlayerImagesOpt(final Optional<List<Image>> playerImages) {
        this.playerImages = playerImages;
    }

    /**
     * set collectable images.
     * 
     * @param collectableImages
     */
    protected final void setCollectableImagesOpt(final Optional<Map<CollectableType, List<Image>>> collectableImages) {
        this.collectableImages = collectableImages;
    }

    /**
     * set background images.
     * 
     * @param backgroundCellImages
     */
    protected final void setBackgroundCellImagesOpt(
            final Optional<Map<BackgroundCellType, List<Image>>> backgroundCellImages) {
        this.backgroundCellImages = backgroundCellImages;
    }

    /**
     * set obstacle images.
     * 
     * @param obstacleImages
     */
    protected final void setObstacleImagesOpt(final Optional<Map<ObstacleType, List<Image>>> obstacleImages) {
        this.obstacleImages = obstacleImages;
    }

    /**
     * set buffer of map as list of string.
     * 
     * @param mapBuffer
     */
    protected final void setMapBufferOpt(final Optional<Map<Difficulty, List<String>>> mapBuffer) {
        this.mapBuffer = mapBuffer;
    }

}
