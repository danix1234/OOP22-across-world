package it.unibo.project.input;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.input.api.Action;
import it.unibo.project.input.impl.SharedInputHandler;

/**
 * tests for {@linkplain InputHandler} class.
 */
class InuptHandlerTest {

    @Test
    void testExecution() {
        assertDoesNotThrow(() -> {
            LauncherImpl.LAUNCHER.start();
            new SharedInputHandler().executeAction(Action.CHANGE_SCENE_TO_GAME);
            assertEquals(LauncherImpl.LAUNCHER.getSceneType(), SceneType.GAME);
            new SharedInputHandler().executeAction(Action.CHANGE_SCENE_TO_MENU);
            assertEquals(LauncherImpl.LAUNCHER.getSceneType(), SceneType.MENU);
        });
    }
}
