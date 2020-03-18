package hemogram.db.main;

import java.io.BufferedReader;
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
	private static BufferedReader reader;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public static void  main(String args[])
	{
		try
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
	
	private static void analizerMenu()
	{
		int analizerId;
		try
		{
			System.out.println("1. New Analizer");
			System.out.println("2. Already signed up");
			
			int option = Integer.parseInt(reader.readLine());
					
			switch(option)
			{
				case 1:
					Analizer analizer = addAnalizer();
					//now we need to get the analizer id to link it to the patient
					analizerId = analizerManager.getAnalizerId(analizer);
					break;
				case 2:
					Analizer analizer2 = signUpAnalizer();
					//now we need to get the analizer id to link it to the patient
					analizerId = analizerManager.getAnalizerId(analizer2);
					break;
				default:
					break;
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
	
	private static void doctorMenu()
	{
		
	}
	private static void patientMenu()
	{
		
	}
}
