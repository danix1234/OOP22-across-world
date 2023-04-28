package it.unibo.project.view;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    }
}
