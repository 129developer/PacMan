/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman.objects;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

/**
 *
 * @author bayasys
 */
public class ghost extends Sprite implements ActionListener {

    boolean left = false,
            openmouth = true;
    private int dx;
    private int dy;
    private int MOVEVAL = 4;
    Timer timer;
    Color BLOCKCOLOR = Color.RED;

    public ghost(int x, int y, String imagename) {
        super(x, y, imagename);
        timer = new Timer(40, this);
        timer.start();
    }

    public boolean isLeft() {
        return left;
    }

    public ghost(int x, int y) {
        super(x, y, "ghost1.png");
        timer = new Timer(250, this);
        timer.start();
    }

    public void move(pacman pm) {
        checkCollition();
        int pmx = pm.getX();
        int pmy = pm.getY();
        int difx = pmx - x;
        int dify = pmy - y;
        dify = dify > 0 ? dify : -dify;
        difx = difx > 0 ? difx : -difx;
        if (difx > dify) {
            // X
            if (pmx > x) {
                dx = 1;
                if (checkCollition(KeyEvent.VK_RIGHT)) {
                    if (checkCollition(KeyEvent.VK_LEFT)) {
                        dx = 0;
                    } else {
                        dx = -1;
                    }

                }
            } else {
                dx = -1;
                if (checkCollition(KeyEvent.VK_LEFT)) {
                    if (checkCollition(KeyEvent.VK_RIGHT)) {
                        dx = 0;
                    } else {
                        dx = 1;
                    }

                }
            }
        } else {
            // Y

            if (pmy > y) { //down
                dy = 1;
                if (checkCollition(KeyEvent.VK_DOWN)) {
                    if (checkCollition(KeyEvent.VK_UP)) {
                        dy = 0;
                    } else {
                        dy = -1;
                    }

                }
            } else {//up
                dy = -1;
                if (checkCollition(KeyEvent.VK_UP)) {
                    if (checkCollition(KeyEvent.VK_DOWN)) {
                        dy = 0;
                    } else {
                        dy = 1;
                    }

                }
            }
        }
        x += dx;
        y += dy;
    }

    public static Color getPixel(int x, int y) throws AWTException {
        Robot rb = new Robot();
        return rb.getPixelColor(x, y);
    }

    public boolean checkCollition() {
        try {
            boolean flag = false;

            if (dx > 0 && (getPixel(dx + x + width, dy + y).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {//right
                System.out.println("RIGHT");
                flag = true;
            } else if (dx < 0 && (getPixel(dx + x + width, dy + y).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {////left
                System.out.println("LEFT");
                flag = true;
            } else if (dy > 0 && (getPixel(dx + x, dy + y + height).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {//down
                System.out.println("DOWN");
                flag = true;
            } else if (dy < 0 && (getPixel(dx + x, dy + y).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {//up
                System.out.println("UP");
                flag = true;
            }
//            System.out.println(x + "," + y);

            if (flag) {
                dx = 0;
                dy = 0;
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkCollition(int key) {
        boolean flag = false;
        try {
            int dx = 0, MOVEVAL = this.MOVEVAL + 10;
            int dy = 0;
            if (KeyEvent.VK_RIGHT == key) {
                dx = MOVEVAL;
                if ((getPixel(dx + x + width, dy + y).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {//right
                    System.out.println("RIGHT FAILED");
                    flag = true;
                }
            }
            if (KeyEvent.VK_LEFT == key) {
                dx = -MOVEVAL;
                if (dx < 0 && (getPixel(dx + x + width, dy + y).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {////left
                    System.out.println("LEFT FAILED");
                    flag = true;
                }
            }
            if (KeyEvent.VK_DOWN == key) {
                dy = MOVEVAL;
                if (dy > 0 && (getPixel(dx + x, dy + y + height).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {//down
                    System.out.println("DOWN FAILED");
                    flag = true;
                }
            }
            if (KeyEvent.VK_UP == key) {
                dy = -MOVEVAL;
                if (dy < 0 && (getPixel(dx + x, dy + y).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {//up
                    System.out.println("UP FAILED");
                    flag = true;
                }
            }
            //    System.out.println(x + "," + y);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            if (!checkCollition(KeyEvent.VK_LEFT)) {
                dx = -MOVEVAL;
                dy = 0;
            }
        }

        if (key == KeyEvent.VK_RIGHT) {
            if (!checkCollition(KeyEvent.VK_RIGHT)) {
                dx = MOVEVAL;
                dy = 0;
            }
        }

        if (key == KeyEvent.VK_UP) {
            if (!checkCollition(KeyEvent.VK_UP)) {
                dy = -MOVEVAL;
                dx = 0;
            }
        }

        if (key == KeyEvent.VK_DOWN) {
            if (!checkCollition(KeyEvent.VK_DOWN)) {
                dy = MOVEVAL;
                dx = 0;
            }
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
    @Override
    public void actionPerformed(ActionEvent e) {
//        if (!openmouth) {
//            openmouth = true;
//            loadImage("pacman.png");
//        } else {
//            openmouth = false;
//            loadImage("pacmanclosed.png");
//        }
    }

}
