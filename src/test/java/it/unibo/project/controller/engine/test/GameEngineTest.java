package it.unibo.project.controller.engine.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.controller.engine.api.GameEngine;
import it.unibo.project.controller.engine.impl.GameEngineFactoryImpl;

/**
 * tests for {@linkplain GameEngine} class.
 */
class GameEngineTest {
    private final GameEngine engine = new GameEngineFactoryImpl().createGameEngine();

    /**
     * assure engine methods work.
     */
    @Test
    void testEngine() {
        // if scenetype was game -> endless loop
        assertDoesNotThrow(() -> {
            LauncherImpl.LAUNCHER.setScene(SceneType.MENU);
            this.engine.start();
            this.engine.render();
            this.engine.processInput();
            this.engine.updateGame();
        });
    }
}
