package it.unibo.project.game.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.project.controller.core.api.Difficulty;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.ObstacleType;
import it.unibo.project.utility.Vector2D;

/**
 * tests for {@linkplain CheckCollisionTest} class.
 */
class CheckCollisionTest {
    /**
     * assure all collision are detected
     */
    @Test
    void checkCollectableCollision() {
        LauncherImpl.LAUNCHER.start();        
        LauncherImpl.LAUNCHER.setDifficulty(Difficulty.NORMAL);
        LauncherImpl.LAUNCHER.loadMap();

        assertEquals(LauncherImpl.LAUNCHER.getCheckCollision().checkCollectableCollision(new Vector2D(8, 26)).get(0).getType(), CollectableType.COIN);
    }

    @Test
    void checkDynamicObstacleCollision() {
        LauncherImpl.LAUNCHER.start();        
        LauncherImpl.LAUNCHER.setDifficulty(Difficulty.NORMAL);
        LauncherImpl.LAUNCHER.loadMap();

        assertEquals(LauncherImpl.LAUNCHER.getCheckCollision().checkDynamicObstacleCollision(new Vector2D(14, 7)).get().getType(), ObstacleType.BIKE_SX);
        assertEquals(LauncherImpl.LAUNCHER.getCheckCollision().checkDynamicObstacleCollision(new Vector2D(2, 14)).get().getType(), ObstacleType.WAGON_DX);
        assertEquals(LauncherImpl.LAUNCHER.getCheckCollision().checkDynamicObstacleCollision(new Vector2D(4, 31)).get().getType(), ObstacleType.TRANSPARENT_OBSTACLE);
    }

    @Test
    void checkStaticObstacleCollision() {
        LauncherImpl.LAUNCHER.start();        
        LauncherImpl.LAUNCHER.setDifficulty(Difficulty.NORMAL);
        LauncherImpl.LAUNCHER.loadMap();

        assertEquals(LauncherImpl.LAUNCHER.getCheckCollision().checkStaticObstacleCollision(new Vector2D(2, 13)).get(), ObstacleType.FENCE);
        assertEquals(LauncherImpl.LAUNCHER.getCheckCollision().checkStaticObstacleCollision(new Vector2D(4, 4)).get(), ObstacleType.BUSH);
    }

    @Test
    void checkFinishLineCollision() {
        LauncherImpl.LAUNCHER.start();        
        LauncherImpl.LAUNCHER.setDifficulty(Difficulty.NORMAL);
        LauncherImpl.LAUNCHER.loadMap();

        assertTrue(LauncherImpl.LAUNCHER.getCheckCollision().checkFinishLineCollision(new Vector2D(2, 103)));
        assertFalse(LauncherImpl.LAUNCHER.getCheckCollision().checkFinishLineCollision(new Vector2D(6, 60)));
    }

    @Test
    void checkTrunkCollision() {
        LauncherImpl.LAUNCHER.start();        
        LauncherImpl.LAUNCHER.setDifficulty(Difficulty.NORMAL);
        LauncherImpl.LAUNCHER.loadMap();
        assertEquals(LauncherImpl.LAUNCHER.getCheckCollision().checkTrunkCollision(new Vector2D(1, 31)).get().getType(), ObstacleType.TRUNK_DX);
        assertFalse(LauncherImpl.LAUNCHER.getCheckCollision().checkTrunkCollision(new Vector2D(4, 20)).isPresent());
    }

    @Test
    void checkWallCollision() {
        LauncherImpl.LAUNCHER.start();        
        LauncherImpl.LAUNCHER.setDifficulty(Difficulty.NORMAL);
        LauncherImpl.LAUNCHER.loadMap();

        assertTrue(LauncherImpl.LAUNCHER.getCheckCollision().checkWallCollision(new Vector2D(15, 32)));
    }
}
