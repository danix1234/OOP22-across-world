package it.unibo.project.controller.core.api;

import it.unibo.project.game.model.api.GameStat;

/**
 * class {@code Loader} interacts with filesystem, abstracting it to the other
 * classes, which can directly obtain the needed data.
 */
public interface Loader {

    /**
     * loads everything from files, and keeps it in memory.
     * 
     * @implNote to use after starting the application, or to refresh what was
     *           loaded from file
     * 
     * @implSpec this method is not meant to be called externally, but internally
     *           whenever data isn't present. Call this externally only if a reload
     *           of the data is needed!
     */
    void loadAllFromFile();

    /**
     * save statistics and variable informations in user home.
     * 
     * @param stats which contains updated game stats
     */
    void saveStatOnFile(GameStat stats);
}
