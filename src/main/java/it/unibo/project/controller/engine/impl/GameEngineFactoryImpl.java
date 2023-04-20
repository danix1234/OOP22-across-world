package it.unibo.project.controller.engine.impl;

import it.unibo.project.controller.engine.api.GameEngine;
import it.unibo.project.controller.engine.api.GameEngineFactory;

/**
 * {@code factory} implementation for {@linkplain GameEngine}.
 */
public class GameEngineFactoryImpl implements GameEngineFactory {

    @Override
    public final GameEngine createGameEngine() {
        return new GameEngineImpl();
    }

}
