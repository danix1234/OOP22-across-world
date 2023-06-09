package it.unibo.project.view.impl;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * The ShopScene class represents the scene where players can purchase skins.
 */
public class ShopScene extends AbstractScene {
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JPanel northPanel = new JPanel(new BorderLayout());
    private final JPanel centerPanel = new JPanel();
    private final JPanel southPanel = new JPanel(new BorderLayout());
    private final JScrollPane scrollPanel = new JScrollPane(centerPanel);
    private final JLabel priceLabel = new JLabel("skin price here");
    private final JLabel coinsLabel = new JLabel("coins here");
    private final JButton exitButton = new JButton("EXIT");
    private final JButton buyRandomButton = new JButton("CHOOSE RANDOM");

    public ShopScene() {
        setPanel(this.panel);
    }

    @Override
    public void update() {
    }
}
