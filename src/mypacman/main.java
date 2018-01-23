/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import mypacman.objects.maps.*;
import mypacman.utils.Constants;

/**
 *
 * @author Mukil
 */
public class main extends Frame implements Runnable {

    RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    int x1 = 100, x2 = 150, y1 = 100, y2 = 100;
    public level currentMap = new level();
    long beforeTime, timeDiff, sleep;
    Graphics2D g2d;

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
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
        setMaximizedBounds(null);
        setVisible(true);
    }

    public static void main(String args[]) {
        Constants.MAIN = new main();
        Constants.MAINTHREAD = new Thread(Constants.MAIN);
        Constants.MAINTHREAD.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g2d = (Graphics2D) g;
        if (Constants.CURRENTLEVEL.running) {
            currentMap.generateMap(g2d);
        }
        if (currentMap.pm != null) {
            g2d.setColor(Color.red);
            g2d.drawImage(currentMap.pm.getImage(), currentMap.pm.getX(), currentMap.pm.getY(), currentMap.pm.getWidth(), currentMap.pm.getHeight(), this);
//            g2d.drawOval(currentMap.pm.getX(), currentMap.pm.getY(), 2, 2);
        }

    }

//    @Override
    @Override
    public void run() {
        beforeTime = System.currentTimeMillis();
        while (true) {
            if (currentMap != null && currentMap.pm != null) {
                currentMap.pm.move();
                currentMap.moveGhosts();
            }
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = 40 - timeDiff;
            if (sleep < 0) {
                sleep = 2;
            }
            try {
                repaint();
                Toolkit.getDefaultToolkit().sync();
                Thread.sleep(30);
            } catch (InterruptedException e) {
                System.out.println("Interrupted: " + e.getMessage());
            }
            beforeTime = System.currentTimeMillis();
        }

    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (currentMap != null && currentMap.pm != null) {
                currentMap.pm.keyPressed(e);
            }
        }
    }

}
