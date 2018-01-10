/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman.objects;

import java.awt.event.KeyEvent;

/**
 *
 * @author bayasys
 */
public class pacman extends Sprite {

    boolean left = false;
    private int dx;
    private int dy;
    private int MOVEVAL = 1;

    public pacman(int x, int y, String imagename) {
        super(x, y, imagename);
    }

    public void flipLeft() {
        if (!left) {
            left = true;
            setX(x + height);
            setWidth(-width);
        }
    }

    public void flipRight() {
        if (left) {
            left = false;
            setX(x - height);
            setWidth(-width);
        }
    }

    public boolean isLeft() {
        return left;
    }

    public pacman(int x, int y) {
        super(x, y, "pacman.png");
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            flipLeft();
            dx = -MOVEVAL;
            dy = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            flipRight();
            dx = MOVEVAL;
            dy = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -MOVEVAL;
            dx = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = MOVEVAL;
            dx = 0;
        }
    }

//    public void keyReleased(KeyEvent e) {
//
//        int key = e.getKeyCode();
//
//        if (key == KeyEvent.VK_LEFT) {
//            dx = 0;
//        }
//
//        if (key == KeyEvent.VK_RIGHT) {
//            dx = 0;
//        }
//
//        if (key == KeyEvent.VK_UP) {
//            dy = 0;
//        }
//
//        if (key == KeyEvent.VK_DOWN) {
//            dy = 0;
//        }
//    }
}
