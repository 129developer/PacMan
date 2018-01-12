/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman.objects.maps;

import java.awt.Graphics2D;
import java.util.ArrayList;
import mypacman.objects.ghost;
import mypacman.objects.pacman;
import mypacman.utils.Constants;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author bayasys
 */
public class basicMapFunctions {

    JSONArray ary = new JSONArray();
    public pacman pm;
    ArrayList<ghost> gAry = new ArrayList<>();

    public void init() {
        try {
            for (int i = 0; i < ary.length(); i++) {
                JSONObject ob = ary.getJSONObject(i);
                if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("PM_POSITION")) {
                    this.pm = new pacman(ob.getInt("x"), ob.getInt("y"));
                    System.out.println("ADDED PM At : " + ob.getInt("x") + " ," + ob.getInt("y"));
                } else if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("H_GHOST_POSITION")) {
                    ghost g = new ghost(ob.getInt("x"), ob.getInt("y"), 2);
                    gAry.add(g);
                    System.out.println("ADDED H_GHOST_POSITION At : " + ob.getInt("x") + " ," + ob.getInt("y"));
                } else if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("V_GHOST_POSITION")) {
                    ghost g = new ghost(ob.getInt("x"), ob.getInt("y"), 3);
                    gAry.add(g);
                    System.out.println("ADDED V_GHOST_POSITION At : " + ob.getInt("x") + " ," + ob.getInt("y"));
                } else if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("F_GHOST_POSITION")) {
                    ghost g = new ghost(ob.getInt("x"), ob.getInt("y"), 1);
                    gAry.add(g);
                    System.out.println("ADDED F_GHOST_POSITION At : " + ob.getInt("x") + " ," + ob.getInt("y"));
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

    public void generateFromJSON(JSONArray ary, Graphics2D g2d) throws Exception {
        for (int i = 0; i < ary.length(); i++) {
            JSONObject ob = ary.getJSONObject(i);
            if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("PATH")) {
                if (ob.getString("Type").equalsIgnoreCase("H")) {
                    genPathBoxH(g2d, ob.getInt("x"), ob.getInt("y"), ob.getInt("width"));
                }
                if (ob.getString("Type").equalsIgnoreCase("V")) {
                    genPathBoxV(g2d, ob.getInt("x"), ob.getInt("y"), ob.getInt("height"));
                }
                if (ob.getString("Type").equalsIgnoreCase("F")) {
                    g2d.fillRect(ob.getInt("x"), ob.getInt("y"), ob.getInt("width"), ob.getInt("height"));
                }
            }
        }
    }

    public void moveGhosts() {
        gAry.forEach(g -> g.move(pm));

    }

    public void paintGhosts(Graphics2D g2d) {
        gAry.forEach(g2 -> g2d.drawImage(g2.getImage(), g2.getX(), g2.getY(), g2.getWidth(), g2.getHeight(), null));

    }

}
