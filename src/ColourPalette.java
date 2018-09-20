import java.awt.Color;
import java.util.ArrayList;

public class ColourPalette {
    private int red = 255;
    private int blue = 0;
    private int green = 0;
    private int changeRed;
    private int changeBlue;
    private int changeGreen;
    private static final int CHANGE = 60;
    private ArrayList<Color> colors = new ArrayList<>();

    ColourPalette(int matches){
        for (int i = 0; i < matches; i++){
            changeRed = 0;
            changeBlue = 0;
            changeGreen = 0;
            if (red == 255) {
                if (blue > 0) {
                    changeBlue = -CHANGE;
                } else {
                    changeGreen = CHANGE;
                }
            } else if (green == 255) {
                if (red>0){
                    changeRed = -CHANGE;
                } else {
                    changeBlue = CHANGE;
                }
            } else if (blue == 255){
                if (green > 0){
                    changeGreen = -CHANGE;
                } else {
                    changeRed = CHANGE;
                }
            }

            red += changeRed;
            green += changeGreen;
            blue += changeBlue;
            if (red > 255){
                red = 0;
            }
            if (blue > 255){
                blue = 0;
            }
            if (green > 255){
                green = 0;
            }
            colors.add(new Color(red, green, blue));
        }
    }

    public ArrayList<Color> getColors() {
        return colors;
    }



}
