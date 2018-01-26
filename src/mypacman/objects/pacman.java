/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman.objects;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import mypacman.utils.Constants;

/**
 *
 * @author Mukil
 */
public class pacman extends Sprite {
    
    boolean left = false, openmouth = true, isAlive = true;
    private int dx;
    private int dy;
    Thread thread;
    
    public pacman(int x, int y) {
        super(x, y, "pacmanclosed.png");
        thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        actionPerformed(null);
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
    
    public void flipLeft() {
        if (!left) {
            left = true;
            loadImage("pacmanclosedle.png");
        }
    }
    
    public void flipRight() {
        if (left) {
            left = false;
            loadImage("pacmanclosed.png");
        }
    }
    
    public boolean contains(pacBall p) {
        return (getX() < p.getX() && getY() < p.getY() && getX() + getWidth() > p.getX() && getY() + getHeight() > p.getY());
    }
    
    public void die() {
        loadImage("Heat.png");
        isAlive = false;
        openmouth = false;
        loadImage("Heat2.png");
        thread.interrupt();
        Constants.CURRENTLEVEL.running = false;
        Constants.MAINTHREAD.interrupt();
        JOptionPane.showMessageDialog(null, "Level Failed with " + Constants.MAIN.Point + " points");
        System.exit(0);
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
            } else if (dx < 0 && (getPixel(dx + x, dy + y).equals(BLOCKCOLOR) || getPixel(dx + x, dy + y + height).equals(BLOCKCOLOR))) {////left
                flag = true;
            } else if (dy > 0 && (getPixel(dx + x, dy + y + height).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {//down
                flag = true;
            } else if (dy < 0 && (getPixel(dx + x, dy + y).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y).equals(BLOCKCOLOR))) {//up
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
                if (dx < 0 && (getPixel(dx + x, dy + y).equals(BLOCKCOLOR) || getPixel(dx + x, dy + y + height).equals(BLOCKCOLOR))) {////left
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
                if (dy < 0 && (getPixel(dx + x, dy + y).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y).equals(BLOCKCOLOR))) {//up
                    flag = true;
                }
            }
            
        } catch (AWTException e) {
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
                if (!left) {
                    loadImage("pacman.png");
                } else {
                    loadImage("pacmanle.png");
                }
            } else {
                openmouth = false;
                if (!left) {
                    loadImage("pacmanclosed.png");
                } else {
                    loadImage("pacmanclosedle.png");
                }
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
