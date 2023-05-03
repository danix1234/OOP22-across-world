package it.unibo.project.game.logic.impl;

import java.util.Optional;

import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.game.logic.api.CheckCollision;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.ObstacleType;

public class CheckCollisionImpl implements CheckCollision {

    @Override
    public Optional<CollectableType> checkCollectableCollision() {
        return Optional.of(LauncherImpl.LAUNCHER.getCollectables().stream()
        .filter(collectable -> collectable.getPosition()==LauncherImpl.LAUNCHER.getPlayer().getPosition())
        .findFirst()
        .get()
        .getType());
    }

    @Override
    public int checkCoinLessDistantThen(final int distance) {
        int collectedCoinCounter = 0;
        for(int x=-1;x<=distance;x++){
            for(int y=-1;y<=distance;y++){
                if(!Optional.of(LauncherImpl.LAUNCHER.getCollectables().stream()
                .filter(collectable -> collectable.getType()==CollectableType.COIN)
                .filter(collectable -> collectable.getPosition()==LauncherImpl.LAUNCHER.getPlayer().getPosition())
                .findFirst()
                .get()
                .getType()).isEmpty()){
                    collectedCoinCounter++;
                }
            }
        }
        return collectedCoinCounter;
    }

    @Override
    public Optional<ObstacleType> checkStaticObstacleCollision() {
        return Optional.of(LauncherImpl.LAUNCHER.getObstacles().stream()
        .filter(obstacle -> obstacle.getType()==ObstacleType.BUSH || obstacle.getType()==ObstacleType.TREE || obstacle.getType()==ObstacleType.TRASPARENT_WATER)
        .filter(staticObstacle -> staticObstacle.getPosition()==LauncherImpl.LAUNCHER.getPlayer().getPosition())        
        .findFirst()
        .get()
        .getType());
    }

    @Override
    public boolean checkFinishLineCollision() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkFinishLineCollision'");
    }

    @Override
    public boolean checkWallCollision() {
        return LauncherImpl.LAUNCHER.getPlayer().getPosition().getX()<0 || LauncherImpl.LAUNCHER.getPlayer().getPosition().getX()>14 ? true : false;
    }

    @Override
    public Optional<ObstacleType> checkDynamicObstacleCollision() {
        return Optional.of(LauncherImpl.LAUNCHER.getObstacles().stream()
        .filter(obstacle -> obstacle.getType()==ObstacleType.CAR_DX || obstacle.getType()==ObstacleType.CAR_SX || obstacle.getType()==ObstacleType.TRAIN_DX || obstacle.getType()==ObstacleType.TRAIN_SX)
        .filter(dynamicObstacle -> dynamicObstacle.getPosition()==LauncherImpl.LAUNCHER.getPlayer().getPosition())        
        .findFirst()
        .get()
        .getType());
    }
}
