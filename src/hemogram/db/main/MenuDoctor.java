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
import hemogram.db.xml.reports.Xml2Html;
import hemogram.db.pojos.Hemogram;

public class MenuDoctor {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void doctorMenu() {
		try {
			while (true) {
				Doctor doctor = null;
				int doctorId = 0;
				int option = 0;
				boolean correctOption = false;
				do {
					System.out.println("\nDOCTOR\n");
					System.out.println("1. New Doctor");
					System.out.println("2. Already signed up");
					System.out.println("3. Go back");
					System.out.print("Select an option: ");

					try {
						option = Integer.parseInt(reader.readLine());
						correctOption = true;
					} catch (NumberFormatException e) {
						System.out.println("Insert an integer please;");
					}
				} while (correctOption == false);

				switch (option) {
				case 1:
					doctor = addDoctor();
					doctorId = Menu.dbManager.getLastId();
					doctor.setId(doctorId);
					doctorSubmenu(doctor);
					break;
				case 2:
					doctor = logInDoctor();
					do {
						if (doctor == null) {
							System.out.println("Try again, the name or work-user does not exist");
							doctor = logInDoctor();
						}
					} while (doctor == null);
					doctorSubmenu(doctor);
					break;
				case 3:
					return;
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Doctor addDoctor() throws Exception {
		// add a new doctor
		System.out.println("\nFILL IN YOUR INFO");
		System.out.print("Name (Username): ");
		String doctorName = reader.readLine();
		System.out.print("Surname: ");
		String doctorSurname = reader.readLine();
		System.out.print("Work_user (password): ");
		String doctorwork_user = reader.readLine();
		System.out.print("Hospital: ");
		String hospital = reader.readLine();
		System.out.print("Specialty: ");
		String specialty = reader.readLine();

		Doctor newDoctor = new Doctor(doctorName, doctorSurname, doctorwork_user, hospital, specialty);
		Menu.doctorManager.insertDoctor(newDoctor);

		// create the user
		String username = doctorName;
		String password = doctorwork_user;
		// Create the password's hash
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] hash = md.digest();

		// get the role from the database (it is going to be a doctor)
		Role role = Menu.usersManager.getRoleByName("doctor");
		// Create the user and store it
		User user = new User(username, hash, role);
		Menu.usersManager.createUser(user);

		System.out.println("Records inserted.");

		return newDoctor;
	}

	public static Doctor logInDoctor() throws Exception {
		Doctor doctor = null;
		System.out.println("\nLOG IN:");
		System.out.print("Username (Name): ");
		String doctorname = reader.readLine();
		System.out.print("Password (Work User): ");
		String doctorwork_user = reader.readLine();
		User user = Menu.usersManager.checkPassword(doctorname, doctorwork_user);
		if (user == null) {
			System.out.println("The username or password doesn't exist, please try again. ");
		} else // Sign in
		{
			doctor = Menu.doctorManager.logInDoctor(doctorname, doctorwork_user);
		}
		return doctor;
	}

	public static void doctorSubmenu(Doctor doctor) {
		try {
			while (true) {
				Patient patient = null;
				List<Patient> doctorpatientList = new ArrayList<Patient>();
				int option = 0;
				boolean correctOption = false;
				do {
					System.out.println("\nDOCTOR\n");
					System.out.println("1. List all your patients");
					System.out.println("2. Search for a patient");
					System.out.println("3. Go back");
					System.out.print("Select an option: ");

					try {
						option = Integer.parseInt(reader.readLine());
						correctOption = true;
					} catch (NumberFormatException e) {
						System.out.println("Insert an integer please;");
					}
				} while (correctOption == false);

				switch (option) {
				case 1:
					doctorpatientList = Menu.patientManager.listPatients(doctor.getId());
					if (doctorpatientList == null) {
						System.out.println("You don't have any patient yet");
					} else {
						System.out.println("\nPatients list:");
						for (Patient doctor_patient : doctorpatientList) {
							System.out.println("DNI: " + doctor_patient.getDni() + " , Name: "
									+ doctor_patient.getName() + " , Surname: " + doctor_patient.getSurname());
						}
						System.out.println(
								"To see a patient's hemogram, you need to search for that specific patient (option 2)");
					}
					break;
				case 2:
					patient = searchPatient();
					do {
						if (patient == null) {
							System.out.println("Try again, doesn't exist any of your patients with that DNI");
							System.out.println("Introduce the DNI of your patient again");
							patient = searchPatient();
						}
					} while (patient == null);
					doctorsubmenu_patient(doctor, patient);

					break;

				case 3:
					return;
				default:
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Patient searchPatient() throws Exception {
		System.out.println("\nSEARCH A PATIENT ");
		System.out.print("Introduce the Patient's DNI: ");
		String DNI = reader.readLine();
		Patient searchedpatient = Menu.patientManager.searchPatient(DNI);
		return searchedpatient;
	}

	public static void doctorsubmenu_patient(Doctor doctor, Patient patient) {
		try {
			while (true) {
				List<Hemogram> hemogramList = new ArrayList<Hemogram>();
				List<FeatureValue> featureValueList = new ArrayList<FeatureValue>();
				int option = 0;
				boolean correctOption = false;
				do {
					System.out.println("\nDOCTOR\n");
					System.out.println("1. Show his hemograms normally");
					System.out.println("2. Generate XML");
					System.out.println("3. Go back");
					System.out.print("Select an option: ");

					try {
						option = Integer.parseInt(reader.readLine());
						correctOption = true;
					} catch (NumberFormatException e) {
						System.out.println("Insert an integer please;");
					}
				} while (correctOption == false);

				switch (option) {
				case 1:

					hemogramList = Menu.hemogramManager.listHemogramDoctor(patient.getId(), doctor.getId());
					patient.setHemograms(hemogramList);
					System.out.println(patient.getName() + " " + patient.getSurname());
					if (hemogramList == null) {
						System.out.println("The patient don't have any hemogram done yet");
						break;
					} else {
						System.out.println("\nHemograms: ");
						for (Hemogram hemogram : hemogramList) {
							System.out.println("ID: " + hemogram.getId() + ", Date: " + hemogram.getDob());
						}
					}
					System.out.print("Select the ID of the Hemogram you want to see: ");
					int ID = Integer.parseInt(reader.readLine());
					Hemogram hemogramPatient = Menu.hemogramManager.getHemogram(ID);
					if (hemogramPatient == null) {
						System.out.println("That hemogram Id doesn't exist");
					} else {
						if (patient.getId() == hemogramPatient.getPatient().getId()) {
							System.out.println("\nHemogram with date " + hemogramPatient.getDob());
							featureValueList = Menu.featureValueManager.getFeatureValuesByHemogram(ID);
							if (featureValueList != null) {
								for (FeatureValue featureValue : featureValueList) {
									System.out.println(featureValue.getFeature().getName() + "; VALUE: "
											+ featureValue.getValue() + ", HEALTHY: " + featureValue.getHealthy()
											+ ", [MIN: " + String.format("%.2f", featureValue.getFeature().getMinimum())
											+ ", MAX: " + String.format("%.2f", featureValue.getFeature().getMaximum())
											+ "]");
								}
							} else {
								System.out.println("This hemogram is empty");
								break;
							}
						}
						System.out.print("\nDo you want to introduce any comments?, please introduce YES/NO: ");
						String respuesta = reader.readLine();
						if (respuesta.equalsIgnoreCase("YES")) {
							String finalcomment;
							System.out.println("You can introduce your comments");
							if (hemogramPatient.getComments() == null) {
								finalcomment = reader.readLine();
							} else {
								String comments = reader.readLine();
								finalcomment = hemogramPatient.getComments() + "\n" + comments;
							}
							Menu.hemogramManager.updatecomment(hemogramPatient.getId(), finalcomment);
						} else {
							break;
						}
					}
					break;

				case 2:
					generateXML(patient.getId());
					System.out.println(
							"XML successfully created, to see the html please go to the xmls folder and open the Patien.html");
					break;

				case 3:
					return;
				default:
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void generateXML(int patientId) throws Exception {
		Patient patient = Menu.patientManager.getPatient(patientId);
		List<Hemogram> hemograms = Menu.hemogramManager.listHemogramPatient(patient.getId());
		for (Hemogram hemogram : hemograms) {
			List<FeatureValue> featureValues = Menu.featureValueManager.getFeatureValuesByHemogram(hemogram.getId());
			hemogram.setFeatureValues(featureValues);
		}
		patient.setHemograms(hemograms);
		JAXBContext context = JAXBContext.newInstance(Patient.class);
		// Get the marshaller
		Marshaller marshall = context.createMarshaller();
		// Pretty formatting
		marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// Marshall the patient to a file
		File file = new File("./xmls/Output-Patient.xml");
		marshall.marshal(patient, file);
		// Marshall the patient to the screen
		marshall.marshal(patient, System.out);

		// Generate the HTML
		Xml2Html.simpleTransform("./xmls/Output-Patient.xml", "./xmls/PatientStyle.xslt", "./xmls/Patient.html");
	}
}
