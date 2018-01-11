/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman.objects.maps;

import java.awt.Graphics2D;
import mypacman.utils.Constants;

/**
 *
 * @author bayasys
 */
public class level1 extends basicMapFunctions implements basicMap {

    public void generateMap(Graphics2D g2d) {
        g2d.setColor(Constants.PATHCOLOR);
        genPathBoxV(g2d, 274, 120, 200);
        genPathBoxH(g2d, 84, 100, 400);//top hot
        genPathBoxH(g2d, 84, 300, 400);
        genPathBoxH(g2d, 204, 200, 100);
        g2d.fillRect(80, 160, 130, 120); //left box
        g2d.setColor(Constants.BLOCKCOLOR);
        g2d.fillOval(117, 195, 50, 50); //left box inner
        g2d.fillRect(117, 195, 50, 50); //left box inner
    }
}
