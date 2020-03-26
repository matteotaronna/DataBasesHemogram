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
	public static AnalizerManager analizerManager;
	public static DoctorManager doctorManager;
	public static PatientManager patientManager;
	public static FeaturesManager featuresManager;
	public static HemogramManager hemogramManager;
	public static FeatureValueManager featureValueManager;
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
				hemogramManager = dbManager.getHemogramManager();
				featureValueManager = dbManager.getFeatureValueManager();
				
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
				Analizer analizer = null;
				int analizerId = 0;
				System.out.println("1. New Analizer");
				System.out.println("2. Already signed up");
				System.out.println("3. Go back");
				
				int option = Integer.parseInt(reader.readLine());
						
				switch(option)
				{
					case 1:
						analizer = addAnalizer();
						analizerId = analizerManager.getAnalizerId(analizer);
						//System.out.println(analizerId);
						//we need to pass the analizerId to then link it to the hemogram
						analizerSubmenu(analizerId);
						break;
					case 2:
						analizer = logInAnalizer();
						analizerId = analizer.getId();
						//System.out.println(analizerId);
						//we need to pass the analizer id to then link the id to the hemogram
						analizerSubmenu(analizerId);
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
	
	private static Analizer logInAnalizer() throws Exception
	{
		System.out.print("Name: ");
		String analizerName = reader.readLine();
		System.out.print("Work User: ");
		String analizerWorkUser = reader.readLine();
		Analizer newAnalizer = analizerManager.logInAnalizer(analizerName, analizerWorkUser);
		return newAnalizer;
	}
	
	private static void analizerSubmenu(int analizerId)
	{
		try
		{
			while(true)
			{
				Patient patient = null;
				int patientId = 0;
				System.out.println("1. New patient");
				System.out.println("2. Search for a patient");
				System.out.println("3. Go back");
				
				int option = Integer.parseInt(reader.readLine());
						
				switch(option)
				{
					case 1:
						patient = addPatient();
						patientId = patientManager.getPatientId(patient);
						//we need to pass the patientId to then link it to the hemogram
						break;
					case 2:
						patient = searchPatient();
						patientId = patient.getId();
						//we need to pass the patientId to then link it to the hemogram
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
	
	private static Patient addPatient() throws Exception
	{
		System.out.println("FILL IN YOUR INFO");
		System.out.print("Name: ");
		String patientName = reader.readLine();
		System.out.print("Surname: ");
		String patientSurname = reader.readLine();
		System.out.print("DNI: ");
		String DNI = reader.readLine();
		System.out.print("Date of Birth (yyyy-MM-dd): ");
		String dob = reader.readLine();
		LocalDate dobDate = LocalDate.parse(dob, formatter);
		Date dobDateP = Date.valueOf(dobDate);
		Patient newPatient = new Patient(patientName, patientSurname, dobDateP, DNI);
		patientManager.signUpPatient(newPatient);
		return newPatient;
	}
	
	
	private static Patient searchPatient() throws Exception
	{
		System.out.print("Patient DNI: ");
		String dni = reader.readLine();
		Patient newPatient = patientManager.searchPatient(dni);
		return newPatient;
	}
	
	
	private static void doctorMenu()
	{
		
	}
	
	
	private static void patientMenu()
	{
		
	}
}
