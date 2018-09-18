import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;

public class TournamentPanel extends JPanel {
    private static final int BORDER_SPACE = 20;
    private static final int VERTICAL_SPACE = 10; //space between each box vertically
    private static final int HORIZONTAL_SPACE = 30; //space between each box horizontally
    private Bracket tournament;
    private int maxX;
    private int maxY;

    TournamentPanel(Bracket tournament, int maxX, int maxY){
        this.tournament = tournament;
        this.maxX = maxX;
        this.maxY = maxY;
        this.setSize(new Dimension(maxX, maxY));
        this.setBackground(new Color(1,1,1));
    }

    public void paintComponent(Graphics g){
        int height = (maxY-BORDER_SPACE*2)/tournament.getNumberOfMatchesInRound(0)+VERTICAL_SPACE; //height of each match box
        int length = (maxX-BORDER_SPACE*2)/tournament.getNumberOfRounds()+HORIZONTAL_SPACE; //length of each match box
        int workingX = 0; //current x from which it is drawing
        int workingY = 0; //current y from which it is drawing
        for (int roundNum = 0; roundNum < tournament.getNumberOfRounds(); roundNum++){
            for (int matchNum = 0; matchNum < tournament.getNumberOfMatchesInRound(roundNum); matchNum++){

            }
        }
    }
}
