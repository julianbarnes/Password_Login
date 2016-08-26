/**Julian Barnes 
  * CSCI 1302-120
  * 03/20/2016
 * */

//import javax.swing.JFrame; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.*;
import java.io.*;


public class PasswordView4 extends JFrame 
{
  
  JTextField usernametext,passwordtext,IDtext;
  JLabel usernamelabel, passwordlabel,IDlabel;
  JButton loginbutton, exitbutton,searchbutton;
  String username,password;
    long userID;
    int lockcount;
  static DataBase4 database;
  
  public PasswordView4() throws FileNotFoundException
  { 
    database = new DataBase4("USERDATA2.txt");
    createcomponents(); 
  }
 
  public void createcomponents()
  {
    //Creates main panel
    JPanel panel1 = new JPanel();
    
    //Initializes labels, textfields, and buttons
    usernametext = new JTextField(8);
    passwordtext = new JTextField(8);
    IDtext = new JTextField(8);
    IDlabel = new JLabel("Student ID");
    usernamelabel = new JLabel("Username");
    passwordlabel = new JLabel("Password");
    //searchlabel = new JLabel("Search by ID");
    
    loginbutton = new JButton("Login");
    exitbutton = new JButton("Exit");
    searchbutton = new JButton("Search ID");
    
    //initializes variables
    username = "";
    password = "";
    userID = 0;
    lockcount = 0;
    
    //adds components to panel
    add(panel1);
    panel1.add(usernamelabel);
    panel1.add(usernametext);
    panel1.add(passwordlabel);
    panel1.add(passwordtext);
    panel1.add(IDlabel);
    panel1.add(IDtext);
    
    panel1.add(loginbutton);
    panel1.add(exitbutton);
    panel1.add(searchbutton);
          
    
    class LoginActionListener implements ActionListener
    { 
      /**
       * Activated when login button is pressed. Verifies the login and either accepts it or denies it.
       * @param Actionevent event
       * */
      public void actionPerformed(ActionEvent event)
      {
        username = usernametext.getText();
        if(IDtext.getText().length() > 0)
          userID = Long.valueOf(IDtext.getText());
        else
          userID = 0;
        checkPassword(password);
        Person4 user = new Person4(username,password,userID);
        if (database.check(user))
        {
          JOptionPane.showMessageDialog(null,"Your password and ID have been accepted!");
        }
        
        else
        {
          JOptionPane.showMessageDialog(null,"Your username, password, or ID is incorrect. Please try again");
          lockcount++;
        }
        usernametext.setText("");
        passwordtext.setText("");
        password = "";
        IDtext.setText("");
        if(lockcount >= 3)
        {
          createCounter();
          
        }
      }
    }
    class SearchActionListener implements ActionListener
    {
      /**
       *Invokes the searchID method of the database. If a username is found for the ID, then a message with the 
       * username is shown. If there is no username associated with the ID, a message saying there is no username is
       * shown.
       * @param ActionEvent event Activated when the search button is clicked.
       *        
       * */
      public void actionPerformed(ActionEvent event)
      {
        if(IDtext.getText().length() > 0)
          userID = Long.valueOf(IDtext.getText());
        else
          userID = 0;
        Person4 tempperson = database.searchID(userID);
        if(tempperson.getStudentID() != 0)
          JOptionPane.showMessageDialog(null, "The username for this ID is "+ tempperson.getUsername());
        else
          JOptionPane.showMessageDialog(null, "There is no username for that ID");
      }
    }
    
    class ExitActionListener implements ActionListener
    {
      /**Activates when the exit button is pressed. Exits the application.
        *@param ActionEvent event Activated when the exit button is pressed
        * */
      
      public void actionPerformed(ActionEvent event)
      {
        System.exit(0);
      }
    }
    
    
    class keyCensor implements KeyListener
    {
      /**Activates when a key is pressed. When the back space is pressed the last character added to the password
        * is erased. 
        * @param KeyEvent event Activated when a key is pressed.
        * */
      public void keyPressed(KeyEvent event)
      {
        if(event.getKeyCode() == KeyEvent.VK_BACK_SPACE)
        {
          if(password.length() > 0)
            password = password.substring(0,password.length()-1);
        }
      }
      public void keyTyped(KeyEvent event)
      {}
      /**Activates when a key is released. When a key is released in the password textfield it is stored in the password
       * string and replaced by an asterick. 
       * @param Keyevent event
       * */
      public void keyReleased(KeyEvent event)
      {
        String text = passwordtext.getText();
        int length = text.length();
        for(int k=0;k<length;k++){
          if(text.charAt(k)!='*'){
            password = password + Character.toString(text.charAt(k));
          }
        }  
        String replace = "";
        for(int i=0; i<length; i++)
          replace = replace + "*";
        passwordtext.setText(replace);
      }
    }
    //Initializes ActionListeners
    ActionListener listener = new LoginActionListener();
    keyCensor censor = new keyCensor();
    ActionListener exitlistener = new ExitActionListener();
    SearchActionListener searchlistener = new SearchActionListener();
    
    //Adds ActionListeners to the components
    passwordtext.addKeyListener(censor);
    loginbutton.addActionListener(listener);
    exitbutton.addActionListener(exitlistener);
    searchbutton.addActionListener(searchlistener);
    
  }
  /**Creates a CounterFrame when login is attempted three times.
    * */
  public void createCounter()
  {
    CounterFrame lockframe = new CounterFrame(this);
  }
   /**Checks to see if the password has at least one uppercase, one number, and ten characters. It also checks to see if
    * the password has no blanks or commas. If the conditions are not met a message is displayed. 
    * @param p This is the password being checked  
   * */
  public void checkPassword(String p)
  {
    String message = "";
    if(p.length() < 10)
    {
      message = message + "Your password must have at least 10 characters\n";
    }
    int uppercases = 0;
    int numbers = 0;
    int blanks = 0;
    int commas = 0;
    for(int i=0; i<p.length(); i++)
    {
      
      if(Character.isUpperCase(p.charAt(i)))
      {
        uppercases++;
      }
      if(Character.isDigit(p.charAt(i)))
      {
        numbers++;
      }
      if(p.charAt(i)==' ')
      {
        blanks++;
      }
      if(p.charAt(i)==',')
      {
        commas++;
      }
    }
    if(uppercases < 1)
    {
      message = message + "Your password must have at least one uppercase character\n";
    }
    if(numbers < 1)
    {
      message = message + "Your password must have at least one number\n";
    }
    if(blanks > 0)
    {
      message = message + "Your password cannot contain any blanks\n";
    }
    if(commas > 1)
    {
      message = message + "Your password cannot contain any commas\n";
    }
    if(message!=""){
    JOptionPane.showMessageDialog(null,message);
    }
  }
}
  
    
    
    
    
    
    