

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class TournamentPanel extends JPanel {
    private static final int BORDER_SPACE = 40;
    private static final int VERTICAL_SPACE = 10; //space between each box vertically
    private static final int HORIZONTAL_SPACE = 100; //space between each box horizontally
    private Bracket tournament;
    private boolean reDraw = false;
    private int maxX;
    private int maxY;
    private int height;
    private int length;

    TournamentPanel(Bracket tournament, int maxX, int maxY){
        this.tournament = tournament;
        this.maxX = maxX;
        this.maxY = maxY;
        this.setSize(new Dimension(maxX, maxY));
        this.setBackground(new Color(255, 114, 204));

    }


    public void paintComponent(Graphics g){
        int numRounds = tournament.getNumberOfRounds();
        int numTeams = tournament.getNumberOfTeams();
        int numMatches;

        int verticalSpace;

        int workingX = BORDER_SPACE; //current x from which it is drawing
        int workingY = BORDER_SPACE; //current y from which it is drawing

        height = (maxY-BORDER_SPACE*2-VERTICAL_SPACE*numTeams)/(numTeams/2); //height of each match box
        length = (maxX-BORDER_SPACE*2-HORIZONTAL_SPACE*numRounds)/numRounds; //length of each match box

       for (int roundNum = 0; roundNum < tournament.getNumberOfRounds(); roundNum++){
           numMatches = tournament.getNumberOfMatchesInRound(roundNum);
           if (numMatches>1) {
               verticalSpace = (maxY - (workingY * 2) - (height * numMatches))/ (numMatches - 1);
           } else {
               verticalSpace = maxY;
           }
           drawRound(g, workingX, workingY, workingX+length/2, workingY+height/4, verticalSpace, roundNum);
           drawLines(g, workingX, workingY);
           workingY = BORDER_SPACE + height/2 + verticalSpace;
           workingX += length + HORIZONTAL_SPACE;
        }
    }

    public void drawRound(Graphics g, int workingX, int workingY, int workingTextX, int workingTextY, int verticalSpace, int roundNum){
        String[][] teams;
        Font font1 = new Font("Sans_Serif", Font.BOLD, 20);
        for (int matchNum = 0; matchNum < tournament.getNumberOfMatchesInRound(roundNum); matchNum++){
            teams = tournament.getTeamsInMatch(roundNum, matchNum);
            g.drawRect(workingX, workingY, length, height);
                for (int teamNum = 0; teamNum < teams.length; teamNum++) {
                    for (int i = 0; i < teams[teamNum].length; i++) { //add more descript variable later LOL
                        if (teams[teamNum].length == 1) {
                            g.drawString(teams[teamNum][i], workingTextX, workingTextY);
                        } else {
                            g.drawString("unknown", workingTextX, workingTextY);
                            break;
                        }
                        workingTextX += 10;
                    }
                    workingTextX = workingX + length / 2;
                    workingTextY += height / 2;
                }
            g.drawString("vs.", workingTextX, workingTextY-height/2-height/4);
            workingY += height + verticalSpace;
            workingTextY += verticalSpace;
        }
    }

    public void drawLines(Graphics g, int workingX, int workingY){

    }

    public void setTournament(Bracket tournament) {
        this.tournament = tournament;
    }

    public void setReDraw(boolean reDraw) {
        this.reDraw = reDraw;
    }
}