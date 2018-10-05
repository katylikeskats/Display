/**
 * [DoubleTournamentPanel.java]
 * The display panel for double elimination tournaments
 * @author Katelyn Wang
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
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;

//IO imports
import java.io.IOException;
import java.io.File;

//Util imports
import java.util.ArrayList;

public class DoubleTournamentPanel extends TournamentPanel {
    private static final int BORDER_SPACE = 20;
    private int horizontalSpace = 100; //space between each box horizontally
    private Bracket tournament;
    private int maxX; //length of panel
    private int maxY; //height of panel
    private int winningHeight; //height of winner bracket
    private int losingHeight; //height of loser bracket
    private int boxHeight; //box height
    private int boxLength; //box length
    private ColourPalette colors;
    private int colorIndex;
    private double losePercentPage; //the percentage of the page dedicated to the winner panel
    private int workingNumMatches; //previous amount of matches created

    /**
     * Constructor
     * @param tournament tournament bracket to be displayed
     * @param panelLength the length of the panel
     * @param panelHeight the height of the panel
     * @param boxHeight the height of the box
     * @param boxLength the length of the box
     */
    public DoubleTournamentPanel(Bracket tournament, int panelLength, int panelHeight, int boxHeight, int boxLength, double losePercentPage){
        super();
        this.tournament = tournament;
        this.maxX = panelLength;
        this.maxY = panelHeight;
        this.boxHeight = boxHeight;
        this.boxLength = boxLength;
        this.losePercentPage = losePercentPage;
        this.setSize(new Dimension(this.maxX, this.maxY));
        this.setPreferredSize(new Dimension(this.maxX, this.maxY));
    }

    /**
     * Draws all the graphics on the screen
     * @param g Graphics to draw the components
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        workingNumMatches = 0;
        ArrayList<MatchBox[]> boxes = new ArrayList<>();
        int numMatches; //number of matches in a round
        int numWinMatches; //number of winner bracket matches in a round
        int numLossMatches; //number of loser bracket matches in a round
        int verticalWinSpace = 0; //space between each matchbox of a given round (for an evenly distributed look)
        int verticalLoseSpace = 0 ;

        int workingX = BORDER_SPACE; //current x from which it is drawing the winner bracket
        int workingWinY; //current y from which it is drawing the winner bracket
        int workingLoseY; //current y from which it is drawing the loser bracket

        //Setting up the font
        Font fontTitle = getFont("assets/Comfortaa-Light.ttf", 40f);
        FontMetrics fontMetrics = g.getFontMetrics(fontTitle);
        g.setFont(fontTitle);
        g.drawString("Tournament Name", workingX, BORDER_SPACE + fontMetrics.getHeight()/2); //drawing the title of the tournament **ATTN: how to input the title**
        workingX += 30;

        colors = new RainbowColourPalette(tournament.getNumberOfTeams()*2-1); //creates new color palette based on predicted number of rounds
        colorIndex = 0; //initializes the colour

        losingHeight = (int) Math.round(losePercentPage *maxY); //calculates the height allotted to the winner bracket using the required percentage
        winningHeight = maxY - losingHeight; //calculates the height allotted to the loser bracket by subtracting the winner height from the total height

        workingWinY = fontMetrics.getHeight() + 10 ; //lowers workingWinY to account for the title space
        workingLoseY = BORDER_SPACE + winningHeight; //sets working y for the loser bracket to just below the winning height threshold

        //Drawing round 1
        numMatches = tournament.getNumberOfMatchesInRound(1); //determines how many matches are in the round
        if (numMatches > 1) {
            verticalWinSpace = (winningHeight - (workingWinY * 2) - (boxHeight * numMatches)) / (numMatches - 1); //finds the space that it will use and divides it between the spaces
        } else {
            workingWinY = winningHeight/2 - boxHeight /2 + 10;
        }
        drawRound(g, workingX, workingWinY, workingLoseY, verticalWinSpace, 0, 1, boxes); //draws the matchboxes

        workingWinY = BORDER_SPACE + boxHeight + verticalWinSpace/2; //adjusts the workingWinY and workingX coordinates
        workingX += boxLength + horizontalSpace;

        g.drawLine(0, winningHeight, maxX, winningHeight); //remove later, divdes winner and lsoer bracket

        for (int roundNum = 2; roundNum <= tournament.getNumberOfRounds(); roundNum++){ //iterates through each round
            numLossMatches = findNumMatches(roundNum, 1); //determines the number of loser bracket matches
            numWinMatches = findNumMatches(roundNum, 0); //determines the number of winner bracket matches

            //If the round has more matches than or equal matches to the previous round, paints it at the same height
            if (((roundNum == 3) &&
                    (findNumMatches(1, 0) <= numWinMatches) && (hasByes())) ||
                    (roundNum == 2) &&
                            (findNumMatches(1, 0) <= numWinMatches)){
                workingWinY = BORDER_SPACE + fontMetrics.getHeight();
            }
            if (numLossMatches < findNumMatches(roundNum-1, 1)){
                workingLoseY += boxHeight/2 + verticalLoseSpace/2; //adjusts the workingLoseY int
            }

            //If there is more than one match, calculates the space between each match for an evenly distributed look
            if (numLossMatches > 1) {
                verticalLoseSpace = (losingHeight - ((workingLoseY-winningHeight) * 2) - (boxHeight * numLossMatches)) / (numLossMatches - 1);
            } else { //if there is only one match, sets the working Y to center the single match
                workingLoseY = winningHeight + losingHeight/2 - boxHeight /2;
            }
            if (numWinMatches > 1){
                verticalWinSpace = (winningHeight - (workingWinY * 2) - (boxHeight * numWinMatches)) / (numWinMatches - 1);
            } else {
                workingWinY = winningHeight/2 - boxHeight /2 + 10 ;
            }

            drawRound(g, workingX, workingWinY, workingLoseY, verticalWinSpace, verticalLoseSpace, roundNum, boxes); //draws the matchboxes

            workingWinY = BORDER_SPACE + boxHeight/2 + verticalWinSpace/2; //adjusts the workingWinY int
            workingX += boxLength + horizontalSpace; //adjusts the workingX int
        }
        drawLines(g, boxes); //draws the lines between matches which feed into each other


        Graphics2D g2 = (Graphics2D) g;
        drawRotate(g2, 30, winningHeight/2, -90, "Winner Bracket");
        drawRotate(g2, 30, winningHeight + losingHeight/2 + 50, -90, "Loser Bracket");
        g2.dispose();
    }

    /**
     * Draw a rotated string
     * @param g2d 2D graphics to draw the string
     * @param x x coordinate
     * @param y y coordinate
     * @param angle Angle of the rotation
     * @param text String to be displayed
     */
    public static void drawRotate(Graphics2D g2d, double x, double y, int angle, String text) {
        g2d.translate((float)x,(float)y);
        g2d.rotate(Math.toRadians(angle));
        g2d.drawString(text,0,0);
        g2d.rotate(-Math.toRadians(angle));
        g2d.translate(-(float)x,-(float)y);
    }

    /**
     * Determines if the bracket contains byes
     * @return true if it has byes, false if it does not
     */
    public boolean hasByes(){
        if (Math.log(tournament.getNumberOfTeams())%1.00 != 1){
            return true;
        }
        return false;
    }

    /**
     * Draws one column of match boxes, teams playing in the match, and their respective lines
     * @param g Graphics to draw the bracket
     * @param workingX The X which is changed as the bracket is drawn from top to bottom of the screen
     * @param workingWinY The Y which is changed as the bracket is drawn from top to bottom of the screen
     * @param verticalWinSpace The calculated space between each match box
     * @param roundNum The round number it is drawing
     * @param boxes ArrayList of arrays of the boxes in each round
     */
    public void drawRound(Graphics g, int workingX, int workingWinY, int workingLoseY, int verticalWinSpace, int verticalLoseSpace, int roundNum, ArrayList<MatchBox[]> boxes){
        String[][] teams; //stores teams who are playing in a certain match match
        MatchBox[] roundBoxes = new MatchBox[tournament.getNumberOfMatchesInRound(roundNum)];
        Graphics2D graphics2 = (Graphics2D) g;

        int workingY = workingWinY; //sets the working variables to the winning variables as it will draw the winner bracket first
        int verticalSpace = verticalWinSpace;

        boolean changed = false; //indicates whether the iteration has reached the loser bracket matches yet

        //Setting up the font
        Font font1 = getFont("assets/Comfortaa-Light.ttf", 15f);
        // Font font1 = new Font("Helvetica", Font.PLAIN, 15);
        FontMetrics fontMetrics = g.getFontMetrics(font1);
        g.setFont(font1);

        for (int matchNum = 1; matchNum <= tournament.getNumberOfMatchesInRound(roundNum); matchNum++){ //iterates through each match
            if ((tournament.getMatchBracket(roundNum, matchNum) == 1) && (!changed)){ //if it detects a loser bracket match, will switch over the working variables to the loser variables
                workingY = workingLoseY;
                verticalSpace = verticalLoseSpace;
                changed = true;
            }

            g.setColor(new Color(255, 255, 255));
            g.fillRoundRect(workingX, workingY, boxLength, boxHeight, 20,20); //draws the solid rounded rectangle
            g.setColor(colors.getColors().get(colorIndex)); //sets the colour
            colorIndex++;

            //drawing the rectangles
            MatchBox currBox = new MatchBox(matchNum, roundNum, workingX, workingY, boxLength, boxHeight,  20);
            roundBoxes[matchNum - 1] = currBox; //stores the object into an array for later reference

            graphics2.setStroke(new BasicStroke(2)); //setting thickness to slightly thicker than default
            g.drawString(roundNum+"."+matchNum, workingX + 10, workingY + 20);
            graphics2.draw(currBox.getRect());
            g.drawLine(currBox.getX(), currBox.getMidY(), currBox.getRightX(), currBox.getMidY()); //draws midline which divides team names

            workingY += boxHeight + verticalSpace; //adjusting the workingY variable

        }
        boxes.add(roundBoxes); //adds the current round's boxes to the matchbox array
        workingNumMatches += tournament.getNumberOfMatchesInRound(roundNum);

        g.setColor(new Color(86, 87, 87)); //sets color to dark grey
        graphics2.setStroke(new BasicStroke(1)); //resets stroke thickness
        Font boldFont = getFont("assets/Comfortaa-Bold.ttf", 15f);

        for (int i = 0; i < roundBoxes.length; i++) { //iterates through the round's matchboxes
            teams = tournament.getTeamsInMatch(roundNum, i+1); //stores the teams which play in that match
            MatchBox currBox = roundBoxes[i]; //retrieves the current match's matchbox


            //draws the right line
            if (roundNum != tournament.getNumberOfRounds()){
                g.drawLine(currBox.getRightX(), currBox.getMidY(), currBox.getRightX() + horizontalSpace / 2, currBox.getMidY());
            }

            if (teams[0].length == 1) { //checking if the teams playing is already determined
                if (((DoubleBracket)tournament).getWinnerOfMatch(roundNum, i+1) == teams[0][0]){
                    fontMetrics = g.getFontMetrics(font1);
                    g.setFont(boldFont);
                }
                g.drawString(teams[0][0], currBox.getMidX() - fontMetrics.stringWidth(teams[0][0]) / 2, currBox.getY() + boxHeight / 4 + fontMetrics.getMaxAscent()/4); //if so, draws the team names
                g.setFont(font1);
                fontMetrics = g.getFontMetrics(font1);
            } else if ((teams[0].length == 2) && (teams[1].length == 2)&& (tournament.getMatchBracket(roundNum, i+1) == 1)){
                int[] num = findPreviousMatch(boxes, currBox, teams[0]);
                g.drawString("Loser Of Round #" + num[0] + "." + num[1], currBox.getMidX() - fontMetrics.stringWidth("Loser Of Round #" + num[0] + "." + num[1]) / 2, currBox.getY() + boxHeight / 4 + fontMetrics.getMaxAscent()/4);
            } else {
                g.drawString("unknown", currBox.getMidX() - fontMetrics.stringWidth("unknown") / 2, currBox.getY() + boxHeight / 4 + fontMetrics.getMaxAscent()/4); // if not, leaves it unknown
            }
            if (teams[1].length == 1){
                if (((DoubleBracket)tournament).getWinnerOfMatch(roundNum, i+1) == teams[1][0]){
                    fontMetrics = g.getFontMetrics(font1);
                    g.setFont(boldFont);
                }
                g.drawString(teams[1][0], currBox.getMidX() - fontMetrics.stringWidth(teams[1][0]) / 2, currBox.getY() + (3* boxHeight) / 4 + fontMetrics.getMaxAscent()/4); //if so, draws the team names
                g.setFont(font1);
                fontMetrics = g.getFontMetrics(font1);
            } else if ((teams[1].length == 2) && (teams[1].length == 2) && (tournament.getMatchBracket( roundNum, i + 1) == 1)){
                int[] num = findPreviousMatch(boxes, currBox, teams[1]);
                g.drawString("Loser Of Round #" + num[0] + "." + num[1], currBox.getMidX() - fontMetrics.stringWidth("Loser Of Round #" + num[0] + "." + num[1]) / 2,currBox.getY() + (3* boxHeight) / 4 + fontMetrics.getMaxAscent()/4);
            } else {
                g.drawString("unknown", currBox.getMidX() - fontMetrics.stringWidth("unknown") / 2, currBox.getY() + (3* boxHeight) / 4 + fontMetrics.getMaxAscent()/4); // if not, leaves it unknown
            }
        }
    }


    /**
     * Finds the number of a certain type of matches within a round (0 for winner bracket matches, 1 for loser bracket matches)
     * @param roundNum the round number
     * @param type the type of matches (0 for winner bracket matches, 1 for loser bracket matches)
     * @return the total number of the given type of matches within the specified round
     */
    public int findNumMatches(int roundNum, int type){
        int sum = 0;
        for (int i = 1; i <= tournament.getNumberOfMatchesInRound(roundNum); i++ ){
            if (tournament.getMatchBracket(roundNum, i) == type){
                sum++;
            }
        }
        return sum;
    }

    /**
     * Draws a line between two given match boxes
     * @param box1 the left box
     * @param box2 the right box
     * @param g the graphics object to draw the line
     */
    public void drawLineBetweenMatch(MatchBox box1, MatchBox box2, Graphics g){
        g.drawLine(box1.getRightX() + horizontalSpace / 2, box1.getMidY(), box2.getX() - horizontalSpace / 2, box2.getMidY());
    }

    /**
     * Given the ArrayList of arrays of match boxes, determines whether the matches feed into each other and correspondingly connects the matches
     * @param g the graphics object to draw the lines
     * @param boxes the ArrayList or arrays of match boxes
     */
    public void drawLines(Graphics g, ArrayList<MatchBox[]> boxes){
        g.setColor(new Color(86, 87, 87));
        for (int i = 0; i < boxes.size()-1; i++){
            for (int j = 0; j < boxes.get(i).length; j++) {
                String[][] currTeams = tournament.getTeamsInMatch(i+1,j+1); //the teams from the current matchbox
                String[][] nextTeams; //arbitrary string array for the teams in a match from the next round
                int change = 2; //the value of how many rounds later it will be checking; if 2, will be checking the immediate next round (for loser bracket matches), if 3, will skip a round and check the second next round (for the winner bracket matches)
                if ((tournament.getMatchBracket(i+1, j+1) == 0) && (findNumMatches(i+2, 0) == 0)){ //checking if the match is part of the winner bracket and if it is before the double finals matches
                    change = 3;
                }
                for (int set = 0; set < 2; set++) { //iterates twice through, since there are two arrays to be checked in the current matchbox
                    for (int matchNum = 1; matchNum <= boxes.get(i + change-1).length; matchNum++) {
                        nextTeams = tournament.getTeamsInMatch(i + change, matchNum); //stores the teams which play in that match
                        for (int teamNum = 0; teamNum < 2; teamNum++) {  //iterates twice through, since there are two arrays to be checked in the next matchbox
                            if (( //checking if the teams playing is already determined
                                    (contains(nextTeams[teamNum], currTeams[set])) &&//checking if they feed into each other
                                    (tournament.getMatchBracket(boxes.get(i)[j].getRound(), boxes.get(i)[j].getRoundIndex()) == tournament.getMatchBracket(boxes.get(i+change-1)[matchNum-1].getRound(), boxes.get(i+change-1)[matchNum-1].getRoundIndex())))) { //checking that the two matches are from the same bracket
                                        drawLineBetweenMatch(boxes.get(i)[j], boxes.get(i + change - 1)[matchNum - 1], g);
                                        g.drawLine(boxes.get(i + change-1)[matchNum -1 ].getX(), boxes.get(i + change-1)[matchNum -1 ].getMidY(), boxes.get(i + change-1)[matchNum -1 ].getX() - horizontalSpace / 2, boxes.get(i + change-1)[matchNum -1 ].getMidY());
                                    }
                                }
                            }
                        }
                    }
                }

        drawLineBetweenMatch(boxes.get(tournament.getNumberOfRounds()-3)[1], boxes.get(tournament.getNumberOfRounds()-2)[0], g); //connects the last loser bracket match to the winner bracket (since winner of loser bracket plays the winner of the winner bracket)
    }

    /**
     * Finds the index of the previous match which the indicated matchbox feeds from
     * @param boxes the array of matchboxes
     * @param box the indicated matchbox
     * @param teams the teams from the matchbox
     * @return index of the previous match which feeds into indicated matchbox
     */
    public int[] findPreviousMatch (ArrayList<MatchBox[]> boxes, MatchBox box, String[] teams){
        String team1; //previous match should have certain players already
        String team2;
        int[] array = new int[2];
        for (int i = 1; i <= tournament.getNumberOfMatchesInRound(box.getRound()-1); i++) {
            if (tournament.getTeamsInMatch(box.getRound() - 1, i)[0].length == 1) {
                team1 = tournament.getTeamsInMatch(box.getRound() - 1, i)[0][0]; //storing the previous match's teams
                team2 = tournament.getTeamsInMatch(box.getRound() - 1, i)[1][0];
                if ((teams[0].equals(team1) && teams[1].equals(team2)) || ((teams[0].equals(team2) && teams[1].equals(team1)))) { //determining if the teams match
                    array[0] = box.getRound() - 1;
                    array[1] = boxes.get(box.getRound() - 2)[i - 1].getRoundIndex();
                }
            }
        }
        for (int i = 1; i <= tournament.getNumberOfMatchesInRound(box.getRound()); i++){
            if (tournament.getTeamsInMatch(box.getRound(), i)[0].length == 1) {
                team1 = tournament.getTeamsInMatch(box.getRound(), i)[0][0]; //storing the previous match's teams
                team2 = tournament.getTeamsInMatch(box.getRound(), i)[1][0];
                if ((teams[0].equals(team1) && teams[1].equals(team2)) || ((teams[0].equals(team2) && teams[1].equals(team1)))) { //determining if the teams match
                    array[0] = box.getRound();
                    array[1] = boxes.get(box.getRound() - 1)[i - 1].getRoundIndex();
                }
            }
        }
        return array;
    }

    /**
     * Checks whether 2 boxes have corresponding teams, indicating that they feed into each other
     * @param teams array of Strings of the teams of the first box
     * @param teamQuery array of Strings of the teams of the second (future round) box
     * @return true if the first box leads to the second, false if not
     */
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

    /**
     * Retrieves a font and creates it
     * @param fileName the name of the font file
     * @param size the desired size of the font to be made
     * @return the created Font
     */
    public static Font getFont(String fileName, float size){
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(fileName)).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.PLAIN,new File(fileName)));
        } catch (IOException | FontFormatException e){
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
        }
        return font;
    }


}