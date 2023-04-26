package it.unibo.project.controller.core.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import it.unibo.project.controller.core.api.Loader;
import it.unibo.project.controller.core.impl.LoaderImpl;
import it.unibo.project.game.model.api.BackgroundCellType;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.ObstacleType;
import it.unibo.project.game.model.impl.GameStatImpl;

public class LoaderTest {
    private final Loader loader = new LoaderImpl();
    private boolean deleteStatFile = true;

    @Test
    public void testLoading() {
        assertDoesNotThrow(() -> {
            this.loader.loadAllFromFile();
        });
    }

    @Test
    public void testGetters() {
        assertNotNull(this.loader.getGameStat());
        assertNotNull(this.loader.getPlayerSprites());
        assertNotNull(this.loader.getBackgroundCellSprites(BackgroundCellType.GRASS));
        assertNotNull(this.loader.getObstacleSprites(ObstacleType.BUSH));
        assertNotNull(this.loader.getCollectablesSprites(CollectableType.COIN));
        assertNotNull(this.loader.getElementRandom(this.loader.getPlayerSprites()));
    }

    @Test
    public void testSaving() {
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

    @Test
    public void testAllWithStatDir() {
        // tests loading statistics from user directory, instead then from default file
        this.deleteStatFile = false;
        testSaving();
        this.deleteStatFile = true;
        testLoading();
        testGetters();
    }
}
