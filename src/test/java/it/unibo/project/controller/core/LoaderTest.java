package it.unibo.project.controller.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import it.unibo.project.controller.core.api.Loader;
import it.unibo.project.controller.core.impl.LoaderImpl;
import it.unibo.project.game.model.api.BackgroundCellType;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.ObstacleType;
import it.unibo.project.game.model.impl.GameStatImpl;

/**
 * tests for {@linkplain Loader} class.
 */
class LoaderTest {
    private final Loader loader = new LoaderImpl();
    private boolean deleteStatFile = true;

    /**
     * assure resorces loading work.
     */
    @Test
    void testLoading() {
        assertDoesNotThrow(() -> {
            this.loader.loadAllFromFile();
        });
    }

    /**
     * assure getter methods work.
     */
    @Test
    void testGetters() {
        assertNotNull(this.loader.getGameStat());
        assertNotNull(this.loader.getPlayerSprites());
        assertNotNull(this.loader.getBackgroundCellSprites(BackgroundCellType.GRASS));
        assertNotNull(this.loader.getObstacleSprites(ObstacleType.BUSH));
        assertNotNull(this.loader.getCollectablesSprites(CollectableType.COIN));
        assertNotNull(this.loader.getElementRandom(this.loader.getPlayerSprites()));
    }

    /**
     * assure saving work.
     * 
     * @implNote deletes directory in user home before ending
     */
    @Test
    void testSaving() {
        assertDoesNotThrow(() -> {
            try {
                this.loader.saveStatOnFile(new GameStatImpl());
            } finally {
                if (deleteStatFile) {
                    this.loader.deleteStatFile();
                }
            }
        });
    }

    /**
     * test loading stats from user dir.
     */
    @Test
    void testAllWithStatDir() {
        try {
            // tests loading statistics from user directory, instead then from default file
            this.deleteStatFile = false;
            assertDoesNotThrow(() -> {
                testSaving();
                testLoading();
                testGetters();
            });
        } finally {
            // removes directory in user home
            this.deleteStatFile = true;
            testSaving();
        }
    }
}
