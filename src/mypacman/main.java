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
import javax.swing.JDialog;
import javax.swing.Timer;
import mypacman.objects.ghost;
import mypacman.objects.maps.*;
import mypacman.objects.pacman;
import mypacman.utils.Constants;
import mypacman.utils.genUtils;

/**
 *
 * @author bayasys
 */
public class main extends Frame implements ActionListener {

    RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    Timer timer;
    int x1 = 100, x2 = 150, y1 = 100, y2 = 100;
//    pacman pm;
//    ghost g1, g2, g3;
    level currentMap = new level();

    public main() {
        super("PacMan");

        addKeyListener(new TAdapter());
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }

            public void windowIconified(WindowEvent we) {
                setState(NORMAL);
            }
        });
        setBackground(Constants.BLOCKCOLOR);
        initSprites();

        timer = new Timer(20, this);
        timer.start();
        setIgnoreRepaint(true);
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setResizable(false);
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
        setMaximizedBounds(null);
        setVisible(true);
    }

    protected void initSprites() {
//        pm = new pacman(currentMap.getPmX(), currentMap.getPmY());
//        pm.setHeight(30);
//        pm.setWidth(30);
//        pm.setMOVEVAL(3);
//        g2 = new ghost(400, 300, 2);

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
        currentMap.generateMap(g2d);
        if (currentMap.pm != null) {

            currentMap.paintGhosts(g2d);
            g2d.drawImage(currentMap.pm.getImage(), currentMap.pm.getX(), currentMap.pm.getY(), currentMap.pm.getWidth(), currentMap.pm.getHeight(), this);
//            g2d.drawImage(g1.getImage(), g1.getX(), g1.getY(), g1.getWidth(), g1.getHeight(), this);
//            g2d.drawImage(g2.getImage(), g2.getX(), g2.getY(), g2.getWidth(), g2.getHeight(), this);
//            g2d.drawImage(g3.getImage(), g3.getX(), g3.getY(), g3.getWidth(), g3.getHeight(), this);
            Toolkit.getDefaultToolkit().sync();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentMap != null && currentMap.pm != null) {
            currentMap.pm.move();
            currentMap.moveGhosts();
        }
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            currentMap.pm.keyPressed(e);
        }
    }

}
