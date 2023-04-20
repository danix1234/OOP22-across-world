package it.unibo.project.view.impl;

import java.awt.Toolkit;
import java.util.Objects;
import java.util.Optional;

import javax.swing.JFrame;

import it.unibo.project.view.api.Scene;
import it.unibo.project.view.api.Window;

/**
 * class {@code WindowImpl}, implements {@linkplain Window}.
 */
public class WindowImpl implements Window {
    private final JFrame frame = new JFrame("Across the world");
    private Scene scene;

    /**
     * {@code WindowImpl} constructor.
     */
    public WindowImpl() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationByPlatform(true);
        this.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.frame.setExtendedState(this.frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        //// uncomment following code to force fullscreen [WARNING: NOT RESIZABLE!] \\\\
        // final GraphicsDevice gd =
        // GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        // if (gd.isFullScreenSupported()) {
        // gd.setFullScreenWindow(this.frame);
        // }
        this.frame.setVisible(true);
    }

    @Override
    public final void setScene(final Scene scene) {
        Objects.requireNonNull(scene);
        Optional.ofNullable(this.scene).ifPresent(oldScene -> this.frame.remove(oldScene.getPanel()));
        this.scene = scene;
        this.frame.add(this.scene.getPanel());
        this.frame.validate();
    }

    @Override
    public final Scene getScene() {
        return Optional.ofNullable(this.scene).orElseThrow();
    }

    @Override
    public final void close() {
        this.frame.dispose();
    }

}
