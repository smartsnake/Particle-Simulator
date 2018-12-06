import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Toolkit;


public class Game extends JFrame
{
    //The number of particals to simulate, Will randomly create size for each particles and places them in a random location.
    int numberOfParticals = 50;
    Model model;
    View view;
    public Game(){
        model = new Model();
        model.createRandomParticles(numberOfParticals);
        view = new View(model);

        this.setTitle("Particles!");
		this.setSize(900, 600);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

    }
    public void update(){
        model.update();
        view.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
}