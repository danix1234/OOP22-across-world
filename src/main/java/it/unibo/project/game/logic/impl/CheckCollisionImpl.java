package it.unibo.project.game.logic.impl;

import java.util.Optional;

import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.game.logic.api.CheckCollision;
import it.unibo.project.game.model.api.BackgroundCellType;
import it.unibo.project.game.model.api.Collectable;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.ObstacleType;
import it.unibo.project.utility.Vector2D;

public class CheckCollisionImpl implements CheckCollision {

    @Override
    public Optional<CollectableType> checkCollectableCollision(Vector2D playerPos) {
        return LauncherImpl.LAUNCHER.getCollectables().stream()
                .filter(collectable -> collectable.getPosition()
                        .equals(playerPos))
                .findFirst()
                .map(Collectable::getType);
    }

    @Override
    public int checkCoinLessDistantThen(final int distance) {
        int collectedCoinCounter = 0;
        int x, y;
        for (x = -distance; x <= distance; x++) {
            for (y = -distance; y <= distance; y++) {
                final int innerX = x;
                final int innerY = y;
                if (!LauncherImpl.LAUNCHER.getCollectables().stream()
                        .filter(collectable -> collectable.getType().equals(CollectableType.COIN))
                        .filter(collectable -> collectable.getPosition()
                                .equals(new Vector2D(LauncherImpl.LAUNCHER.getPlayer().getPosition().getX() + innerX,
                                        LauncherImpl.LAUNCHER.getPlayer().getPosition().getY() + innerY)))
                        .findFirst()
                        .map(Collectable::getType).isEmpty()) {
                    collectedCoinCounter++;
                }
            }
        }
        return collectedCoinCounter;
    }

    @Override
    public Optional<ObstacleType> checkStaticObstacleCollision(Vector2D playerPos) {
        return LauncherImpl.LAUNCHER.getObstacles().stream()
                .filter(obstacle -> obstacle.getType().equals(ObstacleType.BUSH)
                        || obstacle.getType().equals(ObstacleType.TREE))
                .filter(staticObstacle -> staticObstacle.getPosition().equals(playerPos))
                .findFirst()
                .map(Obstacle::getType);
    }

    @Override
    public boolean checkFinishLineCollision(Vector2D playerPos) {
        return LauncherImpl.LAUNCHER.getBackgroundCells().stream()
                .filter(background -> background.getType().equals(BackgroundCellType.FINISHLINE))
                .filter(finishline -> finishline.getPosition().equals(playerPos))
                .findFirst()
                .isPresent();
    }

    @Override
    public boolean checkWallCollision(Vector2D playerPos) {
        return playerPos.getX() < 0 || playerPos.getX() >= LauncherImpl.ORIZ_CELL;
    }

    @Override
    public boolean checkDynamicObstacleCollision(Vector2D playerPos) {
        if (checkRiverCollision(playerPos)) {
            return false;
        }
        return LauncherImpl.LAUNCHER.getObstacles().stream()
                .filter(obstacle -> !obstacle.getType().equals(ObstacleType.TRUNK_DX)
                        && !obstacle.getType().equals(ObstacleType.TRUNK_SX))
                .filter(obstacle -> obstacle.isMovable() || obstacle.getType().equals(ObstacleType.TRANSPARENT_WATER))
                .filter(dynamicObstacle -> dynamicObstacle.getPosition()
                        .equals(playerPos))
                .findFirst()
                .isPresent();
    }

    private boolean checkRiverCollision(Vector2D playerPos) {
        return LauncherImpl.LAUNCHER.getObstacles().stream()
                .filter(obstacle -> obstacle.getType().equals(ObstacleType.TRANSPARENT_WATER))
                .filter(waterObstacle -> waterObstacle.getPosition().equals(playerPos))
                .filter(waterWithPlayer -> LauncherImpl.LAUNCHER.getObstacles().stream()
                                .filter(obstacle -> obstacle.getType().equals(ObstacleType.TRUNK_DX)
                                        || obstacle.getType().equals(ObstacleType.TRUNK_SX))
                                .filter(trunkObstacle -> trunkObstacle.getPosition().equals(waterWithPlayer.getPosition()))
                                .findFirst()
                                .map(Obstacle::getPosition)
                                .isPresent())
                .findFirst()
                .isPresent();
        /*
         * return LauncherImpl.LAUNCHER.getObstacles().stream()
         * .filter(obstacle -> obstacle.getType().equals(ObstacleType.TRASPARENT_WATER)
         * || obstacle.getType().equals(ObstacleType.TRUNK_DX) ||
         * obstacle.getType().equals(ObstacleType.TRUNK_SX))
         * .filter()
         */
    }
}
