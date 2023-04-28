package it.unibo.project.view;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.view.api.Scene;
import it.unibo.project.view.api.Window;
import it.unibo.project.view.impl.SceneFactoryImpl;
import it.unibo.project.view.impl.WindowFactoryImpl;

/**
 * tests for {@linkplain Window} class.
 */
class WindowTest {

    /**
     * assure scene getter/setter work.
     */
    @Test
    void testScene() {
        final Window window = new WindowFactoryImpl().createWindow();
        final Scene scene = new SceneFactoryImpl().createScene(SceneType.OVER);

        assertDoesNotThrow(() -> {
            window.setScene(scene);
        });
    }
}
