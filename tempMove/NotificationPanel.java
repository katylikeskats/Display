import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics2D;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class NotificationPanel extends JPanel {
    private ArrayList<Rainbow> rainbows = new ArrayList();
    private Random random = new Random();
    private double spawnChance = 0.5;
    private double minSpd = 5.0;
    private double maxSpd = 10.0;
    private double minSize = 20.0;
    private double maxSize = 30.0;
    private int maxX;
    private int maxY;

    NotificationPanel(int maxX, int maxY){
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Image background = Toolkit.getDefaultToolkit().getImage("assets/wallpaper.jpg");
        g.drawImage(background, 0,0, this);
        Font font1 = getFont("assets/Comfortaa-Light.ttf", 30f);
        Font font2 = getFont("assets/Comfortaa-Light.ttf", 15f);

        Iterator rainbowItr = this.rainbows.iterator();

        synchronized(this){
            while(rainbowItr.hasNext()) {
                Rainbow rainbow = (Rainbow)rainbowItr.next();
                rainbow.draw(g, this);
            }
        }

        g.setColor(new Color(255, 255, 255, 220));
        g.fillRoundRect(40, 100, 470, 90, 20,20);

        FontMetrics fontMetrics = g.getFontMetrics(font1);
        g.setFont(font1);
        g.setColor(Color.black);
        g.drawString("Tournament Bracket Created!", maxX/2 - fontMetrics.stringWidth("Tournament Bracket Created!")/2, maxY/2-fontMetrics.getHeight());
        g.setFont(font2);
        fontMetrics = g.getFontMetrics(font2);
        g.drawString("Kraft Dinner Display", maxX/2 - fontMetrics.stringWidth("Kraft Dinner Display")/2, maxY/2);

    }

    /**
     * Retrieves a font and creates it
     * @param fileName the name of the font file
     * @param size the desired size of the font to be made
     * @return the created Font
     */
    private Font getFont(String fileName, float size){
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(fileName)).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.PLAIN,new File(fileName)));
        } catch (IOException | FontFormatException e){
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
        }
        return font;
    }

    void refresh() {
        double spawn = this.random.nextDouble();
        if (spawn <= this.spawnChance) {
            this.rainbows.add(new Rainbow((double)this.random.nextInt(this.getWidth()), 0.0D - this.maxSize, this.random.nextDouble() * (this.maxSize - this.minSize) + this.minSize, this.random.nextDouble() * (this.maxSpd - this.minSpd) + this.minSpd));
        }

        Iterator rainbowITr = this.rainbows.iterator();

        while(rainbowITr.hasNext()) {
            Rainbow curr = (Rainbow)rainbowITr.next();
            curr.increment();
            if (curr.getY() > (double)this.getHeight()) {
                rainbowITr.remove();
            }
        }

        super.repaint();

        try {
            Thread.sleep(50L);
        } catch (InterruptedException var5) {
            System.out.println(var5.toString());
        }

    }

}