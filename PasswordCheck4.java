/**Julian Barnes 
  * CSCI 1302-120
  * 03/20/2016
 * */


import java.io.*;
import java.awt.*;
import javax.swing.*;

public class PasswordCheck4
{
  /**
   * This is the main method that creates a new frame for the GUI. In this method the visibility, title, size, and 
   * closing operation are set.
   * @param args
   * */
  public static void main(String[] args) throws FileNotFoundException
  {
    
    PasswordView4 frame = new PasswordView4();
    frame.setVisible(true);
    frame.setTitle("Login");
    frame.setSize(500,180);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //CounterFrame frame2 = new CounterFrame(frame);  
  }
}
  
  