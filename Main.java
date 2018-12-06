import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Toolkit;

// Updated 11/14/2018

public class Main{
    static Graphics g;
    public static void main(String[] args){
        Game game = new Game();
        
        while(true){
            game.update();
            
        }
    }
}