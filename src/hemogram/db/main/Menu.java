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
				Patient patient;
				System.out.println("1. New patient");
				System.out.println("2. Search for a patient");
				System.out.println("3. Go back");
				
				int option = Integer.parseInt(reader.readLine());
						
				switch(option)
				{
					case 1:
						patient = addPatient(); 
						break;
					case 2:
						patient = searchPatient();
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
		patientManager.insertPatient(newPatient);
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
		try
		{
			while(true)
			{
				Doctor doctor;
				System.out.println("1. New Doctor");
				System.out.println("2. Already signed up");
				System.out.println("3. Go back");
				
				int option = Integer.parseInt(reader.readLine());
						
				switch(option)
				{
					case 1:
						doctor = addDoctor();
						//we need to pass the doctor to then link the id to the hemogram
						doctorSubmenu(doctor);
						break;
					case 2:
						doctor = signUpDoctor();
						//we need to pass the analizer to then link the id to the hemogram
						doctorSubmenu(doctor);
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
	
	private static Doctor addDoctor() throws Exception
	{
		System.out.println("FILL IN YOUR INFO");
		System.out.print("Name: ");
		String doctorName = reader.readLine();
		System.out.print("Surname: ");
		String doctorSurname = reader.readLine();
		System.out.print("Hospital: ");
		String hospital = reader.readLine();
		System.out.print("Speciality: ");
		String speciality = reader.readLine();
		System.out.println("Work_user: ");
		String doctorwork_user= reader.readLine();
		Doctor newDoctor = new Doctor (doctorName, doctorSurname, doctorwork_user, hospital, speciality);
		doctorManager.insertDoctor(newDoctor);
		return newDoctor;
	}
	private static Doctor signUpDoctor() throws Exception
	{
		System.out.print("Name: ");
		String doctorName = reader.readLine();
		System.out.print("Work User: ");
		String doctorwork_user = reader.readLine();
		Doctor newDoctor = doctorManager.signUpDoctor(doctorName, doctorwork_user);
		return newDoctor;
	}
	private static void doctorSubmenu(Doctor doctor)
	{
		try
		{
			while(true)
			{
				Patient patient;
				System.out.println("Enter the DNI of the patient");
				String patientDNI = reader.readLine();
				patient=patientManager.searchPatient(patientDNI);
				if(patient!=null)
				{
					//no se muy bien si es asi como lo busca :( ACABAR!!!
				}
			}
		}catch(Exception e)
		{
				e.printStackTrace();
		}
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
