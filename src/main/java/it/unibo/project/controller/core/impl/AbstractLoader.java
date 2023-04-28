package it.unibo.project.controller.core.impl;

import java.awt.Image;
import java.io.IOException;
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

public abstract class AbstractLoader implements Loader {
    // UTILITY PATH
    protected static final String FILE_SEP = System.getProperty("file.separator");
    protected static final String PROJECT_DIR = System.getProperty("user.dir");
    protected static final String USER_HOME_DIR = System.getProperty("user.home");
    protected static final String RESOURCE_DIR = PROJECT_DIR
            + FILE_SEP + "src"
            + FILE_SEP + "main"
            + FILE_SEP + "resources"
            + FILE_SEP + "it"
            + FILE_SEP + "unibo"
            + FILE_SEP + "project";
    protected static final String SPRITE_DIR = RESOURCE_DIR + FILE_SEP + "sprite";

    // RESOURCES DIRECTORIES
    protected static final String MAPS_DIR = RESOURCE_DIR + FILE_SEP + "maps";
    protected static final String BACKGROUND_DIR = SPRITE_DIR + FILE_SEP + "background";
    protected static final String COLLECTABLE_DIR = SPRITE_DIR + FILE_SEP + "collectable";
    protected static final String OBSTACLE_DIR = SPRITE_DIR + FILE_SEP + "obstacle";
    protected static final String PLAYER_DIR = SPRITE_DIR + FILE_SEP + "player";
    protected static final String DEFAULT_STAT_DIR = RESOURCE_DIR + FILE_SEP + "stats";
    protected static final String STAT_DIR = USER_HOME_DIR + FILE_SEP + ".across_world";

    // RESOURCES FILES
    protected static final Map<CollectableType, List<String>> COLLECTABLE_FILES = Map.of(
            CollectableType.COIN, List.of("coin.png"),
            CollectableType.POWERUP_COIN_MAGNET, List.of("powerup.png"),
            CollectableType.POWERUP_COIN_MULTIPLIER, List.of("powerup.png"),
            CollectableType.POWERUP_IMMORTALITY, List.of("powerup.png"));
    protected static final Map<BackgroundCellType, List<String>> BACKGROUND_FILES = Map.of(
            BackgroundCellType.GRASS, List.of("grass.png"),
            BackgroundCellType.RAIL, List.of("rail.png"),
            BackgroundCellType.ROAD, List.of("road.png"),
            BackgroundCellType.WATER, List.of("water.png"));
    protected static final Map<ObstacleType, List<String>> OBSTACLE_FILES = Map.of(
            ObstacleType.BUSH, List.of("bush.png"),
            ObstacleType.TREE, List.of("tree.png"),
            ObstacleType.CAR_SX, List.of("carSX0.png"),
            ObstacleType.CAR_DX, List.of("carDX0.png", "carDX1.png", "carDX2.png"),
            ObstacleType.TRAIN_SX, List.of(),
            ObstacleType.TRAIN_DX, List.of(),
            ObstacleType.TRASPARENT_WATER, List.of());
    protected static final Map<Difficulty, String> MAP_FILES = Map.of(
            Difficulty.EASY, "easy.txt",
            Difficulty.NORMAL, "normal.txt",
            Difficulty.HARD, "hard.txt");
    protected static final List<String> PLAYER_FILES = List.of("player0.png");
    protected static final String STAT_FILE = "stats.txt";

    // LOADED DATA
    protected Optional<GameStat> gameStat = Optional.empty();
    protected Optional<Map<Difficulty, List<Obstacle>>> obstacles = Optional.empty();
    protected Optional<Map<Difficulty, List<BackgroundCell>>> backgroundCells = Optional.empty();
    protected Optional<Map<Difficulty, List<Collectable>>> collectables = Optional.empty();
    protected Optional<List<Image>> playerImages = Optional.empty();
    protected Optional<Map<CollectableType, List<Image>>> collectableImages = Optional.empty();
    protected Optional<Map<BackgroundCellType, List<Image>>> backgroundCellImages = Optional.empty();
    protected Optional<Map<ObstacleType, List<Image>>> obstacleImages = Optional.empty();

    // BUFFERS
    protected Optional<Map<Difficulty, List<String>>> mapBuffer = Optional.empty();

    @Override
    public void loadAllFromFile() {
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
}
