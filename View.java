import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Color;

class View extends JPanel{
    Model m;
    View(Model m){
        //JFrame frame = new JFrame("Particle example");
        this.m = m;
        //setTitle("Particle example");
       /* frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);*/
        //panel = new JPanel();
        
        //getContentPane().add(panel);

        //panel.setLayout(null);
    }
    public void paintComponent(Graphics g){
		
        //g.drawLine(10, 10, 200, 300);	
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());	
        m.draw(g);	
       
        
    }
    
    public void update()
    {
        //panel.add
    }

}