/**Julian Barnes 
* CSCI 1302-120
* 03/20/2016
* */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CounterFrame extends JFrame
{
  private final int LOCKOUT = 15;
  private int time = 0;
  private int clock = 0;
  
  private JLabel warning,countdown;
  private JPanel panel1;
  private Timer locktime;
  private JFrame parent;
    
  public CounterFrame(JFrame parent)
  {
    this.parent = parent;
    this.parent.setVisible(false);
    setVisible(true);
    setTitle("ACCESS DENIED");
    setSize(570,110);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    
    warning = new JLabel("You have incorrectly attempted to login 3 times.\n You will not be allowed to login for 15 seconds.");
    
    TimeListener listener = new TimeListener();
    locktime = new Timer(1000, listener);
    
    countdown = new JLabel("You have 15 seconds before you can login");
    panel1 = new JPanel();
    add(panel1);
    panel1.setLayout(new BorderLayout());
    panel1.add(warning, BorderLayout.NORTH);
    panel1.add(countdown,BorderLayout.CENTER);
    if(time==0){
      locktime.start();
    }
    
    
  }
  
  private class TimeListener implements ActionListener
  {
    /**Increments the countdown timer for the Lockout window and disables the login frame. When the timer runs out, 
      * the login frame is reenabled. 
     * @param ActionEvent event 
     * 
     * */
    public void actionPerformed(ActionEvent event)
    {
      time = time+1;
      clock = LOCKOUT - time;
      countdown.setText("You have "+clock+" seconds before you can login");
      
      
      if(time == LOCKOUT){
        parent.setVisible(true); 
        dispose();
      }
    }                      
  }
}
    
    
    
    
  
  