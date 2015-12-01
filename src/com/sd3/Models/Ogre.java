package com.sd3.Models;

import com.sd3.GlobalParams;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by Brian on 24/11/2015.
 */
public class Ogre extends Monster {

    Random r = new Random();

    public Ogre() {
        setX(r.nextInt(GlobalParams.getWidth() - 1) + 1);
        setY(r.nextInt(GlobalParams.getHeight() - 1) + 1);
    }

    public String getDisplay(){
        return "Ogre";
    }

    public int getStepsSinceLastEnemy() {
        return stepsSinceLastEnemy;
    }

    private int stepsSinceLastEnemy;

    public void resetSteps(){
        stepsSinceLastEnemy = 0;
    }

    @Override
    public void update() {
        moveToRandomSquare();
        stepsSinceLastEnemy++;
    }

}
