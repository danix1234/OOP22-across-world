package it.unibo.project.game.logic.impl;

import java.util.List;
import java.util.Optional;

import it.unibo.project.game.logic.api.CheckCollision;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.ObstacleType;

public class CheckCollisionImpl implements CheckCollision {

    @Override
    public Optional<CollectableType> checkCollectableCollision() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkCollectableCollision'");
    }

    @Override
    public boolean checkCoinLessDistantThen(int distance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkCoinLessDistantThen'");
    }

    @Override
    public Optional<ObstacleType> checkStaticObstacleCollision() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkStaticObstacleCollision'");
    }

    @Override
    public List<ObstacleType> checkDynamicObstacleCollision() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkDynamicObstacleCollision'");
    }

    @Override
    public boolean checkFinishLineCollision() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkFinishLineCollision'");
    }

    @Override
    public boolean checkWallCollision() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkWallCollision'");
    }
}
