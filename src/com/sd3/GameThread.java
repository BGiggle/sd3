package com.sd3;

import com.sd3.Models.EnemyFactory;
import com.sd3.Models.Monster;
import com.sd3.Models.Ogre;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Brian on 26/11/2015.
 */
public class GameThread implements Runnable {


    private GameWorldPanel panel;

    private Ogre ogre;

    private StateController states = new StateController();

    private int currentState;

    private JButton bUndo, bRedo;

    public GameThread(GameWorldPanel panel, JButton bUndo, JButton bRedo) {
        this.panel = panel;
        this.bUndo = bUndo;
        this.bRedo = bRedo;
        GlobalParams.setMonsters(new ArrayList<Monster>());
        ogre = new Ogre();
        GlobalParams.getMonsters().add(ogre);
    }

    private void updateMonsters() {
        GlobalParams.getMonsters().forEach(Monster::update);

        if (ogre.getStepsSinceLastEnemy() > 2) {
            ogre.resetSteps();
            EnemyFactory.SpawnEnemy();
        }
    }

    @Override
    public void run() {
        while (gameRunning) {
            try {
            moveAndDraw();
            Thread.sleep(500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void moveAndDraw(){

        while(currentState < states.count() - 1){
            states.removeState(states.count() - 1);
        }

        if(currentState == states.count() -1){
            bRedo.setVisible(false);
        }

        if(states.count() >= 1){
            bUndo.setVisible(true);
        }

        panel.repaint();

        updateMonsters();
        resolveConfilcts();
        states.saveState(GlobalParams.getMonsters());
        currentState = states.count() - 1;
    }




    private void resolveConfilcts() {
        ArrayList<Monster> collisions = new ArrayList<>();

        for (Monster m : GlobalParams.getMonsters()) {
            if (!(m instanceof Ogre)) {
                if (m.getY() == ogre.getY() && m.getX() == ogre.getX()) {
                    collisions.add(m);
                }
            }
        }

        if (collisions.size() == 1) {
            GlobalParams.getMonsters().remove(collisions.get(0));
        } else if (collisions.size() == 2) {
            if (GlobalParams.getOgreDiet() == "Ogre Enemies") {
                for (Monster m : collisions) {
                    GlobalParams.getMonsters().remove(m);
                }
            } else {
                gameRunning = false;
                ogreKilled();
            }
        } else if (collisions.size() > 2) {
            gameRunning = false;
            ogreKilled();
        }
    }

    private void ogreKilled(){
        JOptionPane.showMessageDialog(null, "The Ogre was overwhelmed by enemies", "The Ogre Died", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean gameRunning;
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void undo() {

        currentState--;
        deserializeAndPaint(currentState);

        bRedo.setVisible(true);
        if(currentState == 0){
            bUndo.setVisible(false);
        }
    }

    public void redo() {

        currentState++;
        deserializeAndPaint(currentState);

        bUndo.setVisible(true);
        if(states.count()-1 == currentState){
            bRedo.setVisible(false);
        }
    }

    private void deserializeAndPaint(int i){


        ArrayList<Monster> ms = states.getState(i);

        ogre = (Ogre)ms.get(0);

        GlobalParams.setMonsters(ms);

        panel.repaint();
    }
}
