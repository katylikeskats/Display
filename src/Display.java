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
    private int maxX;
    private int maxY;
    private int panelX = 0;
    private int panelY = 0;
    private JFrame frame;
    private TournamentPanel tournamentPanel;


    public Display(Bracket tournament){
        super();
        this.frame = this;
        this.maxX = (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        this.maxY = (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        this.setSize(maxX, maxY);
        this.setLocationRelativeTo(null); //start the frame in the center of the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setLayout(null);

        tournamentPanel = new SingleTournamentPanel(tournament, maxX, maxY);
        tournamentPanel.setLocation(panelX, panelY);

        MyKeyListener keyListener = new MyKeyListener();
        this.addKeyListener(keyListener);
        this.add(tournamentPanel);
        this.setVisible(true);
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
                panelY+=CHANGE;
                tournamentPanel.setLocation(panelX, panelY);
                tournamentPanel.refresh();
            } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Down")){
                panelY-=CHANGE;
                tournamentPanel.setLocation(panelX, panelY);
                tournamentPanel.refresh();
            } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Left")){
                panelX+=CHANGE;
                tournamentPanel.setLocation(panelX, panelY);
                tournamentPanel.refresh();
            } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Right")){
                panelX-=CHANGE;
                tournamentPanel.setLocation(panelX, panelY);
                tournamentPanel.refresh();
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
