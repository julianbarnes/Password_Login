/**Julian Barnes 
  * CSCI 1302-120
  * 03/20/2016
 * */

public class Person4 implements Comparable
{
  private String username;
  private String password;
  private Long StudentID;
  
  public Person4(String User, String Pass, Long ID)
  {
    this.username = User;
    this.password = Pass;
    this.StudentID = ID;
  }
  /**
   * Returns this Person4 object's password.
   * @return password
   **/
  public String getPassword ()
  {
    return this.password;
  }
  /**
   * Returns this Person4 object's username.
   * @return username
   **/
  public String getUsername()
  {
    return this.username;
  }
  /**
   * Returns this Person4 object's student ID.
   * @return StudentID
   **/
  public Long getStudentID()
  {
    return this.StudentID;
  }
  /**
   * Checks the equality of this Person4 object and another Person4 object. If the Person4 objects are not equal then it
   * determines which student ID comes first. 
   * @param temp This object is casted to Person4 object. 
   * @return an integer value of 0 if equal, -1 if student ID comes before other Person4 object, and 1 if student ID
   * comes after other Person4 object. 
   * */
  public int compareTo(Object temp)
  {
    Person4 p = (Person4) temp;
    if(p.getPassword().equals(this.password) && p.getUsername().equals(this.username) && p.getStudentID().equals(this.StudentID))
    { return 0;}
    else if(this.StudentID > p.getStudentID())
    { return 1;}
    else
      return -1;
  }
}
