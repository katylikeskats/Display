/**
 * [RainbowColourPalette.java]
 * A rainbow colour palette
 * @author Dora Su
 * September 29 2018
 */

import java.awt.Color;
import java.util.ArrayList;

public class InstagramColourPalette extends ColourPalette{
    private int red = 0;
    private int blue = 200;
    private int green = 0;
    private int changeRed;
    private int changeBlue;
    private int changeGreen;
    private static final int CHANGE = 40;
    private static final int UPPER_LIMIT = 250;
    private static final int LOWER_LIMIT = 0;
    private static final int GREEN_LIMIT = 200;
    private ArrayList<Color> colors = new ArrayList<>();

    public InstagramColourPalette(int matches){
        super();
        for (int i = 0; i < matches; i++){
            changeRed = LOWER_LIMIT;
            changeBlue = LOWER_LIMIT;
            changeGreen = LOWER_LIMIT;
            if (red == UPPER_LIMIT) {
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
            } else if (blue == UPPER_LIMIT){
                if (green > LOWER_LIMIT){
                    changeGreen = -CHANGE;
                } else {
                    changeRed = CHANGE;
                }
            } else {
                changeRed = CHANGE;
            }
            
            red += changeRed;
            green += changeGreen;
            blue += changeBlue;
            if (red > UPPER_LIMIT){
                red = UPPER_LIMIT;
                blue = LOWER_LIMIT;
            }
            if (blue > UPPER_LIMIT){
                blue = UPPER_LIMIT;
                green = LOWER_LIMIT;
            }
            if (green > GREEN_LIMIT){
                green = LOWER_LIMIT;
                blue = UPPER_LIMIT;
                red = LOWER_LIMIT;
            }
            colors.add(new Color(red, green, blue));
        }
    }

    public ArrayList<Color> getColors() {
        return colors;
    }


}
