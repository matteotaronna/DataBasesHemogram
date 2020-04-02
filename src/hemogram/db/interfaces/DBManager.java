package hemogram.db.interfaces;



public interface DBManager 
{
	public void connect();
	public void disconnect();
	public void createTables();
	public int getLastId();
	
	public AnalyzerManager getAnalyzerManager();
	public PatientManager getPatientManager();
	public DoctorManager getDoctorManager();
	public FeaturesManager getFeaturesManager();
	public FeatureValueManager getFeatureValueManager();
	public HemogramManager getHemogramManager();
}
