/**
 * [TournamentPanel.java]
 * Abstract tournament panel class
 * @author Katelyn Wang
 * September 20 2018
 */

//swing imports
import javax.swing.JPanel;

//graphics imports
import java.awt.Color;

abstract public class TournamentPanel extends JPanel {
    /**
     * Constructor
     * Sets background white
     */
    TournamentPanel(){
        this.setBackground(new Color(242, 242, 245));
    }

    /**
     * Changes the tournament bracket the display is painting
     * @param tournament the updated bracket
     */
    abstract public void setTournament(Bracket tournament);

}
