package com.sd3;

import com.sd3.Models.Monster;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Brian on 23/11/2015.
 */
public class GameWorldPanel extends JPanel {


    @Override
    public void paint(Graphics g) {


            Graphics2D g2d = (Graphics2D) g;
            
            int panelWidth = this.getWidth()-1;
            int panelHeight = this.getHeight()-1;


            int gameTileWidth = panelWidth / GlobalParams.getWidth();
            int gameTileHeight = panelHeight / GlobalParams.getHeight();
            g2d.setColor(Color.green);
            g2d.fillRect(0, 0, panelWidth, panelHeight);
            
            g2d.setColor(Color.black);

            for(int x = 0; x < GlobalParams.getWidth(); x ++){
                for(int y = 0; y < GlobalParams.getHeight(); y++){
                    int xPos = x * gameTileWidth;
                    int yPos = y * gameTileHeight;
                    g2d.drawRect(xPos, yPos, gameTileWidth, gameTileHeight);
                }
            }

            for(Monster m : GlobalParams.getMonsters()){
                int xPos = m.getX() * gameTileWidth;
                int yPos = m.getY() * gameTileHeight;
               g2d.drawString(m.getDisplay(), xPos + gameTileWidth/2, yPos + gameTileHeight/2);
        }
    }

}
