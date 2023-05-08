package it.unibo.project.controller.core.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.project.game.model.api.GameStat;
import it.unibo.project.game.model.impl.GameStatImpl;

/**
 * class to save and load stats from file.
 */
public abstract class AbstractStatLoader extends AbstractLoader {

    // LOAD operations

    private Path getStatFile() {
        if (Files.exists(Paths.get(STAT_DIR + FILE_SEP + STAT_FILE))) {
            return Paths.get(STAT_DIR + FILE_SEP + STAT_FILE);
        }
        return Paths.get(DEFAULT_STAT_DIR + FILE_SEP + STAT_FILE);
    }

    private void loadStat(final Path filePath) {
        try {
            final List<String> lines = Files.readAllLines(filePath);
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
        } catch (IOException e) {
            LauncherImpl.LAUNCHER.closeWindow();
        }
    }

    @Override
    public final void loadStats() {
        loadStat(getStatFile());
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
