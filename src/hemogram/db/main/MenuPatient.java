package hemogram.db.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import hemogram.db.pojos.FeatureValue;
import hemogram.db.pojos.Hemogram;
import hemogram.db.pojos.Patient;
import hemogram.db.pojos.users.User;

public class MenuPatient {
	
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static Patient patient = null;
	
	public static void patientMenu() {
		try 
		{
			while (true) 
			{
				System.out.println("PATIENT");
				System.out.println("1. LogIn");
				System.out.println("2. Go back");

				int option = Integer.parseInt(reader.readLine());

				switch (option) 
				{
				case 1:
					patient = logInPatient();
					if (patient != null) {
					patientSubMenu();
					}
					break;
				case 2:
					return;
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void patientSubMenu() {
		try 
		{
			while (true) 
			{
				List<Hemogram> hemogramList = new ArrayList<Hemogram>();
				List<FeatureValue> featureValueList = new ArrayList<FeatureValue>();
				System.out.println("PATIENT");
				System.out.println("1. List all Hemograms");
				System.out.println("2. Generate XML");
				System.out.println("3. Go back");

				int option = Integer.parseInt(reader.readLine());

				switch (option) 
				{
				case 1:
					hemogramList = Menu.hemogramManager.listHemogramPatient(patient.getId());
					patient.setHemograms(hemogramList);
					if(hemogramList == null) {
						System.out.println("The patient do not have a hemogram");
					} else {
						for (Hemogram hemogram : hemogramList) 
						{
							System.out.println("ID: " + hemogram.getId() + ", Date: " + hemogram.getDob() + ", Comments: " + hemogram.getComments());
						}
					}
					System.out.println("Select the ID of the Hemogram you wan to see");
					option = Integer.parseInt(reader.readLine());
					Hemogram hemogramPatient = Menu.hemogramManager.getHemogram(option);
					if(hemogramPatient != null) {
					if(patient.getId() == hemogramPatient.getPatient().getId()) {
						featureValueList  = Menu.featureValueManager.getFeatureValuesByHemogram(option);
						if(featureValueList != null) {
						 for (FeatureValue featureValue : featureValueList)
						  {
							 System.out.println("Name: " + featureValue.getFeature().getName() + ", VALUE: " + featureValue.getValue() + 
									 ", HEALTHY: " + featureValue.getHealthy()+", [MIN: " + String.format("%.2f", featureValue.getFeature().getMinimum()) + 
									 ", MAX: " + String.format("%.2f", featureValue.getFeature().getMaximum())+"]");
						  }
						 
					}
					}
					}
					else {
						System.out.println("The hemogram introduced do not exist");
						break;
					}
					
					break;
				case 2:
					generateXML(patient.getId());
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
		List<Hemogram> hemograms =Menu.hemogramManager.listHemogramPatient(patient.getId());
		for (Hemogram hemogram : hemograms) 
		{
			List<FeatureValue> featureValues = Menu.featureValueManager.getFeatureValuesByHemogram(hemogram.getId());
			hemogram.setFeatureValues(featureValues);
		}
		patient.setHemograms(hemograms);
		JAXBContext context = JAXBContext.newInstance(Patient.class);
		// Get the marshaller
		Marshaller marshal = context.createMarshaller();
		// Pretty formatting
		marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// Marshall the dog to a file
		File file = new File("./xmls/Output-Patient.xml");
		marshal.marshal(patient, file);
		// Marshall the dog to the screen
		marshal.marshal(patient, System.out);
	}
	
	public static Patient logInPatient() throws Exception 
	{
		Patient patient = null;
		System.out.println("LOG IN");
		System.out.print("Username (name):");
		String username = reader.readLine();
		System.out.print("Password (DNI):");
		String dni = reader.readLine();
		User user = Menu.usersManager.checkPassword(username, dni);
		if (user == null) {
			System.out.println("Wrong credentials, please try again!");
		}
		else {
			patient = Menu.patientManager.logInPatient(username, dni);
		}
		return patient;
	}
	
}

