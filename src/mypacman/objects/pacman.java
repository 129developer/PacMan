/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman.objects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

/**
 *
 * @author bayasys
 */
public class pacman extends Sprite {

    boolean left = false, openmouth = true, isAlive = true;
    private int dx;
    private int dy;
//    Timer timer;

//    public pacman(int x, int y, String imagename) {
//        super(x, y, imagename);
//        Thread thread = new Thread() {
//            public void run() {
//                while (true) {
//                    try {
//                        actionPerformed(null);
//                        Thread.sleep(20);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//
//        thread.start();
////        timer = new Timer(150, this);
////        timer.start();
//    }

    public pacman(int x, int y) {
        super(x, y, "pacmanclosed.png");
        Thread thread = new Thread() {
            public void run() {
                while (true) {
                    try {
                        actionPerformed(null);
                        Thread.sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread.start();
//        timer = new Timer(150, this);
//        timer.start();
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

    public void die() {
        loadImage("Heat.png");
        isAlive = false;
        openmouth = false;
        loadImage("Heat2.png");
    }

    public boolean isLeft() {
        return left;
    }

    public void move() {
        if (!isAlive) {
            return;
        }
        checkCollition();
        x += dx;
        y += dy;
    }

    public boolean checkCollition() {
        try {
            boolean flag = false;

            if (dx > 0 && (getPixel(dx + x + width, dy + y).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {//right
                flag = true;
            } else if (dx < 0 && (getPixel(dx + x + width, dy + y).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {////left
                flag = true;
            } else if (dy > 0 && (getPixel(dx + x, dy + y + height).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {//down
                flag = true;
            } else if (dy < 0 && (getPixel(dx + x, dy + y).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {//up
                flag = true;
            }
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
                    flag = true;
                }
            }
            if (KeyEvent.VK_LEFT == key) {
                dx = -MOVEVAL;
                if (dx < 0 && (getPixel(dx + x + width, dy + y).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {////left
                    flag = true;
                }
            }
            if (KeyEvent.VK_DOWN == key) {
                dy = MOVEVAL;
                if (dy > 0 && (getPixel(dx + x, dy + y + height).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {//down
                    flag = true;
                }
            }
            if (KeyEvent.VK_UP == key) {
                dy = -MOVEVAL;
                if (dy < 0 && (getPixel(dx + x, dy + y).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {//up
                    flag = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void keyPressed(KeyEvent e) {
        if (!isAlive) {
            return;
        }
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            if (!checkCollition(KeyEvent.VK_LEFT)) {
                flipLeft();
                dx = -MOVEVAL;
                dy = 0;
            }
        }

        if (key == KeyEvent.VK_RIGHT) {
            if (!checkCollition(KeyEvent.VK_RIGHT)) {
                flipRight();
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

    public void actionPerformed(ActionEvent e) {
        if (isAlive) {
            if (!openmouth) {
                openmouth = true;
                loadImage("pacman.png");
            } else {
                openmouth = false;
                loadImage("pacmanclosed.png");
            }
        } else {
            if (!openmouth) {
                openmouth = true;
                loadImage("Heat.png");
            } else {
                openmouth = false;
                loadImage("Heat2.png");
            }
        }
    }
}
