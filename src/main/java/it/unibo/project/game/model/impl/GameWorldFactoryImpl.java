package it.unibo.project.game.model.impl;

import it.unibo.project.game.model.api.GameWorld;
import it.unibo.project.game.model.api.GameWorldFactory;

public class GameWorldFactoryImpl implements GameWorldFactory {

    @Override
    public GameWorld createGameWorld() {
        return new GameWorldImpl();
    }
    
}