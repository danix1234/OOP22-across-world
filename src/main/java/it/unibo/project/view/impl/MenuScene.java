package it.unibo.project.view.impl;
import javax.swing.*;

import it.unibo.project.view.api.AbstractScene;

import java.awt.*;
import java.awt.event.*;

public class MenuScene extends AbstractScene{ 
    private JPanel panel;
    private JButton startButton;
    private JButton optionsButton;
    private JButton exitButton;
    private JLabel titleLabel;

    public MenuScene() {

        //pannello principale
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBackground(new Color(40, 40, 40));

        //creazione del titolo del gioco
        titleLabel = new JLabel("Across the world");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(120, 60, 300, 50);
        this.panel.add(titleLabel);

        //creazione dei pulsanti del menu
        startButton = new JButton("Inizia il gioco");
        optionsButton = new JButton("Opzioni");
        exitButton = new JButton("Esci dal gioco");

        //impostazioni dei buttons
        startButton.setBackground(new Color(255, 255, 255));
        startButton.setForeground(Color.BLACK);
        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        startButton.setBounds(180, 150, 140, 40);

        optionsButton.setBackground(new Color(255, 255, 255));
        optionsButton.setForeground(Color.BLACK);
        optionsButton.setFont(new Font("Arial", Font.BOLD, 14));
        optionsButton.setBounds(180, 200, 140, 40);

        exitButton.setBackground(new Color(255, 255, 255));
        exitButton.setForeground(Color.RED);
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setBounds(180, 250, 140, 40);

        //aggiunta dei pulsanti al pannello principale
        this.panel.add(startButton);
        this.panel.add(optionsButton);
        this.panel.add(exitButton);

        //aggiungo gli ActionListener 
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // codice per avviare il gioco
            }
        });

        optionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // codice per mostrare le opzioni del gioco
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}




