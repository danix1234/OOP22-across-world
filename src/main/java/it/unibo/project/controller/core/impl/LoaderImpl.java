package it.unibo.project.controller.core.impl;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import it.unibo.project.controller.core.api.Loader;
import it.unibo.project.game.model.api.GameStat;
import it.unibo.project.game.model.api.GameWorld;
import it.unibo.project.game.model.api.ObstacleType;

/**
 * class {@code LoaderImpl} implements {@linkplain Loader}.
 */
// TODO remove warning suppression!
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
    private static final String DEFAULT_STAT_DIR = RESOURCE_DIR + FILE_SEP + "stats";
    private static final String BACKGROUND_DIR = SPRITE_DIR + FILE_SEP + "background";
    private static final String COLLECTABLE_DIR = SPRITE_DIR + FILE_SEP + "collectable";
    private static final String OBSTACLE_DIR = SPRITE_DIR + FILE_SEP + "obstacle";
    private static final String PLAYER_DIR = SPRITE_DIR + FILE_SEP + "player";

    // STATS DIRECTORY (IN USER HOME)
    private static final String STAT_DIR = USER_HOME_DIR + FILE_SEP + ".across_world";

    // STATS FILE
    private static final String STAT_FILE = "stats.txt";

    private Optional<GameWorld> gameWorld = Optional.empty();
    private Optional<GameStat> gameStat = Optional.empty();
    private Optional<List<Image>> playerImages = Optional.empty();
    private Optional<Map<ObstacleType, List<Image>>> collectableImages = Optional.empty();
    private Optional<Map<ObstacleType, List<Image>>> backgroundCellImages = Optional.empty();
    private Optional<Map<ObstacleType, List<Image>>> obstaclesImages = Optional.empty();

    // LOAD operation

    @Override
    public void loadAllFromFile() {

    }

    // SAVE operation

    private void createFile() {
        try {
            Files.createDirectories(Paths.get(STAT_DIR));
            Files.createFile(Paths.get(STAT_DIR + FILE_SEP + STAT_FILE));
        } catch (IOException e) {
        }
    }

    private void saveOnFile(final GameStat stats) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[coins]\n");
        buffer.append(stats.getCoins());
        buffer.append("\n\n");
        buffer.append("[skins]\n");
        buffer.append(stats.getUnlockedSkins().size());
        buffer.append("\n");
        for (var elem : stats.getUnlockedSkins()){
            buffer.append(elem);
            buffer.append("\n");
        }
        try {
            Files.writeString(Paths.get(STAT_DIR + FILE_SEP + STAT_FILE), buffer.toString());
        } catch (IOException e) {
        }
    }

    @Override
    public void saveStatOnFile(final GameStat stats) {
        Objects.requireNonNull(stats);
        createFile();
        saveOnFile(stats);
    }

}
