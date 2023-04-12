package it.unibo.project.game.logic.impl;

import it.unibo.project.game.logic.api.GameLogic;
import it.unibo.project.game.logic.api.GameLogicFactory;

public class GameLogicFactoryImpl implements GameLogicFactory {

    @Override
    public GameLogic creatGameLogic() {
        return new GameLogicImpl();
    }
    
}
