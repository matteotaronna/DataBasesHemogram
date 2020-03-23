package hemogram.db.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;

import hemogram.db.interfaces.*;
import hemogram.db.jdbc.*;
import hemogram.db.pojos.*;

public class Menu 
{
	public static DBManager dbManager;
	public static AnalizerManager analizerManager;
	public static DoctorManager doctorManager;
	public static PatientManager patientManager;
	public static FeaturesManager featuresManager;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public static void  main(String args[])
	{
		try
		{
			while(true)
			{
				//create and connect with the database
				dbManager = new SQLManager();
				dbManager.connect();
				analizerManager = dbManager.getAnalizerManager();
				patientManager = dbManager.getPatientManager();
				doctorManager = dbManager.getDoctorManager();
				featuresManager = dbManager.getFeaturesManager();
				
				//Create tables
				dbManager.createTables();
				
				//starts program
				System.out.println("Who are you?");
				System.out.println("1. Analizer");
				System.out.println("2. Doctor");
				System.out.println("3. Patient");
				System.out.println("0. Exit");
				
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
					case 0: 
						dbManager.disconnect();
						System.exit(0);
						break;
					default:
						break;
				}
			}
		}catch(Exception e)
		{
				e.printStackTrace();
		}
	}
	
	private static void analizerMenu()
	{
		try
		{
			while(true)
			{
				Analizer analizer;
				System.out.println("1. New Analizer");
				System.out.println("2. Already signed up");
				System.out.println("3. Go back");
				
				int option = Integer.parseInt(reader.readLine());
						
				switch(option)
				{
					case 1:
						analizer = addAnalizer();
						//we need to pass the analizer to then link the id to the hemogram
						analizerSubmenu(analizer);
						break;
					case 2:
						analizer = signUpAnalizer();
						//we need to pass the analizer to then link the id to the hemogram
						analizerSubmenu(analizer);
						break;
					case 3:
						return;
					default:
						break;
				}
			}
		}catch(Exception e)
		{
				e.printStackTrace();
		}
	}
	
	private static Analizer addAnalizer() throws Exception
	{
		System.out.println("FILL IN YOUR INFO");
		System.out.print("Name: ");
		String analizerName = reader.readLine();
		System.out.print("Surname: ");
		String analizerSurname = reader.readLine();
		System.out.print("Work User: ");
		String analizerWorkUser = reader.readLine();
		System.out.print("Hospital: ");
		String analizerHospital = reader.readLine();
		Analizer newAnalizer = new Analizer(analizerName, analizerSurname, analizerWorkUser, analizerHospital);
		analizerManager.insertAnalizer(newAnalizer);
		return newAnalizer;
	}
	
	private static Analizer signUpAnalizer() throws Exception
	{
		System.out.print("Name: ");
		String analizerName = reader.readLine();
		System.out.print("Work User: ");
		String analizerWorkUser = reader.readLine();
		Analizer newAnalizer = analizerManager.signUpAnalizer(analizerName, analizerWorkUser);
		return newAnalizer;
	}
	
	private static void analizerSubmenu(Analizer analizer)
	{
		try
		{
			while(true)
			{
				System.out.println("1. New patient");
				System.out.println("2. Search for a patient");
				System.out.println("3. Go back");
				
				int option = Integer.parseInt(reader.readLine());
						
				switch(option)
				{
					case 1:
						
						break;
					case 2:
						
						break;
					case 3:
						
						return;
					default:
						break;
				}
			}
		}catch(Exception e)
		{
				e.printStackTrace();
		}
	}
	
	
	
	private static void doctorMenu()
	{
		
	}
	
	
	private static void patientMenu()
	{
		
	}
}
