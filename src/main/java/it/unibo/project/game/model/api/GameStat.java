package it.unibo.project.game.model.api;

import java.util.List;

public interface GameStat {
    int getCoins();

    void setCoins(int coins);

    void addCoins(int collectedCoins);

    List<Boolean> getUnlockedSkins();

    void changeUnlockedSkins(List<Boolean> unlockedSkins);
}
