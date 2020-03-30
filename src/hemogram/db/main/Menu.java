package hemogram.db.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import hemogram.db.interfaces.*;
import hemogram.db.jdbc.*;
import hemogram.db.pojos.*;

public class Menu 
{
	public static DBManager dbManager;
	public static AnalyzerManager analyzerManager;
	public static DoctorManager doctorManager;
	public static PatientManager patientManager;
	public static FeaturesManager featuresManager;
	public static HemogramManager hemogramManager;
	public static FeatureValueManager featureValueManager;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
	
	public static void  main(String args[])
	{
		try
		{
			while(true)
			{
				//create and connect with the database
				dbManager = new SQLManager();
				dbManager.connect();
				analyzerManager = dbManager.getAnalyzerManager();
				patientManager = dbManager.getPatientManager();
				doctorManager = dbManager.getDoctorManager();
				featuresManager = dbManager.getFeaturesManager();
				hemogramManager = dbManager.getHemogramManager();
				featureValueManager = dbManager.getFeatureValueManager();
				
				//Create tables
				dbManager.createTables();
				
				//starts program
				//THE DOCTOR SHOULD BE CREATED BEFORE CREATING THE HEMOGRAM
				System.out.println("Who are you?");
				System.out.println("1. Analyzer");
				System.out.println("2. Doctor");
				System.out.println("3. Patient");
				System.out.println("0. Exit");
				
				int option = Integer.parseInt(reader.readLine());
						
				switch(option)
				{
					case 1:
						MenuAnalyzer.analyzerMenu();
						break;
					case 2:
						MenuDoctor.doctorMenu();
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
	
	
	private static void createHemogram(int patientId, int analizerId)
	{
		//listar doctores
		//analizer elige al doctor
		
	}
	
	private static void patientMenu()
	{
		try
		{
			while(true)
			{
				Patient patient;
				System.out.println("Introduce your name");
				String patientname= reader.readLine();
				System.out.println("Introduce your DNI");
				String patientDNI =reader.readLine();
				
				
			}
		}catch(Exception e)
		{
				e.printStackTrace();
		}
	}
}
