import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Rainbow{
    private static BufferedImage rainbow;
    private double x;
    private double y;
    private double size;
    private double movSpd;

    public Rainbow(double x, double y, double size, double movSpd) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.movSpd = movSpd;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void increment() {
        this.y += this.movSpd;
    }

    public void draw(Graphics g, NotificationPanel observer) {
        g.drawImage(rainbow, (int)this.x, (int)this.y, (int)this.size, (int)this.size, observer);
    }

    static {
        try {
            rainbow = ImageIO.read(new File("assets/Rainbow.png"));
        } catch (IOException var1) {
            System.out.println(var1.toString());
        }

    }
}
