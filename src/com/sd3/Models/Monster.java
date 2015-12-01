package com.sd3.Models;

import com.sd3.GlobalParams;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by Brian on 24/11/2015.
 */
public abstract class Monster  implements Serializable {

    public Monster() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    public int getY() {

        return y;
    }

    public void setY(int y) {

        this.y = y;
    }

    private int x, y;

    public abstract String getDisplay();

    public abstract void  update();

    public  void moveToRandomSquare() {

        Boolean foundValidMove = false;

        while(!foundValidMove){
            foundValidMove = getValidMove();
        }
    }

    private Boolean getValidMove(){
        Random r = new Random();
        int xMove = r.nextInt(3) - 1;
        int yMove = r.nextInt(3) - 1;

        if(xMove == 0 && yMove == 0){
            return false;
        }

        if(xMove + x < 0 || xMove + x > GlobalParams.getWidth() - 1 || yMove + y < 0 || yMove + y > GlobalParams.getHeight() - 1){
            return false;
        }

        x = xMove + x;
        y = yMove + y;

        return true;
    }
}
