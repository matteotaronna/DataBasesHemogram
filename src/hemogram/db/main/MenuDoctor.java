package hemogram.db.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import hemogram.db.pojos.Doctor;
import hemogram.db.pojos.Patient;

public class MenuDoctor {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void doctorMenu()
	{
		try
		{
			while(true)
			{
				Doctor doctor;
				int doctorId= 0;
				System.out.println("1. New Doctor");
				System.out.println("2. Already signed up");
				System.out.println("3. Go back");
				
				int option = Integer.parseInt(reader.readLine());
						
				switch(option)
				{
					case 1:
						doctor = addDoctor();
						//get doctor id
						//we need to pass the doctor to then link the id to the hemogram
						doctorSubmenu(doctor);
						break;
					case 2:
						doctor = logInDoctor();
						// get doctor id
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
	
	public static Doctor addDoctor() throws Exception
	{
		System.out.println("FILL IN YOUR INFO");
		System.out.print("Name: ");
		String doctorName = reader.readLine();
		System.out.print("Surname: ");
		String doctorSurname = reader.readLine();
		System.out.print("Hospital: ");
		String hospital = reader.readLine();
		System.out.print("Specialty: ");
		String specialty = reader.readLine();
		System.out.println("Work_user: ");
		String doctorwork_user= reader.readLine();
		Doctor newDoctor = new Doctor (doctorName, doctorSurname, doctorwork_user, hospital, specialty);
		Menu.doctorManager.insertDoctor(newDoctor);
		return newDoctor;
	}
	
	public static Doctor logInDoctor() throws Exception
	{
		System.out.print("Name: ");
		String doctorName = reader.readLine();
		System.out.print("Work User: ");
		String doctorwork_user = reader.readLine();
		Doctor newDoctor = Menu.doctorManager.logInDoctor(doctorName, doctorwork_user);
		return newDoctor;
	}
	
	public static void doctorSubmenu(Doctor doctor)
	{
		try
		{
			while(true)
			{
				Patient patient;
				System.out.println("Enter the DNI of the patient");
				String patientDNI = reader.readLine();
				patient=Menu.patientManager.searchPatient(patientDNI);
				if(patient!=null)
				{
					//get doctor id 
					//no se muy bien si es asi como lo busca :( ACABAR!!!
				}
			}
		}catch(Exception e)
		{
				e.printStackTrace();
		}
	}
}
