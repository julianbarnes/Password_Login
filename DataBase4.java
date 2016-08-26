/**Julian Barnes 
  * CSCI 1302-120
  * 03/20/2016
  * */

import java.util.*;
import java.io.*;


public class DataBase4
{
  File inputfile;
  static ArrayList<Person4> database;
  
  public DataBase4(String file) throws FileNotFoundException
  {
    inputfile = new File(file);
    Scanner in = new Scanner(inputfile);
    database = createDictionary(in);
    while(in.hasNext())
    {
      System.out.print(in.next());
    }
  }
  /**Creates an array list of Person objects from a text file.
    * @param s Scanner object from file
    * @return an array list of Person4 entries
    * */
  public ArrayList<Person4> createDictionary(Scanner s)
  {
    ArrayList<Person4> entries = new ArrayList<Person4>();
    int count = 0;
    while(s.hasNextLine())
    {
      String line = s.nextLine();
      String[] split = line.split(",");
      String u = split[0];
      String p = split[1];
      Long i = Long.valueOf(split[2]);
      Person4 entry = new Person4(u,p,i);
      entries.add(entry);
    }
    return entries; 
  }
  /**Uses the Person's compareTo method to check if the user has the same credentials as an entry in the database.
    * @param user that is compared to the entries in database
    * @return true or false for match in database
    * */
  public boolean check(Person4 user)
  {
    int count = 0;
    for( Person4 temp: database)
    {
      if(user.compareTo(temp)==0)
      {
        count++;
      }
    }
    if(count > 0)
    {return true;}
    else
    {return false;}
  }
  /**
   * This is a public helper method for the search method that searches the database by the student ID. It converts the
   * database array list instance variable into an array of persons. This array of persons is then used in the search method. 
   * @param ID This is the ID that is searched throughout the database.
   * @return returns the person object that is returned by the search method
   * */
  public Person4 searchID(long ID)
  {
    Person4[] IDlist = new Person4[database.size()];
    for (int i=0; i<IDlist.length; i++)
    {
      Person4 tempID = database.get(i);
      IDlist[i]= tempID;
    }
    int high = IDlist.length;
    int low = 0;
    
    return search(ID, IDlist,high,low);
  }
  /**
   * This method is private and uses binary search to look for a Person object in the database that matches the given 
   * student ID. 
   * @param ID the method uses binary search to look for an student ID that matches this.
   * @param database the array of persons that is being searched with binary search. 
   * @param high the highest index to be searched in the array
   * @param low the lowest index to be searched in the array
   * @return Person4 object that corresponds to the given student ID. If the username is not found then an error 
   * Person4 object is returned. 
   * */
  private Person4 search(long ID, Person4[] database,int high,int low)
  {
    Arrays.sort(database);
    Person4 error = new Person4("username", "password", Long.valueOf(0));
    if( low > high)
      return error;
    
      int mid = low + (high-low)/2;
      Person4 currentID = database[mid];
      
      if( ID < currentID.getStudentID())
      {
        return search(ID, database, mid-1, low);
      }
      else if(ID > currentID.getStudentID())
      {
        return search(ID, database,high,mid+1);
      }
      else
        return database[mid]; 
 
  }
}



