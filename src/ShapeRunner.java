import javax.swing.JFrame;
import java.awt.Frame;

public class ShapeRunner
{
  public static void main (String argv[])
  {
    // create a frame (main application window)
    Frame frame = new JFrame();
    
    // create a Shape Panel
    ShapePanel shapePanel = new ShapePanel();
    
    // add the shapePanel to the frame
    frame.add(shapePanel);
    frame.pack();         // shrink to fit preferred size
    frame.setVisible(true); // show the frame
  }
}