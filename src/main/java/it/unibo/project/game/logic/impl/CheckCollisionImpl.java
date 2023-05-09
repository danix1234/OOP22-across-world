package it.unibo.project.game.logic.impl;

import java.util.Optional;

import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.game.logic.api.CheckCollision;
import it.unibo.project.game.model.api.Collectable;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.ObstacleType;
import it.unibo.project.utility.Vector2D;

public class CheckCollisionImpl implements CheckCollision {

    @Override
    public Optional<CollectableType> checkCollectableCollision() {
        return LauncherImpl.LAUNCHER.getCollectables().stream()
                .filter(collectable -> collectable.getPosition()
                        .equals(LauncherImpl.LAUNCHER.getPlayer().getPosition()))
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
                        || obstacle.getType().equals(ObstacleType.TREE)
                        || obstacle.getType().equals(ObstacleType.TRASPARENT_WATER))
                .filter(staticObstacle -> staticObstacle.getPosition().equals(playerPos))
                .findFirst()
                .map(Obstacle::getType);
    }

    @Override
    public boolean checkFinishLineCollision() {
        return LauncherImpl.LAUNCHER.getPlayer().getPosition().getY()
                + LauncherImpl.LAUNCHER.getHeightDelta().get1() >= LauncherImpl.LAUNCHER.getLoader()
                        .getBackgroundCells(LauncherImpl.LAUNCHER.getDifficulty()).stream()
                        .map(cell -> cell.getPosition().getY())
                        .reduce(Integer::max)
                        .get();
    }

    @Override
    public boolean checkWallCollision() {
        return LauncherImpl.LAUNCHER.getPlayer().getPosition().getX() <= 0
                || LauncherImpl.LAUNCHER.getPlayer().getPosition().getX() >= LauncherImpl.LAUNCHER.getCellDim().get1();
    }

    @Override
    public Optional<ObstacleType> checkDynamicObstacleCollision() {
        return LauncherImpl.LAUNCHER.getObstacles().stream()
                .filter(obstacle -> obstacle.getType().equals(ObstacleType.CAR_DX)
                        || obstacle.getType().equals(ObstacleType.CAR_SX)
                        || obstacle.getType().equals(ObstacleType.TRAIN_DX)
                        || obstacle.getType().equals(ObstacleType.TRAIN_SX))
                .filter(dynamicObstacle -> dynamicObstacle.getPosition()
                        .equals(LauncherImpl.LAUNCHER.getPlayer().getPosition()))
                .findFirst()
                .map(Obstacle::getType);
    }
}
