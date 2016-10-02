/*Hotel Booking System
**Nathan Ko, Feb 18, 2016
**The program asks the user whether they wish to book a single or double room.
*/
import java.io.*;
import java.util.*;
import hsa.Console;
public class Hotel
{

    public static void main (String[] args) throws IOException
    {
	Console cons = new Console (); //input and output console

	String filePath = "Hotel.txt";
	int[] [] rooms = new int [100] [2];
	int typeOfRoom;

	getData (filePath, rooms);
	typeOfRoom = getInput (cons);
	assignRoom (typeOfRoom, rooms, cons);
	//writeToFile (rooms, filePath);
    }


    //get data
    public static void getData (String path, int[] [] array) throws IOException
    {
	BufferedReader br = new BufferedReader (new FileReader (path));
	String line = br.readLine (); //room line
	String numStr; //current digit of each line
	int index = 0; //current room number
	while (line != null)
	{

	    //read the line and store values into array
	    numStr = line.substring (0, 1);
	    array [index] [0] = Integer.parseInt (numStr);
	    numStr = line.substring (2, 3);
	    array [index] [1] = Integer.parseInt (numStr);

	    index++;
	    line = br.readLine ();
	}
	br.close ();
    }


    //get input
    public static int getInput (Console c)
    {
	c.println ("Would you like to book a single [1] or double room [2]?");
	int choice = c.readInt ();
	if (choice == 1) //user wants single room
	{
	    return 1;
	}
	else if (choice == 2) //user wants double room
	{
	    return 2;
	}
	else //bad input
	{
	    c.println ("Invalid input. Try again.");
	    return getInput (c);
	}
    }


    //assign room
    public static void assignRoom (double roomType, int[] [] array, Console c)
    {
	boolean foundRoom = false;
	for (int i = 0 ; i < array.length ; i++)
	{
	    if (array [i] [0] == roomType && array [i] [1] == 0)
	    {
		array [i] [1] = 1; //indicate that the room is booked
		c.println ("You have booked room #" + (100 + i));
		foundRoom = true;
		break;
	    }
	}

	if (!foundRoom)
	{
	    c.println ("All rooms of your desired type have been booked already. Exit the program to try with another room type, or go home.");
	}
    }


    //rewrite file
    public static void writeToFile (int[] [] array, String filePath) throws IOException
    {
	PrintWriter pw = new PrintWriter (new FileWriter ("Hotel.txt"));

	for (int i = 0 ; i < array.length ; i++)
	{
	    pw.println (array [i] [0] + " " + array [i] [1]);

	}
	pw.close ();
    }
}


