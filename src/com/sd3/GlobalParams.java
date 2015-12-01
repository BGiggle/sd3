package com.sd3;

import com.sd3.Models.Monster;

import java.util.ArrayList;

/**
 * Created by Brian on 23/11/2015.
 */
public class GlobalParams {

    private static GlobalParams GlobalParams;

    private static ArrayList<Monster> monsters = new ArrayList<>();
    private String ogreDiet;





    public static GlobalParams getGlobalParamsInstance() {
    	if(GlobalParams == null){
            GlobalParams = new GlobalParams();
        }
        return GlobalParams;
    }

    public static ArrayList<Monster> getMonsters() {
        return GlobalParams.monsters;
    }

    public static void setMonsters(ArrayList<Monster> monsters) {
        GlobalParams.monsters = monsters;
    }




    public static int getWidth() {
        return GlobalParams.Width;
    }

    public static void setWidth(int width) {
        GlobalParams.Width = width;
    }

    public static int getHeight() {
        return GlobalParams.Height;
    }

    public static void setHeight(int height) {
        GlobalParams.Height = height;
    }

    private int Width;
    private int Height;

    public static void setOgreDiet(String ogreDiet) {
        GlobalParams.ogreDiet = ogreDiet;
    }

    public static String getOgreDiet(){
        return GlobalParams.ogreDiet;
    }
}
