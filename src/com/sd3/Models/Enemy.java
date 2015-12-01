package com.sd3.Models;

import java.io.Serializable;

/**
 * Created by Brian on 27/11/2015.
 */
public abstract class Enemy extends Monster {
    public Enemy() {
        super();
        setX(0);
        setY(0);
    }

    @Override
    public void update() {
        moveToRandomSquare();
    }
}
