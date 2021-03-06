/**
 * [PinkBlueColourPalette.java]
 * A pink, purple, and blue colour palette
 * @author Dora Su
 * October 2 2018
 */

import java.awt.Color;
import java.util.ArrayList;

public class PinkBlueColourPalette extends ColourPalette{
    private int red = 0;
    private int blue = 170;
    private int green = 0;
    private int changeRed;
    private int changeBlue;
    private int changeGreen;
    private static final int CHANGE = 20;
    private static final int RED_LIMIT = 230;
    private static final int LOWER_LIMIT = 150;
    private static final int GREEN_LIMIT = 180;
    private static final int BLUE_LIMIT = 230;
    private ArrayList<Color> colors = new ArrayList<>();

    public PinkBlueColourPalette(int matches){
        super();
        for (int i = 0; i < matches; i++){
            changeRed = LOWER_LIMIT;
            changeBlue = LOWER_LIMIT;
            changeGreen = LOWER_LIMIT;
            if (red == RED_LIMIT) {
                if (blue > LOWER_LIMIT) {
                    changeBlue = -CHANGE;
                } else {
                    changeGreen = CHANGE;
                }
            } else if (green == GREEN_LIMIT) {
                if (red > LOWER_LIMIT){
                    changeRed = -CHANGE;
                } else {
                    changeBlue = CHANGE;
                }
            } else if (blue == BLUE_LIMIT){
                if (green > LOWER_LIMIT){
                    changeGreen = -CHANGE;
                } else {
                    changeRed = CHANGE;
                }
            } else {
                changeBlue = CHANGE;
            }

            red += changeRed;
            green += changeGreen;
            blue += changeBlue;
            if (red > RED_LIMIT){
                red = RED_LIMIT;
                blue = LOWER_LIMIT;
            }
            if (blue > BLUE_LIMIT){
                blue = BLUE_LIMIT;
                green = LOWER_LIMIT;
            }
            if (green > GREEN_LIMIT){
                green = LOWER_LIMIT;
                blue = BLUE_LIMIT;
                red = LOWER_LIMIT;
            }
            colors.add(new Color(red, green, blue));
        }
    }

    public ArrayList<Color> getColors() {
        return colors;
    }

}
