/**
 * [RainbowColourPalette.java]
 * A rainbow colour palette
 *
 * @author Katelyn Wang
 * September 19 2018
 */

import java.awt.Color;
import java.util.ArrayList;

public class BlueColourPalette extends ColourPalette {
    private int red = 0;
    private int blue = 255;
    private int green = 0;
    private int changeRed;
    private int changeGreen;
    private static final int CHANGE = 25;
    private static final int GREEN_LIMIT = 200;
    private static final int LOWER_LIMIT = 0;
    private static final int RED_LIMIT = 25;
    private ArrayList<Color> colors = new ArrayList<>();

    public BlueColourPalette(int matches) {
        super();
        for (int i = 0; i < matches; i++) {
            changeRed = LOWER_LIMIT;
            changeGreen = LOWER_LIMIT;
            if (i % matches < 2) {
                changeGreen += CHANGE;
            } else if (green == LOWER_LIMIT) {
                if (colors.get(i - 2).getGreen() == green + CHANGE || colors.get(i - 1).getRed() > 0) { //decreasing green or increasing red
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
                    changeGreen += CHANGE;
                }
            } else if (green == GREEN_LIMIT) {
                changeGreen -= CHANGE;
            } else {
                if (colors.get(i - 1).getGreen() < colors.get(i - 2).getGreen()) {
                    changeGreen -= CHANGE;
                } else {
                    changeGreen += CHANGE;
                }
            }
            red += changeRed;
            green += changeGreen;

            colors.add(new Color(red, green, blue));
        }
    }

    public ArrayList<Color> getColors() {
        return colors;
    }


}
