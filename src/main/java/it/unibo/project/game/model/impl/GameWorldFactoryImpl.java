package it.unibo.project.game.model.impl;

import it.unibo.project.game.model.api.GameWorld;
import it.unibo.project.game.model.api.GameWorldFactory;

/**
 * {@code factory} implementation for {@linkplain GameWorld}.
 */
public class GameWorldFactoryImpl implements GameWorldFactory {

    @Override
    public final GameWorld createGameWorld() {
        return new GameWorldImpl();
    }

}
