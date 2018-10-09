//Swing Imports 
import javax.swing.JFrame;
import javax.swing.ImageIcon;

//Graphics imports
import java.awt.Dimension;

public class NotificationFrame extends JFrame{
    private static final int MAX_X = 550;
    private static final int MAX_Y = 343;
    private ImageIcon icon = new ImageIcon("assets/Icon.png");

    NotificationFrame(){
        super("Tournament Created");
        this.setIconImage(icon.getImage());
        this.setSize(new Dimension(MAX_X, MAX_Y));
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        NotificationPanel panel = new NotificationPanel(MAX_X, MAX_Y);
        this.add(panel);
        this.setAlwaysOnTop(true);
        this.setVisible(true);

        Thread t = new Thread(new Runnable() { public void run() { animate(panel); }}); //start the gameLoop
        t.start();

    }

    public void animate(NotificationPanel panel){
        while(true) {
            panel.refresh();
        }
    }

}