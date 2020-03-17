package hemogram.db.interfaces;

import java.util.List;

import hemogram.db.pojos.Patient;

public interface PatientManager 
{
	public void admit (Patient patient);
	public Patient getPatient (int patientId);
	public List<Patient> searchPatient (String name, String dni);
}
