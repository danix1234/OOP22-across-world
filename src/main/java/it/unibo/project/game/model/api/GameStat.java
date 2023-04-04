package it.unibo.project.game.model.api;

import java.util.List;

public interface GameStat {
    public void loadStat();

    public void saveStat();

    public int getCoins();

    public List<Boolean> getUnlockedSkins();
}
