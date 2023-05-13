package it.unibo.project.game.logic.impl;

import java.util.Optional;
import java.util.function.Predicate;

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
    public Optional<Collectable> checkCollectableCollision(Vector2D playerPos) {
        return LauncherImpl.LAUNCHER.getCollectables().stream()
                .filter(collectable -> collectable.getPosition()
                        .equals(playerPos))
                .findFirst();
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
                .filter(Predicate.not(Obstacle::isMovable))
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
    public Optional<Obstacle> checkDynamicObstacleCollision(Vector2D playerPos) {
        if (checkRiverCollision(playerPos).isPresent()) {
            return checkRiverCollision(playerPos);
        }

        return LauncherImpl.LAUNCHER.getObstacles().stream()
                .filter(obstacle -> !obstacle.getType().equals(ObstacleType.TRUNK_DX)
                        && !obstacle.getType().equals(ObstacleType.TRUNK_SX))
                .filter(obstacle -> obstacle.isMovable() || obstacle.getType().equals(ObstacleType.TRANSPARENT_OBSTACLE))
                .filter(dynamicObstacle -> dynamicObstacle.getPosition()
                        .equals(playerPos))
                .findFirst();
    }

    private Optional<Obstacle> checkRiverCollision(Vector2D playerPos) {
        return LauncherImpl.LAUNCHER.getObstacles().stream()
                .filter(obstacle -> obstacle.getType().isWalkableOn())
                .filter(trunkObstacle -> trunkObstacle.getPosition().equals(playerPos))                
                .findFirst();
    }
}
