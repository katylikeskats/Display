/**
 * [Display.java]
 * The frame on which the bracket is displayed
 *
 * @author Katelyn Wang & Dora Su
 * September 18 2018
 */

//Swing imports
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.security.Key;

public class Display extends JFrame{
    private static final int CHANGE = 50;
    private static final int BOX_HEIGHT = 100;
    private static final int BOX_LENGTH = 240;
    private static final int VERTICAL_SPACE = 30;
    private static final int HORIZONTAL_SPACE = 100;
    private int scrollableX;
    private int scrollableY;
    private int maxX;
    private int maxY;
    private int panelX = 0;
    private int panelY = 0;
    private JFrame frame;
    private TournamentPanel tournamentPanel;
    private Bracket tournament;

    public Display(Bracket tournament){
        super();
        this.frame = this;
        this.tournament = tournament;

        int requiredHeight = findMostMatches()*(BOX_HEIGHT+VERTICAL_SPACE)+40*2;
        int requiredLength = (tournament.getNumberOfRounds()*BOX_LENGTH)+((tournament.getNumberOfRounds()-1)*HORIZONTAL_SPACE)+40*2;
        if (requiredHeight > Toolkit.getDefaultToolkit().getScreenSize().getHeight()) {
            this.maxY = (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
            scrollableY = requiredHeight - this.maxY;
        } else {
            this.maxY = findMostMatches()*(BOX_HEIGHT+VERTICAL_SPACE)+40*2;
        }
        if (requiredLength > Toolkit.getDefaultToolkit().getScreenSize().getWidth()){
            this.maxX = (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
            scrollableX = requiredLength - this.maxX;
        } else {
            this.maxX = (tournament.getNumberOfRounds()*BOX_LENGTH)+((tournament.getNumberOfRounds()-1)*HORIZONTAL_SPACE)+40*2;
        }
        this.setSize(maxX, maxY);
        this.setLocationRelativeTo(null); //start the frame in the center of the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setLayout(null);

        tournamentPanel = new SingleTournamentPanel(tournament, requiredLength, requiredHeight, BOX_HEIGHT, BOX_LENGTH);
        tournamentPanel.setLocation(panelX, panelY);

        MyKeyListener keyListener = new MyKeyListener();
        this.addKeyListener(keyListener);
        this.add(tournamentPanel);
        this.setVisible(true);
    }

    public int findMostMatches(){
        int most = 0;
        for (int i = 1; i <= tournament.getNumberOfRounds(); i++){
            if (tournament.getNumberOfMatchesInRound(i) >= most){
                most = tournament.getNumberOfMatchesInRound(i);
            }
        }
        return most;
    }



    /**
     * Updates the bracket by calling it to be redrawn
     * @param tournament The updated tournament bracket to be drawn
     */
    public void update(Bracket tournament){
        tournamentPanel.setTournament(tournament);
    }

    private class MyKeyListener implements KeyListener{
        public void keyPressed(KeyEvent e){
            if (KeyEvent.getKeyText(e.getKeyCode()).equals("Up")){
                if (panelY+ CHANGE < 0) {
                    panelY+=CHANGE;
                    tournamentPanel.setLocation(panelX, panelY);
                } else {
                    panelY = 0;
                    tournamentPanel.setLocation(panelX, panelY);
                }
            } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Down")){
                if (panelY - CHANGE > -scrollableY) {
                    panelY -= CHANGE;
                    tournamentPanel.setLocation(panelX, panelY);
                } else {
                    panelY = -scrollableY;
                    tournamentPanel.setLocation(panelX, panelY);
                }
            } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Left")){
                if (panelX + CHANGE < 0) {
                    panelX += CHANGE;
                    tournamentPanel.setLocation(panelX, panelY);
                } else {
                    panelX = 0;
                    tournamentPanel.setLocation(panelX, panelY);
                }

            } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Right")){
                if (panelX - CHANGE > -scrollableX) {
                    panelX -= CHANGE;
                    tournamentPanel.setLocation(panelX, panelY);
                } else {
                    panelX = -scrollableX;
                    tournamentPanel.setLocation(panelX, panelY);
                }
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {  //If ESC is pressed
                frame.dispose(); //close frame
            }
        }

        public void keyReleased(KeyEvent e){
        }

        public void keyTyped(KeyEvent e){
        }
    }

}
