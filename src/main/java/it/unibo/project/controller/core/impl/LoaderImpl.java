package it.unibo.project.controller.core.impl;

import java.awt.Image;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.project.controller.core.api.Difficulty;
import it.unibo.project.controller.core.api.Loader;
import it.unibo.project.game.model.api.BackgroundCellType;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.GameStat;
import it.unibo.project.game.model.api.GameWorld;
import it.unibo.project.game.model.api.ObstacleType;
import it.unibo.project.game.model.impl.GameStatImpl;

/**
 * class {@code LoaderImpl} implements {@linkplain Loader}.
 */
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

    // STATS DIRECTORIES
    private static final String DEFAULT_STAT_DIR = RESOURCE_DIR + FILE_SEP + "stats";
    private static final String STAT_DIR = USER_HOME_DIR + FILE_SEP + ".across_world";

    // RESOURCES FILES
    private static final Map<CollectableType, List<String>> collectableFiles = Map.of();
    private static final Map<BackgroundCellType, List<String>> backgroundCellFiles = Map.of();
    private static final Map<ObstacleType, List<String>> ObstacleFiles = Map.of();
    private static final List<String> playerFiles = List.of();

    // STATS FILE
    private static final String STAT_FILE = "stats.txt";

    // LOADED DATA
    private Optional<GameStat> gameStat = Optional.empty();
    private Optional<List<Image>> playerImages = Optional.empty();
    private Optional<Map<Difficulty, List<GameWorld>>> gameWorld = Optional.empty();
    private Optional<Map<CollectableType, List<Image>>> collectableImages = Optional.empty();
    private Optional<Map<BackgroundCellType, List<Image>>> backgroundCellImages = Optional.empty();
    private Optional<Map<ObstacleType, List<Image>>> obstaclesImages = Optional.empty();

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

    private void loadImages() {
        // TODO
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
    public GameStat getGameStat() {
        return this.gameStat.orElseGet(() -> {
            loadStat();
            return this.gameStat.orElseThrow();
        });
    }

}
