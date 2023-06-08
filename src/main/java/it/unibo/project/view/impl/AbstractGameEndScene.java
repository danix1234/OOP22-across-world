package it.unibo.project.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.input.api.Action;

/**
 * An abstract class representing a generic game end scene.
 * Extends the {@link AbstractScene} class.
 */
public abstract class AbstractGameEndScene extends AbstractScene {
    private final JButton tryAgainButton;
    private final JButton backToMenu;
    private final JButton exitButton;
    private static final int BACKGROUND_RED = 40;
    private static final int BACKGROUND_GREEN = 40;
    private static final int BACKGROUND_BLUE = 40;
    private static final int TITLE_LABEL_HEIGHT = 50;
    private static final int WHITE_RED = 255;
    private static final int WHITE_GREEN = 255;
    private static final int WHITE_BLUE = 255;
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 70;
    private static final int FONT_SIZE = 20;
    private static final String FONT_NAME = "Arial";

    /**
     * Creates an abstract game end scene with the specified title and scene type.
     *
     * @param title The title of the game end scene.
     * @param type The scene type of the game end scene.
     */
    protected AbstractGameEndScene(final String title, final SceneType type) {

        // main panel
        final JPanel panel = new JPanel(new GridBagLayout());
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(BACKGROUND_RED, BACKGROUND_GREEN, BACKGROUND_BLUE));

        // game title label
        final JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, 100));
        titleLabel.setBounds(0, 10, panel.getWidth(), TITLE_LABEL_HEIGHT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // remove focus highlighting
        tryAgainButton = new JButton("RESTART");
        backToMenu = new JButton("BACK TO MENU");
        exitButton = new JButton("SAVE AND EXIT");

        // effetto testo evidenziato rimosso
        tryAgainButton.setFocusPainted(false);
        exitButton.setFocusPainted(false);

        // button settings
        tryAgainButton.setBackground(new Color(WHITE_RED, WHITE_GREEN, WHITE_BLUE));
        tryAgainButton.setForeground(Color.BLACK);
        tryAgainButton.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        tryAgainButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        backToMenu.setBackground(new Color(WHITE_RED, WHITE_GREEN, WHITE_BLUE));
        backToMenu.setForeground(Color.BLACK);
        backToMenu.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        backToMenu.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        exitButton.setBackground(new Color(WHITE_RED, WHITE_GREEN, WHITE_BLUE));
        exitButton.setForeground(Color.RED);
        exitButton.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        exitButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        // add buttons to the main panel
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel);

        gbc.gridy = 1;
        panel.add(tryAgainButton, gbc);

        gbc.gridy = 2;
        panel.add(backToMenu, gbc);

        gbc.gridy = 3;
        panel.add(exitButton, gbc);

        tryAgainButton.addActionListener((e) -> getInputHandler(type).executeAction(Action.CHANGE_SCENE_TO_GAME));
        backToMenu.addActionListener((e) -> getInputHandler(type).executeAction(Action.CHANGE_SCENE_TO_MENU));
        exitButton.addActionListener((e) -> getInputHandler(type).executeAction(Action.EXIT_APP));

        setPanel(panel);
    }

    /**
     * Retrieves the try again button.
     *
     * @return The try again button.
     */
    protected JButton getTryAgainButton() {
        return tryAgainButton;
    }

    /**
     * Retrieves the back to menu button.
     *
     * @return The back to menu button.
     */
    protected JButton getBackToMenu() {
        return backToMenu;
    }

    /**
     * Retrieves the exit button.
     *
     * @return The exit button.
     */
    protected JButton getExitButton() {
        return exitButton;
    }

    @Override
    public void update() {
    }
}
