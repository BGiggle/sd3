package com.sd3.Models;

import com.sd3.GlobalParams;

import java.util.Random;

/**
 * Created by Brian on 27/11/2015.
 */
public class EnemyFactory {

    public static void SpawnEnemy(){
        Random r = new Random();

        switch (r.nextInt(3)){
            case 0:
                GlobalParams.getMonsters().add(new Donkey());
                break;
            case 1:
                GlobalParams.getMonsters().add(new Parrot());
                break;
            case 2:
                GlobalParams.getMonsters().add(new Snake());
                break;
        }

    }
}
