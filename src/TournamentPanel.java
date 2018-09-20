/**
 * [TournamentPanel.java]
 * Abstract tournament panel class
 * @author Katelyn Wang & Dora Su
 * September 20 2018 
 */

import javax.swing.JPanel;

abstract public class TournamentPanel extends JPanel {
    TournamentPanel(){
    }

    abstract public void setTournament(Bracket tournament);
}
