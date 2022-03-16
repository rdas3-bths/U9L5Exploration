import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;

/**
 * Class ShapeIComponent:  holds shapes in a custom drawn area and handles the shape interface
 * Copyright Georgia Institute of Technology 2007
 * @author Barb Ericson ericson@cc.gatech.edu
 */
public class ShapeIComponent extends JComponent implements ShapeInterface
{
  ///////////////// Private Attributes ////////////////////////
  
  private java.util.List<Shape> shapes = new ArrayList<Shape>(); // a list of shapes
  private Shape currentShape = null; // current shape being dragged
  private String currShapeType = Shape.RECTANGLE; // default shape type
  private int width = 800;
  private int height = 600;
  private Color backgroundColor = Color.YELLOW;
  private Graphics backgroundG = null;    // graphics context of background image
  
  /////////////////// Constructors /////////////////////////////
  
  /** A constructor that uses the default size */
  public ShapeIComponent ()
  {
    init();
  }
  
  /**
   * A constructor that takes the width and height
   * @param width   the width of the canvas
   * @param height  the height of the canvas
   */
  public ShapeIComponent(int width, int height)
  {
    // set the local variables
    this.width = width;
    this.height = height;
    
    init();
  }
  
  ////////////////////// Private Methods ///////////////////////////
  
  /* Method to initialize the shape component size and set the mouse listeners*/
  private void init()
  {
    // set the size of the component to the current width and height
    setSize(new Dimension(width, height));
    setMinimumSize(new Dimension(width, height));
    setPreferredSize(new Dimension(width, height));
    
    // add the mouse listener and mouse motion listener
    addMouseListener(new MyMouseAdapter());
    addMouseMotionListener(new MyMouseMotionAdapter());
    
    // add a component listener
    addComponentListener(new ComponentAdapter()
    {
      public void componentResized(ComponentEvent e)
      {
        //backgroundBuffer = null;
      }
    });
  }
  
  ///////////////////////// Public Methods ///////////////////////////////
  /**
   * Method to add a shape to the shape list
   * @param shape   the shape to add
   */
  public void add(Shape shape)
  {
    // add the shape to the list of shapes
    shapes.add(shape);
    
    // force a repaint to show the new shape
    repaint();
  }
  
  /**
   * Method to remove a shape from the shape list
   * @param shape   the shape to remove
   */
  public void remove(Shape shape)
  {
    // remove the shape from the list of shapes
    shapes.remove(shape); // removes first one
    
    // force a repaint to show that it is gone
    repaint();
  }
  
  /**
   * Method to remove a shape given the index
   * @param index   the index of the shape in the shape list that you
   *                wish to remove
   */
  public void remove(int index)
  {
    // remove the shape at the given index
    shapes.remove(index);
    
    // force a repaint to show it is gone
    repaint();
  }
  
  /**
   * Update normally clears the background and calls paint
   * override it here to just call paint
   * @param g   the graphics context on which to draw
   */
  public void update(Graphics g)
  {
    paint(g);
  }
  
  /**
   * Method to paint the shape canvas and all objects in it
   * @param g   the graphic context on which to paint
   */
  public void paintComponent (Graphics g)
  {
    Shape currShape;
    height = this.getHeight();
    int width = this.getWidth();
    
    // clear using the background color
    g.setColor(backgroundColor);
    g.fillRect(0, 0, width, height);
     
    // loop through the shape objects and draw each one
    for (int i = 0; i < shapes.size(); i++)
    {
      currShape = (Shape) shapes.get(i);
      currShape.draw(g);
    }
  }
  
  /**
   * Set the type of the shape that will be created when the user
   * clicks in the shape canvas.
   * @param shapeType the name of the shape
   */
  public void setShape(String shapeType)
  {
    currShapeType = shapeType;
  }
  
  /** method to move all shapes from the list of shapes
    */
  public void clearShapes()
  {
    shapes.clear();
    repaint();
  }
  
  /////////////////// Inner Classes ///////////////////////////
  
  /** An inner class for handling the mouse listener interface */
  class MyMouseAdapter extends MouseAdapter
  {
    /** Method to handle when the user presses down the button */
    public void mousePressed(MouseEvent e)
    {
      int currX = e.getX();
      int currY = e.getY();
      
      // create an object of the current shape type
      try {
        if (currShapeType == Shape.RECTANGLE)
        {
          currentShape = new Rectangle();
        }
        else if (currShapeType == Shape.OVAL)
        {
          currentShape = new Oval();
        }
      } catch (Exception ex) {
        System.err.println("Problem in creating a shape");
        ex.printStackTrace();
        System.exit(1);
      }
      
      // fill in point1 and point2 in the new shape
      currentShape.setPoint1Values(currX,currY);
      currentShape.setPoint2Values(currX + 1, currY + 1);
      
      // add the shape to the list of shapes
      add(currentShape);
      
      // repaint all
      repaint();
    }
    
    /** Method to handle when the user releases the mouse */
    public void mouseReleased(MouseEvent e)
    {
      int currX = e.getX();
      int currY = e.getY();
      
      // update the the point 2 values
      currentShape.setPoint2Values(currX, currY);
      
      // no current shape being dragged
      currentShape = null;
      
      // repaint
      repaint();
    }
  }
  
  /** An inner class for handling the mouse motion listener */
  class MyMouseMotionAdapter extends MouseMotionAdapter
  {
    /** Method to handle the drag of a mouse */
    public void mouseDragged(MouseEvent e)
    {
      int currX = e.getX();
      int currY = e.getY();
      
      // set the point 2 values
      currentShape.setPoint2Values(currX, currY);
      
      // repaint
      repaint();
    }
  }
}


