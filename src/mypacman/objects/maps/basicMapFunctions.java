/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman.objects.maps;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import mypacman.objects.ghost;
import mypacman.objects.pacBall;
import mypacman.objects.pacman;
import mypacman.utils.Constants;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Mukil
 */
public class basicMapFunctions {

    JSONArray ary = new JSONArray();
    public pacman pm;
    ArrayList<ghost> gAry = new ArrayList<>();
    ArrayList<pacBall> pbAry = new ArrayList<>();
    ArrayList<pacBall> ppAry = new ArrayList<>();
    ArrayList<Rectangle> hboxAry = new ArrayList<>();
    ArrayList<Rectangle> vboxAry = new ArrayList<>();

    JSONObject ob;

    public void resetGhost(ghost g) {
//        System.out.println("RESET G" + g.getGHOSTTYPEHIS());
        try {
            for (int i = 0; i < ary.length(); i++) {
                JSONObject ob = ary.getJSONObject(i);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void genPathBoxH(Graphics2D g2d, int x, int y, int width) {
        g2d.fillRect(x, y, width, Constants.CHARWIDTH + 10);
    }

    public void genPathBoxV(Graphics2D g2d, int x, int y, int height) {
        g2d.fillRect(x, y, Constants.CHARWIDTH + 10, height);
    }

    public void init() {
        try {

            for (int i = 0; i < ary.length(); i++) {
                JSONObject ob = ary.getJSONObject(i);
                if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("PM_POSITION")) {
                    this.pm = new pacman(ob.getInt("x"), ob.getInt("y"));
//                    System.out.println("ADDED PM At : " + ob.getInt("x") + " ," + ob.getInt("y"));
                } else if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("H_GHOST_POSITION")) {
                    ghost g = new ghost(ob.getInt("x"), ob.getInt("y"), 2);
                    gAry.add(g);
//                    System.out.println("ADDED H_GHOST_POSITION At : " + ob.getInt("x") + " ," + ob.getInt("y"));
                } else if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("V_GHOST_POSITION")) {
                    ghost g = new ghost(ob.getInt("x"), ob.getInt("y"), 3);
                    gAry.add(g);
//                    System.out.println("ADDED V_GHOST_POSITION At : " + ob.getInt("x") + " ," + ob.getInt("y"));
                } else if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("F_GHOST_POSITION")) {
                    ghost g = new ghost(ob.getInt("x"), ob.getInt("y"), 1);
                    gAry.add(g);
//                    System.out.println("ADDED F_GHOST_POSITION At : " + ob.getInt("x") + " ," + ob.getInt("y"));
                } else if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("PB_POSITION")) {
                    pacBall g = new pacBall(ob.getInt("x"), ob.getInt("y"));
                    pbAry.add(g);
//                    System.out.println("ADDED PB_POSITION At : " + ob.getInt("x") + " ," + ob.getInt("y"));
                } else if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("PP_POSITION")) {
                    pacBall g = new pacBall(ob.getInt("x"), ob.getInt("y"));
                    g.setType("PP");
                    pbAry.add(g);
//                    System.out.println("ADDED PB_POSITION At : " + ob.getInt("x") + " ," + ob.getInt("y"));
                } else if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("PATH")) {
                    if (ob.getString("Type").equalsIgnoreCase("H")) {
                        Rectangle g = new Rectangle(ob.getInt("x"), ob.getInt("y"), ob.getInt("width"), 0);
                        hboxAry.add(g);
                    }
                    if (ob.getString("Type").equalsIgnoreCase("V")) {
                        Rectangle g = new Rectangle(ob.getInt("x"), ob.getInt("y"), 0, ob.getInt("height"));
                        vboxAry.add(g);
                    }
//                    System.out.println("ADDED PB_POSITION At : " + ob.getInt("x") + " ," + ob.getInt("y"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateFromJSON(Graphics2D g2d) throws Exception {

        if (hboxAry.size() > 0 || vboxAry.size() > 0) {
            if (hboxAry != null && hboxAry.size() > 0) {
                hboxAry.forEach(g2 -> genPathBoxH(g2d, (int) g2.getX(), (int) g2.getY(), (int) g2.getWidth()));
            }
            if (vboxAry != null && vboxAry.size() > 0) {
                vboxAry.forEach(g2 -> genPathBoxV(g2d, (int) g2.getX(), (int) g2.getY(), (int) g2.getHeight()));
            }
            if (pbAry.size() == 0) {
                Constants.CURRENTLEVEL.running = false;
                Constants.MAINTHREAD.interrupt();
                int result = JOptionPane.showConfirmDialog(null, "LEVEL COMPLETED...! Continue to next level?", "Congrats", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    Constants.CURRENTLEVEL = new level("[{\"Type\":\"H\",\"x\":149,\"y\":187,\"height\":40,\"width\":994,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1103,\"y\":188,\"height\":180,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":1103,\"y\":330,\"height\":40,\"width\":161,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1223,\"y\":332,\"height\":158,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":1225,\"y\":448,\"height\":40,\"width\":81,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1263,\"y\":450,\"height\":99,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":180,\"y\":514,\"height\":40,\"width\":1119,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":262,\"y\":209,\"height\":332,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":713,\"y\":366,\"height\":0,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"x\":175,\"y\":194,\"OBJTYPE\":\"PM_POSITION\"},{\"x\":1110,\"y\":332,\"OBJTYPE\":\"F_GHOST_POSITION\"},{\"x\":269,\"y\":518,\"OBJTYPE\":\"F_GHOST_POSITION\"},{\"x\":541,\"y\":202,\"OBJTYPE\":\"PP_POSITION\"},{\"x\":1119,\"y\":540,\"OBJTYPE\":\"PP_POSITION\"},{\"x\":1109,\"y\":202,\"OBJTYPE\":\"PP_POSITION\"},{\"x\":279,\"y\":364,\"OBJTYPE\":\"PP_POSITION\"},{\"Type\":\"H\",\"x\":478,\"y\":36,\"height\":0,\"width\":40,\"OBJTYPE\":\"PATH\"}]");
                    Constants.MAIN.currentMap = Constants.CURRENTLEVEL;
                    Constants.MAINTHREAD.start();
                }
            }
        } else {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ELSE  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            for (int i = 0; i < ary.length(); i++) {
                ob = ary.getJSONObject(i);
                if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("PATH")) {
                    if (ob.getString("Type").equalsIgnoreCase("H")) {
                        genPathBoxH(g2d, ob.getInt("x"), ob.getInt("y"), ob.getInt("width"));
                    }
                    if (ob.getString("Type").equalsIgnoreCase("V")) {
                        genPathBoxV(g2d, ob.getInt("x"), ob.getInt("y"), ob.getInt("height"));
                    }
                }
            }
        }
        paintGhosts(g2d);
        paintPBalls(g2d);
    }

    public void paintGhosts(Graphics2D g2d) {
        if (gAry != null && gAry.size() > 0) {
            gAry.forEach(g2 -> g2d.drawImage(g2.getImage(), g2.getX(), g2.getY(), g2.getWidth(), g2.getHeight(), null));
        }
    }

    public void moveGhosts() {
        if (gAry != null && gAry.size() > 0) {
            gAry.forEach(g -> g.move(pm));
        }
    }

    public void removePBAt(int x, int y) {
        pbAry.forEach((t) -> {
            if (t.getX() == x && t.getY() == y) {
                pbAry.remove(t);
            }
        });
    }

    public void paintPBalls(Graphics2D g2d) {
        if (pbAry != null && pbAry.size() > 0) {
            for (int i = 0; i < pbAry.size(); i++) {
                pacBall g2 = pbAry.get(i);
                if (!pm.contains(g2)) {
                    if (g2.getType() == "PP") {
                        g2d.setColor(Color.WHITE);
                        g2d.fillOval(g2.getX(), g2.getY(), 15, 15);
                    } else {
                        g2d.setColor(Color.PINK);
                        g2d.fillOval(g2.getX(), g2.getY(), 10, 10);
                    }
                } else {
                    if (g2.getType() == "PP") {
                        gAry.forEach((t) -> {
                            t.SetFrightend();
                        });
                    }
                    System.out.println("FOUND PB");
                    pbAry.remove(i);
                }
            }
//                vboxAry.forEach(g2 -> genPathBoxV(g2d, (int) g2.getX(), (int) g2.getY(), (int) g2.getHeight()));
        }

//        if (pbAry != null && pbAry.size() > 0) {
//            g2d.setColor(Color.PINK);
//            pbAry.forEach(g2 -> g2d.fillOval(g2.getX(), g2.getY(), 10, 10));
//        }
    }

}
