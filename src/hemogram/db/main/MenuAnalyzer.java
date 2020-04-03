package hemogram.db.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import hemogram.db.pojos.*;

public class MenuAnalyzer 
{

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static void analyzerMenu() 
	{
		try 
		{
			while (true) 
			{
				Analyzer analyzer = null;
				int analyzerId = 0;
				System.out.println("1. New Analyzer");
				System.out.println("2. Already signed up");
				System.out.println("3. Go back");

				int option = Integer.parseInt(reader.readLine());

				switch (option) 
				{
				case 1:
					analyzer = addAnalyzer();
					analyzerId = Menu.dbManager.getLastId();
					analyzer.setId(analyzerId);
					// System.out.println(analizerId);
					// we need to pass the analizerId to then link it to the hemogram
					analyzerSubmenu(analyzer);
					break;
				case 2:
					analyzer = logInAnalyzer();
					do {
						if (analyzer == null) {
							System.out.println("Try again, the name or work-user doesn't exist");
							analyzer = logInAnalyzer();
							//System.out.println("analyzerId: "+analyzer.getId());
						}
					} while (analyzer == null);

					//analyzerId = analyzer.getId();
					analyzerSubmenu(analyzer);

					// System.out.println(analizerId);
					// we need to pass the analyzer id to then link the id to the hemogram
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

	public static void analyzerSubmenu(Analyzer analyzer) 
	{
		try 
		{
			while (true) 
			{
				Patient patient = null;
				Doctor doctor = null;
				int patientId = 0;
				int doctorId = 0;
				System.out.println("1. New patient");
				System.out.println("2. Search for a patient");
				System.out.println("3. Go back");

				int option = Integer.parseInt(reader.readLine());

				switch (option) 
				{
				case 1:
					patient = addPatient();
					patientId = Menu.dbManager.getLastId();
					patient.setId(patientId);
					// System.out.println(patientId);
					// we need to pass the patientId to then link it to the hemogram
					// create hemogram
					doctor = searchDoctor(patient);
					if (doctor != null) {
						doctorId = doctor.getId();
						// join patient and doctor
						createHemogram(analyzer, patient, doctor);
						break;
					} else {
						System.out.println("THE DOCTOR SHOULD BE REGISTERED, WAIT UNTILL HE REGISTERS!\n");
						return;
					}
					// create hemogram
				case 2:
					patient = searchPatient();
					//System.out.println("patientId: "+patient.getId());
					do 
					{
						if (patient == null) 
						{
							System.out.println("Try again, there isn't a patient with that DNI.\n");
							patient = searchPatient();
						}
					} while (patient == null);

					//patientId = patient.getId();
					// we need to pass the patientId to then link it to the hemogram
					doctor = searchDoctor(patient);
					if (doctor != null) 
					{
						doctorId = doctor.getId();
						// join patient and doctor
						createHemogram(analyzer, patient, doctor);
						break;
					} else 
					{
						System.out.println("THE DOCTOR SHOULD BE REGISTERED, WAIT UNTILL HE REGISTERS!\n");
						return;
					}
					// create hemogram
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
		// if he enters a wrong DNI we have to catch the exception
	}

	public static Doctor searchDoctor(Patient patient) throws Exception 
	{
		List<Doctor> doctors = Menu.doctorManager.listDoctors();
		System.out.println(doctors);
		System.out.print("Insert the doctor id (if there isn't the doctor put 0): ");
		int doctorId = Integer.parseInt(reader.readLine());
		if (doctorId != 0) {
			// link doctor with patient in the patients_doctors table
			Menu.analyzerManager.linkPatientDoctor(patient.getId(), doctorId);
			return Menu.doctorManager.getDoctor(doctorId);

		} else
			return null;
	}

	public static void createHemogram(Analyzer analyzer, Patient patient, Doctor doctor) throws Exception 
	{
		System.out.print("Insert the date of the hemogram (yyyy-MM-dd): ");
		String date = reader.readLine();
		LocalDate dateL = LocalDate.parse(date, formatter);
		Date hemogramDate = Date.valueOf(dateL);
		// System.out.println("\n\nanalyzerID: "+analyzerId+", patientId: "+patientId+",
		// doctorId: "+doctorId+", date: "+hemogramDate);
		Hemogram hemogram = new Hemogram(hemogramDate,patient,doctor,analyzer);
		Menu.hemogramManager.insertHemogram(hemogram);
		int hemogramId = Menu.dbManager.getLastId();
		hemogram.setId(hemogramId);

		// inert values
		System.out.println("Insert the values of the hemogram ");

		
		double value;
		boolean healthy;
		int featureId;
		FeatureValue featureValue;
		Feature feature;

		System.out.print("Leukocytes: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("leukocytes");
		//featureId = feature.getId();
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue( value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);

		System.out.print("Erythrocytes: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("erythrocytes");
		//featureId = feature.getId();
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue( value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);

		System.out.print("Hemoglobin: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("hemoglobin");
		//featureId = feature.getId();
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue( value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);

		System.out.print("Hematocrit: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("hematocrit");
		//featureId = feature.getId();
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue( value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);

		System.out.print("Platelets: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("platelets");
		//featureId = feature.getId();
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue( value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);

		System.out.print("Cholesterol: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("cholesterol");
		//featureId = feature.getId();
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue( value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);

		System.out.print("Cholesterol HDL: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("cholesterolHDL");
		//featureId = feature.getId();
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue( value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);

		System.out.print("Triglycerides: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("triglycerides");
		//featureId = feature.getId();
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue( value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);

		System.out.print("Cholesterol LDL: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("cholesterolLDL");
		//featureId = feature.getId();
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue( value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);

		System.out.print("Glycemia: ");
		value = Double.parseDouble(reader.readLine());
		feature = Menu.featuresManager.getFeatureByName("glycemia");
		//featureId = feature.getId();
		healthy = checkHealthy(feature, value);
		featureValue = new FeatureValue( value, feature, hemogram, healthy);
		Menu.featureValueManager.insertFeatureValue(featureValue);
	}

	public static boolean checkHealthy(Feature feature, double value) 
	{
		if (value < feature.getMinimum() || value > feature.getMaximum()) 
		{
			return false;

		} else
			return true;
	}
}