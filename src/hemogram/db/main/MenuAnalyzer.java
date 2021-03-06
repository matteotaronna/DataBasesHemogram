package hemogram.db.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.xml.bind.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import hemogram.db.interfaces.PatientManager;
import hemogram.db.pojos.*;
import hemogram.db.pojos.users.Role;
import hemogram.db.pojos.users.User;
import hemogram.db.xml.utils.CustomErrorHandler;

public class MenuAnalyzer {
	
	public static PatientManager patientManager;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static void analyzerMenu() {
		try {
			while (true) {
				Analyzer analyzer = null;
				int analyzerId = 0;
				int option = 0;
				boolean correctOption = false;
				do {
					System.out.println("\nANALYZER\n");
					System.out.println("1. Sign In");
					System.out.println("2. Log In");
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
					analyzer = signInAnalyzer();
					analyzerId = Menu.dbManager.getLastId();
					analyzer.setId(analyzerId);
					analyzerSubmenu(analyzer);
					break;
				case 2:
					analyzer = logInAnalyzer();
					do {
						if (analyzer == null) {
							analyzer = logInAnalyzer();
						}
					} while (analyzer == null);

					analyzerSubmenu(analyzer);
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

	public static Analyzer signInAnalyzer() throws Exception {
		// add the new analyzer to the database

		System.out.println("\nFILL IN YOUR INFO");
		System.out.print("Name (Username): ");
		String analyzerName = reader.readLine();
		System.out.print("Surname: ");
		String analyzerSurname = reader.readLine();
		System.out.print("Work User (password): ");
		String analyzerWorkUser = reader.readLine();
		System.out.print("Hospital: ");
		String analyzerHospital = reader.readLine();
		Analyzer newAnalyzer = new Analyzer(analyzerName, analyzerSurname, analyzerWorkUser, analyzerHospital);
		Menu.analyzerManager.insertAnalyzer(newAnalyzer);

		createAnalyzerUser(newAnalyzer);
		System.out.println("Records inserted.");

		return newAnalyzer; // we return the analyzer to then link him to the hemogram
	}

	public static Analyzer logInAnalyzer() throws Exception {
		Analyzer analyzer = null;
		System.out.println("\nLOG IN");
		System.out.print("Username (name): ");
		String username = reader.readLine();
		System.out.print("Password (workUser): ");
		String password = reader.readLine();
		User user = Menu.usersManager.checkPassword(username, password);
		// We check if the user/password combination was OK
		if (user == null) {
			System.out.println("Wrong credentials, please try again!");
		}
		// We sign in and return the analyzer
		else {
			analyzer = Menu.analyzerManager.logInAnalyzer(username, password);
		}
		return analyzer;
	}

	public static void analyzerSubmenu(Analyzer analyzer) {
		try {
			while (true) {
				Patient patient = null;
				Doctor doctor = null;
				int patientId = 0;
				int option = 0;
				boolean correctOption = false;
				do {
					System.out.println("\nANALYZER\n");
					System.out.println("1. Sign In a new patient");
					System.out.println("2. Search for a patient");
					System.out.println("3. Create patient and hemogram from XML");
					System.out.println("4. Go back");
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
					patient = signInPatient();
					patientId = Menu.dbManager.getLastId();
					patient.setId(patientId);
					doctor = searchDoctor(patient);
					if (doctor != null) {
						createHemogram(analyzer, patient, doctor);
						break;
					} else {
						System.out.println("THE DOCTOR SHOULD BE REGISTERED, WAIT UNTILL HE REGISTERS!\n");
						return;
					}
				case 2:
					patient = searchPatient();
					do {
						if (patient == null) {
							System.out.println("Try again, there isn't a patient with that DNI.\n");
							patient = searchPatient();
						}
					} while (patient == null);
					doctor = searchDoctor(patient);
					if (doctor != null) {
						createHemogram(analyzer, patient, doctor);
						break;
					} else {
						System.out.print("THE DOCTOR SHOULD BE REGISTERED, WAIT UNTILL HE REGISTERS!\n");
						return;
					}
				case 3:
					createFromXML();
					break;
				case 4:
					return;
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createFromXML() throws Exception {
		// Create a JAXBContext
		JAXBContext context = JAXBContext.newInstance(Patient.class);
		// Get the unmarshaller
		Unmarshaller unmarshal = context.createUnmarshaller();
		// Open the file
		File file = null;
		boolean incorrectPatient = false;
		do {
			System.out.print(
					"Type the filename for the XML document (expected in the xmls folder, probably named Input-Patient.xml): ");
			String fileName = reader.readLine();
			file = new File("./xmls/" + fileName);
			try {
				// Create a DocumentBuilderFactory
				DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
				// Set it up so it validates XML documents
				dBF.setValidating(true);
				// Create a DocumentBuilder and an ErrorHandler (to check validity)
				DocumentBuilder builder = dBF.newDocumentBuilder();
				CustomErrorHandler customErrorHandler = new hemogram.db.xml.utils.CustomErrorHandler();
				builder.setErrorHandler(customErrorHandler);
				// Parse the XML file and print out the result
				Document doc = builder.parse(file);
				if (!customErrorHandler.isValid()) {
					incorrectPatient = true;
				}
			} catch (ParserConfigurationException ex) {
				System.out.println(file + " error while parsing!");
				incorrectPatient = true;
			} catch (SAXException ex) {
				System.out.println(file + " was not well-formed!");
				incorrectPatient = true;
			} catch (IOException ex) {
				System.out.println(file + " was not accesible!");
				incorrectPatient = true;
			}

		} while (incorrectPatient);
		
		// Unmarshall the Patient from a file
		Patient patient = (Patient) unmarshal.unmarshal(file);
		Menu.patientManager.signUpPatient(patient);
		// Get the patientId from the database because the XML file doesn't have it
		int patientId = Menu.dbManager.getLastId();
		patient.setId(patientId);
		createPatientUser(patient);
		// For each hemogram of the patient
		List<Hemogram> hemograms = patient.getHemograms();
		for (Hemogram hemogram : hemograms) {

			hemogram.setPatient(patient);
			// first insert doctor and analyzer
			Menu.doctorManager.insertDoctor(hemogram.getDoctor());
			createDoctorUser(hemogram.getDoctor());
			int doctorId = Menu.dbManager.getLastId();
			hemogram.getDoctor().setId(doctorId);
			Menu.analyzerManager.insertAnalyzer(hemogram.getAnalyzer());
			createAnalyzerUser(hemogram.getAnalyzer());
			int analyzerId = Menu.dbManager.getLastId();
			hemogram.getAnalyzer().setId(analyzerId);

			Menu.hemogramManager.insertHemogram(hemogram);
			int hemogramId = Menu.dbManager.getLastId();
			;
			hemogram.setId(hemogramId);
			Menu.analyzerManager.linkPatientDoctor(patient.getId(), doctorId);

			List<FeatureValue> featureValues = hemogram.getFeatureValues();
			for (FeatureValue featureValue : featureValues) {
				insertFeatureValue(featureValue, hemogram);
			}

		}
		System.out.println("Records inserted.");
	}

	public static void insertFeatureValue(FeatureValue featureValue, Hemogram hemogram) {
		featureValue.setHemogram(hemogram);
		Feature feature = Menu.featuresManager.getFeatureByName(featureValue.getFeature().getName());
		featureValue.setFeature(feature);
		boolean healthy = checkHealthy(feature, featureValue.getValue());
		featureValue.setHealthy(healthy);
		// System.out.println(feature);
		// System.out.println(featureValue);
		Menu.featureValueManager.insertFeatureValue(featureValue);
	}

	public static void createPatientUser(Patient patient) throws Exception {
		// Create the user
		String username = patient.getName();
		String password = patient.getDni();
		// Create the password's hash
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] hash = md.digest();

		// get the role from the database (it is going to be a patient)
		Role role = Menu.usersManager.getRoleByName("patient");
		// Create the user and store it
		User user = new User(username, hash, role);
		Menu.usersManager.createUser(user);
	}

	public static void createAnalyzerUser(Analyzer analyzer) throws Exception {
		// create the user
		String username = analyzer.getName();
		String password = analyzer.getWork_user();
		// Create the password's hash
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] hash = md.digest();

		// get the role from the database (it is going to be an analyzer)
		Role role = Menu.usersManager.getRoleByName("analyzer");
		// Create the user and store it
		User user = new User(username, hash, role);
		Menu.usersManager.createUser(user);
	}

	public static void createDoctorUser(Doctor doctor) throws Exception {
		// create the user
		String username = doctor.getName();
		String password = doctor.getWork_user();
		// Create the password's hash
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] hash = md.digest();

		// get the role from the database (it is going to be an analyzer)
		Role role = Menu.usersManager.getRoleByName("analyzer");
		// Create the user and store it
		User user = new User(username, hash, role);
		Menu.usersManager.createUser(user);
	}

	public static Patient signInPatient() throws Exception {
		System.out.println("\nFILL IN THE PATIENT'S INFO");
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

		createPatientUser(newPatient);
		System.out.println("Records inserted.");

		return newPatient;
	}

	public static Patient searchPatient() throws Exception {
		System.out.println("\nINTRODUCE THE DNI OF THE PATIENT ");
		System.out.print("Patient DNI: ");
		String dni = reader.readLine();
		Patient newPatient = Menu.patientManager.searchPatient(dni);
		return newPatient;
	}

	public static Doctor searchDoctor(Patient patient) throws Exception {
		List<Doctor> doctors = Menu.doctorManager.listDoctors();
		System.out.println("\nDoctors list:");
		for (Doctor doctor : doctors) {
			System.out.println("Doctor: " + doctor.getName() + " " + doctor.getSurname() + ", id: " + doctor.getId());
		}
		System.out.print("Insert the doctor id (if there isn't the doctor put 0): ");
		int doctorId = Integer.parseInt(reader.readLine());
		if (doctorId != 0) {
			// link doctor with patient in the patients_doctors table
			Menu.analyzerManager.linkPatientDoctor(patient.getId(), doctorId);
			return Menu.doctorManager.getDoctor(doctorId);

		} else
			return null;
	}

	public static void createHemogram(Analyzer analyzer, Patient patient, Doctor doctor) throws Exception {
		System.out.print("\nInsert the date of the hemogram (yyyy-MM-dd): ");
		String date = reader.readLine();
		LocalDate dateL = LocalDate.parse(date, formatter);
		Date hemogramDate = Date.valueOf(dateL);
		Hemogram hemogram = new Hemogram(hemogramDate, patient, doctor, analyzer);
		Menu.hemogramManager.insertHemogram(hemogram);
		int hemogramId = Menu.dbManager.getLastId();
		hemogram.setId(hemogramId);

		// inert values
		System.out.println("INSERT THE VALUES FOR THE HEMOGRAM");

		double value;
		boolean healthy;
		FeatureValue featureValue;
		Feature feature;

		System.out.print("Leukocytes: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("leukocytes");
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue(value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);

		System.out.print("Erythrocytes: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("erythrocytes");
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue(value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);

		System.out.print("Hemoglobin: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("hemoglobin");
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue(value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);

		System.out.print("Hematocrit: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("hematocrit");
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue(value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);

		System.out.print("Platelets: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("platelets");
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue(value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);

		System.out.print("Cholesterol: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("cholesterol");
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue(value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);

		System.out.print("Cholesterol HDL: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("cholesterolHDL");
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue(value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);

		System.out.print("Triglycerides: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("triglycerides");
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue(value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);

		System.out.print("Cholesterol LDL: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("cholesterolLDL");
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue(value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);

		System.out.print("Glycemia: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("glycemia");
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue(value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);

		System.out.println("Hemogram successfully created.");
	}

	public static boolean checkHealthy(Feature feature, double value) {
		if (value < feature.getMinimum() || value > feature.getMaximum()) {
			return false;

		} else
			return true;
	}
}