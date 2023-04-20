package it.unibo.project.game.logic.impl;

import it.unibo.project.game.logic.api.GameLogic;
import it.unibo.project.game.logic.api.GameLogicFactory;

/**
 * {@code factory} implementation for {@linkplain GameLogic}.
 */
public class GameLogicFactoryImpl implements GameLogicFactory {

    @Override
    public final GameLogic creatGameLogic() {
        return new GameLogicImpl();
    }

}
