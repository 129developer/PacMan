/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman.objects.maps;

import java.awt.Graphics2D;

/**
 *
 * @author bayasys
 */
public class basicMapFunctions {

    public  void genPathBoxH(Graphics2D g2d, int x, int y, int width) {
        g2d.fillRect(x, y, width, 40);
    }

    public  void genPathBoxV(Graphics2D g2d, int x, int y, int height) {
        g2d.fillRect(x, y, 40, height);
    }
    

}
