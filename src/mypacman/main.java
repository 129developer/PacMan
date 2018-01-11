/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Timer;
import mypacman.objects.ghost;
import mypacman.objects.maps.*;
import mypacman.objects.pacman;
import mypacman.utils.Constants;

/**
 *
 * @author bayasys
 */
public class main extends Frame implements ActionListener {

    RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    Timer timer;
    int x1 = 100, x2 = 150, y1 = 100, y2 = 100;
    pacman pm;
    ghost g1, g2, g3;
    basicMap currentMap = new level1();

    public main() {
        super("PacMan");

        addKeyListener(new TAdapter());
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
        setBackground(Constants.BLOCKCOLOR);
        initSprites();
        
        timer = new Timer(25, this);
        timer.start();
        
        setIgnoreRepaint(true);
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setVisible(true);
    }

    protected void initSprites() {
        pm = new pacman(x2, y2);
        pm.setHeight(30);
        pm.setWidth(30);
        pm.setMOVEVAL(3);
        g1 = new ghost(x1, y1);
        g1.setHeight(30);
        g1.setWidth(30);
        g1.setGHOSTTYPE(2);
        g2 = new ghost(400, 300, "ghost2.png");
        g2.setHeight(30);
        g2.setWidth(30);
        g2.setGHOSTTYPE(2);
        g3 = new ghost(400, 300, "ghost3.png");
        g3.setHeight(30);
        g3.setWidth(30);
        g3.setGHOSTTYPE(1);
    }

    public static void main(String args[]) {
        new main();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
//        rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        g2d.setRenderingHints(rh);
        if (pm != null) {
            currentMap.generateMap(g2d);
            g2d.drawImage(pm.getImage(), pm.getX(), pm.getY(), pm.getWidth(), pm.getHeight(), this);
            g2d.drawImage(g1.getImage(), g1.getX(), g1.getY(), g1.getWidth(), g1.getHeight(), this);
            g2d.drawImage(g2.getImage(), g2.getX(), g2.getY(), g2.getWidth(), g2.getHeight(), this);
            g2d.drawImage(g3.getImage(), g3.getX(), g3.getY(), g3.getWidth(), g3.getHeight(), this);
            Toolkit.getDefaultToolkit().sync();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pm.move();
        g1.move(pm);
        g2.move(pm);
        g3.move(pm);
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            pm.keyPressed(e);
        }
    }

}
