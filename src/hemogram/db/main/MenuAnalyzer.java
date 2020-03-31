package hemogram.db.main;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import hemogram.db.pojos.*;

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
				Doctor doctor = null;
				int patientId = 0;
				int doctorId = 0;
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
						doctor = searchDoctor();
						if (doctor!=null)
						{
							doctorId=doctor.getId();
							//join patient and doctor
							createHemogram(analyzerId,patientId,doctorId);
							break;
						}
						else
						{
							System.out.println("\nTHE DOCTOR SHOULD BE REGISTERED, WAIT UNTILL HE REGISTERS!\n");
							return;
						}
						//create hemogram
					case 2:
						patient = searchPatient();
						patientId = patient.getId();
						//we need to pass the patientId to then link it to the hemogram
						doctor = searchDoctor();
						if (doctor!=null)
						{
							doctorId=doctor.getId();
							//join patient and doctor
							createHemogram(analyzerId,patientId,doctorId);
							break;
						}
						else
						{
							System.out.println("\nTHE DOCTOR SHOULD BE REGISTERED, WAIT UNTILL HE REGISTERS!\n");
							return;
						}
						//create hemogram
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
	
	public static Doctor searchDoctor() throws Exception
	{
		List<Doctor> doctors = Menu.doctorManager.listDoctors();
		System.out.println(doctors);
		System.out.print("Insert the doctor id (if there isn't the doctor put 0): ");
		int doctorId = Integer.parseInt(reader.readLine());
		if(doctorId!=0)
		{
			return Menu.doctorManager.getDoctor(doctorId);
			
		}else return null;
	}
	
	public static void createHemogram (int analyzerId, int patientId, int doctorId) throws Exception
	{
		System.out.print("Insert the date of the hemogram (yyyy-MM-dd): ");
		String date = reader.readLine();
		LocalDate dateL = LocalDate.parse(date, formatter);
		Date hemogramDate = Date.valueOf(dateL);
		System.out.println("\n\nanalyzerID: "+analyzerId+", patientId: "+patientId+", doctorId: "+doctorId+", date: "+hemogramDate);
		Menu.hemogramManager.insertHemogram(hemogramDate, doctorId, patientId, analyzerId);
		
		//inert values
		
		
	}
}

