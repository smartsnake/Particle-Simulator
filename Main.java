import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Toolkit;

 class Game extends JFrame
{
    int numberOfParticals = 100;
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
        /*try
			{
				Thread.sleep(10);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
            }*/
    }
}


public class Main{
    static Graphics g;
    public static void main(String[] args){
       // Model m = new Model();
       // m.createRandomParticles(2);
        //System.out.println("Tests");
        //View view = new View(m);
        //g = view.getGraphics();
        Game game = new Game();
        
        while(true){
            game.update();
            //model.draw(g);
            //view.repaint();
            
        }
    }
}