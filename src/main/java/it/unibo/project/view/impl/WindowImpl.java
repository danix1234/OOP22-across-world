package it.unibo.project.view.impl;

import java.util.Objects;
import java.util.Optional;

import javax.swing.JFrame;

import it.unibo.project.view.api.Scene;
import it.unibo.project.view.api.Window;
import java.awt.Toolkit;

/**
 * class {@code WindowImpl}, implements {@linkplain Window}.
 */
public final class WindowImpl implements Window {
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
    }

    @Override
    public void setScene(final Scene scene) {
        Objects.requireNonNull(scene);
        Optional.ofNullable(this.scene).ifPresent(oldScene -> this.frame.remove(oldScene.getPanel()));
        this.scene = scene;
        this.frame.add(this.scene.getPanel());
        this.frame.validate();
    }

    @Override
    public Scene getScene() {
        return Optional.ofNullable(this.scene).orElseThrow();
    }

    @Override
    public void close() {
        this.frame.dispose();
    }

    @Override
    public void show() {
        this.frame.setVisible(true);
    }

}
