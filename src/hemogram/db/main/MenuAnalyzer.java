package hemogram.db.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import hemogram.db.pojos.Analyzer;
import hemogram.db.pojos.Patient;

public class MenuAnalyzer {
	
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	
	public static void analyzerMenu()
	{
		try
		{
			while(true)
			{
				Analyzer analyzer = null;
				int analyzerId = 0;
				System.out.println("1. New Analyzer");
				System.out.println("2. Already signed up");
				System.out.println("3. Go back");
				
				int option = Integer.parseInt(reader.readLine());
						
				switch(option)
				{
					case 1:
						analyzer = addAnalyzer();
						analyzerId = Menu.analyzerManager.getAnalyzerId(analyzer);
						//System.out.println(analizerId);
						//we need to pass the analizerId to then link it to the hemogram
						analyzerSubmenu(analyzerId);
						break;
					case 2:
						analyzer=logInAnalyzer();
						do {
						if(analyzer==null)
						{
							System.out.println("Try again, the name or work-user doesn't exist");
							analyzer=logInAnalyzer();
						}
						else
						{
							analyzerId = analyzer.getId();
							analyzerSubmenu(analyzerId);
						}
						} while (analyzer == null);
						//System.out.println(analizerId);
						//we need to pass the analyzer id to then link the id to the hemogram
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

	public static Analyzer addAnalyzer() throws Exception
	{
		System.out.println("FILL IN YOUR INFO");
		System.out.print("Name: ");
		String analyzerName = reader.readLine();
		System.out.print("Surname: ");
		String analyzerSurname = reader.readLine();
		System.out.print("Work User: ");
		String analyzerWorkUser = reader.readLine();
		System.out.print("Hospital: ");
		String analyzerHospital = reader.readLine();
		Analyzer newAnalyzer = new Analyzer(analyzerName, analyzerSurname, analyzerWorkUser, analyzerHospital);
		Menu.analyzerManager.insertAnalyzer(newAnalyzer);
		return newAnalyzer;
	}

	public static Analyzer logInAnalyzer() throws Exception
	{
		System.out.print("Name: ");
		String analyzerName = reader.readLine();
		System.out.print("Work User: ");
		String analyzerWorkUser = reader.readLine();
		Analyzer newAnalyzer = Menu.analyzerManager.logInAnalyzer(analyzerName, analyzerWorkUser);
		return newAnalyzer;
	}

	public static void analyzerSubmenu(int analyzerId)
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
						patientId = Menu.patientManager.getPatientId(patient);
						//we need to pass the patientId to then link it to the hemogram
						//create hemogram
						//listar doctores
						//escoger el doctor que sea (si no existe, no se crea y le decimos que avise al paciente)
						//
						break;
					case 2:
						patient = searchPatient();
						patientId = patient.getId();
						//we need to pass the patientId to then link it to the hemogram
						//create hemogram
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
	
	public static Patient addPatient() throws Exception
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
		Menu.patientManager.signUpPatient(newPatient);
		return newPatient;
	}
	
	
	public static Patient searchPatient() throws Exception
	{
		System.out.print("Patient DNI: ");
		String dni = reader.readLine();
		Patient newPatient = Menu.patientManager.searchPatient(dni);
		return newPatient;
	}
}

