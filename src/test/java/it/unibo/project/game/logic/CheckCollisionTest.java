package it.unibo.project.game.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
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
    @BeforeAll
    static void initialize() {
        LauncherImpl.LAUNCHER.start();
        LauncherImpl.LAUNCHER.setDifficulty(Difficulty.NORMAL);
        LauncherImpl.LAUNCHER.loadMap();
    }

    /**
     * assure all collision of collectable type are detected.
     */
    @Test
    void checkCollectableCollision() {
        final Vector2D pos1 = new Vector2D(8, 26);
        assertEquals(LauncherImpl.LAUNCHER
                .getCheckCollision()
                .checkCollectableCollision(pos1).get(0)
                .getType(),
                CollectableType.COIN);
    }

    /**
     * assure all collision of dynamic obstacle are detected.
     */
    @Test
    void checkDynamicObstacleCollision() {
        final Vector2D pos1 = new Vector2D(14, 7);
        final Vector2D pos2 = new Vector2D(2, 14);
        final Vector2D pos3 = new Vector2D(4, 31);
        assertEquals(LauncherImpl.LAUNCHER.getCheckCollision()
                .checkDynamicObstacleCollision(pos1)
                .get()
                .getType(),
                ObstacleType.BIKE_SX);
        assertEquals(LauncherImpl.LAUNCHER.getCheckCollision()
                .checkDynamicObstacleCollision(pos2)
                .get()
                .getType(),
                ObstacleType.WAGON_DX);
        assertEquals(LauncherImpl.LAUNCHER.getCheckCollision()
                .checkDynamicObstacleCollision(pos3)
                .get()
                .getType(),
                ObstacleType.TRANSPARENT_OBSTACLE);
    }

    /**
     * assure all collision of static obstacle are detected.
     */
    @Test
    void checkStaticObstacleCollision() {
        final Vector2D pos1 = new Vector2D(2, 13);
        final Vector2D pos2 = new Vector2D(4, 4);
        assertEquals(LauncherImpl.LAUNCHER
                .getCheckCollision()
                .checkStaticObstacleCollision(pos1)
                .get(),
                ObstacleType.FENCE);
        assertEquals(LauncherImpl.LAUNCHER
                .getCheckCollision()
                .checkStaticObstacleCollision(pos2)
                .get(),
                ObstacleType.BUSH);
    }

    /**
     * assure all collision of finish line are detected.
     */
    @Test
    void checkFinishLineCollision() {
        final Vector2D pos1 = new Vector2D(2, 103);
        final Vector2D pos2 = new Vector2D(6, 60);
        assertTrue(LauncherImpl.LAUNCHER
                .getCheckCollision()
                .checkFinishLineCollision(pos1));
        assertFalse(LauncherImpl.LAUNCHER
                .getCheckCollision()
                .checkFinishLineCollision(pos2));
    }

    /**
     * assure all collision of trunks are detected.
     */
    @Test
    void checkTrunkCollision() {
        final Vector2D pos1 = new Vector2D(1, 31);
        final Vector2D pos2 = new Vector2D(4, 20);
        assertEquals(LauncherImpl.LAUNCHER
                .getCheckCollision()
                .checkTrunkCollision(pos1)
                .get()
                .getType(),
                ObstacleType.TRUNK_DX);
        assertFalse(LauncherImpl.LAUNCHER
                .getCheckCollision()
                .checkTrunkCollision(pos2)
                .isPresent());
    }

    /**
     * assure all collision to the walls are detected.
     */
    @Test
    void checkWallCollision() {
        final Vector2D pos1 = new Vector2D(15, 32);
        assertTrue(LauncherImpl.LAUNCHER
                .getCheckCollision()
                .checkWallCollision(pos1));
    }
}
