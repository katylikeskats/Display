/**
 * [Display.java]
 * Contains the algorithm to draw the bracket based on the data structure
 * @author Katelyn Wang & Dora Su
 * September 18 2018
 */

//Swing imports
import javax.swing.JFrame;
import javax.swing.JPanel;
//Graphics imports
import java.awt.Color;
import java.awt.Dimension;

public class Display extends JFrame{
    private static final int BORDER_SPACE = 20;
    private static final int VERTICAL_SPACE = 10; //space between each box vertically
    private static final int HORIZONTAL_SPACE = 30; //space between each box horizontally
    private Bracket tournament;
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

        update(tournament);
        this.setVisible(true);
        }


    public void update(Bracket tournament){
        int height = (maxY-BORDER_SPACE*2)/tournament.getNumberOfMatchesInRound(0)+VERTICAL_SPACE;
        int length = (maxX-BORDER_SPACE*2)/tournament.getNumberOfRounds()+HORIZONTAL_SPACE;

    }

}
