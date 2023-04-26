package it.unibo.project.controller.core.impl;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import it.unibo.project.controller.core.api.Difficulty;
import it.unibo.project.controller.core.api.Loader;
import it.unibo.project.game.model.api.BackgroundCell;
import it.unibo.project.game.model.api.BackgroundCellType;
import it.unibo.project.game.model.api.Collectable;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.GameStat;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.ObstacleType;
import it.unibo.project.game.model.impl.GameStatImpl;

/**
 * class {@code LoaderImpl} implements {@linkplain Loader}.
 */
// TODO remove temporary warning suppression
@SuppressWarnings("unused")
public class LoaderImpl implements Loader {
    // UTILITY PATH
    private static final String FILE_SEP = System.getProperty("file.separator");
    private static final String PROJECT_DIR = System.getProperty("user.dir");
    private static final String USER_HOME_DIR = System.getProperty("user.home");
    private static final String RESOURCE_DIR = PROJECT_DIR
            + FILE_SEP + "src"
            + FILE_SEP + "main"
            + FILE_SEP + "resources"
            + FILE_SEP + "it"
            + FILE_SEP + "unibo"
            + FILE_SEP + "project";
    private static final String SPRITE_DIR = RESOURCE_DIR + FILE_SEP + "sprite";

    // RESOURCES DIRECTORIES
    private static final String MAPS_DIR = RESOURCE_DIR + FILE_SEP + "maps";
    private static final String BACKGROUND_DIR = SPRITE_DIR + FILE_SEP + "background";
    private static final String COLLECTABLE_DIR = SPRITE_DIR + FILE_SEP + "collectable";
    private static final String OBSTACLE_DIR = SPRITE_DIR + FILE_SEP + "obstacle";
    private static final String PLAYER_DIR = SPRITE_DIR + FILE_SEP + "player";
    private static final String DEFAULT_STAT_DIR = RESOURCE_DIR + FILE_SEP + "stats";
    private static final String STAT_DIR = USER_HOME_DIR + FILE_SEP + ".across_world";

    // RESOURCES FILES
    private static final Map<CollectableType, List<String>> COLLECTABLE_FILES = Map.of(
            CollectableType.COIN, List.of("coin.png"),
            CollectableType.POWERUP_COIN_MAGNET, List.of("powerup.png"),
            CollectableType.POWERUP_COIN_MULTIPLIER, List.of("powerup.png"),
            CollectableType.POWERUP_IMMORTALITY, List.of("powerup.png"));
    private static final Map<BackgroundCellType, List<String>> BACKGROUND_FILES = Map.of(
            BackgroundCellType.GRASS, List.of("grass.png"),
            BackgroundCellType.RAIL, List.of("rail.png"),
            BackgroundCellType.ROAD, List.of("road.png"),
            BackgroundCellType.WATER, List.of("water.png"));
    private static final Map<ObstacleType, List<String>> OBSTACLE_FILES = Map.of(
            ObstacleType.BUSH, List.of("bush.png"),
            ObstacleType.TREE, List.of("tree.png"),
            ObstacleType.CAR_SX, List.of("carSX0.png"),
            ObstacleType.CAR_DX, List.of("carDX0.png", "carDX1.png", "carDX2.png"),
            ObstacleType.TRAIN_SX, List.of(),
            ObstacleType.TRAIN_DX, List.of(),
            ObstacleType.TRASPARENT_WATER, List.of());
    private static final List<String> PLAYER_FILES = List.of("player0.png");
    private static final String STAT_FILE = "stats.txt";

    // LOADED DATA
    private Optional<GameStat> gameStat = Optional.empty();
    private Optional<Map<Difficulty, Map<ObstacleType, List<Obstacle>>>> obstacles = Optional.empty();
    private Optional<Map<Difficulty, Map<BackgroundCellType, List<BackgroundCell>>>> backgroundCells = Optional.empty();
    private Optional<Map<Difficulty, Map<CollectableType, List<Collectable>>>> collectables = Optional.empty();
    private Optional<List<Image>> playerImages = Optional.empty();
    private Optional<Map<CollectableType, List<Image>>> collectableImages = Optional.empty();
    private Optional<Map<BackgroundCellType, List<Image>>> backgroundCellImages = Optional.empty();
    private Optional<Map<ObstacleType, List<Image>>> obstacleImages = Optional.empty();

    // LOAD operations

    @Override
    public final void loadAllFromFile() {
        loadStat();
        loadMaps();
        loadImages();
    }

    // stats

    private Path getStatFile() {
        if (Files.exists(Paths.get(STAT_DIR + FILE_SEP + STAT_FILE))) {
            return Paths.get(STAT_DIR + FILE_SEP + STAT_FILE);
        }
        return Paths.get(DEFAULT_STAT_DIR + FILE_SEP + STAT_FILE);
    }

    private void loadStat(final Path filePath) {
        try {
            final List<String> lines = Files.readAllLines(filePath);
            final int coins = lines.stream()
                    .dropWhile(line -> !"[coins]".equalsIgnoreCase(line))
                    .skip(1)
                    .limit(1)
                    .findAny()
                    .map(Integer::valueOf)
                    .orElseThrow();
            final int skins = lines.stream()
                    .dropWhile(line -> !"[skins]".equalsIgnoreCase(line))
                    .skip(1)
                    .limit(1)
                    .findAny()
                    .map(Integer::valueOf)
                    .orElseThrow();
            final List<Boolean> unlockedSkin = lines.stream()
                    .dropWhile(line -> !"[skins]".equalsIgnoreCase(line))
                    .skip(2)
                    .limit(skins)
                    .map(Boolean::valueOf)
                    .collect(Collectors.toList());
            this.gameStat = Optional.of(new GameStatImpl());
            this.gameStat.ifPresent(stat -> stat.setCoins(coins));
            this.gameStat.ifPresent(stat -> stat.changeUnlockedSkins(List.copyOf(unlockedSkin)));
        } catch (IOException e) {
            LauncherImpl.LAUNCHER.closeWindow();
        }
    }

    private void loadStat() {
        loadStat(getStatFile());
    }

    // maps

    private void loadMaps() {
        // TODO
    }

    // images

    private Image loadImage(final String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            LauncherImpl.LAUNCHER.closeWindow();
        }

        // can't be reached, but java compiler can't know
        return null;
    }

    private List<Image> loadImages(final String directory, final List<String> fileNames) {
        return fileNames.stream()
                .map(fileName -> directory + FILE_SEP + fileName)
                .map(this::loadImage)
                .toList();
    }

    private void loadImages() {
        // players
        this.playerImages = Optional.of(loadImages(PLAYER_DIR, PLAYER_FILES));

        // collectables
        final Map<CollectableType, List<Image>> collectableImages = new HashMap<>();
        for (final CollectableType type : CollectableType.values()) {
            collectableImages.put(type, loadImages(COLLECTABLE_DIR, COLLECTABLE_FILES.get(type)));
        }
        this.collectableImages = Optional.of(collectableImages);

        // obstacle
        final Map<ObstacleType, List<Image>> obstacleImages = new HashMap<>();
        for (final ObstacleType type : ObstacleType.values()) {
            obstacleImages.put(type, loadImages(OBSTACLE_DIR, OBSTACLE_FILES.get(type)));
        }
        this.obstacleImages = Optional.of(obstacleImages);

        // background
        final Map<BackgroundCellType, List<Image>> backgroundCellImages = new HashMap<>();
        for (final BackgroundCellType type : BackgroundCellType.values()) {
            backgroundCellImages.put(type, loadImages(BACKGROUND_DIR, BACKGROUND_FILES.get(type)));
        }
        this.backgroundCellImages = Optional.of(backgroundCellImages);
    }

    // SAVE operations

    private void createFile() {
        try {
            Files.createDirectories(Paths.get(STAT_DIR));
            Files.createFile(Paths.get(STAT_DIR + FILE_SEP + STAT_FILE));
        } catch (IOException e) {
            LauncherImpl.LAUNCHER.closeWindow();
        }
    }

    private void saveOnFile(final GameStat stats) {
        final String buffer = "[coins]\n" + stats.getCoins() + "\n\n"
                + "[skins]\n" + stats.getUnlockedSkins().size() + "\n"
                + stats.getUnlockedSkins()
                        .stream()
                        .map(String::valueOf)
                        .map(bool -> bool + "\n");
        try {
            Files.writeString(Paths.get(STAT_DIR + FILE_SEP + STAT_FILE), buffer);
        } catch (IOException e) {
            LauncherImpl.LAUNCHER.closeWindow();
        }
    }

    @Override
    public final void saveStatOnFile(final GameStat stats) {
        Objects.requireNonNull(stats);
        createFile();
        saveOnFile(stats);
    }

    // GETTERS

    @Override
    public final Image getElementRandom(final List<Image> collection) {
        return collection.get(new Random().nextInt(collection.size()));
    }

    @Override
    public final GameStat getGameStat() {
        return this.gameStat.orElseGet(() -> {
            loadStat();
            return this.gameStat.orElseThrow();
        });
    }

    @Override
    public final List<Image> getBackgroundCellSprites(final BackgroundCellType backgroundCellType) {
        return this.backgroundCellImages.orElseGet(() -> {
            loadImages();
            return this.backgroundCellImages.orElseThrow();
        }).get(backgroundCellType);
    }

    @Override
    public final List<Image> getCollectablesSprites(final CollectableType collectableType) {
        return this.collectableImages.orElseGet(() -> {
            loadImages();
            return this.collectableImages.orElseThrow();
        }).get(collectableType);
    }

    @Override
    public final List<Image> getObstacleSprites(final ObstacleType obstacleType) {
        return this.obstacleImages.orElseGet(() -> {
            loadImages();
            return this.obstacleImages.orElseThrow();
        }).get(obstacleType);
    }

    @Override
    public final List<Image> getPlayerSprites() {
        return this.playerImages.orElseGet(() -> {
            loadImages();
            return this.playerImages.orElseThrow();
        });
    }

    // OTHER

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
