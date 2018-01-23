/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman.utils;

import java.awt.Color;
import mypacman.main;
import static mypacman.main.main;
import mypacman.objects.maps.level;

/**
 *
 * @author Mukil
 */
public class Constants {

    public static Color BLOCKCOLOR = Color.GRAY;
    public static Color PATHCOLOR = Color.BLACK;
    public static int MOVEVAL = 4;
    public static int WINDOW_WIDTH = 600;
    public static int WINDOW_HEIGHT = 600;
    public static int CHARWIDTH = 30;
    public static level CURRENTLEVEL = new level();
    public static main MAIN = null;
    public static Thread MAINTHREAD = null;

}
