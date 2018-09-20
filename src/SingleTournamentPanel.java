/**
 * [SingleTournamentPanel.java]
 * The display panel for single elimination tournaments
 * @author Katelyn Wang & Dora Su
 * September 18 2018
 */

//Swing imports
import javax.swing.JPanel;

//Graphics imports
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.FontMetrics;

public class SingleTournamentPanel extends TournamentPanel {
    private static final int BORDER_SPACE = 40;
    private static final int VERTICAL_SPACE = 10; //space between each box vertically
    private static final int HORIZONTAL_SPACE = 100; //space between each box horizontally
    private Bracket tournament;
    private int maxX;
    private int maxY;
    private int height;
    private int length;
    private ColourPalette colors;
    private int colorIndex;

    SingleTournamentPanel(Bracket tournament, int maxX, int maxY){
        super();
        this.tournament = tournament;
        this.maxX = maxX;
        this.maxY = maxY;
        this.setSize(new Dimension(maxX, maxY));
       // this.setBackground(new Color(255, 114, 204));
    }

    /**
     * Draws all the graphics on the screen
     * @param g Graphics to draw the components
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int numRounds = tournament.getNumberOfRounds();
        int numTeams = tournament.getNumberOfTeams();
        int numMatches;

        int verticalSpace; //space between each matchbox of a given round (for an evenly distributed look)

        int workingX = BORDER_SPACE; //current x from which it is drawing
        int workingY = BORDER_SPACE; //current y from which it is drawing

        height = (maxY-BORDER_SPACE*2-VERTICAL_SPACE*numTeams)/(numTeams/2); //height of each match box
        length = (maxX-BORDER_SPACE*2-HORIZONTAL_SPACE*numRounds)/numRounds; //length of each match box

       for (int roundNum = 0; roundNum < tournament.getNumberOfRounds(); roundNum++){ //iterates through each round
           numMatches = tournament.getNumberOfMatchesInRound(roundNum); //determines how many matches are in the round
           colors = new ColourPalette(numMatches);
           colorIndex = 0;
           if (numMatches>1) { //if it is more than one, calculates the space between each matchbox
               verticalSpace = (maxY - (workingY * 2) - (height * numMatches))/ (numMatches - 1);
           } else {
               verticalSpace = maxY; //if not, defaults to the full length of the screen
           }
           drawRound(g, workingX, workingY, workingX+length/2, workingY+height/4, verticalSpace, roundNum); //draws the matchboxes
           workingY = BORDER_SPACE + height/2 + verticalSpace; //adjusts the workingY and workingX coordinates
           workingX += length + HORIZONTAL_SPACE;
        }
    }

    /**
     * Draws one column of match boxes, teams playing in the match, and their respective lines
     * @param g Graphics to draw the bracket
     * @param workingX The X which is changed as the bracket is drawn from top to bottom of the screen
     * @param workingY The Y which is changed as the bracket is drawn from top to bottom of the screen
     * @param workingTextX The X which is changed as the teams are drawn
     * @param workingTextY The Y which is changed as the teams are drawn
     * @param verticalSpace The calculated space between each match box
     * @param roundNum The round number it is drawing
     */
    public void drawRound(Graphics g, int workingX, int workingY, int workingTextX, int workingTextY, int verticalSpace, int roundNum){
        String[][] teams; //stores teams who are playing in a certain match match
        Font font1 = new Font("Sans_Serif", Font.BOLD, 20);
        int previousPointX; // coordinates to store previous points from which to connect the vertical line
        int previousPointY;

        for (int matchNum = 0; matchNum < tournament.getNumberOfMatchesInRound(roundNum); matchNum++){ //iterates through each match
            teams = tournament.getTeamsInMatch(roundNum, matchNum); //stores the teams which play in that match
            g.setColor(colors.getColors().get(colorIndex));
            colorIndex++;
            Graphics2D graphics2 = (Graphics2D) g;
            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(workingX, workingY, length, height, 20, 20);
            graphics2.draw(roundedRectangle);
           // g.drawRect(workingX, workingY, length, height); //Draws a rectangle to represent that match
            if (roundNum != tournament.getNumberOfRounds()-1){ //If the round is not the last round, draw the lines connecting to the next matchbox
                g.drawLine(workingX + length, workingY + height / 2, workingX + length + HORIZONTAL_SPACE / 2, workingY + height / 2);
                previousPointX = workingX + length + HORIZONTAL_SPACE/2; //storing the previous point to draw the vertical line
                previousPointY = workingY + height/2;
                if (matchNum%2 == 0){ //if it is an even match, draw the vertical line connecting each pair of matches
                    g.drawLine(previousPointX, previousPointY, previousPointX, previousPointY+height+verticalSpace);
                    g.drawLine(previousPointX, previousPointY+(height+verticalSpace)/2, previousPointX +HORIZONTAL_SPACE/2,  previousPointY+(height+verticalSpace)/2);
                }
            }

            for (int teamNum = 0; teamNum < teams.length; teamNum++) {
                for (int i = 0; i < teams[teamNum].length; i++) { //add more descript variable later LOL
                    if (teams[teamNum].length == 1) { //checking if the teams playing is already determined
                        g.drawString(teams[teamNum][i], workingTextX, workingTextY); //if so, draws the team names
                    } else {
                        g.drawString("unknown", workingTextX, workingTextY); // if not, leaves it unknown
                        break;
                    }
                }
                workingTextY += height / 2; //changing where the next text will be drawn
            }
            g.drawString("vs.", workingTextX, workingTextY-height/2-height/4); //drawing the "vs." between the teams; had to be outside the loop or else it would be drawn multiple times
            workingY += height + verticalSpace; //adjusting the workingY height
            workingTextY += verticalSpace; //adjusting the workingTextY height
        }
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