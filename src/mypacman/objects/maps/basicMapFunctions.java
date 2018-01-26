package mypacman.objects.maps;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import mypacman.objects.ghost;
import mypacman.objects.pacBall;
import mypacman.objects.pacman;
import mypacman.utils.Constants;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Mukil
 */
public class basicMapFunctions {

    public JSONArray ary = new JSONArray();
    public pacman pm;
    ArrayList<ghost> gAry = new ArrayList<>();
    ArrayList<pacBall> pbAry = new ArrayList<>();
    ArrayList<pacBall> ppAry = new ArrayList<>();
    ArrayList<Rectangle> hboxAry = new ArrayList<>();
    ArrayList<Rectangle> vboxAry = new ArrayList<>();
    JSONObject ob;

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
                } else if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("H_GHOST_POSITION")) {
                    ghost g = new ghost(ob.getInt("x"), ob.getInt("y"), 2);
                    gAry.add(g);
                } else if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("V_GHOST_POSITION")) {
                    ghost g = new ghost(ob.getInt("x"), ob.getInt("y"), 3);
                    gAry.add(g);
                } else if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("F_GHOST_POSITION")) {
                    ghost g = new ghost(ob.getInt("x"), ob.getInt("y"), 1);
                    gAry.add(g);
                } else if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("PB_POSITION")) {
                    pacBall g = new pacBall(ob.getInt("x"), ob.getInt("y"));
                    pbAry.add(g);
                } else if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("PP_POSITION")) {
                    pacBall g = new pacBall(ob.getInt("x"), ob.getInt("y"));
                    g.setType("PP");
                    pbAry.add(g);
                } else if (ob.has("OBJTYPE") && ob.getString("OBJTYPE").equalsIgnoreCase("PATH")) {
                    if (ob.getString("Type").equalsIgnoreCase("H")) {
                        Rectangle g = new Rectangle(ob.getInt("x"), ob.getInt("y"), ob.getInt("width"), 0);
                        hboxAry.add(g);
                    }
                    if (ob.getString("Type").equalsIgnoreCase("V")) {
                        Rectangle g = new Rectangle(ob.getInt("x"), ob.getInt("y"), 0, ob.getInt("height"));
                        vboxAry.add(g);
                    }
                }
            }
        } catch (JSONException e) {
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
            if (pbAry.isEmpty()) {
                Constants.CURRENTLEVEL.running = false;
                Constants.MAINTHREAD.interrupt();
                int result = JOptionPane.showConfirmDialog(null, "Level completed with " + Constants.MAIN.Point + " points, Continue to next level?", "Congrats", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    Constants.CURRENTLEVEL = new level("[{\"Type\":\"H\",\"x\":41,\"y\":61,\"height\":40,\"width\":1282,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":40,\"y\":62,\"height\":593,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":42,\"y\":616,\"height\":40,\"width\":1266,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1286,\"y\":62,\"height\":594,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":74,\"y\":147,\"height\":40,\"width\":77,\"OBJTYPE\":\"PATH\"},{\"x\":74,\"y\":157,\"OBJTYPE\":\"PP_POSITION\"},{\"x\":74,\"y\":167,\"OBJTYPE\":\"PB_POSITION\"},{\"Type\":\"V\",\"x\":142,\"y\":94,\"height\":169,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":67,\"y\":234,\"height\":40,\"width\":243,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":270,\"y\":264,\"height\":362,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":67,\"y\":342,\"height\":40,\"width\":205,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":53,\"y\":299,\"height\":40,\"width\":16,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":70,\"y\":294,\"height\":40,\"width\":209,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":100,\"y\":264,\"height\":48,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":157,\"y\":269,\"height\":41,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":219,\"y\":266,\"height\":41,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":69,\"y\":404,\"height\":40,\"width\":95,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":125,\"y\":405,\"height\":120,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":74,\"y\":485,\"height\":40,\"width\":52,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":151,\"y\":455,\"height\":40,\"width\":133,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":187,\"y\":483,\"height\":104,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":68,\"y\":547,\"height\":40,\"width\":141,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":152,\"y\":574,\"height\":47,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":299,\"y\":528,\"height\":40,\"width\":1018,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":357,\"y\":96,\"height\":455,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":461,\"y\":97,\"height\":472,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":563,\"y\":94,\"height\":452,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":664,\"y\":96,\"height\":444,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":760,\"y\":91,\"height\":451,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":852,\"y\":91,\"height\":463,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":774,\"y\":177,\"height\":40,\"width\":86,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":695,\"y\":260,\"height\":40,\"width\":71,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":579,\"y\":366,\"height\":40,\"width\":94,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":476,\"y\":455,\"height\":40,\"width\":97,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":383,\"y\":380,\"height\":40,\"width\":87,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":166,\"y\":147,\"height\":40,\"width\":605,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":973,\"y\":90,\"height\":82,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":883,\"y\":142,\"height\":40,\"width\":129,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":936,\"y\":179,\"height\":104,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":969,\"y\":221,\"height\":40,\"width\":143,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1061,\"y\":97,\"height\":161,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":1085,\"y\":140,\"height\":40,\"width\":130,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1185,\"y\":79,\"height\":101,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1111,\"y\":168,\"height\":279,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":875,\"y\":282,\"height\":40,\"width\":243,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1025,\"y\":312,\"height\":164,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":878,\"y\":381,\"height\":40,\"width\":414,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1185,\"y\":174,\"height\":226,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":883,\"y\":475,\"height\":40,\"width\":272,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1052,\"y\":441,\"height\":40,\"width\":5,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":1052,\"y\":428,\"height\":40,\"width\":99,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":1138,\"y\":476,\"height\":0,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":1124,\"y\":475,\"height\":40,\"width\":163,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":538,\"y\":565,\"height\":54,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":740,\"y\":558,\"height\":59,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":959,\"y\":557,\"height\":60,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1126,\"y\":557,\"height\":77,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":851,\"y\":556,\"height\":75,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":638,\"y\":564,\"height\":62,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":459,\"y\":563,\"height\":55,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":298,\"y\":582,\"height\":40,\"width\":7,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":299,\"y\":573,\"height\":40,\"width\":170,\"OBJTYPE\":\"PATH\"},{\"x\":147,\"y\":151,\"OBJTYPE\":\"PM_POSITION\"},{\"x\":45,\"y\":621,\"OBJTYPE\":\"H_GHOST_POSITION\"},{\"Type\":\"H\",\"x\":1290,\"y\":76,\"height\":0,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"x\":1291,\"y\":67,\"OBJTYPE\":\"V_GHOST_POSITION\"},{\"x\":568,\"y\":155,\"OBJTYPE\":\"F_GHOST_POSITION\"},{\"x\":54,\"y\":67,\"OBJTYPE\":\"PP_POSITION\"},{\"x\":673,\"y\":80,\"OBJTYPE\":\"PP_POSITION\"},{\"x\":668,\"y\":627,\"OBJTYPE\":\"PP_POSITION\"},{\"x\":1293,\"y\":624,\"OBJTYPE\":\"PP_POSITION\"},{\"x\":1300,\"y\":356,\"OBJTYPE\":\"PP_POSITION\"},{\"x\":115,\"y\":74,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":268,\"y\":75,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":450,\"y\":77,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":642,\"y\":76,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":891,\"y\":75,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":1051,\"y\":74,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":1211,\"y\":73,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":1203,\"y\":161,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":1207,\"y\":306,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":1208,\"y\":395,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":881,\"y\":401,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":668,\"y\":380,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":469,\"y\":394,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":373,\"y\":402,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":150,\"y\":631,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":307,\"y\":631,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":474,\"y\":634,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":605,\"y\":634,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":806,\"y\":635,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":956,\"y\":630,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":1094,\"y\":632,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":1206,\"y\":636,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":52,\"y\":224,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":52,\"y\":367,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":58,\"y\":508,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":1305,\"y\":167,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":1301,\"y\":283,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":1305,\"y\":444,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":1305,\"y\":556,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":868,\"y\":551,\"OBJTYPE\":\"PB_POSITION\"},{\"x\":483,\"y\":541,\"OBJTYPE\":\"PB_POSITION\"}]");
                    Constants.MAIN.currentMap = Constants.CURRENTLEVEL;
                    Constants.MAINTHREAD.start();
                }
            }
        } else {
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
                    if (g2.getType().equalsIgnoreCase("PP")) {
                        g2d.setColor(Color.WHITE);
                        g2d.fillOval(g2.getX(), g2.getY(), 15, 15);
                    } else {
                        g2d.setColor(Color.PINK);
                        g2d.fillOval(g2.getX(), g2.getY(), 10, 10);
                    }
                } else {
                    if (g2.getType().equalsIgnoreCase("PP")) {
                        gAry.forEach((t) -> {
                            t.SetFrightend();
                        });
                    }
                    Constants.MAIN.Point += 10;
                    pbAry.remove(i);
                }
            }
        }
    }

}
