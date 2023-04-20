package it.unibo.project.view.impl;

import it.unibo.project.view.api.Window;
import it.unibo.project.view.api.WindowFactory;

/**
 * {@code factory} implementation for {@linkplain Window}.
 */
public class WindowFactoryImpl implements WindowFactory {

    @Override
    public final Window createWindow() {
        return new WindowImpl();
    }

}
