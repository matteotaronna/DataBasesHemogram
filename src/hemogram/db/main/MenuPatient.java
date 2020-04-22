package hemogram.db.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import hemogram.db.pojos.Analyzer;
import hemogram.db.pojos.Feature;
import hemogram.db.pojos.FeatureValue;
import hemogram.db.pojos.Hemogram;
import hemogram.db.pojos.Patient;

public class MenuPatient {
	
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public static void patientMenu() {
		try 
		{
			while (true) 
			{
				Patient patient = null;
				int patientId = 0;
				List<Hemogram> hemogramList = new ArrayList<Hemogram>();
				List<FeatureValue> featureValueList = new ArrayList<FeatureValue>();
				System.out.println("1. List all Hemograms");
				System.out.println("2. Go back");

				int option = Integer.parseInt(reader.readLine());

				switch (option) 
				{
				case 1:
					patient = MenuAnalyzer.searchPatient();
					do 
					{
						if (patient == null) 
						{
							System.out.println("Try again, there isn't a patient with that DNI.\n");
							patient = MenuAnalyzer.searchPatient();
						}
					} while (patient == null);
					hemogramList = Menu.hemogramManager.listHemogramPatient(patient.getId());
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
					if(patient.getId() == hemogramPatient.getPatient().getId()) {
						featureValueList  = Menu.featureValueManager.getFeatureValuesByHemogram(option);
						if(featureValueList != null) {
						 for (FeatureValue featureValue : featureValueList)
						  {
							 System.out.print("Name: " + featureValue.getFeature().getName() + ", MIN: " + featureValue.getFeature().getMinimum() +
									 ", MAX: " + featureValue.getFeature().getMaximum() + ", VALUE: " + featureValue.getValue() + ", HEALTHY: " + 
									 featureValue.getHealthy());
						  }
						 
					}
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
	}
