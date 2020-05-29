package hemogram.db.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import hemogram.db.pojos.Doctor;
import hemogram.db.pojos.FeatureValue;
import hemogram.db.pojos.Patient;
import hemogram.db.pojos.users.Role;
import hemogram.db.pojos.users.User;
import hemogram.db.pojos.Hemogram;

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
				System.out.println("DOCTOR");
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
		Doctor doctor = null;
		System.out.println("LOG IN:"); 
		System.out.print("Username (Name): ");
		String doctorname = reader.readLine();
		System.out.print("Password (Work User): ");
		String doctorwork_user = reader.readLine();
		User user= Menu.usersManager.checkPassword(doctorname, doctorwork_user);
		if(user ==null)
		{
			System.out.println("The username or password doesn't exist, please try again. ");
		}
		else //Sign in
		{
			doctor = Menu.doctorManager.logInDoctor(doctorname, doctorwork_user);
		}
		return doctor;
	}
	
	public static void doctorSubmenu(Doctor doctor)
	{
		try
		{
			while(true)
			{
				Patient patient= null;
				List<Patient> doctorpatientList = new ArrayList <Patient>();
				System.out.println("DOCTOR");
				System.out.println("1. List all your patients");
				System.out.println("2. Search for a patient");
				System.out.println("3. Go back");
				
				int option = Integer.parseInt(reader.readLine());
				
				switch (option)
				{
				case 1: 
					doctorpatientList = Menu.patientManager.listPatients(doctor.getId()); //acabar de hacer la funcion
					if(doctorpatientList== null)
					{
						System.out.println("You don't have any patient yet");
					}
					else
					{
						for(Patient doctor_patient : doctorpatientList)
						{
							System.out.println("DNI: " + doctor_patient.getDni() + " , Name: " + doctor_patient.getName() + " , Surname: " + doctor_patient.getSurname() + " , Dob: " + doctor_patient.getDob() + " , ID: " + doctor_patient.getId());
						}
						System.out.println("To see a patient's hemogram, you need to search for that specific patient");
					}
					break; 
				case 2: 
					System.out.println("Introduce the patient DNI: ");
					patient = searchPatient(); //debe buscarlo solo dentro de sus pacientes (igual hay que crear otra funcion)
					do
					{
						if (patient ==null)
						{
							System.out.println("Try again, doesn't exist any of your patients with that DNI");
							System.out.println("Introduce the DNI of your patient again");
							patient= searchPatient();
						}
					}while (patient== null);
					doctorsubmenu_patient(doctor, patient);
					//nos lleva al menu donde vemos que quiere hacer el doctor con ese paciente
					
					break;
				
				case 3 : 
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
	public static Patient searchPatient () throws Exception
	{
		System.out.println("SEARCH A PATIENT: ");
		System.out.println("Introduce the Patient's DNI: ");
		String DNI = reader.readLine();
		Patient searchedpatient = Menu.patientManager.searchPatient(DNI);
		return searchedpatient;
	}
	public static void doctorsubmenu_patient(Doctor doctor, Patient patient)
	{
		try
		{
			while(true)
			{
				List<Hemogram> hemogramList = new ArrayList<Hemogram>();
				List<FeatureValue> featureValueList = new ArrayList<FeatureValue>();
				    System.out.println("DOCTOR");
					System.out.println("1. Show his hemograms normally");
					System.out.println("2. Generate XML");
					System.out.println("3. Go back");
			
					int option = Integer.parseInt(reader.readLine());
			
					switch (option)
					{
						case 1: 
						
							hemogramList = Menu.hemogramManager.listHemogramDoctor(patient.getId(), doctor.getId());
							patient.setHemograms(hemogramList);
							System.out.println(patient.getName() + " " + patient.getSurname());
							if(hemogramList ==null)
							{
								System.out.println("The patient don't have any hemogram done yet");
								break;
							}
							else
							{
								for(Hemogram hemogram : hemogramList)
								{
									System.out.println("ID: " + hemogram.getId() + ", Date: " + hemogram.getDob() + ", Comments: " + hemogram.getComments());
								}
							}
							System.out.println("Select the ID of the Hemogram you want to see: ");
							int ID = Integer.parseInt(reader.readLine());
							Hemogram hemogramPatient = Menu.hemogramManager.getHemogram(ID);
							if (hemogramPatient ==null)
							{
								System.out.println("That hemogram Id doesn't exist");
							}
							else
							{
								if(patient.getId() == hemogramPatient.getPatient().getId())
								{
									featureValueList = Menu.featureValueManager.getFeatureValuesByHemogram(ID);
									if(featureValueList != null) 
									{
										for (FeatureValue featureValue : featureValueList)
										{
											System.out.println("Name: " + featureValue.getFeature().getName() + ", VALUE: " + featureValue.getValue() + 
														", HEALTHY: " + featureValue.getHealthy()+", [MIN: " + String.format("%.2f", featureValue.getFeature().getMinimum()) + 
														", MAX: " + String.format("%.2f", featureValue.getFeature().getMaximum())+"]");
										}
									}
									else
									{
										System.out.println("This hemogram is empty");
										break;
									}
								}
								System.out.println("Do you want to introduce any comments, please introduce YES/NO");
								String respuesta = reader.readLine();
								if(respuesta.equalsIgnoreCase("YES"))
								{
									String finalcomment;
									System.out.println("You can introduce your comments");
									if(hemogramPatient.getComments()==null) 
									{
										finalcomment= reader.readLine();
										//hemogramPatient.setComments(comment);
									}
									else//we don't want to lose the other comments
									{
										String comments= reader.readLine();
										finalcomment = hemogramPatient.getComments() + "\n" + comments; 
										///////////a pesar de ser un String se guarda con el retorno de carro?
										//hemogramPatient.setComments(finalcomment);
									}
									Menu.hemogramManager.updatecomment(hemogramPatient.getId(), finalcomment);
								}
								else
								{
									break;
								}
							}
							break;
				
				case 2: 
					generateXML(patient.getId());
					break;
					
				case 3 : 
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
	
	private static void generateXML(int patientId) throws Exception {
		Patient patient = Menu.patientManager.getPatient(patientId);
		List<Hemogram> hemograms =Menu.hemogramManager.listHemogramPatient(patient.getId());
		for (Hemogram hemogram : hemograms) 
		{
			List<FeatureValue> featureValues = Menu.featureValueManager.getFeatureValuesByHemogram(hemogram.getId());
			hemogram.setFeatureValues(featureValues);
		}
		patient.setHemograms(hemograms);
		JAXBContext context = JAXBContext.newInstance(Patient.class);
		// Get the marshaller
		Marshaller marshall = context.createMarshaller();
		// Pretty formatting
		marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// Marshall the dog to a file
		File file = new File("./xmls/Output-Patient.xml");
		marshall.marshal(patient, file);
		// Marshall the dog to the screen
		marshall.marshal(patient, System.out);
	}
	
}
