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

public abstract class AbstractGameEndScene extends AbstractScene {
    private JPanel panel;
    private JButton tryAgainButton;
    private JButton backToMenu;
    private JButton exitButton;
    private JLabel titleLabel;
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

    protected AbstractGameEndScene(final String title, final Action tryAgainAction, 
    final Action backToMenuAction, final Action exitButtonAction, final SceneType type) {

        // pannello principale
        this.panel = new JPanel(new GridBagLayout());
        this.panel.setLayout(new GridBagLayout());
        this.panel.setBackground(new Color(BACKGROUND_RED, BACKGROUND_GREEN, BACKGROUND_BLUE));

        // creazione del titolo del gioco
        titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 100));
        titleLabel.setBounds(0, 10, panel.getWidth(), TITLE_LABEL_HEIGHT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // creazione dei pulsanti del menu
        tryAgainButton = new JButton("RESTART");
        backToMenu = new JButton("BACK TO MENU");
        exitButton = new JButton("SAVE AND EXIT");

        // effetto testo evidenziato rimosso
        tryAgainButton.setFocusPainted(false);
        exitButton.setFocusPainted(false);

        // impostazioni dei buttons
        tryAgainButton.setBackground(new Color(WHITE_RED, WHITE_GREEN, WHITE_BLUE));
        tryAgainButton.setForeground(Color.BLACK);
        tryAgainButton.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        tryAgainButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        backToMenu.setBackground(new Color(WHITE_RED, WHITE_GREEN, WHITE_BLUE));
        backToMenu.setForeground(Color.BLACK);
        backToMenu.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        backToMenu.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        exitButton.setBackground(new Color(WHITE_RED, WHITE_GREEN, WHITE_BLUE));
        exitButton.setForeground(Color.RED);
        exitButton.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        exitButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        // aggiunta dei pulsanti al pannello principale
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        this.panel.add(titleLabel);

        gbc.gridy = 1;
        this.panel.add(tryAgainButton, gbc);

        gbc.gridy = 2;
        this.panel.add(backToMenu, gbc);

        gbc.gridy = 3;
        this.panel.add(exitButton, gbc);

        tryAgainButton.addActionListener(((e) -> getInputHandler(type).executeAction(tryAgainAction)));
        backToMenu.addActionListener(((e) -> getInputHandler(type).executeAction(backToMenuAction)));
        exitButton.addActionListener(((e) -> getInputHandler(type).executeAction(exitButtonAction)));

        setPanel(this.panel);
    }

    protected JButton getTryAgainButton() {
        return tryAgainButton;
    }

    protected JButton getBackToMenu() {
        return backToMenu;
    }

    protected JButton getExitButton() {
        return exitButton;
    }

    @Override
    public void update() {
    }
}
