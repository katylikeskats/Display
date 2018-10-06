/**
 * [SingleTournamentPanel.java]
 * The display panel for single elimination tournaments
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

//IO imports
import java.io.IOException;
import java.io.File;

//Util imports
import java.util.ArrayList;

public class SingleTournamentPanel extends TournamentPanel {
    private static final int BORDER_SPACE = 20;
    private static final int HORIZONTAL_SPACE = 100; //space between each box horizontally
    private Bracket tournament;
    private int maxX; //panel length
    private int maxY; //panel height
    private int boxHeight; //height of box
    private int boxLength; //length of box
    private ColourPalette colors; // colors to draw with
    private int colorIndex;
    private int workingNumMatches; //the previous number of matches created (to add onto current match number to get total match number)

    /**
     * Constructor
     * @param tournament tournament bracket to be displayed
     * @param panelLength the length of the panel
     * @param panelHeight the height of the panel
     * @param boxHeight the height of the box
     * @param boxLength the length of the box
     */
    public SingleTournamentPanel(Bracket tournament, int panelLength, int panelHeight, int boxHeight, int boxLength){
        super();
        this.tournament = tournament;
        this.maxX = panelLength;
        this.maxY = panelHeight;
        this.boxHeight = boxHeight;
        this.boxLength = boxLength;
        this.setSize(new Dimension(this.maxX, this.maxY));
        this.setPreferredSize(new Dimension(this.maxX, this.maxY));
    }

    /**
     * Draws all the graphics on the screen
     * @param g Graphics to draw the components
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        workingNumMatches = 0; //initializes number of matches to 0
        ArrayList<MatchBox[]> boxes = new ArrayList<>();
        int numMatches;
        int verticalSpace = 0; //space between each matchbox of a given round (for an evenly distributed look)

        int workingX = BORDER_SPACE; //current x from which it is drawing
        int workingY = BORDER_SPACE; //current y from which it is drawing

        colors = new RainbowColourPalette(tournament.getNumberOfTeams()-1); //creating the color palette
        colorIndex = 0;

        //Setting up the font
        Font fontTitle = getFont("assets/Comfortaa-Light.ttf", 40f);
        // Font font1 = new Font("Helvetica", Font.PLAIN, 15);
        FontMetrics fontMetrics = g.getFontMetrics(fontTitle);
        g.setFont(fontTitle);
        g.drawString("Tournament Name", workingX, workingY + fontMetrics.getHeight()/2);

        workingY += fontMetrics.getHeight() + 10 ; //setting up where the drawing will begin


        for (int roundNum = 1; roundNum <= tournament.getNumberOfRounds(); roundNum++){ //iterates through each round
            numMatches = tournament.getNumberOfMatchesInRound(roundNum); //determines how many matches are in the round

            //If the round has the most matches, paints the round beginning at the top of the screen
            if (roundNum == findIndexMostMatches()){
                workingY = BORDER_SPACE + fontMetrics.getHeight() + 10;
            }

            if (numMatches>1) { //if it is more than one, calculates the space between each matchbox
                verticalSpace = (maxY - (workingY*2 - fontMetrics.getHeight()) - (boxHeight * numMatches))/ (numMatches - 1); //finds the space that it will use and divides it between the spaces
            } else {
                workingY = maxY/2 - boxHeight /2; //if there is only 1 match, sets the workingY to the middle to centre the finals match
            }

            drawRound(g, workingX, workingY, verticalSpace, roundNum, boxes); //draws the matchboxes

            workingY = BORDER_SPACE + fontMetrics.getHeight() + boxHeight / 2 + verticalSpace/2; //adjusts the workingY int
            workingX += boxLength + HORIZONTAL_SPACE; //adjusts the workingX int
        }
        drawLines(g, boxes); //draws lines between the matches which feed into each other
    }

    /**
     * Draws one column of match boxes, teams playing in the match, and their respective lines
     * @param g Graphics to draw the bracket
     * @param workingX The X which is changed as the bracket is drawn from top to bottom of the screen
     * @param workingY The Y which is changed as the bracket is drawn from top to bottom of the screen
     * @param verticalSpace The calculated space between each match box
     * @param roundNum The round number it is drawing
     * @param boxes ArrayList of arrays of the boxes in each round
     */
    public void drawRound(Graphics g, int workingX, int workingY, int verticalSpace, int roundNum, ArrayList<MatchBox[]> boxes){
        String[][] teams; //stores teams who are playing in a certain match match
        MatchBox[] roundBoxes = new MatchBox[tournament.getNumberOfMatchesInRound(roundNum)]; //sets up a MatchBox array for as many matches there are in the round
        Graphics2D graphics2 = (Graphics2D) g;

        //Setting up the font
        Font font1 = getFont("assets/Comfortaa-Light.ttf", 15f);
        FontMetrics fontMetrics = g.getFontMetrics(font1);
        g.setFont(font1);

        //g.drawString("Round "+Integer.toString(roundNum), workingX + boxLength/2 - fontMetrics.stringWidth("Round "+Integer.toString(roundNum))/2, workingY -15);
        for (int matchNum = 1; matchNum <= tournament.getNumberOfMatchesInRound(roundNum); matchNum++){ //iterates through each match
            g.setColor(new Color(255, 255, 255));
            g.fillRoundRect(workingX, workingY, boxLength, boxHeight, 20,20); //drawing the solid rounded rectangle
            g.setColor(colors.getColors().get(colorIndex)); //setting the next colour
            colorIndex++; //increasing the colour index

            //drawing the rectangles
            MatchBox currBox = new MatchBox(workingX, workingY, boxLength, boxHeight,  20);
            roundBoxes[matchNum - 1] = currBox;

            //draws match number
            g.drawString(roundNum+"."+matchNum,  workingX + 10, workingY + 20);

            //makes stroke thicker and then draws matchbox
            graphics2.setStroke(new BasicStroke(2)); //setting thickness to slightly thicker than default
            graphics2.draw(currBox.getRect());
            g.drawLine(currBox.getX(), currBox.getMidY(), currBox.getRightX(), currBox.getMidY()); //draws midline which divides team names

            //drawing the lines which judd out of each box
            g.setColor(new Color(86, 87, 87));
            graphics2.setStroke(new BasicStroke(1));

            if (roundNum != tournament.getNumberOfRounds()){ //ensuring extra lines won't be drawn
                g.drawLine(currBox.getRightX(), currBox.getMidY(), currBox.getRightX() + HORIZONTAL_SPACE / 2, currBox.getMidY()); //the one that comes out of the right side
            }

            workingY += boxHeight + verticalSpace; //adjusting the workingY boxHeight

        }
        boxes.add(roundBoxes);
        workingNumMatches += tournament.getNumberOfMatchesInRound(roundNum);

        Font boldFont = getFont("assets/Comfortaa-Bold.ttf", 15f);
        //drawing all the team names
        for (int i = 0; i < roundBoxes.length; i++) {
            boolean connected = false;
            teams = tournament.getTeamsInMatch(roundNum, i+1); //stores the teams which play in that match
            MatchBox currBox = roundBoxes[i];

            if (teams[0].length == 1) { //checking if the teams playing is already determined
                if (((SingleBracket)tournament).getMatchWinner(roundNum, i+1) == teams[0][0]) {
                    fontMetrics = g.getFontMetrics(boldFont);
                    g.setFont(boldFont);
                }
                g.drawString(teams[0][0], currBox.getMidX() - fontMetrics.stringWidth(teams[0][0]) / 2, currBox.getY() + boxHeight / 4 + fontMetrics.getMaxAscent()/4); //if so, draws the team names
                fontMetrics = g.getFontMetrics(font1);
                g.setFont(font1);
            } else {
                g.drawString("unknown", currBox.getMidX() - fontMetrics.stringWidth("unknown") / 2, currBox.getY() + boxHeight / 4 + fontMetrics.getMaxAscent()/4); // if not, leaves it unknown
                connected = true;
            }
            if (teams[1].length == 1){
                if (((SingleBracket)tournament).getMatchWinner(roundNum, i+1) == teams[1][0]){
                    fontMetrics = g.getFontMetrics(boldFont);
                    g.setFont(boldFont);
                }
                g.drawString(teams[1][0], currBox.getMidX() - fontMetrics.stringWidth(teams[1][0]) / 2, currBox.getY() + (3* boxHeight) / 4 + fontMetrics.getMaxAscent()/4); //if so, draws the team names
                g.setFont(font1);
                fontMetrics = g.getFontMetrics(font1);
            } else {
                g.drawString("unknown", currBox.getMidX() - fontMetrics.stringWidth("unknown") / 2, currBox.getY() + (3* boxHeight) / 4 + fontMetrics.getMaxAscent()/4); // if not, leaves it unknown
                connected = true;
            }
            if ((roundNum != 1) && (connected)) { //ensuring extra lines won't be drawn
                g.drawLine(currBox.getX(), currBox.getMidY(), currBox.getX() - HORIZONTAL_SPACE / 2, currBox.getMidY()); // the one that comes out of the left side
            }
        }
    }

    /**
     * Determines the round with the most number of matches
     * @return returns the round number with the most number of matches
     */
    public int findIndexMostMatches(){
        int mostRounds = 0; //the highest number of matches
        int recordIndex = 1; // the index of the round with the highest number of matches
        for (int i = 1; i <= tournament.getNumberOfRounds(); i++){
            if (tournament.getNumberOfMatchesInRound(i) >= mostRounds){
                recordIndex = i;
                mostRounds = tournament.getNumberOfMatchesInRound(i);
            }
        }
        return recordIndex;
    }

    /**
     * Draws a line between two given match boxes
     * @param box1 the left box
     * @param box2 the right box
     * @param g the graphics object to draw the line
     */
    public void drawLineBetweenMatch(MatchBox box1, MatchBox box2, Graphics g){
        g.drawLine(box1.getRightX()+HORIZONTAL_SPACE/2, box1.getMidY(), box2.getX()-HORIZONTAL_SPACE/2, box2.getMidY());
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

                for (int set = 0; set < 2; set++){ //iterates twice through, since there are two arrays to be checked in the current matchbox
                    for (int matchNum = 1; matchNum <= boxes.get(i + 1).length; matchNum++) {
                        nextTeams = tournament.getTeamsInMatch(i + 2, matchNum); //stores the teams which play in that match
                        for (int teamNum = 0; teamNum < 2; teamNum++) {  //iterates twice through, since there are two arrays to be checked in the next matchbox
                            if (contains(nextTeams[teamNum], currTeams[set])) { //if they feed into each other, draw a line between them
                                drawLineBetweenMatch(boxes.get(i)[j], boxes.get(i + 1)[matchNum-1], g);
                            }
                        }
                    }
                }
            }
        }
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