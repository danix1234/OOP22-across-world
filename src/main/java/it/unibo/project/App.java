package it.unibo.project;

import it.unibo.project.controller.core.impl.LauncherFactoryImpl;

public class App{
    public static void main(String[] args) {
        new LauncherFactoryImpl().createLauncher().start();
    }
}
