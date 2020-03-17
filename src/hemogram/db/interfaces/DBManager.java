package hemogram.db.interfaces;



public interface DBManager 
{
	public void connect();
	public void disconnect();
	public void createTables();
	
	public AnalizerManager getAnalizerManager();
	public PatientManager getPatientManager();
	public DoctorManager getDoctorManager();
}
