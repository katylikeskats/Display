
public class ColorTest{
    private static ColourPalette colors = new RainbowColourPalette(10);
    public static void main(String[] args){
        for (int i = 0; i < 10; i++){
            System.out.println(colors.getColors().get(i).getRed()+", "+colors.getColors().get(i).getGreen()+", "+colors.getColors().get(i).getBlue());

        }
    }

}
