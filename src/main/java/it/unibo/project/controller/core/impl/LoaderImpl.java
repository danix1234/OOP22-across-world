package it.unibo.project.controller.core.impl;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import it.unibo.project.game.model.impl.BackgroundCellImpl;
import it.unibo.project.game.model.impl.CollectableImpl;
import it.unibo.project.game.model.impl.GameStatImpl;
import it.unibo.project.game.model.impl.ObstacleImpl;
import it.unibo.project.utility.Vector2D;

/**
 * class {@code LoaderImpl} implements {@linkplain Loader}.
 */
public class LoaderImpl extends AbstractLoader {

    // LOAD operations
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

    @Override
    public void loadStats() {
        loadStat(getStatFile());
    }

    // maps

    private void loadMapBuffers() {
        try {
            final var mapBuffers = new HashMap<Difficulty, List<String>>();
            for (final var difficulty : Difficulty.values()) {
                final var path = Paths.get(MAPS_DIR + FILE_SEP + MAP_FILES.get(difficulty));
                mapBuffers.put(difficulty, Files.readAllLines(path));
            }
            this.mapBuffer = Optional.of(mapBuffers);
        } catch (IOException e) {
            LauncherImpl.LAUNCHER.closeWindow();
        }
    }

    private List<String> getMapBuffer(final Difficulty difficulty) {
        return this.mapBuffer.orElseGet(() -> {
            loadMapBuffers();
            return this.mapBuffer.orElseThrow();
        }).get(difficulty);
    }

    private List<Vector2D> loadEntity(final Difficulty difficulty, final String nameEntity) {
        return getMapBuffer(difficulty)
                .stream()
                .dropWhile(line -> !line.equalsIgnoreCase("[" + nameEntity + " ]"))
                .skip(1)
                .map(String::strip)
                .takeWhile(line -> line.length() > 0)
                .map(line -> line.split(" "))
                .map(line -> new Vector2D(Integer.parseInt(line[0]), Integer.parseInt(line[1])))
                .toList();
    }

    private List<Collectable> loadEntityCollectable(final Difficulty difficulty, final CollectableType type) {
        return loadEntity(difficulty, type.name())
                .stream()
                .map(vector -> (Collectable) new CollectableImpl(vector, type))
                .toList();
    }

    private List<Obstacle> loadEntityObstacle(final Difficulty difficulty, final ObstacleType type) {
        return loadEntity(difficulty, type.name())
                .stream()
                .map(vector -> (Obstacle) new ObstacleImpl(vector, type))
                .toList();
    }

    private List<BackgroundCell> loadEntityBackground(final Difficulty difficulty, final BackgroundCellType type) {
        return loadEntity(difficulty, type.name())
                .stream()
                .map(vector -> (BackgroundCell) new BackgroundCellImpl(vector, type))
                .toList();
    }

    @Override
    public void loadMaps() {
        final Map<Difficulty, List<Obstacle>> obstacleAll = new HashMap<>();
        final Map<Difficulty, List<BackgroundCell>> backgroundCellAll = new HashMap<>();
        final Map<Difficulty, List<Collectable>> collectableAll = new HashMap<>();

        for (final var difficulty : Difficulty.values()) {
            final List<Obstacle> obstacles = new ArrayList<>();
            final List<BackgroundCell> backgroundCells = new ArrayList<>();
            final List<Collectable> collectables = new ArrayList<>();

            for (final var obstacleType : ObstacleType.values()) {
                obstacles.addAll(loadEntityObstacle(difficulty, obstacleType));
            }
            for (final var backgroundType : BackgroundCellType.values()) {
                backgroundCells.addAll(loadEntityBackground(difficulty, backgroundType));
            }
            for (final var collectableType : CollectableType.values()) {
                collectables.addAll(loadEntityCollectable(difficulty, collectableType));
            }

            obstacleAll.put(difficulty, obstacles);
            backgroundCellAll.put(difficulty, backgroundCells);
            collectableAll.put(difficulty, collectables);
        }

        this.obstacles = Optional.of(obstacleAll);
        this.backgroundCells = Optional.of(backgroundCellAll);
        this.collectables = Optional.of(collectableAll);
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

    @Override
    public void loadImages() {
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
            loadStats();
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

    @Override
    public List<BackgroundCell> getBackgroundCells(final Difficulty difficulty) {
        return this.backgroundCells.orElseGet(() -> {
            loadMaps();
            return this.backgroundCells.orElseThrow();
        }).get(difficulty);
    }

    @Override
    public List<Collectable> getCollectables(final Difficulty difficulty) {
        return this.collectables.orElseGet(() -> {
            loadMaps();
            return this.collectables.orElseThrow();
        }).get(difficulty);
    }

    @Override
    public List<Obstacle> getObstacles(final Difficulty difficulty) {
        return this.obstacles.orElseGet(() -> {
            loadMaps();
            return this.obstacles.orElseThrow();
        }).get(difficulty);
    }

}
