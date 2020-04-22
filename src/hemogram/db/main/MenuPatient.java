package hemogram.db.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import hemogram.db.pojos.Analyzer;
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
				System.out.println("1. List all Hemograms");
				System.out.println("2. Search Hemogram by Date");
				System.out.println("3. Go back");

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
							System.out.println(hemogram);
						}
					}
					
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
	}
}
