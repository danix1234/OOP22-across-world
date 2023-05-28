package it.unibo.project.input;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import it.unibo.project.input.api.Action;
import it.unibo.project.input.impl.SharedInputHandler;

/**
 * tests for {@linkplain InputHandler} class.
 */
class InuptHandlerTest {

    @Test
    void testExecution() {
        assertDoesNotThrow(() -> {
            new SharedInputHandler().executeAction(Action.CHANGE_SCENE_TO_GAME);
            new SharedInputHandler().executeAction(Action.CHANGE_SCENE_TO_MENU);
            new SharedInputHandler().executeAction(Action.CHANGE_SCENE_TO_OVER);
            new SharedInputHandler().executeAction(Action.CHANGE_SCENE_TO_SHOP);
        });
    }
}
