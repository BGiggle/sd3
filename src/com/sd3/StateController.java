package com.sd3;

import com.sd3.Models.Monster;
import com.sd3.Models.State;

import java.util.ArrayList;

/**
 * Created by Brian on 28/11/2015.
 */
public class StateController {


    private ArrayList<State> states = new ArrayList<>();


    public ArrayList<Monster> getState(int index){
        return states.get(index).getMosters();
    }

    public void saveState(ArrayList<Monster> state){
        State s = new State(state);
        states.add(s);
    }

    public int count(){
        return states.size();
    }


    public void removeState(int i) {
        states.remove(i);
    }
}
