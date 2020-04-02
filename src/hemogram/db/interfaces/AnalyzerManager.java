package hemogram.db.interfaces;

import hemogram.db.pojos.*;

public interface AnalyzerManager 
{
	public void insertAnalyzer(Analyzer analyzer);
	public Analyzer getAnalyzer (int analyzerId);
	public Analyzer logInAnalyzer (String name, String work_user);
	public int getAnalyzerId (Analyzer analyzer);
	//join doctor with patient bc is the analyzer the one that creates the patient
	public void linkPatientDoctor (int patientId, int doctorId);
}
