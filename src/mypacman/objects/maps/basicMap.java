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
public interface basicMap {

    public abstract void genPathBoxH(Graphics2D g2d, int x, int y, int width);

    public abstract void genPathBoxV(Graphics2D g2d, int x, int y, int height);

    public abstract void generateMap(Graphics2D g2d);

}
