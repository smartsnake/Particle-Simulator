import java.util.Random;
import java.awt.Graphics;
import java.lang.Math;
import java.awt.Color;

public class Particle{
    int id;
    public float x;
    public float y;
    int seed = 123456789;
    float fricktion = 0;//(float)0.0002;
    public double mass;
    boolean debug = false;
    boolean hasCollided = false;
    public Particle(int id, float x, float y, double mass){
        this.id = id;
        this.x = x;
        this.y = y;
        this.x_vel = 0;
        this.y_vel = 0;
        this.mass = mass;
    }
    Random ran;
    float step = 1;
    float x_vel;
    float y_vel;
    public void update(){
        //ran = new Random(seed);
        //int dir = ran.nextInt(step);
        //x_vel = ran.nextFloat();//(float) ran.nextInt(step);
        //y_vel = ran.nextFloat();//(float) ran.nextInt(step);
        this.x -= x_vel;
        this.y -= y_vel;
        if(this.x < 0)
        {
            this.x = 0;
            this.x_vel *= (float)-1;
        }
        if(this.y < 0){
            this.y = 0;
            this.y_vel *= (float)-1;
        }
        if(this.x >= 800){
            this.x = 800 - (float)this.mass;
            this.x_vel *= (float)-1;
        }
        if(this.y >= 500){
            this.y = 500 - (float)this.mass;
            this.y_vel *= (float)-1;
        }

        /*if(x_vel > 0){
            x_vel -= fricktion;
        }
        if(x_vel < 0){
            x_vel += fricktion;
        }
        if(y_vel > 0){
            y_vel -= fricktion;
        }
        if(y_vel < 0){
            y_vel += fricktion;
        }*/
        
        //this.fricktion += fricktion;
    }



    public boolean collision(Particle other){
        //System.out.println("This x: " + this.x + ", this y: " + this.y + ", this mass: " + this.mass);
        //System.out.println("Other x: " + other.x + ", other y: " + other.y + ", other mass: " + other.mass);
       
        if(this.id != other.id){
            if(((other.x >= this.x && other.x <= (this.mass + this.x)) || (other.x + other.mass >= this.x  && other.x + other.mass <= (this.mass + this.x)) ) && (other.y >= this.y && other.y <= (this.mass + this.y) || other.y + other.mass >= this.y && other.y + other.mass <= (this.mass + this.y)  ) )
            {
                //System.out.println("id: " + this.id + ", other id: " + other.id);
                //System.out.println("This x: " + this.x + ", this y: " + this.y + ", this mass: " + this.mass);
                //System.out.println("Other x: " + other.x + ", other y: " + other.y + ", other mass: " + other.mass);
                if(debug)
                    System.out.println(this.id + " Collided with: " + other.id);
                return true;
            }
        }
        return false;
    }

    public void runCollision(Particle other)
    {
        float distX = (other.x + (float)(other.mass/2))-(this.x + (float)(this.mass/2)), distY = (other.y + (float)(other.mass/2))-(this.y + (float)(this.mass/2));
        float dist = (float)Math.sqrt(distX*distX+distY*distY);
        float xComp = distX/dist, yComp = distY/dist;
        float va1 = (x_vel*xComp+y_vel*yComp), vb1 = (-x_vel*yComp+y_vel*xComp);
        float va2 = (other.x_vel*xComp+other.y_vel*yComp), vb2 = (-other.x_vel*yComp+other.y_vel*xComp);
        double ed = .75; //Make this less to create a sense of speed loss with each collision
        float vaP1 =(float) (va1 + (1+ed)*(va2-va1)/(1+mass/other.mass)), vaP2 = (float)(va2 + (1+ed)*(va1-va2)/(1+other.mass/mass));

        float vx1 = vaP1*xComp-vb1*yComp, vy1 = vaP1*yComp+vb1*xComp;
        float vx2 = vaP2*xComp-vb1*yComp, vy2 = vaP2*yComp+vb1*xComp;

        x_vel = vx1;
        y_vel = vy1;
        other.x_vel = vx2;
        other.y_vel = vy2;

        hasCollided = true;
        other.hasCollided = true;
    }
    

    float gCont = (float)0.00000005;
    public void gravity(Particle other){
        if(this.id != other.id){
            if(debug)
                System.out.println("id: " + this.id);
            float xDist = (this.x + (float)(this.mass/2)) - (other.x + (float)(other.mass/2));
            float yDist = (this.y + (float)(this.mass/2)) - (other.y + (float)(other.mass/2));
            if(debug)
                System.out.println("xDist: " +xDist + ", yDist: " + yDist);
            float dist =(float) Math.sqrt(xDist*xDist + yDist*yDist);
            if(debug)
                System.out.println("dist: " + dist);
            float accle = (float)(gCont * this.mass * other.mass) / (dist*dist);
            if(debug)
                System.out.println("accle: " + accle);
            x_vel += accle * xDist/dist;
            y_vel += accle * yDist/dist;
            if(debug)
                System.out.println("x_vel: " + x_vel + ", y_vel: " +  y_vel);
        }
    }


    public void draw(Graphics g){
        if(this.id == 0)
        {
            g.setColor(Color.RED);
        }
        else{
            g.setColor(Color.BLACK);
        }
        g.drawOval((int)this.x, (int)this.y, (int)this.mass, (int)this.mass);
        g.drawString("" + this.id, (int)(this.x + this.mass), (int)this.y);
    }
}