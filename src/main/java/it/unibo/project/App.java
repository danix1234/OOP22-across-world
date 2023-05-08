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
            /*
             * to avoid stats being saved, when not loaded yet
             * 
             * note:
             * - loading happens when game scene start because maps and entity depends on
             * difficulty chosen, thus it need to happen only after we are sure difficulty
             * can't be changed anymore!
             * 
             * - maps and entity are loaded twice by doing this, so refactor this if loading
             * becomes computationally expensive!
             */
            LauncherImpl.LAUNCHER.loadMap();
            LauncherImpl.LAUNCHER.showWindow();
        });
    }
}
