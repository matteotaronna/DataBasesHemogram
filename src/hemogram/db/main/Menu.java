package hemogram.db.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import hemogram.db.interfaces.*;
import hemogram.db.jdbc.*;

public class Menu 
{
	public static DBManager dbManager;
	public static AnalizerManager analizerManager;
	public static DoctorManager doctorManager;
	public static PatientManager patientManager;
	
	private static BufferedReader reader;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public void initialMenu()
	{
		try
		{
			//create and connect with the database
			dbManager = new SQLManager();
			dbManager.connect();
			analizerManager = dbManager.getAnalizerManager();
			patientManager = dbManager.getPatientManager();
			
			//Create tables
			dbManager.createTables();
			
			//starts program
			System.out.println("Who are you?");
			System.out.println("1. Doctor");
			System.out.println("2. Owner");
			int option = Integer.parseInt(reader.readLine());
					
			switch(option)
			{
				case 1:
					analizerMenu();
					break;
				case 2:
					doctorMenu();
					break;
				case 3:
					patientMenu();
					break;
				default:
					break;
			}
		}catch(Exception e)
		{
				e.printStackTrace();
		}
	}
	
	public void analizerMenu()
	{
		
	}
	public void doctorMenu()
	{
		
	}
	public void patientMenu()
	{
		
	}
}
