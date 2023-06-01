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

public abstract class AbstractGameEndScene extends AbstractScene {
    private JPanel panel;
    private JButton tryAgainButton;
    private JButton backToMenu;
    private JButton exitButton;
    private JLabel titleLabel;

    protected AbstractGameEndScene(String title) {


        // pannello principale
        this.panel = new JPanel(new GridBagLayout());
        this.panel.setLayout(new GridBagLayout());
        this.panel.setBackground(new Color(40, 40, 40));

        // creazione del titolo del gioco
        titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 100));
        titleLabel.setBounds(0, 10, panel.getWidth(), 50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // creazione dei pulsanti del menu
        tryAgainButton = new JButton("RESTART");
        backToMenu = new JButton("BACK TO MENU");
        exitButton = new JButton("SAVE AND EXIT");

        // effetto testo evidenziato rimosso
        tryAgainButton.setFocusPainted(false);
        exitButton.setFocusPainted(false);

        // impostazioni dei buttons
        tryAgainButton.setBackground(new Color(255, 255, 255));
        tryAgainButton.setForeground(Color.BLACK);
        tryAgainButton.setFont(new Font("Arial", Font.BOLD, 20));
        tryAgainButton.setPreferredSize(new Dimension(200, 70));

        backToMenu.setBackground(new Color(255, 255, 255));
        backToMenu.setForeground(Color.BLACK);
        backToMenu.setFont(new Font("Arial", Font.BOLD, 20));
        backToMenu.setPreferredSize(new Dimension(200, 70));

        exitButton.setBackground(new Color(255, 255, 255));
        exitButton.setForeground(Color.RED);
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.setPreferredSize(new Dimension(200, 70));

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
