import java.awt.Graphics;
import java.awt.Point;

/**
 * Class Rectangle:  inherits from Shape and draws a rectangle
 * @author Barb Ericson
 */
public class Rectangle extends Shape
{
  ///////////////////// Constructors /////////////////////////////
  
  /** No argument constructor */
  public Rectangle()
  {
    super();  // call parent default no-argument constructor
  }
  
  /////////////////////// Public Methods //////////////////////////////
  
  /** Draw the shape
   * @param g   the graphics context on which to draw
   */
  @Override
  public void draw(Graphics g)
  {
    // set the color
    g.setColor(getColor());
    
    // draw the rectangle given the top left point and width and height
    g.drawRect(getMinX(), getMinY(), getWidth(), getHeight());
  }
}


