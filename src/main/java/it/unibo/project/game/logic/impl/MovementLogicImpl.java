package it.unibo.project.game.logic.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.game.logic.api.MovementLogic;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.ObstacleType;
import it.unibo.project.utility.Vector2D;

public class MovementLogicImpl implements MovementLogic {
    private final CheckCollisionImpl checker = new CheckCollisionImpl();
    private final HandlePowerupImpl powerupHandler = new HandlePowerupImpl();
    private final Map<Integer, Integer> lineRandomValue = new HashMap<>();
    private final Random random = new Random();

    @Override
    public void movePlayer(final int x, final int y) {
        final Vector2D nextPlayerPosition = new Vector2D(x, y);
        final var checkDynamicCollision = checker.checkDynamicObstacleCollision(nextPlayerPosition);

        if (checkDynamicCollision.isPresent()
                && (checkDynamicCollision.map(obstacle -> obstacle.getType()).get().equals(ObstacleType.TRUNK_DX)
                        || checkDynamicCollision.map(obstacle -> obstacle.getType()).get()
                                .equals(ObstacleType.TRUNK_SX))) {
            LauncherImpl.LAUNCHER.getPlayer().move(x, y);
        } else if (!checkDynamicCollision.isPresent()
                && checker.checkStaticObstacleCollision(nextPlayerPosition).isEmpty()
                && !checker.checkWallCollision(nextPlayerPosition)
                && !checker.checkFinishLineCollision(LauncherImpl.LAUNCHER.getPlayer().getPosition())) {
            checker.checkCollectableCollision(nextPlayerPosition).ifPresent(collectable -> {
                switch (collectable.getType()) {
                    case COIN:
                        LauncherImpl.LAUNCHER.getCollectables().remove(collectable);
                        LauncherImpl.LAUNCHER.getGameStat().addCoins(1);
                        break;
                    default:
                        LauncherImpl.LAUNCHER.getCollectables().remove(collectable);
                        powerupHandler.addPowerUp(collectable.getType());
                }
            });
            LauncherImpl.LAUNCHER.getPlayer().move(x, y);
        } else if (checker.checkFinishLineCollision(LauncherImpl.LAUNCHER.getPlayer().getPosition())) {
            LauncherImpl.LAUNCHER.setScene(SceneType.MENU);
        } else if (checkDynamicCollision.isPresent()) {
            LauncherImpl.LAUNCHER.setScene(SceneType.OVER);
        }
    }

    @Override
    public void moveObstacle() {
        final Vector2D playerPos = LauncherImpl.LAUNCHER.getPlayer().getPosition();
        final var checkDynamicCollision = checker.checkDynamicObstacleCollision(playerPos);

        LauncherImpl.LAUNCHER.getObstacles()
                .stream()
                .filter(Obstacle::isMovable)
                .forEach(obstacle -> {
                    final var type = obstacle.getType();
                    final var pixelX = obstacle.getPixelPosition();
                    final var wrapAround = type.getWrapAroundDim() * LauncherImpl.CELL_DIM;
                    final var cellY = obstacle.getPosition().getY();
                    final var minSpeed = Double.min(type.getMinSpeed(), type.getMaxSpeed());
                    final var maxSpeed = Double.max(type.getMinSpeed(), type.getMaxSpeed());
                    var speed = type.getSpeed();
                    if (LauncherImpl.RANDOMIZE_SPEED) {
                        calculateLineRandomValue(cellY);
                        speed = new Random(this.lineRandomValue.get(cellY)).nextDouble(minSpeed, maxSpeed);
                    }
                    obstacle.movePixelPosition((pixelX + wrapAround + speed) % wrapAround);
                    final var cellPos = LauncherImpl.LAUNCHER.convertPixelToCellPos(pixelX, cellY);
                    obstacle.move(cellPos.getX(), cellPos.getY());
                    if (obstacle.getPosition().equals(playerPos)) {
                        System.out.println(type);
                    }
                });

        if (checkDynamicCollision.isPresent()
                && (checkDynamicCollision.map(obstacle -> obstacle.getType()).get()
                        .equals(ObstacleType.TRUNK_DX) || checkDynamicCollision.map(obstacle -> obstacle.getType()).get()
                        .equals(ObstacleType.TRUNK_DX))) {
            LauncherImpl.LAUNCHER.movePlayerIfPossible(checkDynamicCollision.get().getPosition().getX(),
                    checkDynamicCollision.get().getPosition().getY());

        } else if (checkDynamicCollision.isPresent()) {
            LauncherImpl.LAUNCHER.setScene(SceneType.OVER);
        }
    }

    private void calculateLineRandomValue(final int line) {
        if (!lineRandomValue.containsKey(line)) {
            lineRandomValue.put(line, random.nextInt(0, Integer.MAX_VALUE));
        }
    }
}
