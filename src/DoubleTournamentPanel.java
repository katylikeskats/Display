/**
 * [SingleTournamentPanel.java]
 * The display panel for single elimination tournaments
 * @author Katelyn Wang & Dora Su
 * September 18 2018
 */

//Graphics imports
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.FontMetrics;
import java.awt.BasicStroke;

//Util imports
import java.util.ArrayList;

public class DoubleTournamentPanel extends TournamentPanel {
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

    public DoubleTournamentPanel(Bracket tournament, int x, int y){
        super();
        this.tournament = tournament;
        this.maxX = x + 600;
        this.maxY = y + 600;
        this.setSize(new Dimension(this.maxX, this.maxY));
    }

    /**
     * Draws all the graphics on the screen
     * @param g Graphics to draw the components
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int numRounds = tournament.getNumberOfRounds();
        ArrayList<MatchBox[]> boxes = new ArrayList<>();
        int numMatches;
        int verticalSpace; //space between each matchbox of a given round (for an evenly distributed look)

        int workingX = BORDER_SPACE; //current x from which it is drawing
        int workingY = BORDER_SPACE; //current y from which it is drawing

        height = (maxY-BORDER_SPACE*2-(VERTICAL_SPACE*tournament.getNumberOfMatchesInRound(findMostMatches())))/(tournament.getNumberOfMatchesInRound(findMostMatches())); //height of each match box
        length = (maxX-BORDER_SPACE*2-HORIZONTAL_SPACE*numRounds)/numRounds; //length of each match box
        colors = new RainbowColourPalette(tournament.getNumberOfTeams()-1);
        colorIndex = 0;

        for (int roundNum = 1; roundNum <= tournament.getNumberOfRounds(); roundNum++){ //iterates through each round
            numMatches = tournament.getNumberOfMatchesInRound(roundNum); //determines how many matches are in the round

            if (roundNum == findMostMatches()){
                workingY = BORDER_SPACE;
            }

            if (numMatches>1) { //if it is more than one, calculates the space between each matchbox
                verticalSpace = (maxY - (workingY * 2) - (height * numMatches))/ (numMatches - 1);
            } else {
                verticalSpace = maxY; //if not, defaults to the full length of the screen
                workingY = maxY/2 - height/2 ; //adjusts the workingY and workingX coordinates

            }

            drawRound(g, workingX, workingY, workingX+length/2, workingY+height/4, verticalSpace, roundNum, boxes); //draws the matchboxes
            if (roundNum != tournament.getNumberOfRounds()-1) {
                workingY = BORDER_SPACE + height / 2 + verticalSpace; //adjusts the workingY and workingX coordinates
            }
            workingX += length + HORIZONTAL_SPACE;
        }
        drawLines(g, boxes);
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
    public void drawRound(Graphics g, int workingX, int workingY, int workingTextX, int workingTextY, int verticalSpace, int roundNum, ArrayList<MatchBox[]> boxes){
        String[][] teams; //stores teams who are playing in a certain match match
        MatchBox[] roundBoxes = new MatchBox[tournament.getNumberOfMatchesInRound(roundNum)];
        Graphics2D graphics2 = (Graphics2D) g;

        //Setting up the font
        Font font1 = new Font("Sans_Serif", Font.PLAIN, 15);
        FontMetrics fontMetrics = g.getFontMetrics(font1);
        g.setFont(font1);


        for (int matchNum = 1; matchNum <= tournament.getNumberOfMatchesInRound(roundNum); matchNum++){ //iterates through each match
            teams = tournament.getTeamsInMatch(roundNum, matchNum); //stores the teams which play in that match
            g.setColor(colors.getColors().get(colorIndex));
            colorIndex++;


            //drawing the rectangles
            graphics2.setStroke(new BasicStroke(2)); //setting thickness to slightly thicker than default
            MatchBox currBox = new MatchBox(workingX, workingY, length, height,  20);
            roundBoxes[matchNum - 1] = currBox;
            graphics2.draw(currBox.getRect());
            //g.fillRoundRect(workingX, workingY, length, height, 20,20); option for our clients!


            //drawing the team names/text
            g.setColor(new Color(86, 87, 87));
            for (int teamNum = 0; teamNum < teams.length; teamNum++) {
                if (teams[teamNum].length == 1) { //checking if the teams playing is already determined
                    g.drawString(teams[teamNum][0], workingTextX-fontMetrics.stringWidth(teams[teamNum][0])/2, workingTextY); //if so, draws the team names
                } else {
                    g.drawString("unknown", workingTextX-fontMetrics.stringWidth("unknown")/2, workingTextY); // if not, leaves it unknown
                    g.drawLine(currBox.getX(), currBox.getMidY(), currBox.getX()-HORIZONTAL_SPACE/2, currBox.getMidY());
                }
                workingTextY += height / 2; //changing where the next text will be drawn
            }
            g.drawString("vs.", workingTextX-fontMetrics.stringWidth("vs.")/2, workingTextY-height/2-height/4); //drawing the "vs." between the teams; had to be outside the loop or else it would be drawn multiple times

            workingY += height + verticalSpace; //adjusting the workingY height
            workingTextY = workingY +height/4; //adjusting the workingTextY height
        }
        boxes.add(roundBoxes);
    }

    public int findMostMatches(){
        int most = 0;
        int record = 1;
        for (int i = 0; i < tournament.getNumberOfRounds(); i++){
            if (tournament.getNumberOfMatchesInRound(i) >= most){
                record = i;
                most = tournament.getNumberOfMatchesInRound(i);
            }
        }
        return record;
    }

    public void drawLineBetweenMatch(MatchBox box1, MatchBox box2, Graphics g){
        g.drawLine(box1.getRightX()+HORIZONTAL_SPACE/2, box1.getMidY(), box1.getRightX()+HORIZONTAL_SPACE/2, box2.getMidY());
    }

    public void drawLines(Graphics g, ArrayList<MatchBox[]> boxes){
        //public void drawLines(Graphics g, Graphics2D graphics2, ArrayList<MatchBox> boxes, String[][] teams){
        //graphics2.setStroke(new BasicStroke(1)); // resetting thickness
        int x;
        int y;
        g.setColor(new Color(86, 87, 87));
        for (int i = 0; i < boxes.size()-1; i++){
            for (int j = 0; j < boxes.get(i).length; j++) {
                x = boxes.get(i)[j].getRightX();
                y = boxes.get(i)[j].getMidY();
                g.drawLine(x, y, x + HORIZONTAL_SPACE / 2, y);
                String[][] currTeams = tournament.getTeamsInMatch(i+1,j+1);
                String[][] nextTeams;

                for (int set = 0; set < 2; set++){
                    for (int matchNum = 1; matchNum <= boxes.get(i + 1).length; matchNum++) {
                        nextTeams = tournament.getTeamsInMatch(i + 2, matchNum); //stores the teams which play in that match
                        for (int teamNum = 0; teamNum < 2; teamNum++) {
                            if (nextTeams[teamNum].length > 1) { //checking if the teams playing is already determined
                                if (contains(nextTeams[teamNum], currTeams[set])) {
                                    drawLineBetweenMatch(boxes.get(i)[j], boxes.get(i + 1)[matchNum-1], g);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean contains(String[] teams, String[] teamQuery){
        for (int i = 0; i < teams.length; i++){
            for (int j = 0; j < teamQuery.length; j++) {
                if (teams[i].equals(teamQuery[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Updates the tournament bracket display by changing the tournament bracket it is drawing. Repaints the screen once called
     * @param tournament Updated tournament bracket to be displayed
     */
    public void setTournament(Bracket tournament) {
        this.tournament = tournament;
        repaint();
    }

    public void refresh(){
        repaint();
    }

}