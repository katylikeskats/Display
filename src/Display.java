/**
 * [Display.java]
 * The frame on which the bracket is displayed
 *
 * @author Katelyn Wang & Dora Su
 * September 18 2018
 */

//Swing imports
import javax.swing.JFrame;

public class Display extends JFrame{
    private int maxX;
    private int maxY;
    private TournamentPanel tournamentPanel;

    public Display(Bracket tournament){
        super();

        maxX = 1530;
        maxY = 1150;
        this.setSize(maxX, maxY);
        this.setLocationRelativeTo(null); //start the frame in the center of the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);

        tournamentPanel = new SingleTournamentPanel(tournament, maxX, maxY);
        this.add(tournamentPanel);
        this.setVisible(true);
    }


    /**
     * Updates the bracket by calling it to be redrawn
     * @param tournament The updated tournament bracket to be drawn
     */
    public void update(Bracket tournament){
        tournamentPanel.setTournament(tournament);
    }

}
