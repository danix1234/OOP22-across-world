package it.unibo.project;

import javax.swing.SwingUtilities;

import it.unibo.project.controller.core.impl.LauncherImpl;

/**
 * Class {@code App}, contains the {@code main} function.
 */
public final class App {
    private App() {
    }

    /**
     * entry point of the program.
     * 
     * @param args strings passed from terminal
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            LauncherImpl.LAUNCHER.start();
            LauncherImpl.LAUNCHER.showWindow();
        });
    }
}
