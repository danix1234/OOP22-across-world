package it.unibo.project.game.model.impl;

import java.util.List;

import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.game.model.api.GameStat;

public class GameStatImpl implements GameStat {

    @Override
    public void loadStat() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadStat'");
    }

    @Override
    public void saveStat() {
        LauncherImpl.LAUNCHER.getLoader().saveStatOnFile(this);
    }

    @Override
    public int getCoins() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCoins'");
    }

    @Override
    public List<Boolean> getUnlockedSkins() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUnlockedSkins'");
    }

    @Override
    public void addCoins(final int collectedCoins) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCoins'");
    }

    @Override
    public void changeUnlockedSkins(final List<Boolean> unlockedSkins) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeUnlockedSkins'");
    }

}
