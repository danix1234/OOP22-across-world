package it.unibo.project.game.logic.impl;

import java.util.ArrayList;
import java.util.List;
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
    public List<Collectable> checkCollectableCollision(Vector2D playerPos) {
        List<Collectable> collectables = new ArrayList<>();
        if (LauncherImpl.LAUNCHER.getHandlePowerup().getCurrentPowerUp()
                .filter(b -> b.equals(CollectableType.POWERUP_COIN_MAGNET)).isPresent()) {
            checkCoinLessDistantThen(1, collectables, playerPos);
        }
        LauncherImpl.LAUNCHER.getCollectables().stream()
                .filter(collectable -> LauncherImpl.LAUNCHER.getHandlePowerup().getCurrentPowerUp()
                        .filter(b -> b.equals(CollectableType.POWERUP_COIN_MAGNET)).isPresent()
                                ? !collectable.getType().equals(CollectableType.COIN)
                                : true)
                .filter(collectable -> collectable.getPosition()
                        .equals(playerPos))
                .findFirst()
                .map(collectable -> collectables.add(collectable));
        return collectables;
    }

    private List<Collectable> checkCoinLessDistantThen(final int distance, List<Collectable> collectables, Vector2D playerPos) {
        int x, y;
        for (x = -distance; x <= distance; x++) {
            for (y = -distance; y <= distance; y++) {
                final int innerX = x;
                final int innerY = y;
                LauncherImpl.LAUNCHER.getCollectables().stream()
                        .filter(collectable -> collectable.getType().equals(CollectableType.COIN))
                        .filter(collectable -> collectable.getPosition()
                                .equals(new Vector2D(playerPos.getX() + innerX,
                                        playerPos.getY() + innerY)))
                        .forEach(coin -> collectables.add(coin));
            }
        }
        return collectables;
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
        if (LauncherImpl.LAUNCHER.getHandlePowerup().getCurrentPowerUp()
                .filter(b -> b.equals(CollectableType.POWERUP_IMMORTALITY)).isEmpty()) {
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

    private Optional<Obstacle> checkRiverCollision(Vector2D playerPos) {
        return LauncherImpl.LAUNCHER.getObstacles().stream()
                .filter(obstacle -> obstacle.getType().isWalkableOn())
                .filter(trunkObstacle -> trunkObstacle.getPosition().equals(playerPos))
                .findFirst();
    }
}
