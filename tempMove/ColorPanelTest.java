import javax.swing.*;
import java.awt.*;

public class ColorPanelTest extends JPanel {
    private ColourPalette colors;
    ColorPanelTest(ColourPalette colors){
        this.colors = colors;
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        int x = 0;
        int y = 0;
        for (int i = 0; i < 10; i++){
            g.setColor(colors.getColors().get(i));
            g.drawOval(x, y, 5, 5);
            g.drawString("hi", 3, 3);
            x += 10;
            y += 10;
        }
    }
}
