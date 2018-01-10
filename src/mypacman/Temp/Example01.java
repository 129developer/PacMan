/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman.Temp;

/**
 *
 * @author bayasys
 */
import java.awt.*;
import java.awt.event.*;
import mypacman.utils.genUtils;

public class Example01 extends Frame {

    /**
     * 008 * Instantiates an Example01 object. 009 *
     */
    public static void main(String args[]) {
        new Example01();
    }

    /**
     * Our Example01 constructor sets the frame's size, adds the visual
     * components, and then makes them visible to the user. It uses an adapter
     * class to deal with the user closing the frame.
     *
     */
    public Example01() {
        //Title our frame.
        super("Java 2D Example01");
        setSize(400, 300);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    /**
     * The paint method provides the real magic. Here we cast the Graphics
     * object to Graphics2D to illustrate that we may use the same old graphics
     * capabilities with Graphics2D that we are used to using with Graphics.
     *
     */
    public void paint(Graphics g) {
        //Here is how we used to draw a square with width
        //of 200, height of 200, and starting at x=50, y=50.
        Graphics2D g2d = (Graphics2D) g;
        Toolkit t = Toolkit.getDefaultToolkit();
        try {
//            Image img = t.getImage(genUtils.getResource("p3.gif"));
//            g2d.drawImage(img, 100, 100, this);
            g2d.drawString("TEST", 100, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        g.setColor(Color.red);
        g.drawRect(50, 50, 200, 200);
        g.fill3DRect(50, 50, 50, 50, false);
       
//        g2d.setColor(Color.blue);
//        g2d.drawRect(75, 75, 300, 200);
    }
}
