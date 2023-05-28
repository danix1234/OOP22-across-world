package it.unibo.project.game.model.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.project.game.model.api.GameStat;

public class GameStatImpl implements GameStat {
    int coins;
    List<Boolean> unlockedSkins = new ArrayList<>();

    @Override
    public int getCoins() {
        return coins;
    }

    @Override
    public List<Boolean> getUnlockedSkins() {
        return List.copyOf(unlockedSkins);
    }

    @Override
    public void addCoins(final int collectedCoins) {
        coins += collectedCoins;
    }

    @Override
    public void changeUnlockedSkins(final List<Boolean> unlockedSkins) {
        this.unlockedSkins = new ArrayList<>(List.copyOf(unlockedSkins));
    }

    @Override
    public void setCoins(final int coins) {
        this.coins = coins;
    }

}
