package it.unibo.project.view.api;

/**
 * class {@code Window} is the top of the {@code View} gerarchy.
 */
public interface Window {
    /**
     * changes scene to be handled.
     * 
     * @param scene
     */
    void setScene(Scene scene);

    /**
     * @return current {@linkplain Scene}
     */
    Scene getScene();

    /**
     * closes {@code gracefully} the window.
     * 
     * @see {@linkplain LauncherImpl}
     */
    void close();
}
