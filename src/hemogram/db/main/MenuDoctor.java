package hemogram.db.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;

import hemogram.db.pojos.Doctor;
import hemogram.db.pojos.Patient;
import hemogram.db.pojos.users.Role;
import hemogram.db.pojos.users.User;

public class MenuDoctor {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void doctorMenu()
	{
		try
		{
			while(true)
			{
				Doctor doctor = null;
				int doctorId= 0;
				System.out.println("1. New Doctor");
				System.out.println("2. Already signed up");
				System.out.println("3. Go back");
				
				int option = Integer.parseInt(reader.readLine());
						
				switch(option)
				{
					case 1:
						doctor = addDoctor();
						doctorId= Menu.dbManager.getLastId();
						doctor.setId(doctorId);
						doctorSubmenu(doctor);
						break;
					case 2:
						doctor = logInDoctor();
						do 
						{
							if(doctor ==null)
							{
								System.out.println("Try again, the name or work-user does not exist");
								doctor = logInDoctor();
							}
						}while(doctor== null);
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
		//add a new doctor
		System.out.println("FILL IN YOUR INFO");
		System.out.print("Name (Username): ");
		String doctorName = reader.readLine();
		System.out.print("Surname: ");
		String doctorSurname = reader.readLine();
		System.out.println("Work_user: ");
		String doctorwork_user= reader.readLine();
		System.out.print("Hospital: ");
		String hospital = reader.readLine();
		System.out.print("Specialty: ");
		String specialty = reader.readLine();
		
		Doctor newDoctor = new Doctor (doctorName, doctorSurname, doctorwork_user, hospital, specialty);
		Menu.doctorManager.insertDoctor(newDoctor);
		
		//create the user
		String username = doctorName;
		String password = doctorwork_user;
		//Create the password's hash
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] hash = md.digest();
		
		//get the role from the database (it is going to be a doctor)
		Role role = Menu.usersManager.getRoleByName("doctor");
		//Create the user and store it
		User user = new User (username,hash, role);
		Menu.usersManager.createUser(user);
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
