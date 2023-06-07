package it.unibo.project.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.project.controller.core.api.Difficulty;
import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.input.api.Action;

public class MenuScene extends AbstractScene {
    private final JComboBox<String> difficultyComboBox;
    private static final int PANEL_BACKGROUND_RED = 40;
    private static final int PANEL_BACKGROUND_GREEN = 40;
    private static final int PANEL_BACKGROUND_BLUE = 40;
    private static final int TITLE_FONT_SIZE = 50;
    private static final int TITLE_HEIGHT = 50;
    private static final int WHITE_RED = 255;
    private static final int WHITE_GREEN = 255;
    private static final int WHITE_BLUE = 255;
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 70;
    private static final int FONT_SIZE = 20;
    private static final int GRID_Y_VALUE = 5;
    private static final String FONT_NAME = "Arial";

    public MenuScene() {
        // pannello principale
        final JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(PANEL_BACKGROUND_RED, PANEL_BACKGROUND_GREEN, PANEL_BACKGROUND_BLUE));

        // creazione del titolo del gioco
        final JLabel titleLabel = new JLabel("Across the world");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, TITLE_FONT_SIZE));
        titleLabel.setBounds(0, 10, panel.getWidth(), TITLE_HEIGHT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // creazione dei buttons del menu
        final JButton startButton = new JButton("START");
        final var difficulties = Arrays
                .stream(Difficulty.values())
                .map(Enum::name)
                .toArray(String[]::new);
        difficultyComboBox = new JComboBox<>(difficulties);
        final JButton shopButton = new JButton("SHOP");
        final JButton clearProgressButton = new JButton("CLEAR PROGRESS");
        final JButton exitButton = new JButton("SAVE AND EXIT");
        // bug fix: difficolty didn't change to what is shown
        //LauncherImpl.LAUNCHER.setDifficulty(Difficulty.valueOf(difficultyComboBox.getSelectedItem() + ""));
        LauncherImpl.LAUNCHER.setDifficulty(
            Difficulty.valueOf(
                difficultyComboBox.getSelectedItem()
                .toString().
                toUpperCase(Locale.ROOT)
            )
        );

        // elimino effetto testo evidenziato
        startButton.setFocusPainted(false);
        difficultyComboBox.setFocusable(false);
        shopButton.setFocusPainted(false);
        clearProgressButton.setFocusPainted(false);
        exitButton.setFocusPainted(false);

        // estetica dei buttons
        startButton.setBackground(new Color(WHITE_RED, WHITE_GREEN, WHITE_BLUE));
        startButton.setForeground(Color.BLACK);
        startButton.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        startButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        difficultyComboBox.setBackground(new Color(WHITE_RED, WHITE_GREEN, WHITE_BLUE));
        difficultyComboBox.setForeground(Color.BLACK);
        difficultyComboBox.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        difficultyComboBox.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        shopButton.setBackground(new Color(WHITE_RED, WHITE_GREEN, WHITE_BLUE));
        shopButton.setForeground(Color.BLACK);
        shopButton.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        shopButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        clearProgressButton.setBackground(new Color(WHITE_RED, WHITE_GREEN, WHITE_BLUE));
        clearProgressButton.setForeground(Color.BLACK);
        clearProgressButton.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        clearProgressButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        exitButton.setBackground(new Color(WHITE_RED, WHITE_GREEN, WHITE_BLUE));
        exitButton.setForeground(Color.RED);
        exitButton.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        exitButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        // aggiunta i pulsanti al pannello principale
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel);
        gbc.gridy = 1;
        panel.add(startButton, gbc);
        gbc.gridy = 2;
        panel.add(difficultyComboBox, gbc);
        gbc.gridy = 3;
        panel.add(shopButton, gbc);
        gbc.gridy = 4;
        panel.add(clearProgressButton, gbc);
        gbc.gridy = GRID_Y_VALUE;
        panel.add(exitButton, gbc);

        // aggiungo gli ActionListener
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                getInputHandler(SceneType.MENU).executeAction(Action.CHANGE_SCENE_TO_GAME);
            }
        });

        difficultyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String selectedDifficulty = (String) difficultyComboBox.getSelectedItem();
                LauncherImpl.LAUNCHER.setDifficulty(Difficulty.valueOf(selectedDifficulty));
            }
        });

        shopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                getInputHandler(SceneType.MENU).executeAction(Action.CHANGE_SCENE_TO_SHOP);
            }
        });

        clearProgressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                LauncherImpl.LAUNCHER.getLoader().deleteStatFile();
                getInputHandler(SceneType.MENU).executeAction(Action.NO_SAVE_EXIT_APP);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                getInputHandler(SceneType.MENU).executeAction(Action.EXIT_APP);
            }
        });
        setPanel(panel);
    }

    @Override
    public void update() {
    }
}
