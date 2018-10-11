/**
 * [RainbowColourPalette.java]
 * A rainbow colour palette
 *
 * @author Dora Su
 * September 28 2018
 */

import java.awt.Color;
import java.util.ArrayList;

public class GreenColourPalette extends ColourPalette {
    private int red = 0;
    private int blue = 0;
    private int green = 235;
    private int changeRed;
    private int changeBlue;
    private static final int CHANGE = 30;
    private static final int LOWER_LIMIT = 0;
    private static final int RED_LIMIT = 180;
    private static final int BLUE_LIMIT = 240;
    private ArrayList<Color> colors = new ArrayList<>();

    public GreenColourPalette(int matches) {
        super();
        for (int i = 0; i < matches; i++) {
            changeRed = LOWER_LIMIT;
            changeBlue = LOWER_LIMIT;
            if (i % matches < 2) {
                changeBlue += CHANGE;
            } else if (blue == LOWER_LIMIT) {
                if (colors.get(i - 2).getBlue() == blue + CHANGE || colors.get(i - 1).getRed() > 0) { //decreasing blue or increasing red
                    if (red == RED_LIMIT) {
                        changeRed -= CHANGE;
                    } else if (red < RED_LIMIT) {
                        if (colors.get(i - 1).getRed() < colors.get(i - 2).getRed()) {
                            changeRed -= CHANGE;
                        } else {
                            changeRed += CHANGE;
                        }
                    }
                } else {
                    changeBlue += CHANGE;
                }
            } else if (blue == BLUE_LIMIT) {
                changeBlue -= CHANGE;
            } else {
                if (colors.get(i - 1).getBlue() < colors.get(i - 2).getBlue()) {
                    changeBlue -= CHANGE;
                } else {
                    changeBlue += CHANGE;
                }
            }
            red += changeRed;
            blue += changeBlue;

            colors.add(new Color(red, green, blue));
        }
    }

    public ArrayList<Color> getColors() {
        return colors;
    }

}
