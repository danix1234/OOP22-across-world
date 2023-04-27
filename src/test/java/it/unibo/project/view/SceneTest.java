package it.unibo.project.view;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.view.api.Scene;

/**
 * tests for {@linkplain Scene}.
 */
class SceneTest {

    /**
     * assure all scene getters work.
     */
    @Test
    void testGetters() {
        LauncherImpl.LAUNCHER.start();
        final Scene scene = LauncherImpl.LAUNCHER.getScene();
        assertNotNull(scene.getPanel());
        assertNotNull(scene.getInputHandler(SceneType.MENU));
        
        // strong equality (input handler MUST BE THE SAME OBJECT, not just similar) 
        assertTrue(scene.getInputHandler(SceneType.GAME) == LauncherImpl.LAUNCHER.getInputHandler(SceneType.GAME));
        assertFalse(scene.getInputHandler(SceneType.MENU) == LauncherImpl.LAUNCHER.getInputHandler(SceneType.OVER));
    }
}
