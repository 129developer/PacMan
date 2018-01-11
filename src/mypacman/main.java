/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import mypacman.objects.ghost;
import mypacman.objects.pacman;
import mypacman.utils.genUtils;

/**
 *
 * @author bayasys
 */
public class main extends Frame implements ActionListener {

    RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
    Dimension size;
    double w;
    double h;
    Timer timer;
    int x1 = 100, x2 = 150, y1 = 100, y2 = 100;
    pacman pm;
    ghost g1;
    Color BLOCKCOLOR = Color.red;
    Color PATHCOLOR = Color.BLACK;
    boolean mapReady = false;

    public main() {
        super("PacMan");
        setSize(600, 600);
        setVisible(true);
        addKeyListener(new TAdapter());
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
        setBackground(BLOCKCOLOR);

        pm = new pacman(x2, y2);
        pm.setHeight(30);
        pm.setWidth(30);
        g1 = new ghost(x1, y1);
        g1.setHeight(30);
        g1.setWidth(30);
        g1.setGHOSTTYPE(2);
        timer = new Timer(25, this);
        timer.start();
        setIgnoreRepaint(true);
    }

    public static void main(String args[]) {
        new main();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
//        rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        if (pm != null) {
            drawmap(g2d);
            g2d.drawImage(pm.getImage(), pm.getX(), pm.getY(), pm.getWidth(), pm.getHeight(), this);
            g2d.drawImage(g1.getImage(), g1.getX(), g1.getY(), g1.getWidth(), g1.getHeight(), this);
//            drawSpriteFrame(new ImageIcon(genUtils.getResource("ghosts.png")).getImage(), g2d, 100, 100, 1, 0, 30, 30);
            Toolkit.getDefaultToolkit().sync();
        }

    }

    private void drawmap(Graphics2D g2d) {
        g2d.setColor(PATHCOLOR);
        genPathBoxV(g2d, 274, 120, 200);
        genPathBoxH(g2d, 84, 100, 400);//top hot
        genPathBoxH(g2d, 84, 300, 400);
        genPathBoxH(g2d, 204, 200, 100);
        g2d.fillRect(80, 160, 130, 120); //left box

        g2d.setColor(BLOCKCOLOR);
        g2d.fillOval(117, 195, 50, 50); //left box inner
        g2d.fillRect(117, 195, 50, 50); //left box inner
        mapReady = true;
//        Toolkit.getDefaultToolkit().sync();
    }

    private static void genPathBoxH(Graphics2D g2d, int x, int y, int width) {
        g2d.fillRect(x, y, width, 40);
    }

    private static void genPathBoxV(Graphics2D g2d, int x, int y, int height) {
        g2d.fillRect(x, y, 40, height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pm.move();
        g1.move(pm);
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            pm.keyPressed(e);
        }
    }

}
