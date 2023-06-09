package it.unibo.project.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.input.api.Action;

/**
 * The ShopScene class represents the scene where players can purchase skins.
 */
public class ShopScene extends AbstractScene {
    private static final int SCROLL_UNIT = 25;
    private static final Color BACKGROUND_COLOR = new Color(40, 40, 40);
    private static final int COINS_FONT_SIZE = 20;
    private static final int WHITE_RED = 255;
    private static final int WHITE_GREEN = 255;
    private static final int WHITE_BLUE = 255;
    private static final int FONT_SIZE = 20;
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 70;
    private static final int COINS_TO_SUBTRACT = -25;
    private static final int SKIN_SIZE = 150;
    private static final String FONT_NAME = "Arial";
    private static final String PRICE_LABEL = "Skin Price: 25 Coins";
    private static final int PRICE_SKIN = 25;

    private final JPanel panel = new JPanel(new BorderLayout());
    private final JPanel northPanel = new JPanel(new BorderLayout());
    private final JPanel centerPanel = new JPanel(new GridBagLayout());
    private final JPanel southPanel = new JPanel(new BorderLayout());
    private final JScrollPane scrollPanel = new JScrollPane(centerPanel);
    private final JLabel priceLabel = new JLabel("skin price here");
    private final JLabel coinsLabel = new JLabel("coins here");
    private final JButton exitButton = new JButton("EXIT");
    private final JButton buyRandomButton = new JButton("BUY RANDOM");
    private final List<JButton> skinButtons = new ArrayList<>();
    private final Random random = new Random();

    /** constructor of shopscene. */
    public ShopScene() {
        initAllComponents();
        updateAllComponents();

        final List<Image> playerSprites = LauncherImpl.LAUNCHER.getLoader().getPlayerSprites();
        for (final Image img : playerSprites) {
            if (playerSprites.indexOf(img) >= LauncherImpl.LAUNCHER.getGameStat().getUnlockedSkins().size()) {
                break;
            }
            this.centerPanel.add(createSkinButton(img, playerSprites.indexOf(img)));
        }

        if (LauncherImpl.LAUNCHER.getGameStat().getUnlockedSkins().size() > playerSprites.size()) {
            throw new IllegalStateException("Skin quantity is less than unlocked skin quantity");
        }

        this.buyRandomButton.addActionListener(e -> {
            if (hasEnoughCoins(PRICE_SKIN)) {
                purchaseRandomSkin();
            } else {
                showMessage("Not enough coins!");
            }
        });

        this.exitButton.addActionListener(e -> getInputHandler(SceneType.SHOP).executeAction(Action.CHANGE_SCENE_TO_MENU));

        setPanel(this.panel);
    }

    private void initAllComponents() {
        this.panel.add(northPanel, BorderLayout.NORTH);
        this.panel.add(southPanel, BorderLayout.SOUTH);
        this.panel.add(scrollPanel, BorderLayout.CENTER);
        this.northPanel.add(priceLabel, BorderLayout.WEST);
        this.northPanel.add(coinsLabel, BorderLayout.EAST);
        this.southPanel.add(exitButton, BorderLayout.WEST);
        this.southPanel.add(buyRandomButton, BorderLayout.EAST);
        this.scrollPanel.getHorizontalScrollBar().setUnitIncrement(SCROLL_UNIT);
        this.scrollPanel.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        this.scrollPanel.setBorder(null);
        this.northPanel.setBackground(BACKGROUND_COLOR);
        this.centerPanel.setBackground(BACKGROUND_COLOR);
        this.southPanel.setBackground(BACKGROUND_COLOR);
        this.coinsLabel.setForeground(Color.GREEN);
        this.coinsLabel.setFont(new Font(FONT_NAME, Font.BOLD, COINS_FONT_SIZE));
        this.priceLabel.setText(PRICE_LABEL);
        this.priceLabel.setForeground(Color.GREEN);
        this.priceLabel.setFont(new Font(FONT_NAME, Font.BOLD, COINS_FONT_SIZE));
        this.buyRandomButton.setFocusPainted(false);
        this.buyRandomButton.setBackground(new Color(WHITE_RED, WHITE_GREEN, WHITE_BLUE));
        this.buyRandomButton.setForeground(Color.BLACK);
        this.buyRandomButton.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        this.buyRandomButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        this.exitButton.setFocusPainted(false);
        this.exitButton.setBackground(new Color(WHITE_RED, WHITE_GREEN, WHITE_BLUE));
        this.exitButton.setForeground(Color.RED);
        this.exitButton.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        this.exitButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
    }

    private void updateAllComponents() {
        this.coinsLabel.setText("Money: " + LauncherImpl.LAUNCHER.getGameStat().getCoins() + " Coins");
    }

    private JButton createSkinButton(final Image image, final int skinIndex) {
        final JButton button = new JButton();
        button.setPreferredSize(new Dimension(SKIN_SIZE, SKIN_SIZE));

        final Image scaledImage = image.getScaledInstance(SKIN_SIZE, SKIN_SIZE,
                Image.SCALE_DEFAULT);
        button.setIcon(new ImageIcon(scaledImage));

        if (LauncherImpl.LAUNCHER.getGameStat().getUnlockedSkins().get(skinIndex)) {
            button.setEnabled(false);
        }

        skinButtons.add(button);

        button.addActionListener(e -> {
            if (hasEnoughCoins(PRICE_SKIN)) {
                purchaseSkin(skinIndex);
            } else {
                showMessage("Not enough coins!");
            }
        });

        return button;
    }

    private boolean hasEnoughCoins(final int amount) {
        return LauncherImpl.LAUNCHER.getGameStat().getCoins() >= amount;
    }

    private void purchaseSkin(final int skinIndex) {
        LauncherImpl.LAUNCHER.getGameStat().addCoins(COINS_TO_SUBTRACT);
        setSkinPurchased(skinIndex);
        coinsLabel.setText("Coins:" + LauncherImpl.LAUNCHER.getGameStat().getCoins());
        showMessage("Purchased Skin " + skinIndex + "!");
    }

    private void purchaseRandomSkin() {
        if (hasEnoughCoins(PRICE_SKIN)) {
            final List<Boolean> unlockedSkins = LauncherImpl.LAUNCHER.getGameStat().getUnlockedSkins();
            final List<Integer> unlockedIndexes = new ArrayList<>();
            for (int i = 0; i < unlockedSkins.size(); i++) {
                if (!unlockedSkins.get(i)) {
                    unlockedIndexes.add(i);
                }
            }

            if (!unlockedIndexes.isEmpty()) {
                final int randomSkin = unlockedIndexes.get(this.random.nextInt(unlockedIndexes.size()));
                purchaseSkin(randomSkin);
            } else {
                showMessage("No skins available!");
            }
        } else {
            showMessage("Not enough coins!");
        }
    }

    private void setSkinPurchased(final int skinIndex) {
        final List<Boolean> unlockedSkins = new ArrayList<>(LauncherImpl.LAUNCHER.getGameStat().getUnlockedSkins());
        unlockedSkins.set(skinIndex, true);
        LauncherImpl.LAUNCHER.getGameStat().changeUnlockedSkins(unlockedSkins);
        skinButtons.get(skinIndex).setEnabled(false);
    }

    private void showMessage(final String message) {
        JOptionPane.showMessageDialog(panel, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
    }
}
