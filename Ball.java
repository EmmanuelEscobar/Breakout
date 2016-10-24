import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.awt.Color;
import java.util.List;

/**
 * A ball is an object that can hit other objects and bounce off the 
 * edges of the world (except the bottom edge).  It will bounce off of
 * a paddle as well.
 * 
 * @author Barbara Ericson Georgia Tech 
 * @version 1.0 April 6, 2007
 */
public class Ball extends Actor
{

    //////////// fields /////////////////////////////
    
    /** the radius of this ball */
    private int radius = 10;
    
    /** the width of this ball (diameter) */
    private int width = radius * 2;
    
    /** the color of the ball */
    private Color color = Color.PINK;
    
    /** the amount of change in x during each act */
    private int velX;
    
    /** the amount of change in y during each act */
    private int velY = 2;
    
    
    private int ydireccion=205;
    private int xdireccion=226;
    private Ball ball;
    
    ////////////////// constructors /////////////////////
    
    /**
     * Constructor that takes no arguments 
     */
    public Ball()
    {
        // set velX to 1-2 or -1 to -2
        velX = Greenfoot.getRandomNumber(2) + 1;
        if (Greenfoot.getRandomNumber(2) == 0)
           velX = -1 * velX;
        updateImage();
    }
    
    /**
     * Constructor that takes initial values for all fields
     * @param theRadius the radius to use
     * @param theColor  the color to use
     * @param theVelX   the amount to change in X per act
     * @param theVelY   the amount to change in Y per act
     */
    public Ball(int theRadius, Color theColor, 
                int theVelX, int theVelY)
    {
        radius = theRadius;
        color = theColor;
        velX = theVelX;
        velY = theVelY;
        updateImage();
        
    }
    
    ///////////////////// Methods ///////////////////////////////
    
    /**
     * Balls will move and check if they have hit a brick or paddle or
     * one of the edges of the world
     */
    public void act() 
    {
        
        xdireccion= xdireccion + velX;
        ydireccion= ydireccion + velY*1;
        setLocation(xdireccion,ydireccion);
        
        tocaCuadro();
        limites();
        tocaPaleta();
        Ball();
    }
    
    /**
     * Method to set the ball color
     */
    public void setColor(Color theColor)
    {
        this.color = theColor;
        updateImage();
    }
    
    /**
     * Method to create the image and set it for the ball 
     * If you change the ball width or color you should 
     * invoke this again
     */
    public void updateImage()
    {
        GreenfootImage image = new GreenfootImage(width,width);
        image.setColor(color);
        image.fillOval(0,0,width,width);
        setImage(image);
    }
    
    public void tocaCuadro(){
        World mundo = getWorld();
        Brick aux ;
        if(isTouching(Brick.class)){
            removeTouching(Brick.class);
            velY=2;
            setColor(color.BLUE);
        }
        ((BreakoutWorld)mundo).checkIfWon();
    }
    public void tocaPaleta(){
        if(isTouching(Paddle.class))
        {
            velY=-2;
            System.out.println("Toco paleta ");
        }  
    }
    public void limites(){
        if(xdireccion>400){
            velX=-1;
            System.out.println("Toco final x ");
        }
        if(xdireccion<2){
            velX=1;
            System.out.println("Toco principio x ");
        }
        if(ydireccion>500){
            velY=1;
            System.out.println("Toco final y ");
        }
        if(ydireccion<2){
            velY=1;
            System.out.println("toco principio de y ");
            
        }
    }
    
    public void Ball(){
        World mundo = getWorld();
       if(ydireccion>500){
            ((BreakoutWorld)mundo).newBall();
            getWorld().removeObject(this);
        }
    }
   
}
