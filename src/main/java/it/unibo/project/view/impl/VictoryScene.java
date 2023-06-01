package it.unibo.project.view.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.input.api.Action;

public class VictoryScene extends AbstractGameEndScene {

    public VictoryScene() {
        super("YOU WIN!");
    
        // aggiungo gli ActionListener
        getTryAgainButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // codice per riavviare il gioco
                getInputHandler(SceneType.VICTORY).executeAction(Action.CHANGE_SCENE_TO_GAME);
            }
        });

        getBackToMenu().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                getInputHandler(SceneType.VICTORY).executeAction(Action.CHANGE_SCENE_TO_MENU);
            }
        });

        getExitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // codice per uscire dal gioco
                getInputHandler(SceneType.VICTORY).executeAction(Action.EXIT_APP);
            }
        });

    }
}

