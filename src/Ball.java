import java.awt.*;
import java.util.*;

public class Ball extends Rectangle{
  
  private Random rand;
  private int xVelocity;
  private int yVelocity;
  private int initialSpeed=4;

  public void setYVelocity(int yVelocity){
    this.yVelocity = yVelocity;
  }

  public int getYVelocity(){
    return this.yVelocity;
  }

  public void setXVelocity(int xVelocity){
    this.xVelocity = xVelocity;
  }

  public int getXVelocity(){
    return this.xVelocity;
  }

  public Ball(int x, int y,int width,int height) {
    super(x,y,width,height);
    rand = new Random();
    int randxDirection = rand.nextInt(2);
    if(randxDirection == 0)
      randxDirection --;
    setXDirection(randxDirection*initialSpeed);

    int randyDirection = rand.nextInt(2);
    if(randyDirection == 0)
      randyDirection --;
    setYDirection(randyDirection*initialSpeed);
    
  }

  public void setYDirection(int randyDirection) {
    yVelocity = randyDirection;
  }

  public void setXDirection(int randxDirection) {
    xVelocity = randxDirection;
  }

  public void move() {
    x += xVelocity;
    y += yVelocity;
  }

  public void draw(Graphics g) {
    g.setColor(Color.WHITE);
    g.fillOval(x,y,width,height);
  }

}
