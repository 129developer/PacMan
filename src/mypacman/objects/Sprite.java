/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman.objects;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Image;
import java.awt.Robot;
import javax.swing.ImageIcon;
import mypacman.utils.Constants;
import mypacman.utils.genUtils;

public class Sprite {

    protected int x;
    protected int y;
    protected int width = Constants.CHARWIDTH;
    protected int height = Constants.CHARWIDTH;
    protected boolean vis;
    protected Image image;
    protected Color BLOCKCOLOR = Constants.BLOCKCOLOR;
    protected Color PATHCOLOR = Constants.PATHCOLOR;
    protected int MOVEVAL = Constants.MOVEVAL;

    
    
    public int getMOVEVAL() {
        return MOVEVAL;
    }

    public void setMOVEVAL(int MOVEVAL) {
        this.MOVEVAL = MOVEVAL;
    }

    public Sprite(int x, int y, String imageName) {
        this.x = x;
        this.y = y;
        vis = true;
        loadImage(imageName);
    }

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
        vis = true;
    }

    protected void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(genUtils.getResource(imageName));
        image = ii.getImage();
    }

    protected void getImageDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static Color getPixel(int x, int y) throws AWTException {
        Robot rb = new Robot();
        return rb.getPixelColor(x, y);
    }

}
