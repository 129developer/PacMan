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
 * @author Mukil
 */
public class ghost extends Sprite implements ActionListener {

    boolean touchleft = true, touchright = false, openmouth = true;
    private int dx = 0;
    private int dy = 0;
    private int GHOSTTYPE = 2;//1 Follower, 2 horizondal guard, 3 vertical guard
    Timer timer;

    public int getGHOSTTYPE() {
        return GHOSTTYPE;
    }

    public void setGHOSTTYPE(int GHOSTTYPE) {
        this.GHOSTTYPE = GHOSTTYPE;
    }

    public ghost(int x, int y, String imagename) {
        super(x, y, imagename);
//        timer = new Timer(40, this);
//        timer.start();
    }

    public ghost(int x, int y, int gt) {
        super(x, y, "ghost" + gt + ".png");
        GHOSTTYPE = gt;
//        timer = new Timer(250, this);
//        timer.start();
    }

    public void move(pacman pm) {
        if (GHOSTTYPE == 1) {
            moveFollower(pm);
        } else if (GHOSTTYPE == 2) {
            moveHGuard();
        } else if (GHOSTTYPE == 3) {
            moveHGuard();
        }
        int pmx = pm.getX();
        int pmy = pm.getY();
        int difx = pmx - x;
        int dify = pmy - y;
        dify = Math.abs(dify);
        difx = Math.abs(difx);
        if (difx < width && dify < height) {
            pm.die();
        }
    }

    public void moveFollowerNew(pacman pm) {
        checkCollition();
        int pmx = pm.getX();
        int pmy = pm.getY();
        int difx = Math.abs(pmx - x);
        int dify = Math.abs(pmy - y);
        if (difx > dify) { // dist to X is > dis to Y
            if (pmx > x) { //PM on right
                if (!checkCollition(KeyEvent.VK_RIGHT)) {
                    dx = MOVEVAL;
                } else if (!checkCollition(KeyEvent.VK_LEFT)) {
                    dx = -MOVEVAL;
                } else {
                    dx = 0;
                }
            } else if (x > pmx) { //PM on left
                if (!checkCollition(KeyEvent.VK_LEFT)) {
                    dx = -MOVEVAL;
                } else if (!checkCollition(KeyEvent.VK_RIGHT)) {
                    dx = MOVEVAL;
                } else {
                    dx = 0;
                }
            }
            if (dx == 0) { //Cant move left or right
                if (pmy > y) { //PM down 
                    dy = MOVEVAL;
                    if (checkCollition(KeyEvent.VK_DOWN)) {
                        if (checkCollition(KeyEvent.VK_UP)) {
                            dy = 0;
                        } else {
                            dy = -MOVEVAL;
                        }
                    }
                } else {//up
                    dy = -MOVEVAL;
                    if (checkCollition(KeyEvent.VK_UP)) {
                        if (checkCollition(KeyEvent.VK_DOWN)) {
                            dy = 0;
                        } else {
                            dy = MOVEVAL;
                        }
                    }
                }
            }
        } else {// dist to Y is > dis to X
            if (pmy > y) { //PM on right
                if (!checkCollition(KeyEvent.VK_DOWN)) {
                    dy = MOVEVAL;
                } else if (!checkCollition(KeyEvent.VK_UP)) {
                    dy = -MOVEVAL;
                } else {
                    dy = 0;
                }
            } else if (y > pmy) { //PM on left
                if (!checkCollition(KeyEvent.VK_UP)) {
                    dy = -MOVEVAL;
                } else if (!checkCollition(KeyEvent.VK_DOWN)) {
                    dy = MOVEVAL;
                } else {
                    dy = 0;
                }
            }

            if (dy == 0) {
                if (pmx > x) {
                    dx = MOVEVAL;
                    if (checkCollition(KeyEvent.VK_RIGHT)) {
                        if (checkCollition(KeyEvent.VK_LEFT)) {
                            dx = 0;
                        } else {
                            dx = -MOVEVAL;
                        }
                    }
                } else {
                    dx = -MOVEVAL;
                    if (checkCollition(KeyEvent.VK_LEFT)) {
                        if (checkCollition(KeyEvent.VK_RIGHT)) {
                            dx = 0;
                        } else {
                            dx = MOVEVAL;
                        }

                    }
                }
            }

        }
        x += dx;
        y += dy;
    }

    public void moveFollower(pacman pm) {

//        checkCollition();
        int pmx = pm.getX();
        int pmy = pm.getY();
        int difx = Math.abs(pmx - x);
        int dify = Math.abs(pmy - y);
        if (difx > dify) {
            // X
            if (pmx > x) {
                dx = MOVEVAL;
                if (checkCollition(KeyEvent.VK_RIGHT)) {
                    if (checkCollition(KeyEvent.VK_LEFT)) {
                        dx = 0;
                    } else {
                        dx = -MOVEVAL;
                    }
                }
            } else {
                dx = -MOVEVAL;
                if (checkCollition(KeyEvent.VK_LEFT)) {
                    if (checkCollition(KeyEvent.VK_RIGHT)) {
                        dx = 0;
                    } else {
                        dx = MOVEVAL;
                    }

                }
            }
        }

        if (dx == 0) {
            // Y

            if (pmy > y) { //down
                dy = MOVEVAL;
                if (checkCollition(KeyEvent.VK_DOWN)) {
                    if (checkCollition(KeyEvent.VK_UP)) {
                        dy = 0;
                    } else {
                        dy = -MOVEVAL;
                    }
                }
            } else {//up
                dy = -MOVEVAL;
                if (checkCollition(KeyEvent.VK_UP)) {
                    if (checkCollition(KeyEvent.VK_DOWN)) {
                        dy = 0;
                    } else {
                        dy = MOVEVAL;
                    }

                }
            }
        }
        x += dx;
        y += dy;
//        moveFollowerNew(pm);
    }

    public void moveHGuard() {
//        checkCollition();
        // X
        if (touchleft) {
            if (!checkCollition(KeyEvent.VK_RIGHT)) {
                dx = MOVEVAL;
            } else {
                touchright = true;
                touchleft = false;
                dx = -MOVEVAL;
            }
        }

        if (touchright) {
            dx = MOVEVAL;
            if (!checkCollition(KeyEvent.VK_LEFT)) {
                dx = -MOVEVAL;
            } else {
                touchleft = true;
                touchright = false;
                dx = MOVEVAL;
            }
        }
        x += dx;
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

//    public boolean checkCollition(int key) {
//        boolean flag = false;
//        try {
//            int dx = 0, MOVEVAL = this.MOVEVAL;
//            int dy = 0;
//            if (KeyEvent.VK_RIGHT == key) {
//                dx = MOVEVAL;
//                if ((getPixel(dx + x + width, dy + y).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {//right
//                    flag = true;
//                }
//            }
//            if (KeyEvent.VK_LEFT == key) {
//                dx = -MOVEVAL;
//                if (dx < 0 && (getPixel(dx + x + width, dy + y).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {////left
//                    flag = true;
//                }
//            }
//            if (KeyEvent.VK_DOWN == key) {
//                dy = MOVEVAL;
//                if (dy > 0 && (getPixel(dx + x, dy + y + height).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {//down
//                    flag = true;
//                }
//            }
//            if (KeyEvent.VK_UP == key) {
//                dy = -MOVEVAL;
//                if (dy < 0 && (getPixel(dx + x, dy + y).equals(BLOCKCOLOR) || getPixel(dx + x + width, dy + y + height).equals(BLOCKCOLOR))) {//up
//                    flag = true;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return flag;
//    }
    public boolean checkCollition(int key) {
        boolean flag = false;
        try {
            int dx = 0, MOVEVAL = this.MOVEVAL;
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

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
