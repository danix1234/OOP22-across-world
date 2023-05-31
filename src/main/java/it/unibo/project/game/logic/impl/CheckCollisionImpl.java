package it.unibo.project.game.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.game.logic.api.CheckCollision;
import it.unibo.project.game.model.api.BackgroundCellType;
import it.unibo.project.game.model.api.Collectable;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.ObstacleType;
import it.unibo.project.utility.Vector2D;

/**
 * Class {@code CheckCollisionImpl}, implements {@link CheckCollision}.
 */
public final class CheckCollisionImpl implements CheckCollision {

    @Override
    public List<Collectable> checkCollectableCollision(final Vector2D playerPos) {
        final List<Collectable> collectables = new ArrayList<>();
        if (LauncherImpl.LAUNCHER.getHandlePowerup().getCurrentPowerUp().stream()
                .filter(powerup -> powerup.equals(CollectableType.POWERUP_COIN_MAGNET))
                .findAny().isPresent()) {
            checkCoinLessDistantThen(1, collectables, playerPos);
        }
        LauncherImpl.LAUNCHER.getCollectables().stream()
                .filter(collectable -> LauncherImpl.LAUNCHER.getHandlePowerup().getCurrentPowerUp()
                        .stream()
                        .filter(powerup -> powerup.equals(CollectableType.POWERUP_COIN_MAGNET))
                        .findAny().isPresent()
                                ? collectable.getType().isPowerUp()
                                : true)
                .filter(collectable -> collectable.getPosition()
                        .equals(playerPos))
                .findFirst()
                .map(collectable -> collectables.add(collectable));
        return collectables;
    }

    private List<Collectable> checkCoinLessDistantThen(final int distance, final List<Collectable> collectables,
            final Vector2D playerPos) {
        int x, y;
        for (x = -distance; x <= distance; x++) {
            for (y = -distance; y <= distance; y++) {
                final int innerX = x;
                final int innerY = y;
                LauncherImpl.LAUNCHER.getCollectables().stream()
                        .filter(collectable -> collectable.getType().isCoin())
                        .filter(collectable -> collectable.getPosition()
                                .equals(new Vector2D(playerPos.getX() + innerX,
                                        playerPos.getY() + innerY)))
                        .forEach(coin -> collectables.add(coin));
            }
        }
        return collectables;
    }

    @Override
    public Optional<ObstacleType> checkStaticObstacleCollision(final Vector2D playerPos) {
        return LauncherImpl.LAUNCHER.getObstacles().stream()
                .filter(obstacle -> !obstacle.isMovable()
                        && !obstacle.getType().equals(ObstacleType.TRANSPARENT_OBSTACLE))
                .filter(staticObstacle -> staticObstacle.getPosition().equals(playerPos))
                .findFirst()
                .map(Obstacle::getType);
    }

    @Override
    public boolean checkFinishLineCollision(final Vector2D playerPos) {
        return LauncherImpl.LAUNCHER.getBackgroundCells().stream()
                .filter(background -> background.getType().equals(BackgroundCellType.FINISHLINE))
                .filter(finishline -> finishline.getPosition().equals(playerPos))
                .findFirst()
                .isPresent();
    }

    @Override
    public boolean checkWallCollision(final Vector2D playerPos) {
        return playerPos.getX() < 0 || playerPos.getX() >= LauncherImpl.ORIZ_CELL;
    }

    @Override
    public Optional<Obstacle> checkDynamicObstacleCollision(final Vector2D playerPos) {
        if (checkTrunkCollision(playerPos).isPresent()) {
            return checkTrunkCollision(playerPos);
        }
        if (LauncherImpl.LAUNCHER.getHandlePowerup().getCurrentPowerUp()
        .stream()
                .filter(b -> b.equals(CollectableType.POWERUP_IMMORTALITY))
                .findAny().isEmpty()) {
            return LauncherImpl.LAUNCHER.getObstacles().stream()
                    .filter(obstacle -> !obstacle.getType().isWalkableOn())
                    .filter(obstacle -> obstacle.isMovable()
                            || obstacle.getType().equals(ObstacleType.TRANSPARENT_OBSTACLE))
                    .filter(dynamicObstacle -> dynamicObstacle.getPosition()
                            .equals(playerPos))
                    .findFirst();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Obstacle> checkTrunkCollision(final Vector2D playerPos) {
        return LauncherImpl.LAUNCHER.getObstacles().stream()
                .filter(obstacle -> obstacle.getType().isWalkableOn())
                .filter(trunkObstacle -> trunkObstacle.getPosition().equals(playerPos))
                .findFirst();
    }
}
