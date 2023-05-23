package it.unibo.project.controller.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import it.unibo.project.controller.core.api.Difficulty;
import it.unibo.project.controller.core.api.Launcher;
import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.utility.Vector2D;
import it.unibo.project.view.impl.GameOverScene;

/**
 * tests for {@linkplain Launcher} class.
 */
class LauncherTest {
    private final Launcher launcher = LauncherImpl.LAUNCHER;

    /**
     * assure scene type setter and getter work.
     */
    @Test
    void testSceneType() {
        assertNotNull(this.launcher.getSceneType());
        assertDoesNotThrow(() -> {
            this.launcher.setScene(SceneType.OVER);
        });
        assertEquals(SceneType.OVER, this.launcher.getSceneType());
        assertNotNull(this.launcher.getScene());
        assertEquals(this.launcher.getScene().getClass(), GameOverScene.class);
    }

    /**
     * assure difficulty setter and getter work.
     */
    @Test
    void testDifficulty() {
        assertDoesNotThrow(() -> {
            this.launcher.setDifficulty(Difficulty.HARD);
        });
        assertEquals(Difficulty.HARD, this.launcher.getDifficulty());
    }

    /**
     * assure all other getters works.
     */
    @Test
    void testGetters() {
        assertDoesNotThrow(() -> this.launcher.loadMap());
        assertNotNull(this.launcher.getPlayer());
        assertNotNull(this.launcher.getObstacles());
        assertNotNull(this.launcher.getBackgroundCells());
        assertNotNull(this.launcher.getCollectables());
        assertNotNull(this.launcher.getGameStat());
        assertNotNull(this.launcher.getLoader());
        assertNotNull(this.launcher.getHandlePowerup());
        assertNotNull(this.launcher.getCheckCollision());

        assertDoesNotThrow(() -> this.launcher.start());
        for (final var type : SceneType.values()) {
            assertNotNull(this.launcher.getInputHandler(type));
        }

        assertDoesNotThrow(() -> {
            this.launcher.convertCellToPixelPos(new Vector2D(1, 0));
            assertNotNull(this.launcher.convertPixelToCellPos(10.5, 10));
            this.launcher.getActualPixelX(60.8);
            this.launcher.getObstaclePixelX(45.3);
        });

        assertDoesNotThrow(() -> this.launcher.movePlayerIfPossible(0, 0));
        assertDoesNotThrow(() -> this.launcher.moveDynamicObstacles());
    }

}
