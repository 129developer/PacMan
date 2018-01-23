/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman.utils;

import java.awt.Color;
import java.awt.Frame;
import static java.awt.Frame.NORMAL;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.Timer;
import mypacman.objects.Sprite;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author bayasys
 */
public class MapGenerator extends Frame implements ActionListener {

    public static void main(String[] args) {
        new MapGenerator();
    }

    ArrayList<Shape> sAry = new ArrayList<Shape>();
    boolean started = false;
    int startX = 0, startY = 0, endX = 0, endY = 0, curX = 0, curY = 0, popupX = 0, popupY = 0;
    Timer timer = new Timer(20, this);

    public MapGenerator() {
        super("MapGenerator");
        TAdapter ta = new TAdapter();

        addMouseListener(ta);
        addKeyListener(new KbAdapter());
        addMouseMotionListener(ta);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }

            public void windowIconified(WindowEvent we) {
                setState(NORMAL);
            }
        });
        timer.start();
        setBackground(Constants.BLOCKCOLOR);
        setIgnoreRepaint(true);
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setResizable(false);
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
        setMaximizedBounds(null);
        setVisible(true);
        try {
            generateFromJSONAry("[{\"Type\":\"H\",\"x\":41,\"y\":61,\"height\":40,\"width\":1282,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":40,\"y\":62,\"height\":593,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":42,\"y\":616,\"height\":40,\"width\":1266,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1286,\"y\":62,\"height\":594,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":74,\"y\":147,\"height\":40,\"width\":77,\"OBJTYPE\":\"PATH\"},{\"x\":74,\"y\":157,\"OBJTYPE\":\"PP_POSITION\"},{\"x\":74,\"y\":167,\"OBJTYPE\":\"PB_POSITION\"},{\"Type\":\"V\",\"x\":142,\"y\":94,\"height\":169,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":67,\"y\":234,\"height\":40,\"width\":243,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":270,\"y\":264,\"height\":362,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":67,\"y\":342,\"height\":40,\"width\":205,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":53,\"y\":299,\"height\":40,\"width\":16,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":70,\"y\":294,\"height\":40,\"width\":209,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":100,\"y\":264,\"height\":48,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":157,\"y\":269,\"height\":41,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":219,\"y\":266,\"height\":41,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":69,\"y\":404,\"height\":40,\"width\":95,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":125,\"y\":405,\"height\":120,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":74,\"y\":485,\"height\":40,\"width\":52,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":151,\"y\":455,\"height\":40,\"width\":133,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":187,\"y\":483,\"height\":104,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":68,\"y\":547,\"height\":40,\"width\":141,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":152,\"y\":574,\"height\":47,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":299,\"y\":528,\"height\":40,\"width\":1018,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":357,\"y\":96,\"height\":455,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":461,\"y\":97,\"height\":472,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":563,\"y\":94,\"height\":452,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":664,\"y\":96,\"height\":444,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":760,\"y\":91,\"height\":451,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":852,\"y\":91,\"height\":463,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":774,\"y\":177,\"height\":40,\"width\":86,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":695,\"y\":260,\"height\":40,\"width\":71,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":579,\"y\":366,\"height\":40,\"width\":94,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":476,\"y\":455,\"height\":40,\"width\":97,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":383,\"y\":380,\"height\":40,\"width\":87,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":166,\"y\":147,\"height\":40,\"width\":605,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":973,\"y\":90,\"height\":82,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":883,\"y\":142,\"height\":40,\"width\":129,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":936,\"y\":179,\"height\":104,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":969,\"y\":221,\"height\":40,\"width\":143,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1061,\"y\":97,\"height\":161,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":1085,\"y\":140,\"height\":40,\"width\":130,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1185,\"y\":79,\"height\":101,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1111,\"y\":168,\"height\":279,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":875,\"y\":282,\"height\":40,\"width\":243,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1025,\"y\":312,\"height\":164,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":878,\"y\":381,\"height\":40,\"width\":414,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1185,\"y\":174,\"height\":226,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":883,\"y\":475,\"height\":40,\"width\":272,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1052,\"y\":441,\"height\":40,\"width\":5,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":1052,\"y\":428,\"height\":40,\"width\":99,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":1138,\"y\":476,\"height\":0,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":1124,\"y\":475,\"height\":40,\"width\":163,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":538,\"y\":565,\"height\":54,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":740,\"y\":558,\"height\":59,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":959,\"y\":557,\"height\":60,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1126,\"y\":557,\"height\":77,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":851,\"y\":556,\"height\":75,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":638,\"y\":564,\"height\":62,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":459,\"y\":563,\"height\":55,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":298,\"y\":582,\"height\":40,\"width\":7,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":299,\"y\":573,\"height\":40,\"width\":170,\"OBJTYPE\":\"PATH\"},{\"x\":147,\"y\":151,\"OBJTYPE\":\"PM_POSITION\"},{\"x\":45,\"y\":621,\"OBJTYPE\":\"H_GHOST_POSITION\"},{\"Type\":\"H\",\"x\":1290,\"y\":76,\"height\":0,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"x\":1291,\"y\":67,\"OBJTYPE\":\"V_GHOST_POSITION\"},{\"x\":569,\"y\":367,\"OBJTYPE\":\"F_GHOST_POSITION\"}]");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
//        System.out.println("FILL :" + started);
//            g2d.drawImage(g3.getImage(), g3.getX(), g3.getY(), g3.getWidth(), g3.getHeight(), this);
//        if (started) {
//        System.out.println("FILL");

        for (int i = 0; i < sAry.size(); i++) {
            Shape s = sAry.get(i);
            if (s.getClass().getTypeName().contains("Circle")) {
                Circle c = (Circle) s;
                if (c.getType().equalsIgnoreCase("PM")) {
                    g2d.setColor(Color.BLUE);
                    g2d.fillOval(c.getX(), c.getY(), c.getRadius() / 2, c.getRadius() / 2);
                } else if (c.getType().equalsIgnoreCase("GH")) {
                    g2d.setColor(Color.GREEN);
                    g2d.fillOval(c.getX(), c.getY(), c.getRadius() / 2, c.getRadius() / 2);
                } else if (c.getType().equalsIgnoreCase("GV")) {
                    g2d.setColor(Color.RED);
                    g2d.fillOval(c.getX(), c.getY(), c.getRadius() / 2, c.getRadius() / 2);
                } else if (c.getType().equalsIgnoreCase("GF")) {
                    g2d.setColor(Color.YELLOW);
                    g2d.fillOval(c.getX(), c.getY(), c.getRadius() / 2, c.getRadius() / 2);
                } else if (c.getType().equalsIgnoreCase("PB")) {
                    g2d.setColor(Color.PINK);
                    g2d.fillOval(c.getX(), c.getY(), 10, 10);
                } else if (c.getType().equalsIgnoreCase("PP")) {
                    g2d.setColor(Color.WHITE);
                    g2d.fillOval(c.getX(), c.getY(), 15, 15);
                }

            } else {
                g2d.setColor(Color.BLACK);
                g2d.fillRoundRect((int) s.getBounds().getX(), (int) s.getBounds().getY(), (int) s.getBounds().getWidth(), (int) s.getBounds().getHeight(), 30, 30);
            }
        }
        int width = Math.abs(curX - startX);
        int height = Math.abs(curY - startY);
        if (width > height) {
            height = Constants.CHARWIDTH + 10;
        } else {
            width = Constants.CHARWIDTH + 10;
        }
        g2d.fillRect(Math.min(startX, curX), Math.min(startY, curY), width, height);
//        }
        Toolkit.getDefaultToolkit().sync();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private class TAdapter extends MouseAdapter {

        @Override
        public void mouseDragged(MouseEvent e) {
//            if (e.getButton() == MouseEvent.BUTTON1) {
            curX = e.getX();
            curY = e.getY();
            repaint();
//            }
//            System.out.println("RX : " + e.getX() + ", RY : " + e.getY());
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                started = true;
//            System.out.println("X : " + e.getX() + ", Y : " + e.getY());
                startX = e.getX();
                startY = e.getY();
            } else {
                if (e.isPopupTrigger()) {
                    popupX = e.getX();
                    popupY = e.getY();
                    doPop(e);
                }
            }
        }

        private void doPop(MouseEvent e) {
            PopUp menu = new PopUp();
            menu.show(e.getComponent(), e.getX(), e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                started = false;
                endX = e.getX();
                endY = e.getY();
                repaint();
                int width = Math.abs(endX - startX);
                int height = Math.abs(endY - startY);
                if (width > height) {
                    height = Constants.CHARWIDTH + 10;
                } else {
                    width = Constants.CHARWIDTH + 10;
                }
                Shape s = new Rectangle(Math.min(startX, endX), Math.min(startY, endY), width, height);
                sAry.add(s);
//            System.out.println("RX : " + e.getX() + ", RY : " + e.getY());
                startX = 0;
                startY = 0;
                endX = 0;
                endY = 0;
                curX = 0;
                curY = 0;
            }
        }
    }

    class PopUp extends JPopupMenu {

        JMenuItem anItem;

        public PopUp() {
            anItem = new JMenuItem("Set Pacman Position");
            anItem.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                        Shape s = new Circle(popupX, popupY, 60, "PM");
                        sAry.add(s);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            add(anItem);

            anItem = new JMenuItem("Add H Ghost");
            anItem.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                        Shape s = new Circle(popupX, popupY, 60, "GH");
                        sAry.add(s);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            add(anItem);

            anItem = new JMenuItem("Add V Ghost");
            anItem.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                        Shape s = new Circle(popupX, popupY, 60, "GV");
                        sAry.add(s);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            add(anItem);

            anItem = new JMenuItem("Add F Ghost");
            anItem.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                        Shape s = new Circle(popupX, popupY, 60, "GF");
                        sAry.add(s);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            add(anItem);

            anItem = new JMenuItem("Add PAC-Balls");
            anItem.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                        Shape s = new Circle(popupX, popupY, 60, "PB");
                        sAry.add(s);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            add(anItem);

            anItem = new JMenuItem("Add PAC-Pellets");
            anItem.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                        Shape s = new Circle(popupX, popupY, 60, "PP");
                        sAry.add(s);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            add(anItem);
            anItem = new JMenuItem("Save");
            anItem.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                        saveData();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            add(anItem);

        }
    }

    private void generateFromJSONAry(String str) throws Exception {
        JSONArray ary = new JSONArray(str);
        for (int i = 0; i < ary.length(); i++) {
            JSONObject ob = ary.getJSONObject(i);
            System.out.println("OBJ " + ob);
            if (ob.getString("OBJTYPE").equalsIgnoreCase("PATH")) {
                Shape s = new Rectangle(ob.getInt("x"), ob.getInt("y"), ob.getInt("width"), ob.getInt("height"));
                sAry.add(s);
                System.out.println("PATH ADDED");
            } else if (ob.getString("OBJTYPE").equalsIgnoreCase("PM_POSITION")) {
                Shape s = new Circle(ob.getInt("x"), ob.getInt("y"), 60, "PM");
                sAry.add(s);
            } else if (ob.getString("OBJTYPE").equalsIgnoreCase("H_GHOST_POSITION")) {
                Shape s = new Circle(ob.getInt("x"), ob.getInt("y"), 60, "GH");
                sAry.add(s);
            } else if (ob.getString("OBJTYPE").equalsIgnoreCase("V_GHOST_POSITION")) {
                Shape s = new Circle(ob.getInt("x"), ob.getInt("y"), 60, "GV");
                sAry.add(s);
            } else if (ob.getString("OBJTYPE").equalsIgnoreCase("F_GHOST_POSITION")) {
                Shape s = new Circle(ob.getInt("x"), ob.getInt("y"), 60, "GF");
                sAry.add(s);
            } else if (ob.getString("OBJTYPE").equalsIgnoreCase("PB_POSITION")) {
                Shape s = new Circle(ob.getInt("x"), ob.getInt("y"), 60, "PB");
                sAry.add(s);
            } else if (ob.getString("OBJTYPE").equalsIgnoreCase("PP_POSITION")) {
                Shape s = new Circle(ob.getInt("x"), ob.getInt("y"), 60, "PP");
                sAry.add(s);
            }

        }
    }

    private JSONArray generateJSONArray() throws Exception {
        JSONArray ary = new JSONArray();
        JSONArray PathAry = new JSONArray();

        for (int i = 0; i < sAry.size(); i++) {
            Shape s = sAry.get(i);
            if (s.getClass().getTypeName().equalsIgnoreCase("java.awt.Rectangle")) {
                JSONObject ob = new JSONObject();
                int x = (int) s.getBounds().getX();
                int y = (int) s.getBounds().getY();
                int width = s.getBounds().width;
                int height = s.getBounds().height;

                if (width > height) {
                    ob.put("Type", "H");
                } else {
                    ob.put("Type", "V");
                }
                ob.put("x", x);
                ob.put("y", y);
                ob.put("height", height);
                ob.put("width", width);
                ob.put("OBJTYPE", "PATH");
                ary.put(ob);
            } else {
                Circle s1 = (Circle) s;
                if (s1.getType().equalsIgnoreCase("PM")) {
                    JSONObject pmOb = new JSONObject();
                    pmOb.put("x", (int) s1.getX());
                    pmOb.put("y", (int) s1.getY());
                    pmOb.put("OBJTYPE", "PM_POSITION");
                    ary.put(pmOb);
                }
                if (s1.getType().equalsIgnoreCase("GH")) {
                    JSONObject pmOb = new JSONObject();
                    pmOb.put("x", (int) s1.getX());
                    pmOb.put("y", (int) s1.getY());
                    pmOb.put("OBJTYPE", "H_GHOST_POSITION");
                    ary.put(pmOb);
                }
                if (s1.getType().equalsIgnoreCase("GV")) {
                    JSONObject pmOb = new JSONObject();
                    pmOb.put("x", (int) s1.getX());
                    pmOb.put("y", (int) s1.getY());
                    pmOb.put("OBJTYPE", "V_GHOST_POSITION");
                    ary.put(pmOb);
                }
                if (s1.getType().equalsIgnoreCase("GF")) {
                    JSONObject pmOb = new JSONObject();
                    pmOb.put("x", (int) s1.getX());
                    pmOb.put("y", (int) s1.getY());
                    pmOb.put("OBJTYPE", "F_GHOST_POSITION");
                    ary.put(pmOb);
                }
                if (s1.getType().equalsIgnoreCase("PB")) {
                    JSONObject pmOb = new JSONObject();
                    pmOb.put("x", (int) s1.getX());
                    pmOb.put("y", (int) s1.getY());
                    pmOb.put("OBJTYPE", "PB_POSITION");
                    ary.put(pmOb);
                }
                if (s1.getType().equalsIgnoreCase("PP")) {
                    JSONObject pmOb = new JSONObject();
                    pmOb.put("x", (int) s1.getX());
                    pmOb.put("y", (int) s1.getY());
                    pmOb.put("OBJTYPE", "PP_POSITION");
                    ary.put(pmOb);
                }
            }

        }
        return ary;
    }

    private void saveData() {
        JFileChooser c = new JFileChooser();

        // Demonstrate "Save" dialog:
        int rVal = c.showSaveDialog(MapGenerator.this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            try {
                fileUtils.WriteToFile(c.getCurrentDirectory().toString(), c.getSelectedFile().getName(), generateJSONArray().toString());
                JOptionPane.showMessageDialog(null, "File saved to :" + c.getCurrentDirectory().toString());
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Error saving file Cause : " + e1.getMessage());
            }

        }
    }

    private class KbAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());
            System.out.println(e.getModifiers());

            if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                saveData();
            } else if ((e.getKeyCode() == KeyEvent.VK_Z) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                sAry.remove(sAry.size() - 1);
            }
        }
    }

    class Circle implements Shape {

        int X, Y, radius;
        String type = "";

        public Circle(int x, int y, int r, String t) {
            X = x;
            Y = y;
            radius = r;
            type = t;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getX() {
            return X;
        }

        public void setX(int X) {
            this.X = X;
        }

        public int getY() {
            return Y;
        }

        public void setY(int Y) {
            this.Y = Y;
        }

        public int getRadius() {
            return radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        @Override
        public Rectangle getBounds() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Rectangle2D getBounds2D() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean contains(double x, double y) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean contains(Point2D p) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean intersects(double x, double y, double w, double h) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean intersects(Rectangle2D r) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean contains(double x, double y, double w, double h) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean contains(Rectangle2D r) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public PathIterator getPathIterator(AffineTransform at) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public PathIterator getPathIterator(AffineTransform at, double flatness) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

}
