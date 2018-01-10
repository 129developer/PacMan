/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman.objects;

/**
 *
 * @author bayasys
 */
public class pacman extends Sprite {

    public pacman(int x, int y, String imagename) {
        super(x, y, imagename);
    }

    public pacman(int x, int y) {
        super(x, y, "pacman.png");
    }

}
