package it.unibo.project.game.model.api;

import java.util.List;

/**
 * Class {@code GameStat}, contain the number of coin, the list of skins and the metods to modify them.
 * 
 */
public interface GameStat {
    /**
     * Called for get the number of coins collected so far.
     * @return integer that represent the number of coins
     */
    int getCoins();

    /**
    * Called for set the number of coins in the class, could be used only on loading.
    * @param coins value of set to represent coins in the game
    */
    void setCoins(int coins);

    /**
     * Called for increase the number of coins, add the value of passed parameter to the attribute coins.
     * @param collectedCoins value that coins will be increased
     */
    void addCoins(int collectedCoins);

    /**
     * Called for get the list of locked/unlocked skins.
     * @return a list that contain if a skin is locked or unlocked
     */
    List<Boolean> getUnlockedSkins();

    /**
     * Called for change the content of the list that contain locked/unlocked skins.
     * @param unlockedSkins new list with new values to save instead of the previous list
     */
    void changeUnlockedSkins(List<Boolean> unlockedSkins);
}
