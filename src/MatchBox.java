import java.awt.geom.RoundRectangle2D;

public class MatchBox {
    private RoundRectangle2D rect;
    private int x;
    private int y;
    private int length;
    private int height;

    public MatchBox(int x, int y, int length, int height, int arch){
        this.rect = new RoundRectangle2D.Float(x,y, length, height, arch, arch);
        this.x = x;
        this.y = y;
        this.length = length;
        this.height = height;
    }

    public int getMidY(){
        return y + height/2;
    }

    public int getRightX(){
        return x+length;
    }

    public int getY(){
        return y;
    }

    public int getMidX(){
        return x+length/2;
    }

    public int getX(){
        return x;
    }

    public RoundRectangle2D getRect() {
        return rect;
    }

}
