package hemogram.db.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import hemogram.db.interfaces.*;
import hemogram.db.jdbc.*;
import hemogram.db.jpa.JPAUserManager;
import hemogram.db.pojos.users.Role;

public class Menu 
{
	public static DBManager dbManager;
	public static AnalyzerManager analyzerManager;
	public static DoctorManager doctorManager;
	public static PatientManager patientManager;
	public static FeaturesManager featuresManager;
	public static HemogramManager hemogramManager;
	public static FeatureValueManager featureValueManager;
	public static UserManager usersManager;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
	
	public static void  main(String args[])
	{
		try
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
				usersManager = new JPAUserManager();
				usersManager.connect();
				Role role = new Role("doctor");
				usersManager.createRole(role);
				Role role2 = new Role("analyzer");
				usersManager.createRole(role2);
				Role role3 = new Role("patient");
				usersManager.createRole(role3);
				
				while(true) {
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
						MenuPatient.patientMenu();
						break;
					case 0: 
						dbManager.disconnect();
						usersManager.disconnect();
						System.out.println("Thank you for using our program! Have a good day :D");
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
}
