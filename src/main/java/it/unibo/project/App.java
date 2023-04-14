package it.unibo.project;

import it.unibo.project.controller.core.impl.LauncherImpl;

public final class App {
    private App() {
    }

    public static void main(final String[] args) {
        LauncherImpl.LAUNCHER.start();
    }
}
