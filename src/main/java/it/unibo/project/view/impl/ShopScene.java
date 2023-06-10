package it.unibo.project.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
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
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import it.unibo.project.controller.core.api.Launcher;
import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.input.api.Action;

/**
 * The ShopScene class represents the scene where players can purchase skins.
 */
public class ShopScene extends AbstractScene {

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
    private static final int SKIN_SIZE = 300;
    private static final String FONT_NAME = "Arial";
    private static final int SKIN_PRICE = 25;
    private static final int COIN_SIZE = 30;
    private static final String PRICE_LABEL = "Skin Price: " + SKIN_PRICE;
    private static final int SCROLL_INCREMENT = 50;
    private final Launcher launcher = LauncherImpl.LAUNCHER;
    private final Random random = new Random();
    private JPanel panel;
    private final List<JButton> skinButtons = new ArrayList<>();
    private JLabel coinsLabel;

    /**
     * Creates a new instance of the ShopScene.
     */
    public ShopScene() {
        initHead();
        initBody();
        initFooter();
        setPanel(this.panel);
    }

    private void initHead() {
        this.panel = new JPanel();
        this.panel.setLayout(new BorderLayout());
        this.panel.setBackground(new Color(PANEL_BACKGROUND_RED, PANEL_BACKGROUND_GREEN, PANEL_BACKGROUND_BLUE));

        final JPanel head = new JPanel();
        head.setLayout(new BorderLayout());
        head.setBackground(new Color(PANEL_BACKGROUND_RED, PANEL_BACKGROUND_GREEN, PANEL_BACKGROUND_BLUE));

        final JLabel titleLabel = new JLabel("SHOP");
        titleLabel.setForeground(Color.YELLOW);
        titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, TITLE_FONT_SIZE));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        head.add(titleLabel, BorderLayout.CENTER);

        final Image coinImage = LauncherImpl.LAUNCHER.getLoader().getCollectablesSprites(CollectableType.COIN).get(0);
        final Image scaledCoinImage = coinImage.getScaledInstance(COIN_SIZE, COIN_SIZE, Image.SCALE_DEFAULT);

        coinsLabel = new JLabel();
        coinsLabel.setText("Money: " + launcher.getGameStat().getCoins());
        coinsLabel.setIcon(new ImageIcon(scaledCoinImage));
        coinsLabel.setHorizontalTextPosition(JLabel.LEFT);
        coinsLabel.setForeground(Color.GREEN);
        coinsLabel.setFont(new Font(FONT_NAME, Font.BOLD, COINS_FONT_SIZE));
        head.add(coinsLabel, BorderLayout.LINE_END);

        final JLabel priceLabel = new JLabel();
        priceLabel.setText(PRICE_LABEL);
        priceLabel.setForeground(Color.GREEN);
        priceLabel.setFont(new Font(FONT_NAME, Font.BOLD, COINS_FONT_SIZE));
        priceLabel.setIcon(new ImageIcon(scaledCoinImage));
        priceLabel.setHorizontalTextPosition(JLabel.LEFT);
        head.add(priceLabel, BorderLayout.LINE_START);

        this.panel.add(head, BorderLayout.PAGE_START);
    }

    private void initBody() {
        final JPanel body = new JPanel(new GridBagLayout());
        body.setBackground(new Color(PANEL_BACKGROUND_RED, PANEL_BACKGROUND_GREEN, PANEL_BACKGROUND_BLUE));

        final JScrollPane skinPane = new JScrollPane(body);
        skinPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        skinPane.setBackground(new Color(PANEL_BACKGROUND_RED, PANEL_BACKGROUND_GREEN, PANEL_BACKGROUND_BLUE));
        skinPane.setBorder(null);
        skinPane.getHorizontalScrollBar().setUnitIncrement(SCROLL_INCREMENT);
        skinPane.getVerticalScrollBar().setUnitIncrement(SCROLL_INCREMENT);

        /**
        * Retrieves a list of player sprites from the game loader and creates skin buttons for each sprite.
        */
        final List<Image> playerSprites = LauncherImpl.LAUNCHER.getLoader().getPlayerSprites();
        for (final Image img : playerSprites) {
            if (playerSprites.indexOf(img) >= launcher.getGameStat().getUnlockedSkins().size()) {
                break;
            }
            body.add(createSkinButton(img, playerSprites.indexOf(img)));
        }

        this.panel.add(skinPane, BorderLayout.CENTER);
    }

    private void initFooter() {
        final JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(new Color(PANEL_BACKGROUND_RED, PANEL_BACKGROUND_GREEN, PANEL_BACKGROUND_BLUE));

        final JButton exitButton = new JButton("EXIT");
        exitButton.setFocusPainted(false);
        exitButton.setBackground(new Color(WHITE_RED, WHITE_GREEN, WHITE_BLUE));
        exitButton.setForeground(Color.RED);
        exitButton.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        exitButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        exitButton.addActionListener(e -> getInputHandler(SceneType.SHOP).executeAction(Action.CHANGE_SCENE_TO_MENU));
        footer.add(exitButton, BorderLayout.LINE_START);

        final JButton randomButton = new JButton("BUY RANDOM");
        randomButton.setFocusPainted(false);
        randomButton.setBackground(new Color(WHITE_RED, WHITE_GREEN, WHITE_BLUE));
        randomButton.setForeground(Color.BLACK);
        randomButton.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        randomButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        randomButton.addActionListener(e -> {
            if (hasEnoughCoins()) {
                purchaseRandomSkin();
            } else {
                showMessage("Not enough coins!");
            }
        });
        footer.add(randomButton, BorderLayout.LINE_END);

        this.panel.add(footer, BorderLayout.PAGE_END);
    }

    private JButton createSkinButton(final Image image, final int skinIndex) {
        final JButton button = new JButton();
        button.setPreferredSize(new Dimension(SKIN_SIZE, SKIN_SIZE));

        final Image scaledImage = image.getScaledInstance(SKIN_SIZE, SKIN_SIZE, Image.SCALE_DEFAULT);
        button.setIcon(new ImageIcon(scaledImage));

        if (launcher.getGameStat().getUnlockedSkins().get(skinIndex)) {
            button.setEnabled(false);
        }

        skinButtons.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (hasEnoughCoins()) {
                    purchaseSkin(skinIndex);
                } else {
                    showMessage("Not enough coins!");
                }
            }
        });

        return button;
    }

     /**
     * Check if the player has enough coins.
     * 
     * @return true if the player has enough coins, false otherwise
     */
    private boolean hasEnoughCoins() {
        return launcher.getGameStat().getCoins() >= SKIN_PRICE;
    }

    /**
     * Purchase a skin with the specified index.
     *
     * @param skinIndex the index of the skin to be purchased
     */
    private void purchaseSkin(final int skinIndex) {
        launcher.getGameStat().addCoins(-SKIN_PRICE);
        setSkinPurchased(skinIndex);
        coinsLabel.setText("Coins:" + launcher.getGameStat().getCoins());
        showMessage("Purchased Skin " + skinIndex + "!");
    }

    /**
    * Attempts to purchase a random skin if the player has enough coins.
    * It checks the list of unlocked skins and selects a random index from the locked ones.
    * If there are unlocked skins available, it purchases the selected random skin.
    * Otherwise, it displays a message indicating that no skins are available.
    * If the player doesn't have enough coins, it displays a message indicating insufficient coins.
    */
    private void purchaseRandomSkin() {
        if (hasEnoughCoins()) {
            final List<Boolean> unlockedSkins = launcher.getGameStat().getUnlockedSkins();
            final List<Integer> unlockedIndexes = new ArrayList<>();
            for (int i = 0; i < unlockedSkins.size(); i++) {
                if (!unlockedSkins.get(i)) {
                    unlockedIndexes.add(i);
                }
            }

            if (!unlockedIndexes.isEmpty()) {
                final int randomSkin = unlockedIndexes.get(random.nextInt(unlockedIndexes.size()));
                purchaseSkin(randomSkin);
            } else {
                showMessage("No skins available!");
            }
        } else {
            showMessage("Not enough coins!");
        }
    }

    /**
    * Set a skin as purchased.
    *
    * @param skinIndex the index of the skin to be set as purchased
    */
    private void setSkinPurchased(final int skinIndex) {
        final List<Boolean> unlockedSkins = new ArrayList<>(launcher.getGameStat().getUnlockedSkins());
        unlockedSkins.set(skinIndex, true);
        launcher.getGameStat().changeUnlockedSkins(unlockedSkins);
        skinButtons.get(skinIndex).setEnabled(false);
    }

     /**
     * Show a message dialog with the specified message.
     *
     * @param message the message to be displayed
     */
    private void showMessage(final String message) {
        JOptionPane.showMessageDialog(panel, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void update() {
    }
}
