package it.unibo.project.game.model.api;

import java.util.List;

public interface GameStat {
    public void loadStat();

    public void saveStat();

    public int getCoins();

    public void addCoins(int collectedCoins);

    public List<Boolean> getUnlockedSkins();

    public void changeUnlockedSkins(List<Boolean> unlockedSkins);
}
