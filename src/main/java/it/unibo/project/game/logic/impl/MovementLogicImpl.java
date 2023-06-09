package it.unibo.project.game.logic.impl;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.game.logic.api.CheckCollision;
import it.unibo.project.game.logic.api.HandlePowerup;
import it.unibo.project.game.logic.api.MovementLogic;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.utility.RandomizeLine;
import it.unibo.project.utility.Vector2D;

/**
 * Class {@code MovementLogicImpl}, implements {@link MovementLogic}.
 */
public final class MovementLogicImpl implements MovementLogic {
    private final RandomizeLine randomizeLine = new RandomizeLine();
    private double speedMultiplier = 1.0;

    @Override
    public void movePlayer(final int x, final int y) {
        final CheckCollision checker = LauncherImpl.LAUNCHER.getCheckCollision();
        final HandlePowerup powerupHandler = LauncherImpl.LAUNCHER.getHandlePowerup();
        final Vector2D nextPlayerPosition = new Vector2D(x, y);
        final var checkDynamicCollision = checker.checkDynamicObstacleCollision(nextPlayerPosition);
        final int coinMultipliedValue = 5;

        if (checkDynamicCollision.isPresent() // se è sul tronco e non c'è collisione col muro entra
                && checkDynamicCollision.map(obstacle -> obstacle.getType()).get().isWalkableOn()
                && !checker.checkWallCollision(nextPlayerPosition)) {
            LauncherImpl.LAUNCHER.getPlayer().move(x, y);
        } else if (!checkDynamicCollision.isPresent() // se non collide con bordi o ostacoli o traguardo entra
                && checker.checkStaticObstacleCollision(nextPlayerPosition).isEmpty()
                && !checker.checkWallCollision(nextPlayerPosition)
                && !checker.checkFinishLineCollision(LauncherImpl.LAUNCHER.getPlayer().getPosition())) {
            checker.checkCollectableCollision(nextPlayerPosition).stream().forEach(collectable -> {
                switch (collectable.getType()) {
                    case COIN:
                        LauncherImpl.LAUNCHER.removeCollectable(collectable);
                        LauncherImpl.LAUNCHER.getGameStat()
                                .addCoins(powerupHandler.getCurrentPowerUp()
                                        .stream()
                                        .filter(powerup -> powerup.equals(CollectableType.POWERUP_COIN_MULTIPLIER))
                                        .findAny()
                                        .isPresent() ? coinMultipliedValue : collectable.getType().getCoinValue().get());
                        break;
                    default:
                        LauncherImpl.LAUNCHER.removeCollectable(collectable);
                        powerupHandler.addPowerUp(collectable.getType());
                }
            });
            LauncherImpl.LAUNCHER.getPlayer().move(x, y);
        } else if (checker.checkFinishLineCollision(LauncherImpl.LAUNCHER.getPlayer().getPosition())) {
            LauncherImpl.LAUNCHER.setScene(SceneType.VICTORY);
        } else if (checkDynamicCollision.stream().filter(obstacle -> !obstacle.getType().isWalkableOn()).findAny()
                .isPresent()) { // se collide con un ostacolo
            LauncherImpl.LAUNCHER.setScene(SceneType.OVER);
        }
    }

    @Override
    public void moveObstacle() {
        final CheckCollision checker = LauncherImpl.LAUNCHER.getCheckCollision();
        final Vector2D playerPos = LauncherImpl.LAUNCHER.getPlayer().getPosition();
        final var checkDynamicCollision = checker.checkDynamicObstacleCollision(playerPos);

        LauncherImpl.LAUNCHER.getObstacles()
                .stream()
                .filter(Obstacle::isMovable)
                .forEach(obstacle -> {
                    final var type = obstacle.getType();
                    final var pixelX = obstacle.getPixelPosition();
                    final var cellY = obstacle.getPosition().getY();
                    final var wrapAround = type.getWrapAroundDim() * LauncherImpl.CELL_DIM
                            + LauncherImpl.CELL_DIM / 2;
                    var speed = type.getSpeed();
                    if (LauncherImpl.RANDOMIZE_SPEED) {
                        speed = this.randomizeLine.getLineRandomNumber(
                                type.getMinSpeed(),
                                type.getMaxSpeed(),
                                cellY);
                    }
                    speed *= speedMultiplier;
                    obstacle.movePixelPosition((pixelX + wrapAround + speed) % wrapAround);
                    final var cellPos = LauncherImpl.LAUNCHER.convertPixelToCellPos(pixelX, cellY);
                    obstacle.move(cellPos.getX(), cellPos.getY());
                });

        if (checkDynamicCollision.isPresent()
                && checkDynamicCollision.map(obstacle -> obstacle.getType()).get().isWalkableOn()
                && !checker.checkWallCollision(playerPos)) {
            if (LauncherImpl.REMAIN_PLAYER_ON_TRUNK) {
                LauncherImpl.LAUNCHER.movePlayerIfPossible(checkDynamicCollision.get().getPosition().getX(),
                        checkDynamicCollision.get().getPosition().getY());
            }
        } else if (checkDynamicCollision.isPresent()) {
            LauncherImpl.LAUNCHER.setScene(SceneType.OVER);
        }
    }

    @Override
    public void setSpeedMultiplier(final double value) {
        this.speedMultiplier = value;
    }
}
