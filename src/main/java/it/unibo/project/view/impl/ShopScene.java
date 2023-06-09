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
