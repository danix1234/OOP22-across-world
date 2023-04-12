package it.unibo.project.controller.engine.impl;

import it.unibo.project.controller.engine.api.GameEngine;
import it.unibo.project.controller.engine.api.GameEngineFactory;

public class GameEngineFactoryImpl implements GameEngineFactory {

    @Override
    public GameEngine createGameEngine() {
        return new GameEngineImpl();
    }
    
}
