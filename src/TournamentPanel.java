import javax.swing.JPanel;

abstract public class TournamentPanel extends JPanel {
    TournamentPanel(){
    }

    abstract public void setTournament(Bracket tournament);
}
