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
    int x1 = 0, x2 = 100, y1 = 50, y2 = 100;
    pacman pm;
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
        timer = new Timer(25, this);
        timer.start();
        setIgnoreRepaint(true);
    }

    public static void main(String args[]) {
        new main();
    }

    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
//        Graphics2D g2d1 = (Graphics2D) g;
//        Graphics2D g2d2 = (Graphics2D) g;
//        size = getSize();
//        w = size.getWidth();
//        h = size.getHeight();
//        rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        g2d.setRenderingHints(rh);
//        g2d1.setRenderingHints(rh);
//        g2d2.setRenderingHints(rh);
        if (pm != null) {
            drawmap(g2d);
//            BufferedImage bf = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            g2d.drawImage(pm.getImage(), pm.getX(), pm.getY(), pm.getWidth(), pm.getHeight(), this);
            drawSpriteFrame(new ImageIcon(genUtils.getResource("ghosts.png")).getImage(),g2d,100,100,1,0,30,30);
            Toolkit.getDefaultToolkit().sync();
        }

    }

    void drawSpriteFrame(Image source, Graphics2D g2d, int x, int y,
            int columns, int frame, int width, int height) {
        int frameX = (frame % columns) * width;
        int frameY = (frame / columns) * height;
        g2d.drawImage(source, x, y, x + width, y + height,
                frameX, frameY, frameX + width, frameY + height, this);
    }

    private void drawmap(Graphics2D g2d) {
//        g2d.setColor(BLOCKCOLOR);
//        g2d.fillRect(0, 0, 600, 600);
        g2d.setColor(PATHCOLOR);

//        g2d.fillRect(150, 50, 250, 50);
//        g2d.fillRect(150, 150, 100, 200);
        g2d.fillRect(274, 120, 40, 200);
//        g2d.fillRect(284, 100, 200, 40);
        g2d.fillRect(84, 100, 400, 40);
        g2d.fillRect(84, 300, 400, 40);

//        g2d.setStroke(new BasicStroke(5));
//        g2d.drawRect(10, 40, 550, 550);
//        g2d.setColor(Color.blue);
//        g2d.fillOval(x2, y2, 5, 5);
        mapReady = true;
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pm.move();
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            pm.keyPressed(e);
        }
    }

}
