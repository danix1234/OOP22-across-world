package it.unibo.project.view.impl;

import it.unibo.project.view.api.Window;
import it.unibo.project.view.api.WindowFactory;

public class WindowFactoryImpl implements WindowFactory {

    @Override
    public Window createWindow() {
        return new WindowImpl();
    }

}
