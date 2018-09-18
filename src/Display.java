import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Display extends JFrame{
    //private Bracket tournament;
    private int maxX;
    private int maxY;

    //Display(Bracket tournament){
    Display(){
        super();
        //this.tournament = tournament;

        maxX = 1530;
        maxY = 1150;
        this.setSize(maxX, maxY);
        this.setLocationRelativeTo(null); //start the frame in the center of the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);


        //Creating a frame for the black background (otherwise would interfere with other layers if each panel had a black background)
        JPanel blackBackground = new JPanel();
        blackBackground.setSize(new Dimension(maxX, maxY));
        blackBackground.setBackground(new Color(1,1,1));
        blackBackground.setLocation(0,0);

        this.setVisible(true);
        }


   // public void update(Bracket tournament);

}
