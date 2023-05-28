package it.unibo.project.controller.core.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.project.game.model.api.GameStat;
import it.unibo.project.game.model.impl.GameStatImpl;

/**
 * class to save and load stats from file.
 */
public abstract class AbstractStatLoader extends AbstractLoader {

    // LOAD operations

    private List<String> getStatFile() {
        if (Files.exists(Paths.get(STAT_DIR + FILE_SEP + STAT_FILE))) {
            try {
                return Files.readAllLines(Paths.get(STAT_DIR + FILE_SEP + STAT_FILE));
            } catch (IOException e) {
                LauncherImpl.LAUNCHER.closeWindow();
            }
        }
        return loadResourceFile(DEFAULT_STAT_DIR + FILE_SEP + STAT_FILE);
    }

    private void loadStat(final List<String> lines) {
        final GameStat gameStat = new GameStatImpl();
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
        gameStat.setCoins(coins);
        gameStat.changeUnlockedSkins(unlockedSkin);
        setGameStatOpt(Optional.of(gameStat));
    }

    @Override
    public final void loadStats() {
        try {
            loadStat(getStatFile());
        } catch (final NoSuchElementException | IllegalArgumentException e) {
            deleteStatFile();
            loadStat(getStatFile());
        }
    }

    // SAVE operations

    private void createFile() {
        try {
            Files.createDirectories(Paths.get(STAT_DIR));
            if (!Files.exists(Paths.get(STAT_DIR + FILE_SEP + STAT_FILE))) {
                Files.createFile(Paths.get(STAT_DIR + FILE_SEP + STAT_FILE));
            }
        } catch (IOException e) {
            LauncherImpl.LAUNCHER.closeWindow();
        }
    }

    private void saveOnFile(final GameStat stats) {
        try {
            final String buffer = "[coins]\n" + stats.getCoins() + "\n\n"
                    + "[skins]\n" + stats.getUnlockedSkins().size() + "\n"
                    + stats.getUnlockedSkins()
                            .stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining("\n"));
            Files.writeString(Paths.get(STAT_DIR + FILE_SEP + STAT_FILE), buffer);
        } catch (IOException e) {
            LauncherImpl.LAUNCHER.closeWindow();
        }
    }

    @Override
    public final void saveStatOnFile(final GameStat stats) {
        createFile();
        saveOnFile(stats);
    }

}
