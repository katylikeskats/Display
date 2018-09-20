public class DoubleTournamentPanel extends TournamentPanel {
    private Bracket tournament;
    
    DoubleTournamentPanel(Bracket tournament){
        this.tournament =  tournament;
    }

    /**
     * Updates the tournament bracket display by changing the tournament bracket it is drawing. Repaints the screen once called
     * @param tournament Updated tournament bracket to be displayed
     */
    public void setTournament(Bracket tournament) {
        this.tournament = tournament;
        repaint();
    }
}
