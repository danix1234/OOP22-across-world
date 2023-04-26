package it.unibo.project.controller.core.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import it.unibo.project.controller.core.api.Difficulty;
import it.unibo.project.controller.core.api.Launcher;
import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
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
        this.launcher.setScene(SceneType.OVER);
        assertEquals(SceneType.OVER, this.launcher.getSceneType());
        assertNotNull(this.launcher.getScene());
        assertEquals(this.launcher.getScene().getClass(), GameOverScene.class);
    }

    /**
     * assure difficulty setter and getter work.
     */
    @Test
    void testDifficulty() {
        this.launcher.setDifficulty(Difficulty.HARD);
        assertEquals(Difficulty.HARD, this.launcher.getDifficulty());
    }

    /**
     * assure entity getter works.
     */
    @Test
    void testEntity() {
        assertNotNull(this.launcher.getPlayer());
        assertNotNull(this.launcher.getObstacles());
        assertNotNull(this.launcher.getBackgroundCells());
        assertNotNull(this.launcher.getCollectables());
    }

    /**
     * assure loader getter works.
     */
    @Test
    void testLoader() {
        assertNotNull(this.launcher.getLoader());
    }

    /**
     * assure input handler getter works.
     */
    @Test
    void testInputHandler() {
        for (final var type : SceneType.values()) {
            assertNotNull(this.launcher.geInputHandler(type));
        }
    }
}
