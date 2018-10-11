/**
 * [RainbowColourPalette.java]
 * A rainbow colour palette
 *
 * @author Katelyn Wang
 * September 19 2018
 */

import java.awt.Color;
import java.util.ArrayList;

public class PinkColourPalette extends ColourPalette {
    private int red = 0;
    private int blue = 255;
    private int green = 0;
    private int changeRed;
    private int changeBlue;
    private int changeGreen;
    private static final int CHANGE = 25;
    private static final int UPPER_LIMIT = 250;
    private static final int LOWER_LIMIT = 0;
    private static final int RED_LIMIT = 75;
    private ArrayList<Color> colors = new ArrayList<>();
    private boolean redDirection = true; // up is true, down is false

    public PinkColourPalette(int matches) {
        //Color color = new Color(10,10,10);
        super();
        for (int i = 0; i < matches; i++) {
            changeRed = LOWER_LIMIT;
            changeGreen = LOWER_LIMIT;
            if (i % matches < 2) {
                changeGreen += CHANGE;
            } else if (green == LOWER_LIMIT) {
                if (colors.get(i - 2).getGreen() == green + CHANGE || colors.get(i - 1).getRed() > 0) {
                    if (red == RED_LIMIT) {
                        changeRed -= CHANGE;
                    } else if (red < RED_LIMIT) {
                        if (colors.get(i - 1).getRed() < colors.get(i - 2).getRed() && red >= CHANGE) {
                            changeRed -= CHANGE;
                        } else {
                            changeRed += CHANGE;
                        }
                    }
                }
                else {
                    changeGreen += CHANGE;
                }
            }
            else if (green == UPPER_LIMIT) {
                changeGreen -= CHANGE;
            } else if (green < UPPER_LIMIT && i >= 2) {
                if (colors.get(i - 1).getGreen() < colors.get(i - 2).getGreen()) {
                    if (green >= CHANGE) {
                        changeGreen -= CHANGE;
                    }
                } else if (colors.get(i - 1).getGreen() > colors.get(i - 2).getGreen()) {
                    changeGreen += CHANGE;
                }
            } else if (red <= UPPER_LIMIT && red > LOWER_LIMIT) {
                changeRed -= CHANGE;
            } else if (red < RED_LIMIT) {
                changeRed += CHANGE;
            }

            red += changeRed;
            green += changeGreen;
            blue += changeBlue;
            if (red > RED_LIMIT) {
                red = RED_LIMIT;
            }
            if (green > UPPER_LIMIT) {
                green = UPPER_LIMIT;
            }
            colors.add(new Color(red, green, blue));
        }
    }

    public ArrayList<Color> getColors() {
        return colors;
    }

}
