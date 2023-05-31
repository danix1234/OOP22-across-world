package it.unibo.project.view.impl;

import javax.swing.*;

//import it.unibo.project.controller.core.api.Difficulty;
import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.controller.core.impl.LauncherImpl;
import it.unibo.project.controller.core.api.Launcher;
import it.unibo.project.input.api.Action;
//import it.unibo.project.controller.core.impl.LoaderImpl;
//import it.unibo.project.controller.core.api.Loader;
import java.awt.*;
import java.awt.event.*;
//import java.util.Arrays;
import java.util.List;
//import java.util.ArrayList;


public class ShopScene extends AbstractScene {
    private final Launcher launcher = LauncherImpl.LAUNCHER;
    //private final Loader loader = new LoaderImpl();
    //private List<String> skin = List.of("player0.png", "player1.png","player2.png");
    private JPanel panel;
    private JButton exitButton;
    private JLabel titleLabel;
    private JButton skin1Button;
    private JButton skin2Button;
    private JButton skin3Button;
    private JButton randomButton;
    private boolean skin1Purchased;
    private boolean skin2Purchased;
    private boolean skin3Purchased;
    //private int coins;
    private JLabel coinsLabel;

    public ShopScene(){
        // pannello principale
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setBackground(new Color(40, 40, 40));

        //launcher.getGameStat().setCoins(200); // Imposta il numero di monete iniziali a 200
        coinsLabel = new JLabel();
        coinsLabel.setText("Monete: " + launcher.getGameStat().getCoins());
        coinsLabel.setForeground(Color.WHITE);
        coinsLabel.setFont(new Font("Arial", Font.BOLD, 20));
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
        titleLabel = new JLabel("SHOP");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
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
        gbc.insets = new Insets(10, 0, 10, 0); // Spazio esterno (padding)
        this.panel.add(titleLabel, gbc);

        // Creazione dei pulsanti delle skin
        //skin1Button = createSkinButton("src/main/resources/it/unibo/project/sprite/player/player0.png", 1, 0, 1);
        //skin2Button = createSkinButton("src/main/resources/it/unibo/project/sprite/player/player1.png", 2, 1, 1);
        //skin3Button = createSkinButton("src/main/resources/it/unibo/project/sprite/player/player2.png", 3, 2, 1);
        //loader.loadImages("src/main/resources/it/unibo/project/sprite/player/",skin);
        List<Image> playerSprites = LauncherImpl.LAUNCHER.getLoader().getPlayerSprites();
        skin1Button = createSkinButton(playerSprites.get(0),0,0,1);
        skin2Button = createSkinButton(playerSprites.get(1),1,1,1);
        skin3Button = createSkinButton(playerSprites.get(2),2,2,1);


        // Pulsante per l'acquisto randomico di una skin
        randomButton = new JButton("BUY RANDOM");
        randomButton.setFocusPainted(false);
        randomButton.setBackground(new Color(255, 255, 255));
        randomButton.setForeground(Color.BLACK);
        randomButton.setFont(new Font("Arial", Font.BOLD, 20));
        randomButton.setPreferredSize(new Dimension(200,70));
        randomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

        exitButton = new JButton("EXIT");
        exitButton.setFocusPainted(false);
        exitButton.setBackground(new Color(255, 255, 255));
        exitButton.setForeground(Color.RED);
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.setPreferredSize(new Dimension(200, 70));
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
            public void actionPerformed(ActionEvent e) {
                getInputHandler(SceneType.SHOP).executeAction(Action.CHANGE_SCENE_TO_MENU);
            }
        });

        setPanel(this.panel);
    }

    private JButton createSkinButton(Image image, int skinIndex, int gridX, int gridY) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(150, 150));

        //ImageIcon icon = new ImageIcon();
        Image scaledImage = image.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        button.setIcon(new ImageIcon(scaledImage));

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!isSkinPurchased(skinIndex)) {
                    if (hasEnoughCoins(100)) {
                        purchaseSkin(skinIndex);
                    } else {
                        showMessage("Monete insufficienti!");
                    }
                } else {
                    showMessage("Skin " + skinIndex + " già acquistata!");
                }
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridX;
        gbc.gridy = gridY;
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

    private boolean hasEnoughCoins(int amount) {
        return launcher.getGameStat().getCoins() >= amount;
    }

    private void purchaseSkin(int skinIndex) {
        //coins -= 100;
        launcher.getGameStat().addCoins(-100);
        setSkinPurchased(skinIndex);
        showMessage("Acquistata Skin " + skinIndex + "!");
    }

    private void purchaseRandomSkin() {
        int randomSkin = (int) (Math.random() * 3) + 1;
        if (isSkinPurchased(randomSkin)) {
            showMessage("Skin " + randomSkin + " già acquistata!");
        } else {
            purchaseSkin(randomSkin);
        }
    }

    private boolean isSkinPurchased(int skinIndex) {
        switch (skinIndex) {
            case 1:
                return skin1Purchased;
            case 2:
                return skin2Purchased;
            case 3:
                return skin3Purchased;
            default:
                return false;
        }
    }

    private void setSkinPurchased(int skinIndex) {
        switch (skinIndex) {
            case 1:
                skin1Purchased = true;
                skin1Button.setEnabled(false);
                break;
            case 2:
                skin2Purchased = true;
                skin2Button.setEnabled(false);
                break;
            case 3:
                skin3Purchased = true;
                skin3Button.setEnabled(false);
                break;
        }
    }

    /*private void setCoins(int amount) {
        coins = amount;
        coinsLabel.setText("Monete: " + launcher.getGameStat().getCoins());
    }*/

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(panel, message, "Messaggio", JOptionPane.INFORMATION_MESSAGE);
    }


    @Override
    public void update() {
    }
}


