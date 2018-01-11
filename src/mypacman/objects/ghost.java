/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman.objects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author bayasys
 */
public class ghost extends Sprite implements ActionListener {

    public ghost(int x, int y, String imageName) {
        super(x, y, "ghost1.png");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
