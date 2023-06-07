package it.unibo.project.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.project.controller.core.api.Launcher;
import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.input.api.Action;

public class ShopScene extends AbstractScene {
    private final Launcher launcher = LauncherImpl.LAUNCHER;
    private final Random random = new Random();
    private final JPanel panel;
    //private final JButton exitButton;
    //private final JLabel titleLabel;
    private final List<JButton> skinButtons = new ArrayList<>();
    //private final JButton randomButton;
    private final JLabel coinsLabel;
    private static final int PANEL_BACKGROUND_RED = 40;
    private static final int PANEL_BACKGROUND_GREEN = 40;
    private static final int PANEL_BACKGROUND_BLUE = 40;
    private static final int COINS_FONT_SIZE = 20;
    private static final int TITLE_FONT_SIZE = 50;
    private static final int WHITE_RED = 255;
    private static final int WHITE_GREEN = 255;
    private static final int WHITE_BLUE = 255;
    private static final int FONT_SIZE = 20;
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 70;
    private static final int COINS_TO_SUBTRACT = 100;
    private static final int SKIN_SIZE = 150;
    private static final int BASE_GRID_X = 200;
    private static final String FONT_NAME = "Arial";


    public ShopScene() {
        // pannello principale
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setBackground(new Color(PANEL_BACKGROUND_RED, PANEL_BACKGROUND_GREEN, PANEL_BACKGROUND_BLUE));

        //launcher.getGameStat().setCoins(500); // Imposta il numero di monete iniziali a 200
        coinsLabel = new JLabel();
        coinsLabel.setText("Coins: " + launcher.getGameStat().getCoins());
        coinsLabel.setForeground(Color.WHITE);
        coinsLabel.setFont(new Font(FONT_NAME, Font.BOLD, COINS_FONT_SIZE));
        this.panel.add(coinsLabel);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 0;
        gbc.gridheight = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 10, 10, 10);
        this.panel.add(coinsLabel, gbc);

        gbc.anchor = GridBagConstraints.NORTHEAST;

        // creazione del titolo del gioco
        final JLabel titleLabel = new JLabel("SHOP");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, TITLE_FONT_SIZE));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 0, 10, 0); 
        this.panel.add(titleLabel, gbc);

        // Creazione dei pulsanti delle skin
        final List<Image> playerSprites = LauncherImpl.LAUNCHER.getLoader().getPlayerSprites();
        for (final Image img : playerSprites) {
            createSkinButton(img, playerSprites.indexOf(img));
        }

        // Pulsante per l'acquisto randomico di una skin
        final JButton randomButton = new JButton("BUY RANDOM");
        randomButton.setFocusPainted(false);
        randomButton.setBackground(new Color(WHITE_RED, WHITE_GREEN, WHITE_BLUE));
        randomButton.setForeground(Color.BLACK);
        randomButton.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        randomButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (hasEnoughCoins(100)) {
                    purchaseRandomSkin();
                } else {
                    showMessage("Monete insufficienti!");
                }
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 0;
        gbc.gridheight = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 0, 10, 0);
        this.panel.add(randomButton, gbc);

        final JButton exitButton = new JButton("EXIT");
        exitButton.setFocusPainted(false);
        exitButton.setBackground(new Color(WHITE_RED, WHITE_GREEN, WHITE_BLUE));
        exitButton.setForeground(Color.RED);
        exitButton.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        exitButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 10, 10, 10); 
        this.panel.add(exitButton, gbc);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                getInputHandler(SceneType.SHOP).executeAction(Action.CHANGE_SCENE_TO_MENU);
            }
        });

        setPanel(this.panel);
    }

    private JButton createSkinButton(final Image image, final int skinIndex) {
        final JButton button = new JButton();
        button.setPreferredSize(new Dimension(SKIN_SIZE, SKIN_SIZE));

        //ImageIcon icon = new ImageIcon();
        final Image scaledImage = image.getScaledInstance(SKIN_SIZE, SKIN_SIZE, Image.SCALE_DEFAULT);
        button.setIcon(new ImageIcon(scaledImage));

        if (launcher.getGameStat().getUnlockedSkins().get(skinIndex)) {
            button.setEnabled(false);
        }

        skinButtons.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (hasEnoughCoins(100)) {
                    purchaseSkin(skinIndex);
                } else {
                    showMessage("Monete insufficienti!");
                }
            }
        });

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = BASE_GRID_X * skinIndex;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 10, 10, 10);
        this.panel.add(button, gbc);

        return button;
    }

    private boolean hasEnoughCoins(final int amount) {
        return launcher.getGameStat().getCoins() >= amount;
    }

    private void purchaseSkin(final int skinIndex) {
        launcher.getGameStat().addCoins(COINS_TO_SUBTRACT);
        setSkinPurchased(skinIndex);
        coinsLabel.setText("Coins:" + launcher.getGameStat().getCoins());
        showMessage("Acquistata Skin " + skinIndex + "!");
    }

    private void purchaseRandomSkin() {
        if (hasEnoughCoins(100)) {
            final List<Boolean> unlockedSkins = launcher.getGameStat().getUnlockedSkins();
            final List<Integer> unlockedIndexes = new ArrayList<>();
            for (int i = 0; i < unlockedSkins.size(); i++) {
                if (!unlockedSkins.get(i)) {
                    unlockedIndexes.add(i);
                }
            }

            if (unlockedIndexes.isEmpty()) {
                final int randomSkin = unlockedIndexes.get(random.nextInt(unlockedIndexes.size()));
                purchaseSkin(randomSkin);
            } else {
                showMessage("Nessuna skin disponibile!");
            }
        } else {
            showMessage("Monete insufficienti!");
        }
    }

    private void setSkinPurchased(final int skinIndex) {
        final List<Boolean> unlockedSkins = new ArrayList<>(launcher.getGameStat().getUnlockedSkins());
        unlockedSkins.set(skinIndex, true);
        launcher.getGameStat().changeUnlockedSkins(unlockedSkins);
        skinButtons.get(skinIndex).setEnabled(false);
    }

    private void showMessage(final String message) {
        JOptionPane.showMessageDialog(panel, message, "Messaggio", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void update() {
    }
}


