import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import java.lang.Math;

public class Model{

    ArrayList<Particle> particles;
    boolean eat = true;
    public Model(){
        particles = new ArrayList<Particle>();

    }
    public void createParticle(float x, float y, double mass){
        //System.out.println("x: " + x + ", y: " + y + ", mass: " + mass);
        particles.add(new Particle(particles.size(), x, y, mass));
    }
    public void createRandomParticles(int size){
        int count = 0;
        Random ran = new Random();
        //System.out.println("Tests");
        while(count < size){
            //System.out.println("Tests: " + count);
            int x = ran.nextInt(800);
            int y = ran.nextInt(500);
            double mass = (double)ran.nextInt(10) + 5;
            createParticle((float)x, (float)y, mass);
            count++;
        }
        //count++;
    }

    public void draw(Graphics g){
       // System.out.println("size: " + particles.size());
       g.setColor(new Color(0, 0, 0));
        for(int x = 0; x < particles.size(); x++){
            //g.drawOval((int)particles.get(x).x, (int)particles.get(x).y, (int)particles.get(x).mass+1, (int)particles.get(x).mass);
            particles.get(x).draw(g);
        }
        
    }

    public void update(){
        //System.out.println("size: " + particles.size());
        int size = particles.size();
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                particles.get(x).gravity(particles.get(y));
                //if(particles.get(x).collision(particles.get(y))){System.out.println("Collision!!!");}
            }
            particles.get(x).update();
        }
        for(int x = 0; x < size; x++){ 
            for(int y = 0; y < size; y++){
                //System.out.println("x: " + x + ", y: " +y);
                if(particles.get(x).collision(particles.get(y))){

                    if(!(particles.get(x).hasCollided && particles.get(y).hasCollided))
                    {
                        particles.get(x).runCollision(particles.get(y));
                        if(eat){
                            if(particles.get(x).mass > particles.get(y).mass){
                                particles.get(x).mass +=  Math.sqrt(particles.get(y).mass/4);
                                particles.get(x).x -= Math.sqrt(particles.get(y).mass/4)/2;
                                particles.get(x).y -= Math.sqrt(particles.get(y).mass/4)/2;
                                particles.remove(y);
                                size = particles.size();
                                //particles.get(0).mass +=  particles.get(y).mass;
                                //particles.remove(y);
                            }
                            else{
                                //particles.get(y).mass += particles.get(0).mass;
                                //particles.remove(0);
                                particles.get(y).mass +=  Math.sqrt(particles.get(x).mass/4);
                                particles.get(y).x -= Math.sqrt(particles.get(x).mass/4)/2;
                                particles.get(y).y -= Math.sqrt(particles.get(x).mass/4)/2;
                                particles.remove(x);
                                size = particles.size();
                            }
                        }
                    }
                    //System.out.println("x: " + x + ", y: " +y);
                    /*if(particles.get(x).mass > particles.get(y).mass){
                        particles.get(x).mass +=  Math.sqrt(particles.get(y).mass/4);
                        particles.get(x).x -= Math.sqrt(particles.get(y).mass/4)/2;
                        particles.get(x).y -= Math.sqrt(particles.get(y).mass/4)/2;
                        particles.remove(y);
                        size = particles.size();
                        //particles.get(0).mass +=  particles.get(y).mass;
                        //particles.remove(y);
                    }
                    else if(particles.get(x).mass == particles.get(y).mass){
                       
                       
                        /*particles.get(x).x_vel *= -1;
                        particles.get(x).y_vel *= -1;

                        particles.get(y).x_vel *= -1;
                        particles.get(y).y_vel *= -1;*/


                /*}
                    else{
                        //particles.get(y).mass += particles.get(0).mass;
                        //particles.remove(0);
                        particles.get(y).mass +=  Math.sqrt(particles.get(x).mass/4);
                        particles.get(y).x -= Math.sqrt(particles.get(x).mass/4)/2;
                        particles.get(y).y -= Math.sqrt(particles.get(x).mass/4)/2;
                        particles.remove(x);
                        size = particles.size();
                    }*/
                   //System.out.println("Collision!!!");
                }
            }
            
        }
        for(int x = 0; x < size; x++)
        {
            particles.get(x).hasCollided = false;
        }
    }
}