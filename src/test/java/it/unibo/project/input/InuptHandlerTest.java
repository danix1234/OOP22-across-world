package it.unibo.project.input;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.input.api.Action;

/**
 * tests for {@linkplain InputHandler} class.
 */
class InuptHandlerTest {

    @Test
    void testExecution() {
        assertDoesNotThrow(() -> {
            LauncherImpl.LAUNCHER.start();
            for (final var type : SceneType.values()) {
                LauncherImpl.LAUNCHER.getInputHandler(type).executeAction(Action.CHANGE_SCENE_TO_GAME);
                assertEquals(LauncherImpl.LAUNCHER.getSceneType(), SceneType.GAME);
                LauncherImpl.LAUNCHER.getInputHandler(type).executeAction(Action.CHANGE_SCENE_TO_OVER);
                assertEquals(LauncherImpl.LAUNCHER.getSceneType(), SceneType.OVER);
            }
        });
    }
}
