package it.unibo.project.view.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.input.api.Action;

/**
 * The ShopScene class represents the scene where players can purchase skins.
 */
public class ShopScene extends AbstractScene {
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JPanel northPanel = new JPanel(new BorderLayout());
    private final JPanel centerPanel = new JPanel(new GridBagLayout());
    private final JPanel southPanel = new JPanel(new BorderLayout());
    private final JScrollPane scrollPanel = new JScrollPane(centerPanel);
    private final JLabel priceLabel = new JLabel("skin price here");
    private final JLabel coinsLabel = new JLabel("coins here");
    private final JButton exitButton = new JButton("EXIT");
    private final JButton buyRandomButton = new JButton("CHOOSE RANDOM");

    public ShopScene() {
        // layout
        this.panel.add(northPanel, BorderLayout.NORTH);
        this.panel.add(southPanel, BorderLayout.SOUTH);
        this.panel.add(scrollPanel, BorderLayout.CENTER);
        this.northPanel.add(priceLabel, BorderLayout.WEST);
        this.northPanel.add(coinsLabel, BorderLayout.EAST);
        this.southPanel.add(exitButton, BorderLayout.WEST);
        this.southPanel.add(buyRandomButton, BorderLayout.EAST);

        // scroll visual fixes
        this.scrollPanel.getHorizontalScrollBar().setUnitIncrement(25);
        this.scrollPanel.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        this.scrollPanel.setBorder(null);

        // action listeners
        this.exitButton.addActionListener(e -> getInputHandler(SceneType.SHOP).executeAction(Action.CHANGE_SCENE_TO_MENU));

        setPanel(this.panel);
    }

    @Override
    public void update() {
    }
}




// /**
//  * The ShopScene class represents the scene where players can purchase skins.
//  */
// public class ShopScene extends AbstractScene {
//     private final Launcher launcher = LauncherImpl.LAUNCHER;
//     private final Random random = new Random();
//     private final JPanel panel;
//     private final List<JButton> skinButtons = new ArrayList<>();
//     private final JLabel coinsLabel;
//     private static final int PANEL_BACKGROUND_RED = 40;
//     private static final int PANEL_BACKGROUND_GREEN = 40;
//     private static final int PANEL_BACKGROUND_BLUE = 40;
//     private static final int COINS_FONT_SIZE = 20;
//     private static final int TITLE_FONT_SIZE = 50;
//     private static final int WHITE_RED = 255;
//     private static final int WHITE_GREEN = 255;
//     private static final int WHITE_BLUE = 255;
//     private static final int FONT_SIZE = 20;
//     private static final int BUTTON_WIDTH = 200;
//     private static final int BUTTON_HEIGHT = 70;
//     private static final int COINS_TO_SUBTRACT = -25;
//     private static final int SKIN_SIZE = 150;
//     private static final String FONT_NAME = "Arial";
//     private static final String PRICE_LABEL = "Skin Price: 25 Coins";
//     private static final int PRICE_SKIN = 25;

//     /**
//      * Creates a new instance of the ShopScene.
//      */
//     public ShopScene() {
//         // main panel
//         this.panel = new JPanel();
//         this.panel.setLayout(new GridBagLayout());
//         this.panel.setBackground(new Color(PANEL_BACKGROUND_RED, PANEL_BACKGROUND_GREEN, PANEL_BACKGROUND_BLUE));

//         coinsLabel = new JLabel();
//         coinsLabel.setText("Money: " + launcher.getGameStat().getCoins() + " Coins");
//         coinsLabel.setForeground(Color.GREEN);
//         coinsLabel.setFont(new Font(FONT_NAME, Font.BOLD, COINS_FONT_SIZE));
//         this.panel.add(coinsLabel);
//         GridBagConstraints gbc = new GridBagConstraints();
//         gbc.gridx = 1;
//         gbc.gridy = 0;
//         gbc.gridwidth = 1;
//         gbc.gridheight = 1;
//         gbc.fill = GridBagConstraints.NONE;
//         gbc.anchor = GridBagConstraints.NORTHEAST;
//         gbc.weightx = 1.0;
//         gbc.weighty = 0.0;
//         gbc.insets = new Insets(0, 10, 10, 10);
//         this.panel.add(coinsLabel, gbc);

//         final JLabel priceLabel = new JLabel();
//         priceLabel.setText(PRICE_LABEL);
//         priceLabel.setForeground(Color.GREEN);
//         priceLabel.setFont(new Font(FONT_NAME, Font.BOLD, COINS_FONT_SIZE));
//         this.panel.add(priceLabel);
//         gbc.gridx = 1;
//         gbc.gridy = 0;
//         gbc.gridwidth = 1;
//         gbc.gridheight = 1;
//         gbc.fill = GridBagConstraints.NONE;
//         gbc.anchor = GridBagConstraints.NORTHWEST;
//         gbc.weightx = 1.0;
//         gbc.weighty = 0.0;
//         gbc.insets = new Insets(0, 10, 10, 10);
//         this.panel.add(priceLabel, gbc);

//         final JLabel titleLabel = new JLabel("SHOP");
//         titleLabel.setForeground(Color.YELLOW);
//         titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, TITLE_FONT_SIZE));
//         titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
//         gbc = new GridBagConstraints();
//         gbc.gridx = 0;
//         gbc.gridy = 0;
//         gbc.gridwidth = 2;
//         gbc.gridheight = 1;
//         gbc.fill = GridBagConstraints.NONE;
//         gbc.anchor = GridBagConstraints.NORTH;
//         gbc.weightx = 1.0;
//         gbc.weighty = 0.0;
//         gbc.insets = new Insets(10, 0, 10, 0);
//         this.panel.add(titleLabel, gbc);

//         /**
//         * Retrieves a list of player sprites from the game loader and creates skin buttons for each sprite.
//         */
//         final List<Image> playerSprites = LauncherImpl.LAUNCHER.getLoader().getPlayerSprites();
//         for (final Image img : playerSprites) {
//             if (playerSprites.indexOf(img) >= launcher.getGameStat().getUnlockedSkins().size()) {
//                 break;
//             }
//             createSkinButton(img, playerSprites.indexOf(img));
//         }

//         if (launcher.getGameStat().getUnlockedSkins().size() > playerSprites.size()) {
//             throw new IllegalStateException("Skin quantity is less than unlocked skin quantity");
//         }

//         // random skin button
//         final JButton randomButton = new JButton("BUY RANDOM");
//         randomButton.setFocusPainted(false);
//         randomButton.setBackground(new Color(WHITE_RED, WHITE_GREEN, WHITE_BLUE));
//         randomButton.setForeground(Color.BLACK);
//         randomButton.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
//         randomButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
//         randomButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(final ActionEvent e) {
//                 if (hasEnoughCoins(PRICE_SKIN)) {
//                     purchaseRandomSkin();
//                 } else {
//                     showMessage("Not enough coins!");
//                 }
//             }
//         });
//         gbc = new GridBagConstraints();
//         gbc.gridx = 0;
//         gbc.gridy = playerSprites.size() + 2;
//         gbc.gridwidth = 2;
//         gbc.gridheight = 1;
//         gbc.fill = GridBagConstraints.NONE;
//         gbc.anchor = GridBagConstraints.CENTER;
//         gbc.weightx = 1.0;
//         gbc.weighty = 0.0;
//         gbc.insets = new Insets(10, 0, 10, 0);
//         this.panel.add(randomButton, gbc);

//         final JButton exitButton = new JButton("EXIT");
//         exitButton.setFocusPainted(false);
//         exitButton.setBackground(new Color(WHITE_RED, WHITE_GREEN, WHITE_BLUE));
//         exitButton.setForeground(Color.RED);
//         exitButton.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
//         exitButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
//         gbc = new GridBagConstraints();
//         gbc.gridx = 0;
//         gbc.gridy = playerSprites.size() + 3;
//         gbc.gridwidth = 2;
//         gbc.gridheight = 1;
//         gbc.fill = GridBagConstraints.NONE;
//         gbc.anchor = GridBagConstraints.SOUTHWEST; 
//         gbc.weightx = 1.0;
//         gbc.weighty = 0.0;
//         gbc.insets = new Insets(0, 10, 10, 10);
//         this.panel.add(exitButton, gbc);

//         exitButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(final ActionEvent e) {
//                 getInputHandler(SceneType.SHOP).executeAction(Action.CHANGE_SCENE_TO_MENU);
//             }
//         });

//         setPanel(this.panel);
//     }

//     // creates the skins buttons
//     private JButton createSkinButton(final Image image, final int skinIndex) {
//         final JButton button = new JButton();
//         button.setPreferredSize(new Dimension(SKIN_SIZE, SKIN_SIZE));

//         final Image scaledImage = image.getScaledInstance(SKIN_SIZE, SKIN_SIZE, Image.SCALE_DEFAULT);
//         button.setIcon(new ImageIcon(scaledImage));

//         if (launcher.getGameStat().getUnlockedSkins().get(skinIndex)) {
//             button.setEnabled(false);
//         }

//         skinButtons.add(button);

//         button.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(final ActionEvent e) {
//                 if (hasEnoughCoins(PRICE_SKIN)) {
//                     purchaseSkin(skinIndex);
//                 } else {
//                     showMessage("Not enough coins!");
//                 }
//             }
//         });

//         final GridBagConstraints gbc = new GridBagConstraints();
//         gbc.gridx = 0;
//         gbc.gridy = skinIndex + 2;
//         gbc.gridwidth = 2;
//         gbc.gridheight = 1;
//         gbc.fill = GridBagConstraints.NONE;
//         gbc.anchor = GridBagConstraints.CENTER;
//         gbc.weightx = 1.0;
//         gbc.weighty = 0.0;
//         gbc.insets = new Insets(0, 10, 10, 10);
//         this.panel.add(button, gbc);

//         return button;
//     }

//     /**
//      * Check if the player has enough coins.
//      *
//      * @param amount the amount of coins required
//      * @return true if the player has enough coins, false otherwise
//      */
//     private boolean hasEnoughCoins(final int amount) {
//         return launcher.getGameStat().getCoins() >= amount;
//     }

//     /**
//      * Purchase a skin with the specified index.
//      *
//      * @param skinIndex the index of the skin to be purchased
//      */
//     private void purchaseSkin(final int skinIndex) {
//         launcher.getGameStat().addCoins(COINS_TO_SUBTRACT);
//         setSkinPurchased(skinIndex);
//         coinsLabel.setText("Coins:" + launcher.getGameStat().getCoins());
//         showMessage("Purchased Skin " + skinIndex + "!");
//     }

//     /**
//     * Attempts to purchase a random skin if the player has enough coins.
//     * It checks the list of unlocked skins and selects a random index from the locked ones.
//     * If there are unlocked skins available, it purchases the selected random skin.
//     * Otherwise, it displays a message indicating that no skins are available.
//     * If the player doesn't have enough coins, it displays a message indicating insufficient coins.
//     */
//     private void purchaseRandomSkin() {
//         if (hasEnoughCoins(PRICE_SKIN)) {
//             final List<Boolean> unlockedSkins = launcher.getGameStat().getUnlockedSkins();
//             final List<Integer> unlockedIndexes = new ArrayList<>();
//             for (int i = 0; i < unlockedSkins.size(); i++) {
//                 if (!unlockedSkins.get(i)) {
//                     unlockedIndexes.add(i);
//                 }
//             }

//             if (!unlockedIndexes.isEmpty()) {
//                 final int randomSkin = unlockedIndexes.get(random.nextInt(unlockedIndexes.size()));
//                 purchaseSkin(randomSkin);
//             } else {
//                 showMessage("No skins available!");
//             }
//         } else {
//             showMessage("Not enough coins!");
//         }
//     }

//     /**
//      * Set a skin as purchased.
//      *
//      * @param skinIndex the index of the skin to be set as purchased
//      */
//     private void setSkinPurchased(final int skinIndex) {
//         final List<Boolean> unlockedSkins = new ArrayList<>(launcher.getGameStat().getUnlockedSkins());
//         unlockedSkins.set(skinIndex, true);
//         launcher.getGameStat().changeUnlockedSkins(unlockedSkins);
//         skinButtons.get(skinIndex).setEnabled(false);
//     }

//     /**
//      * Show a message dialog with the specified message.
//      *
//      * @param message the message to be displayed
//      */
//     private void showMessage(final String message) {
//         JOptionPane.showMessageDialog(panel, message, "Message", JOptionPane.INFORMATION_MESSAGE);
//     }

//     @Override
//     public void update() {
//     }
// }
