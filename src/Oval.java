import java.awt.Graphics;
import java.awt.Point;

/**
 * Class Oval:  inherits from Shape and handles oval shapes
 * @author Barb Ericson
 */
public class Oval extends Shape
{
  ///////////////////// Constructors ///////////////////////////////
  
  /** No argument constructor */
  public Oval()
  {
    super();  // call parent default no-argument constructor
  }
  
  //////////////////////// Public Methods //////////////////////////////
  
  /**
   * Draw the shape
   * @param g   the graphics context to draw to
   */
  @Override
  public void draw(Graphics g)
  {
    // set the color to draw the shape in
    g.setColor(getColor());
    
    // draw the shape given the top left corner of the enclosing
    // rectangle and the width and height
    g.drawOval(getMinX(), getMinY(), getWidth(), getHeight());
  }
}


