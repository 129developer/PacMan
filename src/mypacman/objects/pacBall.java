package mypacman.objects;

/**
 *
 * @author Mukil
 */
public class pacBall extends Sprite {

    boolean touchleft = true, touchright = false, openmouth = true;
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
