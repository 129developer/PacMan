/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman.objects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.TimerTask;
import javax.swing.Timer;
import mypacman.utils.Constants;
import mypacman.utils.genUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Mukil
 */
public class ghost extends Sprite implements ActionListener {

    boolean touchleft = true, touchright = false, openmouth = true;
    private int FRIGHT_DELAY = 10;
    private int dx = 0;
    private int dy = 0;
    private boolean FRIGHTEND = false;
    private int GHOSTTYPE = 1;//1 Follower, 2 horizondal guard, 3 vertical guard
    private int GHOSTTYPEHIS = 2;//1 Follower, 2 horizondal guard, 3 vertical guard,4 FRIGHTEND
    Timer timer;
    java.util.Timer timer1 = new java.util.Timer();

    public int getGHOSTTYPE() {
        return GHOSTTYPE;
    }

    public int getGHOSTTYPEHIS() {
        return GHOSTTYPEHIS;
    }

    public void SetFrightend() {
        if (FRIGHTEND == true) {
            if (timer != null) {
                timer.stop();
                timer.start();
            }
            return;
        }
        FRIGHTEND = true;
        if (timer != null) {
            timer.stop();
        }
        GHOSTTYPEHIS = GHOSTTYPE;
        GHOSTTYPE = 4;
        loadImage("ghost" + GHOSTTYPE + ".png");
        MOVEVAL -= 2;
        timer = new Timer(1000 * FRIGHT_DELAY, this);
        timer.start();
    }

    public void RevertFrightend() {
        FRIGHTEND = false;
        GHOSTTYPE = GHOSTTYPEHIS;
        loadImage("ghost" + GHOSTTYPE + ".png");
        MOVEVAL += 2;
        timer.stop();
        timer = null;
    }

    public ghost(int x, int y, int gt) {
        super(x, y, "ghost" + gt + ".png");
        GHOSTTYPE = gt;
        MOVEVAL = Constants.MOVEVAL;
        timer1.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!FRIGHTEND) {
                    GHOSTTYPE = genUtils.getRandomNumberInRange(1, 4);
                }
            }
        }, 500, 1000);
    }

    public void move(pacman pm) {

        if (GHOSTTYPE == 1) {
            moveFollower(pm);
        } else if (GHOSTTYPE == 2) {
            moveHGuard();
        } else if (GHOSTTYPE == 3) {
            moveHGuard();
        } else if (GHOSTTYPE == 4) {
            moveFrightend(pm);
        }
        int pmx = pm.getX();
        int pmy = pm.getY();
        int difx = pmx - x;
        int dify = pmy - y;
        dify = Math.abs(dify);
        difx = Math.abs(difx);

        if (contains(pm) && GHOSTTYPE != 4) {
            pm.die();
        } else if (contains(pm) && GHOSTTYPE == 4) {
            Constants.MAIN.Point += 100;
            resetGhost(this);
        }
    }

    public void resetGhost(ghost g) {
        System.out.println("RESET G" + g.getGHOSTTYPEHIS());

        try {
            for (int i = 0; i < Constants.CURRENTLEVEL.ary.length(); i++) {
                JSONObject ob = Constants.CURRENTLEVEL.ary.getJSONObject(i);
                if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("H_GHOST_POSITION") && g.getGHOSTTYPEHIS() == 2) {
                    g.setX(ob.getInt("x"));
                    g.setY(ob.getInt("y"));
                    g.RevertFrightend();
                } else if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("V_GHOST_POSITION") && g.getGHOSTTYPEHIS() == 3) {
                    g.setX(ob.getInt("x"));
                    g.setY(ob.getInt("y"));
                    g.RevertFrightend();
                } else if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("F_GHOST_POSITION") && g.getGHOSTTYPEHIS() == 1) {
                    g.setX(ob.getInt("x"));
                    g.setY(ob.getInt("y"));
                    g.RevertFrightend();
                }
            }
            g.setMOVEVAL(Constants.MOVEVAL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean contains(pacman r) {
        return x < r.x + r.width && x + width > r.x && y < r.y + r.height && y + height > r.y;
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
        checkCollition();
        int pmx = pm.getX();
        int pmy = pm.getY();
        int difx = Math.abs(pmx - x);
        int dify = Math.abs(pmy - y);

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
        x += dx;
        y += dy;
    }

    public void moveFrightend(pacman pm) {
        checkCollition();
        int pmx = pm.getX();
        int pmy = pm.getY();
        int difx = Math.abs(pmx - x);
        int dify = Math.abs(pmy - y);

        if (pmy < y) { //down
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
        if (pmx < x) {
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
        x += dx;
        y += dy;
    }

    public void moveHGuard() {
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

    public boolean checkCollition(int key) {
        boolean flag = false;
        try {
            int dx = 0, MOVEVAL = this.MOVEVAL + 5;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        RevertFrightend();
    }

}
