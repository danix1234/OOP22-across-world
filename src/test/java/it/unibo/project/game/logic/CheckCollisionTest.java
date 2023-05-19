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
    void checkCollision() {
        LauncherImpl.LAUNCHER.start();
        LauncherImpl.LAUNCHER.loadMap();
        LauncherImpl.LAUNCHER.setDifficulty(Difficulty.NORMAL);

        //Collectable collision
        assertEquals(LauncherImpl.LAUNCHER.getCheckCollision().checkCollectableCollision(new Vector2D(2, 6)).get(0).getType(), CollectableType.COIN);
        
        //Dynamic obstacle collision
        assertEquals(LauncherImpl.LAUNCHER.getCheckCollision().checkDynamicObstacleCollision(new Vector2D(14, 7)).get().getType(), ObstacleType.BIKE_SX);
        assertEquals(LauncherImpl.LAUNCHER.getCheckCollision().checkDynamicObstacleCollision(new Vector2D(2, 14)).get().getType(), ObstacleType.TRAIN_DX);
        assertEquals(LauncherImpl.LAUNCHER.getCheckCollision().checkDynamicObstacleCollision(new Vector2D(4, 31)).get().getType(), ObstacleType.TRANSPARENT_OBSTACLE);

        //Finish line collision
        assertTrue(LauncherImpl.LAUNCHER.getCheckCollision().checkFinishLineCollision(new Vector2D(2, 103)));
        assertFalse(LauncherImpl.LAUNCHER.getCheckCollision().checkFinishLineCollision(new Vector2D(6, 60)));

        //Static obstacle collision
        assertEquals(LauncherImpl.LAUNCHER.getCheckCollision().checkStaticObstacleCollision(new Vector2D(2, 13)).get(), ObstacleType.FENCE);
        assertEquals(LauncherImpl.LAUNCHER.getCheckCollision().checkStaticObstacleCollision(new Vector2D(4, 4)).get(), ObstacleType.BUSH);

        //Trunk collision
        assertEquals(LauncherImpl.LAUNCHER.getCheckCollision().checkTrunkCollision(new Vector2D(1, 32)).get().getType(), ObstacleType.TRUNK_DX);
        assertFalse(LauncherImpl.LAUNCHER.getCheckCollision().checkTrunkCollision(new Vector2D(4, 32)).isPresent());

        //Wall collision
        assertTrue(LauncherImpl.LAUNCHER.getCheckCollision().checkWallCollision(new Vector2D(15, 32)));
    }
}
