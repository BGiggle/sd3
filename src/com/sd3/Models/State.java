package com.sd3.Models;

import com.sd3.GlobalParams;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Brian on 30/11/2015.
 */
public class State {

    public byte[] s;

    public State(ArrayList<Monster> state) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(GlobalParams.getMonsters());
            byte[] yourBytes = bos.toByteArray();
            s = yourBytes;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
    }

    public ArrayList<Monster> getMosters() {

        ByteArrayInputStream bis = new ByteArrayInputStream(s);

        ObjectInputStream in;
        try {
            in = new ObjectInputStream(bis);
            ArrayList<Monster> ms = (ArrayList<Monster>)in.readObject();

            GlobalParams.setMonsters(ms);
            in.close();
            return ms;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

}
