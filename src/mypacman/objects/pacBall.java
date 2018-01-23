/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman.objects;

/**
 *
 * @author Mukil
 */
public class pacBall extends Sprite {

    boolean touchleft = true, touchright = false, openmouth = true;
    private int dx = 0;
    private int dy = 0;
    private String type = "BALL";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public pacBall(int x, int y) {
        super(x, y);
    }
}
