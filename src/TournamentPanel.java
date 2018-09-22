/**
 * [TournamentPanel.java]
 * Abstract tournament panel class
 * @author Katelyn Wang & Dora Su
 * September 20 2018
 */

import javax.swing.JPanel;
import java.awt.*;

abstract public class TournamentPanel extends JPanel {
    TournamentPanel(){
        this.setBackground(new Color(243, 242, 242));
    }

    abstract public void setTournament(Bracket tournament);

    abstract public void refresh();
}
