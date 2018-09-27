/**
 * [Display.java]
 * The frame on which the bracket is displayed
 *
 * @author Katelyn Wang & Dora Su
 * September 18 2018
 */

//Swing imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Display extends JFrame{
    private static final int CHANGE = 50;
    private static final int BOX_HEIGHT = 100;
    private static final int BOX_LENGTH = 240;
    private static final int VERTICAL_SPACE = 30;
    private static final int HORIZONTAL_SPACE = 100;
    private static final int BORDER_SPACE = 40;
    private int maxX;
    private int maxY;
    private JFrame frame;
    private TournamentPanel tournamentPanel;
    private Bracket tournament;

    public Display(Bracket tournament){
        super();
        this.frame = this;
        this.tournament = tournament;

        int requiredHeight = findMostMatches()*(BOX_HEIGHT+VERTICAL_SPACE)+ BORDER_SPACE*2 + 50;
        int requiredLength = (tournament.getNumberOfRounds()*BOX_LENGTH)+((tournament.getNumberOfRounds()-1)*HORIZONTAL_SPACE)+(40*2);
        if (requiredHeight > Toolkit.getDefaultToolkit().getScreenSize().getHeight()) {
            this.maxY = (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        } else {
            this.maxY = requiredHeight;
        }
        if (requiredLength > Toolkit.getDefaultToolkit().getScreenSize().getWidth()){
            this.maxX = (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        } else {
            this.maxX = requiredLength;
        }
        this.setSize(maxX, maxY);
        this.setLocationRelativeTo(null); //start the frame in the center of the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);

        tournamentPanel = new SingleTournamentPanel(tournament, requiredLength, requiredHeight, BOX_HEIGHT, BOX_LENGTH);

        JScrollPane sp = new JScrollPane(tournamentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        sp.setViewportView(tournamentPanel);

        MyKeyListener keyListener = new MyKeyListener();
        this.addKeyListener(keyListener);
        this.add(sp);
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
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {  //If ESC is pressed
                frame.dispose(); //close frame
            }
        }

        public void keyReleased(KeyEvent e){
        }

        public void keyTyped(KeyEvent e){
        }
    }

}
