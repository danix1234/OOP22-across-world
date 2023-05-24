package it.unibo.project.controller.core.impl;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.project.controller.core.api.Difficulty;
import it.unibo.project.controller.core.api.Loader;
import it.unibo.project.game.model.api.BackgroundCell;
import it.unibo.project.game.model.api.BackgroundCellType;
import it.unibo.project.game.model.api.Collectable;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.GameStat;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.ObstacleType;
import it.unibo.project.game.model.api.Player;
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
    protected static final Map<CollectableType, List<String>> COLLECTABLE_FILES = Map.ofEntries(
            Map.entry(CollectableType.COIN, List.of("coin.png")),
            Map.entry(CollectableType.POWERUP_COIN_MAGNET, List.of("magnet.png")),
            Map.entry(CollectableType.POWERUP_COIN_MULTIPLIER, List.of("multiplier.png")),
            Map.entry(CollectableType.POWERUP_IMMORTALITY, List.of("immortality.png")));
    /** all background sprite file names. */
    protected static final Map<BackgroundCellType, List<String>> BACKGROUND_FILES = Map.ofEntries(
            Map.entry(BackgroundCellType.GRASS, List.of("grass.png")),           
            Map.entry(BackgroundCellType.RAIL, List.of("rail.png")),           
            Map.entry(BackgroundCellType.ROAD, List.of("road.png")),           
            Map.entry(BackgroundCellType.WATER, List.of("water.png")),
            Map.entry(BackgroundCellType.FINISHLINE, List.of("finishline.png")),
            Map.entry(BackgroundCellType.BICYCLELANE_GRASS, List.of("bicyclelaneGrass.png")),
            Map.entry(BackgroundCellType.BICYCLELANE_SAND, List.of("bicyclelaneSand.png")),
            Map.entry(BackgroundCellType.SAND, List.of("sand.png")),
            Map.entry(BackgroundCellType.DIRT, List.of("dirt.png")),
            Map.entry(BackgroundCellType.HARDRAIL, List.of("hardrail.png")),
            Map.entry(BackgroundCellType.HARDROAD, List.of("hardroad.png")),
            Map.entry(BackgroundCellType.LAVA, List.of("lava.png")),
            Map.entry(BackgroundCellType.FIRE, List.of("fire0.png", "fire1.png")));
    /** all obstacle sprite file names. */
    protected static final Map<ObstacleType, List<String>> OBSTACLE_FILES = Map.ofEntries(
            Map.entry(ObstacleType.BUSH, List.of("bush.png")),
            Map.entry(ObstacleType.TREE, List.of("tree.png")),
            Map.entry(ObstacleType.FENCE, List.of("fence.png")),
            Map.entry(ObstacleType.STOPLIGHT, List.of("stoplight.png")),
            Map.entry(ObstacleType.SANDCASTLE, List.of("sandcastle.png")),
            Map.entry(ObstacleType.PALM, List.of("palm0.png", "palm1.png")),
            Map.entry(ObstacleType.BEACHUMBRELLA, List.of("beachumbrella.png")),
            Map.entry(ObstacleType.STARFISH, List.of("starfish0.png", "starfish1.png", "starfish2.png", "starfish3.png")),
            Map.entry(ObstacleType.CAR_SX, List.of("carSX0.png", "carSX1.png", "carSX2.png")),
            Map.entry(ObstacleType.CAR_DX, List.of("carDX0.png", "carDX1.png", "carDX2.png")),
            Map.entry(ObstacleType.TRAIN_SX, List.of("trainSX.png")),
            Map.entry(ObstacleType.TRAIN_DX, List.of("trainDX.png")),
            Map.entry(ObstacleType.WAGON_SX, List.of("wagon.png")),
            Map.entry(ObstacleType.WAGON_DX, List.of("wagon.png")),
            Map.entry(ObstacleType.TRANSPARENT_OBSTACLE, List.of("transparentobstacle.png")),
            Map.entry(ObstacleType.TRUNK_START_SX, List.of("trunkSXstart.png")),
            Map.entry(ObstacleType.TRUNK_SX, List.of("trunkSXcenter.png")),
            Map.entry(ObstacleType.TRUNK_FINISH_SX, List.of("trunkSXfinish.png")),
            Map.entry(ObstacleType.TRUNK_START_DX, List.of("trunkDXstart.png")),
            Map.entry(ObstacleType.TRUNK_DX, List.of("trunkDXcenter.png")),
            Map.entry(ObstacleType.TRUNK_FINISH_DX, List.of("trunkDXfinish.png")),
            Map.entry(ObstacleType.BIKE_SX, List.of("bikeSX.png")),
            Map.entry(ObstacleType.BIKE_DX, List.of("bikeDX.png")),
            Map.entry(ObstacleType.BEACHMATTRESS_SX, List.of("beachmattressSX.png")),
            Map.entry(ObstacleType.BEACHMATTRESS_DX, List.of("beachmattressDX.png")),
            Map.entry(ObstacleType.BALL_SX, List.of("ballSX.png")),
            Map.entry(ObstacleType.BALL_DX, List.of("ballDX.png")),
            Map.entry(ObstacleType.FIRETREE, List.of("firetree.png")),
            Map.entry(ObstacleType.MONSTER_SX, List.of("monsterSX0.png", "monsterSX1.png", "monsterSX2.png")),
            Map.entry(ObstacleType.MONSTER_DX, List.of("monsterDX0.png", "monsterDX1.png", "monsterDX2.png")),
            Map.entry(ObstacleType.JET_SX, List.of("jetSX.png")),
            Map.entry(ObstacleType.JET_DX, List.of("jetDX.png")),
            Map.entry(ObstacleType.ROCK_SX, List.of("rock.png")),
            Map.entry(ObstacleType.ROCK_DX, List.of("rock.png")));
    /** all map file names. */
    protected static final Map<Difficulty, String> MAP_FILES = Map.ofEntries(
            Map.entry(Difficulty.EASY, "easy.txt"),
            Map.entry(Difficulty.NORMAL, "normal.txt"),
            Map.entry(Difficulty.HARD, "hard.txt"));
    /** all player sprite file names. */
    protected static final List<String> PLAYER_FILES = List.of("player0.png", "player1.png", "player2.png");
    /** stats file name. */
    protected static final String STAT_FILE = "stats.txt";

    // LOADED DATA
    private Vector2D playerPos = new Vector2D(7, 4);
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
        } catch (IOException e) {
            LauncherImpl.LAUNCHER.closeWindow();
        }
    }

    @Override
    public final Player getPlayerCell(final Difficulty difficulty) {
        return new PlayerImpl(this.playerPos);
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
            final String buffer = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            return buffer.lines().toList();
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
