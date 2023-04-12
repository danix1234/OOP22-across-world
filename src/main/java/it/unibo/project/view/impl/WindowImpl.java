package it.unibo.project.view.impl;

import java.util.Objects;
import java.util.Optional;

import javax.swing.JFrame;

import it.unibo.project.view.api.Scene;
import it.unibo.project.view.api.Window;

public class WindowImpl implements Window{
    private final JFrame frame = new JFrame("Across the world");
    private Scene scene = null;

    // TODO add window size (relative to screen dimension)
    public WindowImpl(){
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    @Override
    public void setScene(Scene scene) {
        Objects.requireNonNull(scene);
        Optional.ofNullable(this.scene).ifPresent(old_scene -> this.frame.remove(old_scene.getPanel()));
        this.scene = scene;
        this.frame.add(this.scene.getPanel());
        this.frame.validate();
    }

    @Override
    public Scene getScene() {
        return Optional.ofNullable(this.scene).orElseThrow();
    }
    
}
