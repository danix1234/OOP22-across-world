package it.unibo.project.view;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.view.api.Scene;
import it.unibo.project.view.api.Window;
import it.unibo.project.view.impl.SceneFactoryImpl;
import it.unibo.project.view.impl.WindowFactoryImpl;

/**
 * tests for {@linkplain Scene}.
 */
class SceneTest {

    /**
     * assure all scene getters work.
     */
    @Test
    void testGetters() {
        final Window window = new WindowFactoryImpl().createWindow();
        final Scene scene = new SceneFactoryImpl().createScene(SceneType.OVER);
        window.setScene(scene);
        assertNotNull(window.getScene());
        assertNotNull(scene.getPanel());
        assertNotNull(scene.getInputHandler(SceneType.MENU));
    }
}
