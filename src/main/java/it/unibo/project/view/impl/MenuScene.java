package it.unibo.project.view.impl;

import javax.swing.*;

import it.unibo.project.controller.core.api.Difficulty;
import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.input.api.Action;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class MenuScene extends AbstractScene {
    private JPanel panel;
    private JButton startButton;
    private JComboBox<String> difficultyComboBox;
    private JButton exitButton;
    private JButton shopButton;
    private JLabel titleLabel;

    public MenuScene() {
        // pannello principale
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setBackground(new Color(40, 40, 40));

        // creazione del titolo del gioco
        titleLabel = new JLabel("Across the world");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setBounds(0, 10, panel.getWidth(), 50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // creazione dei buttons del menu
        startButton = new JButton("START");
        var difficulties = Arrays
                .stream(Difficulty.values())
                .map(Enum::name)
                .toArray(String[]::new);
        difficultyComboBox = new JComboBox<>(difficulties);
        shopButton = new JButton("SHOP");
        exitButton = new JButton("EXIT");
        // bug fix: difficolty didn't change to what is shown
        LauncherImpl.LAUNCHER.setDifficulty(Difficulty.valueOf(difficultyComboBox.getSelectedItem() + ""));

        // elimino effetto testo evidenziato
        startButton.setFocusPainted(false);
        difficultyComboBox.setFocusable(false);
        shopButton.setFocusPainted(false);
        exitButton.setFocusPainted(false);

        // estetica dei buttons
        startButton.setBackground(new Color(255, 255, 255));
        startButton.setForeground(Color.BLACK);
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setPreferredSize(new Dimension(200, 70));

        difficultyComboBox.setBackground(new Color(255, 255, 255));
        difficultyComboBox.setForeground(Color.BLACK);
        difficultyComboBox.setFont(new Font("Arial", Font.BOLD, 20));
        difficultyComboBox.setPreferredSize(new Dimension(200, 70));

        shopButton.setBackground(new Color(255, 255, 255));
        shopButton.setForeground(Color.BLACK);
        shopButton.setFont(new Font("Arial", Font.BOLD, 20));
        shopButton.setPreferredSize(new Dimension(200, 70));

        exitButton.setBackground(new Color(255, 255, 255));
        exitButton.setForeground(Color.RED);
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.setPreferredSize(new Dimension(200, 70));

        // aggiunta i pulsanti al pannello principale
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        this.panel.add(titleLabel);
        gbc.gridy = 1;
        this.panel.add(startButton, gbc);
        gbc.gridy = 2;
        this.panel.add(difficultyComboBox, gbc);
        gbc.gridy = 3;
        this.panel.add(shopButton, gbc);
        gbc.gridy = 4;
        this.panel.add(exitButton, gbc);

        // aggiungo gli ActionListener
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getInputHandler(SceneType.MENU).executeAction(Action.CHANGE_SCENE_TO_GAME);
            }
        });

        difficultyComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedDifficulty = (String) difficultyComboBox.getSelectedItem();
                LauncherImpl.LAUNCHER.setDifficulty(Difficulty.valueOf(selectedDifficulty));
            }
        });

        shopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getInputHandler(SceneType.MENU).executeAction(Action.CHANGE_SCENE_TO_SHOP);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getInputHandler(SceneType.MENU).executeAction(Action.EXIT_APP);
            }
        });
        setPanel(this.panel);
    }

    @Override
    public void update() {
    }
}
