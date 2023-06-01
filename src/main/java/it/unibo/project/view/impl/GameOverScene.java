package it.unibo.project.view.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unibo.project.controller.core.api.SceneType;
import it.unibo.project.input.api.Action;

public class GameOverScene extends AbstractGameEndScene {


    public GameOverScene() {
        super("GAME OVER!");

        // aggiungo gli ActionListener
        getTryAgainButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // codice per riavviare il gioco
                getInputHandler(SceneType.OVER).executeAction(Action.CHANGE_SCENE_TO_GAME);
            }
        });

        getBackToMenu().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                getInputHandler(SceneType.OVER).executeAction(Action.CHANGE_SCENE_TO_MENU);
            }
        });

        getExitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // codice per uscire dal gioco
                getInputHandler(SceneType.OVER).executeAction(Action.EXIT_APP);
            }
        });

    }

 
}

