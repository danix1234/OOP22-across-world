package it.unibo.project.game.model.api;

import java.util.List;

public interface GameStat {
    void loadStat();

    void saveStat();

    int getCoins();

    void addCoins(int collectedCoins);

    List<Boolean> getUnlockedSkins();

    void changeUnlockedSkins(List<Boolean> unlockedSkins);
}
