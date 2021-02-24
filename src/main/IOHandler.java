package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class IOHandler {
	
	public static DriverList MakeDriverList(String pathAndFileName) {
		//Reads in a file containing driver data and creates a Driver obj for each driver returns them
		
		DriverList list  = new DriverList();
		//Create file and scanner objects
		//File f = null;
		//Scanner s = null;
		File f;
		try {
			f = new File(pathAndFileName);
			Scanner s = new Scanner(f);
			//int lineCounter = 1;
			while (s.hasNextLine()) {
				String line = s.nextLine();
				String[] arr = line.split(","); //Seems to automatically split on \n
				//System.out.println(lineCounter);
				for (int i = 0; i < arr.length; i++) {
					arr[i] = arr[i].trim();
					//System.out.println(i+":"+arr[i]+";");
				}
				int number;
				if (!arr[2].isEmpty()) {
					number = Integer.parseInt(arr[2]);
				} else number = 0;				 
				Driver d = new Driver(arr[0], arr[1], number);
				list.Add(d);
				//lineCounter++;
			}
			s.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Error: file "+pathAndFileName+" could not be found");
		} finally {
			
		}
		
		return list;
	}
	
	public static int GetNumberFromUser() {
		//Gets numerical input from user	
			Scanner s = new Scanner(System.in);
			int result = 0;
			while (s.hasNext()) {
				result = s.nextInt();
				break;
			}
			//s.close();  //This line causes infinite loop - why???
			return result;
	}
	
	public static String GetNextLineFromUser() {
		//Gets string input from user	
			Scanner s = new Scanner(System.in);
			String result = "";
			while (s.hasNextLine()) {
				result = s.nextLine();
				break;
			}
			//s.close(); //This line causes infinite loop - why???
			return result;
	}
	
	public static void WriteToFile(String inputText, String pathAndFileName) {
		//Writes inputText to file
		try {
			Scanner scnr = new Scanner(inputText);
			Formatter f1 = new Formatter(pathAndFileName);
			while(scnr.hasNextLine()){
				String nextLine = scnr.nextLine();
				f1.format("%s", nextLine +"\r\n");
				//System.out.println("nextLine: " + nextLine);
			}  
			f1.close(); 
			scnr.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error: File was not found. " + e.getMessage());
		}
	}
	
}

//Rough work
//
//while (s.hasNextLine()) {

	
	
	//find the first comma
	//int firstComma = line.indexOf(line);
	//String name = 

