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
                }

            } else {
                g2d.setColor(Color.BLACK);
                g2d.fill(s);
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
