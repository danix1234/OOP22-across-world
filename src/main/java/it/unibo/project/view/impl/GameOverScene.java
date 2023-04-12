package it.unibo.project.view.impl;

import it.unibo.project.view.api.AbstractScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOverScene extends AbstractScene {
    private JPanel panel;
    private JButton tryAgainButton;
    private JButton exitButton;
    private JLabel titleLabel;

    public GameOverScene() {
        //pannello principale
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBackground(new Color(40, 40, 40));

        //creazione del titolo del gioco
        titleLabel = new JLabel("GAME OVER!");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(150, 60, 300, 50);
        this.panel.add(titleLabel);

        //creazione dei pulsanti del menu
        tryAgainButton = new JButton("Riprova");
        
        exitButton = new JButton("Esci dal gioco");

        //impostazioni dei buttons
        tryAgainButton.setBackground(new Color(255, 255, 255));
        tryAgainButton.setForeground(Color.BLACK);
        tryAgainButton.setFont(new Font("Arial", Font.BOLD, 14));
        tryAgainButton.setBounds(180, 150, 140, 40);

        exitButton.setBackground(new Color(255, 255, 255));
        exitButton.setForeground(Color.RED);
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setBounds(180, 230, 140, 40);

        //aggiunta dei pulsanti al pannello principale
        this.panel.add(tryAgainButton);
        this.panel.add(exitButton);

        //aggiungo gli ActionListener 
        tryAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // codice per riavviare il gioco
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        setPanel(this.panel);
    }

    @Override
    public void update() {
    }

}

