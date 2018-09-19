import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;

public class TournamentPanel extends JPanel {
    private static final int BORDER_SPACE = 20;
    private static final int VERTICAL_SPACE = 10; //space between each box vertically
    private static final int HORIZONTAL_SPACE = 30; //space between each box horizontally
    private Bracket tournament;
    private boolean reDraw = false;
    private int maxX;
    private int maxY;

    TournamentPanel(Bracket tournament, int maxX, int maxY){
        this.tournament = tournament;
        this.maxX = maxX;
        this.maxY = maxY;
        this.setSize(new Dimension(maxX, maxY));
        this.setBackground(new Color(255, 114, 204));

    }
    public void paintComponent(Graphics g) {
        //Team Lines: Horizontal, Match Lines: Vertical
        //Round 1
        //Team Lines
        g.drawLine(50,100, 150, 100);
        g.drawLine(50,200, 150, 200);
        g.drawLine(50,300, 150, 300);
        g.drawLine(50,400, 150, 400);
        g.drawLine(50,500, 150, 500);
        g.drawLine(50,600, 150, 600);
        g.drawLine(50,700, 150, 700);
        g.drawLine(50,800, 150, 800);
        //Match Lines
        g.drawLine(150,100, 150, 200);
        g.drawLine(150,300, 150, 400);
        g.drawLine(150,500, 150, 600);
        g.drawLine(150,700, 150, 800);

        //Round 2
        //Team Lines
        g.drawLine(150,150, 250, 150);
        g.drawLine(150,350, 250, 350);
        g.drawLine(150,550, 250, 550);
        g.drawLine(150,750, 250, 750);
        //Match Lines
        g.drawLine(250,150, 250, 350);
        g.drawLine(250,550, 250, 750);

        //Round 3
        //Team Lines
        g.drawLine(250,250, 350, 250);
        g.drawLine(250,650, 350, 650);
        //Match Lines
        g.drawLine(350,250, 350, 650);

        //Winner
        g.drawLine(350,450, 550, 450);

    }


   /* public void paintComponent(Graphics g){
        int height = (maxY-BORDER_SPACE*2)/tournament.getNumberOfMatchesInRound(0)+VERTICAL_SPACE; //height of each match box
        int length = (maxX-BORDER_SPACE*2)/tournament.getNumberOfRounds()+HORIZONTAL_SPACE; //length of each match box
        int workingX = 15; //current x from which it is drawing
        int workingY = 15; //current y from which it is drawing
        for (int roundNum = 0; roundNum < tournament.getNumberOfRounds(); roundNum++){
            for (int matchNum = 0; matchNum < tournament.getNumberOfMatchesInRound(roundNum); matchNum++){

            }
        }
    }*/

    public void setTournament(Bracket tournament) {
        this.tournament = tournament;
    }

    public void setReDraw(boolean reDraw) {
        this.reDraw = reDraw;
    }
}
